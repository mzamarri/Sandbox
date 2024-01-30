const http = require('http');
const url = require('url');
const fs = require('fs');
const { google } = require('googleapis');
const clientSecret = require('./client_secret.json');
const env = require('dotenv');

// Checks if a .env file exists and creates one if it doesn't
if (!fs.existsSync('./.env')) {
    fs.writeFileSync('./.env', '');
}
env.config();

// This creates a oauth2Client object that will be used to generate an authUrl
const oauth2Client = new google.auth.OAuth2(
    clientSecret.web.client_id,
    clientSecret.web.client_secret,
    clientSecret.web.redirect_uris,
);

//Declaring the scopes that will be used to generate an authUrl
const scopes = [
    'https://mail.google.com/',
];

//Generates an authUrl
const authUrl = oauth2Client.generateAuthUrl({
    access_type: 'offline',
    scope: scopes,
});
console.log('Authorize this app by visiting this url:', authUrl);

// This function will be used to get the access token and refresh token
async function getAccessToken() {
    const server = http.createServer(async (req, res) => {
        console.log('req.url: ', req.url)
        if (req.url == '/') {
            res.writeHead(301, { Location: authUrl });
        }
        console.log("Checking if req.url starts with '/oauth2callback'...");
        if (req.url.startsWith('/oauth2callback')) {
            console.log('authorization code block entered');
            let q = url.parse(req.url, true);
            console.log('q: ', q.query);
            let code = q.query.code;

            if (q.error) {
                console.log('Error: ', q.error);
            } else {
                const { tokens } = await oauth2Client.getToken(code);
                console.log('tokens: ', tokens);
                // the following code will write the tokens to an environmental variable
                if (process.env.REFRESH_TOKEN) { // if the refresh token already exists, it will not be overwritten yet
                    console.log('refresh token already exists');
                    if (process.env.REFRESH_TOKEN === tokens.refresh_token) { // if the refresh token is the same, it will not be overwritten
                        console.log('refresh token is the same');
                    } else {
                        console.log('refresh token is different');
                        const envConfig = env.parse(fs.readFileSync('./.env'));
                        envConfig.REFRESH_TOKEN = tokens.refresh_token;
                        const newEnvConfig = Object.entries(envConfig).map(([key, value]) => `${key}=${value}`).join('\n');
                        // Write back to .env file
                        fs.writeFileSync('./.env', newEnvConfig);
                    }
                } else {
                    fs.writeFileSync('./.env', `REFRESH_TOKEN=${tokens.refresh_token}\n`);
                    console.log('tokens written to .env file')
                }
            }
        }
        res.end("End of server response");
        server.close(() => {
            console.log('Server closed');
        })
    })
    server.listen(3000, () => {
        console.log('Server listening on port', 3000);
    });
}

getAccessToken().catch(console.error);

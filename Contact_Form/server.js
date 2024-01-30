const http = require("http");
const fs = require("fs");
const querystring = require("querystring");
const { google } = require("googleapis");
const env = require('dotenv').config();
const clientSecret = require('./client_secret.json');
const nodemailer = require('nodemailer');

// Creating oauth2Client
const oauth2Client = new google.auth.OAuth2(
    clientSecret.web.client_id,
    clientSecret.web.client_secret,
);

// Sets the credentials to the refresh token
const refresh_token = process.env.REFRESH_TOKEN;
// console.log("refresh token: ", refresh_token);
oauth2Client.setCredentials({
    refresh_token: refresh_token,
});

async function sendMail(subject, message) {
    const client_id = clientSecret.web.client_id;
    const client_secret = clientSecret.web.client_secret;
    console.log("client id: ", client_id);
    console.log("client secret: ", client_secret);
    const refresh_token = process.env.REFRESH_TOKEN;
    console.log("refresh token: ", refresh_token);
    // Generate an access token
    const tokenResponse = await oauth2Client.getAccessToken();
    console.log("token response: ", tokenResponse);
    console.log("Status: ", tokenResponse.res.config.validateStatus)
    const access_token = tokenResponse.token;
    console.log("access token: ", access_token);
    const expiry_date = new Date(tokenResponse.res.data.expiry_date);
    console.log(expiry_date);
    let transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            type: 'OAuth2',
            user: 'noreply.mazr@gmail.com',
            clientId: client_id,
            clientSecret: client_secret,
            refreshToken: refresh_token,
            accessToken: access_token,
        }
    });
    let mailOptions = {
        from: 'noreply.mazr@gmail.com',
        to: 'miguelazamarripar@gmail.com',
        subject: `${subject}`,
        text: `${message}`
    };
    // send email
    transporter.sendMail(mailOptions, function(err, info) {
        if (err) {
            console.log("error: ", err);
        } else {
            console.log("Email sent! \ninfo:", info);
        }
    });
}

// function to handle form data
function formHandler(req) {
    return new Promise((resolve, reject) => {
        let body = '';
        console.log("POST request received");
        // adds data to body as it is received
        req.on('data', chunk => {
            body += chunk.toString(); // convert Buffer to string
            console.log("body: ", body);
        });
        // once all data is received, resolve promise or reject if there is an error
        req.on('end', () => {
            console.log("body: ", body);
            const formData = querystring.parse(body);
            console.log("formData: ", formData);
            resolve(formData);
        });
        // if there is an error, reject promise
        req.on('error', (err) => {
            console.error(err.stack);
            reject(err);
        });
    })
};

// input validation function to check if all fields are filled out appropriately
function inputValidation(formData) {
    const { fullName, email, phoneNumber, subject, message } = formData;
    console.log("full name: ", fullName);
    console.log("email: ", email);
    console.log("phone number: ", phoneNumber);
    console.log("subject: ", subject);
    console.log("message: ", message);

    if (!fullName || !email || !phoneNumber || !subject || !message) {
        return false; // if any of the fields are empty, return false
    } else {
        // regex variables for testing input
        let fullNameTest = /^([a-zA-Z]+\s+)+[A-Za-z]+\s*$/;
        let emailTest = /^\s*[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}\s*$/;
        let phoneNumTest = /^\s*\(?\d{3}\)?\s*-?\s*\d{3}\s*-?\s*\d{4}\s*$/;

        // validate full name
        if (!fullNameTest.test(fullName)) {
            console.log("full name invalid");
            return false; // if full name does not match regex, return false
        };
        // validate email
        if (!emailTest.test(email)) {
            console.log("email invalid");
            return false; // if email does not match regex, return false
        }
        // validate phone number
        if (!phoneNumTest.test(phoneNumber)) {
            console.log("phone number invalid");
            return false; // if phone number does not match regex, return false
        }
        return true; // if all fields are filled out and valid, return true
    };
};

const server = http.createServer(function (req, res) {
    console.log("Request was made: " + req.url);
    console.log("Request method: " + req.method);

    if (req.method === 'POST' && req.url === '/contact-form/') {
        formHandler(req).then(formData => {
            if (inputValidation(formData)) {
                console.log("Form data is valid");
                const subject = formData.subject;
                const message = `
                    Name: ${formData.fullName}
                    Email: ${formData.email}
                    Phone Number: ${formData.phoneNumber}

                    Message: 
                    ${formData.message}
                `.split('\n').map(line => line.trimStart()).join('\n');
                // Now that input is validated, send email
                sendMail(subject, message);
                res.end("Message sent!")
            } else {
                console.log("Form data is invalid");
                res.end('form data invalid');
            }
        });
    };

    let filePath = "." + req.url;
    if (filePath === "./") {
        filePath = "./index.html";
    }

    // Check if file exists in working directory
    if (fs.existsSync(filePath)) {
        fs.readFile(filePath, function(err, data) {
            if (err) {
                console.error("There was an error reading this file!", err);
                return;
            } else {
                // res.writeHead(200, { 'Content-Type': contentType });
                res.writeHead(200, { 'Content-Type': 'text/html' });
                res.end(data, 'utf-8');
            }
        });
    }
});

server.listen(4000, () => {
    console.log("Server is running on port 4000");
});

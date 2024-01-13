const http = require("http");
const path = require("path");
const fs = require("fs");
const querystring = require("querystring");
const { google } = require("googleapis");
const OAuth2 = google.auth.OAuth2;

// OAuth2 client
const oauth2Client = new OAuth2(
    'process.env.CLIENT_ID',
    'process.env.CLIENT_SECRET',
    'http://localhost:3000/oauth2callback'
);

console.log("OAuth2 client: ", oauth2Client);

// generate a OAuth2 URL

function formHandler(req, res) {
    let body = '';
        console.log("POST request received");
        req.on('data', chunk => {
            body += chunk.toString(); // convert Buffer to string
            console.log("body: ", body);
        });
        req.on('end', () => {
            console.log("body: ", body);
            const formData = querystring.parse(body);
            console.log("form Data: ",  formData);
            if (inputValidation(formData)) {
                console.log("Form data is valid");
                res.end('Received form data');
            } else {
                console.log("Form data is invalid");
                res.end('form data invalid');
            }
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

    if (req.url === "/favicon.ico") {
        res.writeHead(204, { 'Content-Type': 'image/x-icon' });
        res.end(); // ignores favicon
        console.log('favicon requested ignored');
        return;
    }
    console.log("Request was made: " + req.url);
    console.log("Request method: " + req.method);
    if (req.method === 'POST' && req.url === '/contact-form/') {
        formHandler(req, res);
        return ;
    };
    let filePath = "." + req.url;
    if (filePath === "./") {
        filePath = "./index.html";
    }

    // const extname = String(path.extname(filePath)).toLowerCase();
    // const mimeTypes = {
    //     '.html': 'text/html',
    //     '.css': 'text/css',
    //     '.js': 'text/javascript',
    //     '.png': 'image/png',
    //     '.jpg': 'image/jpg',
    //     '.gif': 'image/gif',
    //     '.json': 'application/json',
    // }

    // const contentType = mimeTypes[extname] || 'application/octet-stream';

    fs.readFile(filePath, function(err, data) {
        if (err) {
            console.error("There was an error reading this file!", err);
            return;
        } else {
            // res.writeHead(200, { 'Content-Type': contentType });
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.end(data, 'utf-8');
        }
    } )
});

server.listen(3000, () => {
    console.log("Server is running on port 3000");
});

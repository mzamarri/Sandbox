const http = require("http");
const path = require("path");
const fs = require("fs");

const server = http.createServer(function (req, res) {
    console.log("Request was made: " + req.url);

    if (req.url === "/favicon.ico") {
        res.writeHead(204, { 'Content-Type': 'image/x-icon' });
        res.end(); // ignores favicon
        console.log('favicon requested ignored');
        return;
    }

    let filePath = "." + req.url;
    if (filePath == "./") {
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

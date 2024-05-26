const express = require("express");
const path = require("path");

const app = express();

console.log("Static files are served from: ", path.resolve(__dirname, "../frontend/static/"));

app.use("/static", express.static(path.resolve(__dirname, "../frontend/static/")));

app.get("/", (req, res) => {
    res.sendFile(path.resolve(__dirname, "../frontend/index.html"));
    console.log("Dashboard Request received");
});


app.listen(4500, () => {
    console.log("Server is running on port 4500");
});
const path = require('path');

console.log("__filename: " + __filename);
console.log("__dirname: " + __dirname);
console.log("path.basename(__filename): " + path.basename(__filename));
message = path.parse(__filename);

console.log(message);

console.log("\n" + message.dir + "\n");
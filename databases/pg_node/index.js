const queryDatabase = require("./queryDatabase");

const query = `SELECT * FROM catalog;`
const insertQuery = `
    CALL add_to_catalog('ps', 400.00, 1000.00, '123 street');
    SELECT * FROM catalog;
    SELECT * FROM orders;
`

queryDatabase(insertQuery)
.then(res => res.forEach(result => {
        console.log(result.rows);
    }
))
.catch(err => console.error(err));
function printHelloWorld() {
    console.log("Hello World!");
}
var active = true;
function toggleButton() {
    var button = document.getElementById("button");
    if (active === true) {
        button.setAttribute("class", "verified");
        console.log("Button Value: " + String(active));
    } else {
        button.setAttribute("class", "error");
        console.log("Button Value: " + String(active));
    }
    active = !active;
}

function submitText() {
    let text = document.getElementById("input").value;
    let p = document.querySelector("p");
    console.log("Text Value: " + String(text));
    p.innerHTML = text;
}

// printHelloWorld();

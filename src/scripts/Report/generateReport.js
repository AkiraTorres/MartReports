const response = [
    {
        "Local A": 10,
        "Local B": 20,
        "Local C": 2,
        "Local D": 50
    },
    {
        "15/06/2023": 10,
        "13/07/2023": 2,
        "08/08/2023": 50,
        "22/09/2023": 15,
        "30/10/2023": 5
    }
];

const field = document.getElementById("relatorio");
let htmlString = "";

// Create a structured report format
htmlString += `
    <h2>Local</h2>
    <ul>
`;

// Add properties from the first object
for (const key in response[0]) {
    htmlString += `<li>${key}: ${response[0][key]}</li>`;
}

htmlString += `</ul><h2>Datas</h2><ul>`;

// Add properties from the second object
for (const key in response[1]) {
    htmlString += `<li>${key}: ${response[1][key]}</li>`;
}

htmlString += '</ul>';

// Find the local with the highest value
let maxLocal = "";
let maxLocalValue = -1;

for (const key in response[0]) {
    const value = response[0][key];
    if (value > maxLocalValue) {
        maxLocal = key;
        maxLocalValue = value;
    }
}

// Find the date with the highest value
let maxDate = "";
let maxDateValue = -1;

for (const key in response[1]) {
    const value = response[1][key];
    if (value > maxDateValue) {
        maxDate = key;
        maxDateValue = value;
    }
}

// Add the messages to the HTML content
htmlString += `
    <p>O local com maior probabilidade de venda é: ${maxLocal}</p>
    <p>O dia com maior número de vendas foi: ${maxDate}</p>
`;

// Set the HTML content of the "relatorio" div
field.innerHTML = htmlString;

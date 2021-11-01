// select year checkboxes
let allYears = document.querySelector("#all-years");
let year2019 = document.querySelector("#year-2019");
let year2021 = document.querySelector("#year-2021");
let year2020 = document.querySelector("#year-2020");

function check() {
    // year checkboxes
    if (year2019.checked || year2020.checked || year2021.checked) {
        allYears.checked = false;
    }

    if (year2019.checked && year2020.checked && year2021.checked) {
        allYears.checked = true;
        year2019.checked = false;
        year2020.checked = false;
        year2021.checked = false;
    }
}
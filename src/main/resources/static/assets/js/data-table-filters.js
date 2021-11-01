// select year checkboxes
let allYears = document.querySelector("#all-years");
let year2019 = document.querySelector("#year-2019");
let year2021 = document.querySelector("#year-2021");
let year2020 = document.querySelector("#year-2020");

// select class checkboxes
let allClasses = document.querySelector("#all-classes");
let firstYearClass = document.querySelector("#first-year-class");
let secondYearClass = document.querySelector("#second-year-class");
let thirdYearClass = document.querySelector("#third-year-class");

// select semester checkboxes
let allSemesters = document.querySelector("#all-semesters")
let semester1 = document.querySelector("#semester-1")
let semester2 = document.querySelector("#semester-2")

// status checkboxes
let allStatus = document.querySelector("#all-status");
let statusSafe = document.querySelector("#safe");
let statusAtRisk = document.querySelector("#at-risk");


function check() {
    // status checkboxes
    if (statusSafe.checked || statusAtRisk.checked) {
        allStatus.checked = false;
    }

    if (statusAtRisk.checked && statusSafe.checked) {
        allStatus.checked = true;
        statusAtRisk.checked = false;
        statusSafe.checked = false;
    }

    // semester checkboxes
    if (semester1.checked || semester2.checked) {
        allSemesters.checked = false;
    }

    if (semester1.checked && semester2.checked) {
        allSemesters.checked = true;
        semester1.checked = false;
        semester2.checked = false;
    }

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

    // class checkboxes
    if (firstYearClass.checked || secondYearClass.checked || thirdYearClass.checked) {
        allClasses.checked = false;
    }

    if (firstYearClass.checked && secondYearClass.checked && thirdYearClass.checked) {
        allClasses.checked = true;
        firstYearClass.checked = false;
        secondYearClass.checked = false;
        thirdYearClass.checked = false;
    }
}
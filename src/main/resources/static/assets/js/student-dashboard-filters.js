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

// select assessemnts checkboxes
let allAssessments = document.querySelector("#all-assessments");
let ca1 = document.querySelector("#ca-1");
let ca2 = document.querySelector("#ca-2");
let assignment = document.querySelector("#assignment");
let exam = document.querySelector("#exam");


function check() {
    if (ca1.checked || ca2.checked || assignment.checked || exam.checked){
        allAssessments.checked = false;
    }

    if (ca1.checked && ca2.checked && assignment.checked && exam.checked) {
        allAssessments.checked = true;
        ca1.checked = false;
        ca2.checked = false;
        assignment.checked = false;
        exam.checked = false;
    }

    if (allAssessments.checked) {
        ca1.checked = false;
        ca2.checked = false;
        assignment.checked = false;
        exam.checked = false;
    }

    // semester checkboxes
    if (semester1.checked || semester2.checked) {
        allSemesters.checked = false;
    }

    if (allSemesters.checked) {
        semester1.checked = false;
        semester2.checked = false;
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

    if (allYears.checked) {
        year2019.checked = false;
        year2020.checked = false;
        year2021.checked = false;
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

    if (allClasses.checked) {
        firstYearClass.checked = false;
        secondYearClass.checked = false;
        thirdYearClass.checked = false;
    }

    if (firstYearClass.checked && secondYearClass.checked && thirdYearClass.checked) {
        allClasses.checked = true;
        firstYearClass.checked = false;
        secondYearClass.checked = false;
        thirdYearClass.checked = false;
    }
}
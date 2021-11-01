let allStudents = document.querySelector("#message-students");
let firstYear = document.querySelector("#class-1");
let secondYear = document.querySelector("#class-2");
let thirdYear = document.querySelector("#class-3");
let safe = document.querySelector("#safe-students");
let atRisk = document.querySelector("#at-risk-students");
let recipient = document.querySelector("#recipient-id");

let options = document.querySelectorAll(".options");

recipient.value = allStudents.value;

function recipientCheck() {
    recipient.value = "";
    if (allStudents.checked){
        recipient.value = safe.value
    }

    if  (firstYear.checked || secondYear.checked || thirdYear.checked || safe.checked || recipient.checked){
        allStudents.checked = false;
    }

    if (firstYear.checked && secondYear.checked && thirdYear.checked && safe.checked && atRisk.checked) {
        allStudents.checked = true;
        firstYear.checked = false;
        secondYear.checked = false;
        thirdYear.checked = false;
        safe.checked = false;
        atRisk.checked = false;
        recipient.value = allStudents.value;
    }

    options.forEach(element => {
        if(element.checked){
            recipient.value += element.value + " ";
        }
    });
}
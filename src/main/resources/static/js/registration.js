var regButton = document.getElementById("submitRegistration");


var registrationFunction = function () {
    var json = generateJsonFromForm();
    sendUserData(json);
};

if (regButton) {
    regButton.addEventListener("click", registrationFunction);
}

function generateJsonFromForm() {
    var form = document.getElementById("register-form");
    var formData = new FormData(form);
    var object = {
        "organization": {
            "id": null
        }
    };
    formData.forEach(function (value, key) {
        if (key === "orgId") {
            object.organization.id = value;
        } else {
            object[key] = value;
        }
    });
    var result = JSON.stringify(object);
    return result;
}

function sendUserData(json) {
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/lkz_project_war/registerUser/";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(json);
}












var regButton = document.getElementById("submitRegistration");
var registrationFunction = function () {


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
    var json = JSON.stringify(object);
    console.log(json);
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/lkz_project_war/registerUser/";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var som = JSON.parse(xhr.responseText);
            console.log(som);
        }
    };
    xhr.send(json);
};

if (regButton) {
    regButton.addEventListener("click", registrationFunction);
}











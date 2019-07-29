var filterForm = document.getElementById("filter-form");
var submitButtton = document.getElementById("apply_filters");
var json;

var doFilter = function () {
    generateJson();
    sendFilterData(json);
};

// var onLoad = function () {
//     if (request.readyState === XMLHttpRequest.DONE) {
//         var page = request.responseText;
//         document.body.innerHTML += "sdsdsd";
//     }
// };


if (submitButtton) {
    submitButtton.addEventListener("click", doFilter);
}

function generateJson() {
    var formVar = {
        "page":
            {
                "content": [],
                "number": 0,
                "size": 10,
                "totalElements": 10,
                "pageable":
                    {
                        "sort":
                            {
                                "sorted": false,
                                "unsorted": true,
                                "empty": true
                            },
                        "offset": 0,
                        "pageSize": 10,
                        "pageNumber": 0,
                        "unpaged": false,
                        "paged": true
                    },
                "last": true,
                "totalPages": 1,
                "sort": {
                    "sorted": false,
                    "unsorted": true,
                    "empty": true
                },
                "first": true,
                "numberOfElements": 10,
                "empty": false
            },
        "timeFilter": "THIRTY_DAYS",
        "newFilter": false,
        "inProcessingFilter": false,
        "approvedFilter": false,
        "rejectedFilter": false
    };
    var form = document.getElementById("filter-form");
    var formData = new FormData(form);
    formData.forEach(function (value, key) {
        console.log("key - " + key + ", value - " + value);
        if (key === "page") {
            formVar[key].number = value;
        } else {
            formVar[key] = value;
        }
    });
    json = JSON.stringify(formVar);
}


function sendFilterData(json) {
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/lkz_project_war/account/";
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    // var strJson = "{\"page\":{\"content\":[],\"number\":0,\"size\":10,\"totalElements\":10,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":10,\"empty\":false},\"timeFilter\":\"TEN_DAYS\",\"newFilter\":\"true\",\"inProcessingFilter\":\"true\",\"approvedFilter\":false,\"rejectedFilter\":false}\n";
    request.send(json);
    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            var body = document.getElementById("main-element");
            var response = request.responseText;
            response += "hello";
            body.innerHTML = response;
        }
    }
    // request.onreadystatechange = function () {
    //     if (request.readyState == XMLHttpRequest.DONE) {
    //         alert("test");
    //         var page = request.responseText;
    //         alert(page);
    //         var html = document.getElementsByTagName("html");
    //         html.innerHTML = page;
    //     }
    // }
}






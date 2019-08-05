function resetFilter() {
    var inputs = document.getElementsByTagName('input');
    document.getElementById('select1');
    var select1_option1 = document.getElementById('select1-op1');
    var select2_option1 = document.getElementById('select2-op1');
    var select1 = document.getElementById('select1');
    var select2 = document.getElementById('select2');

    for (var i = 0; i < select1.options.length; i++) {
        select1.options[i].removeAttribute("selected");
        select2.options[i].removeAttribute("selected");
    }
    select1.selected = select1_option1.value;
    select1.selected = select2_option1.value;

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type === 'checkbox' && inputs[i].checked === true) {
            inputs[i].removeAttribute("checked");
        }
    }
}


// var doFilter = function () {
//     var json = generateJson();
//     sendFilterData(json);
// };
//
// var formData;
//
// function generateJson() {
//     var formVar = {
//         "page":
//             {
//                 "content": [],
//                 "number": 0,
//                 "size": 10,
//                 "totalElements": 10,
//                 "pageable":
//                     {
//                         "sort":
//                             {
//                                 "sorted": false,
//                                 "unsorted": true,
//                                 "empty": true
//                             },
//                         "offset": 0,
//                         "pageSize": 10,
//                         "pageNumber": 0,
//                         "unpaged": false,
//                         "paged": true
//                     },
//                 "last": true,
//                 "totalPages": 1,
//                 "sort": {
//                     "sorted": false,
//                     "unsorted": true,
//                     "empty": true
//                 },
//                 "first": true,
//                 "numberOfElements": 10,
//                 "empty": false
//             },
//         "timeFilter": "THIRTY_DAYS",
//         "newFilter": false,
//         "inProcessingFilter": false,
//         "approvedFilter": false,
//         "rejectedFilter": false
//     };
//     var form = document.getElementById("filter-form");
//     formData = new FormData(form);
//     formData.forEach(function (value, key) {
//         console.log("key - " + key + ", value - " + value);
//         if (key === "pageNumber") {
//             formVar.page.number = value - 1;
//         } else {
//             formVar[key] = value;
//         }
//     });
//     return JSON.stringify(formVar);
// }
//
//
// function sendFilterData(json) {
//     var request = new XMLHttpRequest();
//     var url = "http://localhost:8080/lkz_project_war/account/";
//     request.open("POST", url, true);
//     request.setRequestHeader("Content-Type", "application/json");
//     // var strJson = "{\"page\":{\"content\":[],\"number\":0,\"size\":10,\"totalElements\":10,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":10,\"empty\":false},\"timeFilter\":\"TEN_DAYS\",\"newFilter\":\"true\",\"inProcessingFilter\":\"true\",\"approvedFilter\":false,\"rejectedFilter\":false}\n";
//     request.send(json);
//     request.onreadystatechange = function () {
//         if (request.readyState === XMLHttpRequest.DONE) {
//             var body = document.getElementById("main-element");
//             body.innerHTML = request.responseText;
//         }
//     }
// }







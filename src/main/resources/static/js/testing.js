var request = new XMLHttpRequest();
var url = "http://localhost:8080/lkz_project_war/account/";
request.open("GET", url, true);
request.setRequestHeader("Content-Type", "application/json");
var strJson = "{\"page\":{\"content\":[],\"number\":0,\"size\":10,\"totalElements\":10,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":10,\"empty\":false},\"timeFilter\":\"TEN_DAYS\",\"newFilter\":\"true\",\"inProcessingFilter\":\"true\",\"approvedFilter\":false,\"rejectedFilter\":false}\n";
request.send(strJson);
request.onreadystatechange = function () {
    if (request.readyState === XMLHttpRequest.DONE) {
        var body = document.getElementById("main-element");
        var response = request.responseText;
        response += "hello";
        body.innerHTML = response;
    }
};

var filterForm = document.getElementById("account-filter-form");
var json = {
    "page":
        {
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



filterForm.addEventListener("submit", doFilter);

function doFilter() {
    var formData = new FormData(filterForm);
    formData.forEach(function (value, key) {
        if (key === "page") {
            json.page.number = value;
        } else {
            json.key = value;
        }
    });
    var stringJson = JSON.stringify(json);
    console.log(stringJson);
}



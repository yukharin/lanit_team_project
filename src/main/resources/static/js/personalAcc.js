var filterForm = document.getElementById("account-filter-form");
var json = {
    "page": {
        "pageable":
            {
                "sort":
                    {
                        "sorted": false,
                        "unsorted": true,
                        "empty": true
                    },
                "offset": 0,
                "pageNumber": 0,
                "pageSize": 10,
                "paged": true,
                "unpaged": false
            },
        "last": true,
        "totalElements": 6,
        "totalPages": 1,
        "size": 10,
        "number": 0,
        "sort":
            {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
        "first": true,
        "numberOfElements": 6,
        "empty": false
    },
    "timeFilter": "NO_FILTER",
    "newFilter": false,
    "inProcessingFilter": false,
    "approvedFilter": false,
    "rejectedFilter": false
};

var json2 = {
    "page": {
        "number": 0,
        "size": 10,
        "totalElements": 6,
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
                "paged": true,
                "unpaged": false
            },
        "last": true,
        "totalPages": 1,
        "sort":
            {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
        "first": true,
        "numberOfElements": 6,
        "empty": false
    },
    "timeFilter": "NO_FILTER",
    "newFilter": true,
    "inProcessingFilter": true,
    "approvedFilter": false,
    "rejectedFilter": false
};


filterForm.addEventListener("submit", doFilter);

function doFilter() {
    var formData = new FormData(filterForm);
    formData.forEach(function (value, key) {
        if (key === "orgId") {
            json.organization.id = value;
        } else {
            object[key] = value;
        }
    });
}



(function (define) {

    "use strict";

    define(
        [],
        function () {
            var Movie = function ($resource) {
                return function (url) {
                    return $resource(
                        url, null,
                        {
                            searchByTitle: {method: "GET", url: "movie/search/smart/by-title/:title"},
                            findByTitle: {method: "GET", url: "movie/search/by-title/:title"}
                        });
                };
            };
            return ["$resource", "$log", Movie];
        }
    );
})(define);

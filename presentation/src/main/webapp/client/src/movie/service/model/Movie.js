(function (define) {

    "use strict";

    define(
        [],
        function () {
            var Movie = function ($resource, $log) {
                return function (url) {
                    return $resource(
                        url, null,
                        {
                            findByTitle: {method: "GET", url: "movie/search/by-title"},
                            requestByWordAndPersist: {method: "POST", url: "movie/persistence/request/by-word"}
                        });
                };
            };
            return ["$resource", "$log", Movie];
        }
    );
})(define);

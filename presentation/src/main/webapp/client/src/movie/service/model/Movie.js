(function (define) {

    "use strict";

    define(
        [],
        function () {
            var Movie = function ($resource, $log) {
                return $resource(
                    "movie", null,
                    {
                        findByTitle: {method: "GET", url: "movie/search/by-title/:title"},
                        requestByWordAndPersist: {method: "POST", url: "movie/persistence/request/by-word"}
                    });
            };
            return ["$resource", "$log", Movie];
        }
    );
})(define);

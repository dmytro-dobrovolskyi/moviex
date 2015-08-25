(function (define) {

    "use strict";

    define(
        [],
        function () {
            var Movie = function ($resource, $log) {
                return $resource(
                    "movie", null,
                    {
                        findByTitle: {method: "GET", url: "movie/search/by-title"},
                        requestAndSave: {method: "POST", url: "movie/request-and-save"}
                    });
            };
            return ["$resource", "$log", Movie];
        }
    );
})(define);

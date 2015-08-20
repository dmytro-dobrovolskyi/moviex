(function (define) {

    "use strict";

    define(
        [],
        function () {
            var Movie = function ($resource, $log) {
                return $resource(
                    "movie", null,
                    {
                        findByTitle: {method: "GET", url: "movie/search/findByTitle"}
                    });
            };
            return ["$resource", "$log", Movie];
        }
    );
})(define);

(function (define) {

    "use strict";

    define(
        [],
        function () {
            var MovieDetailsController = function ($scope, $log) {
                $log.info("MovieDetailsController");
            };
            return["$scope", "$log", MovieDetailsController];
        })
})
(define);

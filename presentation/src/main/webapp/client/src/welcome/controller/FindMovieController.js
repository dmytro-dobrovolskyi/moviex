(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function($scope, $log) {
                $log.debug("In FindMovieController");
            };

            return ["$scope", "$log", FindMovieController];
        }
    );
})(define);

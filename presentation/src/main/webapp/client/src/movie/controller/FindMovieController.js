(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function($rootScope, $scope, $log) {
                $log.debug("FindMovieController");
                $rootScope.isLoaded = true;
            };
            return ["$rootScope", "$scope", "$log", FindMovieController];
        }
    );
})(define);

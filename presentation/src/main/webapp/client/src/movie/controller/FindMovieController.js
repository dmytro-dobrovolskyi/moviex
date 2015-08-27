(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function ($rootScope, $scope, $log, $state) {
                $rootScope.isLoaded = true;
                $scope.title = {};
                $scope.isForceBtnShown = {};

                $scope.findMovie = function () {
                    $state.go("root.find-movie.search-result", {title: $scope.title.value}, {reload: true});
                };
            };
            return ["$rootScope", "$scope", "$log", "$state", FindMovieController];
        }
    );

    var ResultInfo = {
        OK: "OK",
        BY_WORD_REQUEST_REQUIRED: "BY_WORD_REQUEST_REQUIRED"
    }
})
(define);

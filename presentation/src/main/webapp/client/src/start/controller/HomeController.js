(function (define) {

    "use strict";

    define(
        [],
        function () {
            var HomeController = function ($rootScope, $scope, $log) {
                $scope.isCollapsed = true;

                $scope.slides =
                    [
                        {
                            image: "client/assets/css/images/slides/game-of-thrones.jpg"
                        },
                        {
                            image: "client/assets/css/images/slides/hannibal.jpg"
                        },
                        {
                            image: "client/assets/css/images/slides/true-detective.jpg"
                        }
                    ];
                $rootScope.isLoaded = true;
            };
            return ["$rootScope", "$scope", "$log", HomeController];
        }
    );
})(define);

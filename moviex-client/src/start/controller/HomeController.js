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
                            id: 0,
                            image: "assets/css/images/slides/game-of-thrones.png"
                        },
                        {
                            id: 1,
                            image: "assets/css/images/slides/hannibal.png"
                        },
                        {
                            id: 2,
                            image: "assets/css/images/slides/true-detective.png"
                        }
                    ];
                $rootScope.isLoaded = true;
            };
            return ["$rootScope", "$scope", "$log", HomeController];
        }
    );
})(define);

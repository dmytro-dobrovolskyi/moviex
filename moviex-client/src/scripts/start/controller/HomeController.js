(function (define) {

    var NUMBER_OF_SLIDES = 3;
    "use strict";

    define(
        [],
        function () {
            var HomeController = function ($rootScope, $scope, $log) {
                $scope.isCollapsed = true;
                $scope.slides = [];

                for (var i = 0; i < NUMBER_OF_SLIDES; i++) {

                    $scope.slides.push({
                        id: i,
                        image: $rootScope.cdnCustomImagesUrl + "images/slides/"+ i + ".png"
                    });
                }

                $rootScope.isLoaded = true;
            };
            return ["$rootScope", "$scope", "$log", HomeController];
        }
    );
})(define);

(function (define) {

    "use strict";

    define(
        [],
        function () {
            var HomeController = function($scope, $log) {
                $scope.isCollapsed = true;
                $log.info("Home Controller");
            };
            return ["$scope", "$log", HomeController];
        }
    );
})(define);

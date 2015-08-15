(function (define) {

    "use strict";

    define(
        [],
        function () {
            var HomeController = function($scope, $log) {
                $log.info("HomeController");
                $scope.isCollapsed = true;
            };

            return ["$scope", "$log", HomeController];
        }
    );
})(define);

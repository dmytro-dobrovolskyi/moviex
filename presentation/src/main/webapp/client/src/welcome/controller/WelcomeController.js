(function (define) {

    "use strict";

    define(
        [],
        function () {
            var WelcomeController = function($scope, $log) {
                $scope.tryIt = "";
                $log.debug($scope.tryIt);
            };

            return ["$scope", "$log", WelcomeController];
        }
    );

})(define);

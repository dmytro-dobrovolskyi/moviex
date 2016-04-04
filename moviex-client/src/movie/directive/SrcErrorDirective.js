(function (define, angular) {

    "use strict";

    define(
        [],
        function () {
            return function () {
                return {
                    link: function (scope, element, attrs) {
                        element.bind('error', function () {
                            angular.element(this).attr("src", attrs.srcErr);
                        });
                    }
                }
            };
        })
})(define, angular);

(function (define) {

    "use strict";

    define(
        [],
        function () {
            var MovieSearchRequestController = function ($scope, $log, $stateParams, $location, Movie, SpringDataRestAdapter) {
                $scope.isMoviesLoading = true;
                $scope.title.value = $stateParams.title;

                var isForce = $stateParams.isForce === "true";

                Movie
                    .findByTitle({isForce: isForce, title: $stateParams.title}, function (data, getHeaders) {
                        SpringDataRestAdapter
                            .process(data.$promise)
                            .then(function (processedResponse) {
                                $scope.result = processedResponse._embeddedItems;
                            });

                        var titleWords = $stateParams.title.split(" ");

                        if (getHeaders().result === ResultInfo.BY_WORD_REQUEST_REQUIRED && titleWords.length > 1) {
                            Movie.requestByWordAndPersist(titleWords);
                        }
                        $scope.isMoviesLoading = !($scope.isMoviesLoaded = true);
                        $location.search('isForce', null);
                        $scope.isForceBtnShown.value = !isForce;
                    });

                $scope.toFullMovieInfo = function (links) {
                    $log.info(links);
                };

            };
            return ["$scope", "$log", "$stateParams", "$location", "Movie", "SpringDataRestAdapter", MovieSearchRequestController];
        }
    );

    var ResultInfo = {
        OK: "OK",
        BY_WORD_REQUEST_REQUIRED: "BY_WORD_REQUEST_REQUIRED"
    }
})
(define);

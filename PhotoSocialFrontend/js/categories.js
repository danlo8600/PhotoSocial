var module = angular.module("categories",[])
    /* Category controller */
    .controller('ngCategory', function ($scope, $http){
            var getCat = "http://localhost:8080/PhotoSocial/getCategories";
            $http.get(getCat).success(function(data_cat) {
                    $scope.categories = data_cat;
            });
    });
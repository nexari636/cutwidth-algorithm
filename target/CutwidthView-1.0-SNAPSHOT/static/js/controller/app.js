angular.module("app",["ngRoute"])
        .config(function($routeProvider)
        {
           $routeProvider
                .when("/cutwNN",{
                   controller: "rest_controller_cutnn",
                   templateUrl: "cutwidthNN.html"
               })
               .when("/cutwSA",{
                    controller: "rest_controller_cutsa",
                    templateUrl: "cutwidthSA.html"
                })
                .otherwise({
                   redirectTo:"/"
               });
        });


//controller global
angular.module("app").controller("application",function($scope,$http){

    $scope.obtener_archivos=(id_control)=> document.getElementById(id_control).files[0];
    
});
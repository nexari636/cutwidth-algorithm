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

//aparecer el toasts
function toaskActive(leyenda)
{
    const toastLive = $('#liveToast');
    toastLive[0].lastElementChild.innerText=leyenda;
    console.log(toastLive[0].lastElementChild);
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLive[0])
    toastBootstrap.show();
}
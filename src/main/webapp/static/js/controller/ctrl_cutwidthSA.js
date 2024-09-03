angular.module("app")
.controller("rest_controller_cutsa", function ($scope, $http) 
{

    $(document).ready(function ()
    {
        init_diagram();
        limpiar_diagrama();
    });

    $scope.archivo=null;

    $scope.enviar_data=(form)=>
    {
        limpiar_diagrama();
        console.log(form);
        $scope.archivo=$scope.obtener_archivos("archivo");

        if($scope.archivo!=null && form.temperaturaI.$viewValue!=undefined 
            && form.temperaturaF.$viewValue!=undefined && form.bajar_temperatura.$viewValue!=undefined
            && form.alfa.$viewValue!=undefined)
        {
            let formData=new FormData();
            formData.append("file",$scope.archivo);
            formData.append("temperatura_inicial",form.temperaturaI.$viewValue);
            formData.append("temperatura_final",form.temperaturaF.$viewValue);
            formData.append("L",form.bajar_temperatura.$viewValue);
            formData.append("Alfa",form.alfa.$viewValue);

            $.ajax({
                method:"POST",
                dataType:"json",
                url:"http://127.0.0.1:8081/api/cutwidth/resolverSA",
                processData:false,
                contentType: false,
                data: formData,
                beforeSend: function (xhr) {
                    console.log("cargando...");
                },
                success: function (data) {
                    $scope.$apply(function() 
                    {
                        console.log(data);
                        let x=0;
                        for(let nodo of data.solucion) {  
                            crear_nodos(nodo.value,nodo.cant_vertices_particionB,x==data.solucion.length-1);
                            x++;
                        }
                            
                        for(let nodo of data.conexion)           
                            crear_conexiones(nodo.nodo,nodo.conexion);
                        asignar_nodos_diagrama();

                        toaskActive("La tarea fue completada con exito");

                    });
                },
                error: function (objXMLHttpRequest) {
                    console.log("error: ", objXMLHttpRequest);
                    toaskActive("Ha ocurrido un error en el sistema");
                }
            });
        }else{
            console.log("Error los campos estan incompletos");
            toaskActive("Error campos incompletos");
        }
    } 



});
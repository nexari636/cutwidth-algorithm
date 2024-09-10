angular.module("app")
.controller("rest_controller_cutnn", function ($scope, $http) 
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

        if($scope.archivo!=null && form.interacciones.$viewValue!=undefined && form.muestra.$viewValue!=undefined)
        {
            let formData=new FormData();
            formData.append("file",$scope.archivo);
            formData.append("interacciones",form.interacciones.$viewValue);
            formData.append("muestra",form.muestra.$viewValue);

            $.ajax({
                method:"POST",
                dataType:"json",
                url:"http://127.0.0.1:8081/api/cutwidth/resolver",
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
                  
                        $('#corteval').text(data.mejorCorte)
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
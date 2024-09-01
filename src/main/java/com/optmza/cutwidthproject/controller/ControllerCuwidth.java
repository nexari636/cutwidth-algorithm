/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.optmza.cutwidthproject.controller;

import com.optmza.cutwidthproject.cutwidth.Scutwidth;
import com.optmza.cutwidthproject.cutwidthUT.ConexionInicial;
import com.optmza.cutwidthproject.cutwidthUT.Utilidades;
import com.optmza.cutwidthproject.utilidades.Messages;
import com.optmza.cutwidthproject.utilidades.ParametrosApi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/api/cutwidth")
@CrossOrigin("*")
public class ControllerCuwidth 
{
    
    @PostMapping("/resolver")
    public ResponseEntity<?> resolver_normal(@RequestParam(name="file",required=true) MultipartFile file,ParametrosApi paramapi)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            if(paramapi.getInteracciones()!=0 && paramapi.getPoblacion()!=0)
            {
                List<ConexionInicial> lst_conexion_inicial=Utilidades.leer_archivo_web(file.getBytes());
                Scutwidth scut=new Scutwidth(lst_conexion_inicial,paramapi.getPoblacion(),paramapi.getInteracciones());
                System.out.println(paramapi.getPoblacion());            
                System.out.println(paramapi.getInteracciones());
                response.put("nodos", scut.getNod());
                response.put("conexion", scut.getLst_solucionActualGLOBAL());
                response.put("solucion", scut.getNodosActualSolucionGLOBAL());
            }
            else
            {
                response.put(Messages.ERROR_KEY, Messages.ERROR_SISTEMA);
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);               
            }
        }
        catch(Exception ex)
        {
            response.put(Messages.ERROR_KEY, Messages.ERROR_SISTEMA);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK); 
    }
    
    
    @PostMapping("/resolverSA")
    public ResponseEntity<?> resolver_sa(@RequestParam(name="file",required=true) MultipartFile file,ParametrosApi paramapi)
    {
        Map<String,Object> response=new HashMap();
        try
        {
            if(paramapi.getTemperatura_inicial()>paramapi.getTemperatura_final() && paramapi.getL()!=0 && paramapi.getTemperatura_inicial()!=0 && paramapi.getTemperatura_final()!=0 && paramapi.getAlfa()!=0)
            {
                List<ConexionInicial> lst_conexion_inicial=Utilidades.leer_archivo_web(file.getBytes());
                Scutwidth scut=new Scutwidth(lst_conexion_inicial,paramapi.getTemperatura_inicial(),paramapi.getTemperatura_final(),paramapi.getL(),paramapi.getAlfa());
                System.out.println(paramapi.getTemperatura_inicial());            
                System.out.println(paramapi.getTemperatura_final());
                System.out.println(paramapi.getL());
                System.out.println(paramapi.getAlfa());
                response.put("nodos", scut.getNod());
                response.put("conexion", scut.getLst_solucionActual());
                response.put("solucion", scut.getNodosActualSolucionGLOBAL());
            }
            else
            {
                response.put(Messages.ERROR_KEY, Messages.ERROR_SISTEMA);
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);               
            }
        }
        catch(Exception ex)
        {
            response.put(Messages.ERROR_KEY, Messages.ERROR_SISTEMA);
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK); 
    }

    
}

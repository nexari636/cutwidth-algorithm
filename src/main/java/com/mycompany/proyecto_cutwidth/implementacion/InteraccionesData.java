/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth.implementacion;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author USUARIO
 */
public class InteraccionesData 
{
    int poblacion;
    List<ConexionInicial> lst_conexion_inicial;
    List<List<ConexionInicial>> conjunto_interacciones;
    String []nodos_data;
    
    public InteraccionesData(List<ConexionInicial> lst_conexion_inicial,int poblacion)
    {
        this.lst_conexion_inicial=lst_conexion_inicial;
        this.conjunto_interacciones=new ArrayList<>();
        nodos_data=new String[lst_conexion_inicial.size()];
        this.poblacion=poblacion;
        data_nodo();
        baraja_lista();
    }

    private void data_nodo()
    {
        int x=0;
        for(ConexionInicial ci : lst_conexion_inicial)
        {
            nodos_data[x]=ci.nodo;
            x++;
        }
    }
    
    
    private void baraja_lista()
    {
        try
        {
            Random ramdom =  SecureRandom.getInstance("SHA1PRNG");
            Boolean conjunto_falloso=false;
            List<ConexionInicial> copia_original=copy_list_conexiones(lst_conexion_inicial);
            int cont=0;
            while(cont<this.poblacion)
            {
                conjunto_falloso=false;
                Collections.shuffle(copia_original,ramdom);
                List<ConexionInicial> lst_copia=copy_list_conexiones(copia_original);

                for(int x=0;x<lst_copia.size();x++)
                {
                    lst_copia.get(x).nodo=nodos_data[x];
                  
                    if(lst_copia.get(x).nodo.equals(lst_copia.get(x).conexion)){
                        conjunto_falloso=true;
                        break;
                    }
                    
                }
   
                if(!conjunto_falloso){
                    this.conjunto_interacciones.add(lst_copia);
                    cont++;  
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    private List<ConexionInicial> copy_list_conexiones(List<ConexionInicial> list_conexionInicial)
    {
        List<ConexionInicial> lst_conex=new ArrayList<>();
        for(ConexionInicial ci : list_conexionInicial)
        {
            lst_conex.add(new ConexionInicial(ci.nodo,ci.conexion,ci.peso));
        }
        return lst_conex;
    }
   
}

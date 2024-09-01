/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.optmza.cutwidthproject.cutwidthUT;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USUARIO
 */
@Getter
@Setter
public class InteraccionesData 
{
    List<ConexionInicial> lst_conexion_inicial;
    List<ConexionInicial> conexionesBaraja;
    
    public InteraccionesData(List<ConexionInicial> lst_conexion_inicial)
    {
        this.lst_conexion_inicial=lst_conexion_inicial;
        this.conexionesBaraja=new ArrayList<>();
        //baraja_lista();
    }

    public void baraja_lista()
    {
        try
        {
            LinkedHashSet<ConexionInicial> lst_busca = new LinkedHashSet<>();
            
            Random ramdom =  SecureRandom.getInstance("SHA1PRNG");
            conexionesBaraja=copy_list_conexiones(lst_conexion_inicial);
            
            Collections.shuffle(conexionesBaraja,ramdom);

            for(int x=0;x<conexionesBaraja.size();x++)
                conexionesBaraja.get(x).nodo=lst_conexion_inicial.get(x).nodo;
            
            lst_busca.addAll(conexionesBaraja);
            conexionesBaraja.clear();
            conexionesBaraja.addAll(lst_busca);
            lst_busca.clear();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public List<ConexionInicial> copy_list_conexiones(List<ConexionInicial> list_conexionInicial)
    {
        List<ConexionInicial> lst_conex=new ArrayList<>();
        for(ConexionInicial ci : list_conexionInicial)
        {
            lst_conex.add(new ConexionInicial(ci.nodo,ci.conexion,ci.peso));
        }
        return lst_conex;
    }
   
}

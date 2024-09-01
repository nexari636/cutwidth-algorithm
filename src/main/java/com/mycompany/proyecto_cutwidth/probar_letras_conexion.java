/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class probar_letras_conexion 
{
    public static void main(String[] args) 
    {
        List<ConexionInicial> lst_con=new ArrayList<>();
        lst_con.add(new ConexionInicial("a","b",1));        
        lst_con.add(new ConexionInicial("a","d",1));
        lst_con.add(new ConexionInicial("c","b",1));
        lst_con.add(new ConexionInicial("d","c",1));
      
        
        int posinicial=-1,posfinal=-1;
        Boolean postIni=true;
        String letra_buscar="h";
        for(int x=0;x<lst_con.size();x++)
        {
            if(postIni)
            {
                if(lst_con.get(x).nodo.equals(letra_buscar))
                {
                    postIni=false;
                    posinicial=x;
                    posfinal=x;
                }
            }
            else
            {
                if(!lst_con.get(x).nodo.equals(letra_buscar))
                {
                    posfinal=x-1;
                    break;
                }
                posfinal=x;
            }
        }
        
        System.out.println(posinicial+" - "+posfinal);
        
    }
}

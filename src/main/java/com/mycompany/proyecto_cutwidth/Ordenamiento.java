/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Ordenamiento 
{
    //Quick Sort algoritmo
    
    public static void main(String[] args) 
    {
        List<ConexionInicial> lst_con=new ArrayList<>();
        lst_con.add(new ConexionInicial("c","b",1));
        lst_con.add(new ConexionInicial("a","d",1));
        lst_con.add(new ConexionInicial("d","c",1));
        lst_con.add(new ConexionInicial("a","b",1));       
        
        burbuja(lst_con);
    }

    private static void burbuja(List<ConexionInicial> lst_con) 
    {
        String nodo=lst_con.get(0).nodo.toLowerCase();
        boolean es_numero=nodo.matches("[0-9,;]*"); //FALSE SI ES STRING Y TRUE SI ES NUMERICO
        System.out.println(es_numero); 
        
        for(int x=0;x<lst_con.size()-1;x++)
        {
            for(int i=0;i<lst_con.size()-1;i++)
            {
                char nodo_pos_uno=lst_con.get(i).nodo.toLowerCase().charAt(0);                
                char nodo_pos_dos=lst_con.get(i+1).nodo.toLowerCase().charAt(0);
                
                if(nodo_pos_uno>nodo_pos_dos)
                {
                    ConexionInicial aux=lst_con.get(i);
                    lst_con.set(i, lst_con.get(i+1));
                    lst_con.set(i+1, aux);
                }
            }
        }
        
        
        
        for(ConexionInicial ci : lst_con)
        {
            System.out.println(ci.nodo);
        }
        
        
    }
}

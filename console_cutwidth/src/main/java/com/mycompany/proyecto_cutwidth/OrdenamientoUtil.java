/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public class OrdenamientoUtil 
{
    
    
    private static void burbuja_letras(List<ConexionInicial> lst_con) 
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
    }
    
    
    public static void burbuja_numeros(List<ConexionInicial> lst_con) 
    {
        String nodo=lst_con.get(0).nodo.toLowerCase();
        boolean es_numero=nodo.matches("[0-9,;]*"); //FALSE SI ES STRING Y TRUE SI ES NUMERICO
        System.out.println(es_numero); 
        
        for(int x=0;x<lst_con.size()-1;x++)
        {
            for(int i=0;i<lst_con.size()-1;i++)
            {
                int nodo_pos_uno=Integer.parseInt(lst_con.get(i).nodo);                
                int nodo_pos_dos=Integer.parseInt(lst_con.get(i+1).nodo);
                
                if(nodo_pos_uno>nodo_pos_dos)
                {
                    ConexionInicial aux=lst_con.get(i);
                    lst_con.set(i, lst_con.get(i+1));
                    lst_con.set(i+1, aux);
                }
            }
        } 
    }
    
    
    
}

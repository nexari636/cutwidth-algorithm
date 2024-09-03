/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class prueba_objetos_iguales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ConexionInicial ciuno=new ConexionInicial("1","1",1);        
        ConexionInicial cidos=new ConexionInicial("6","1",1);
        
        System.out.println("HASH ciuno= "+ciuno.hashCode());
        System.out.println("HASH ciuno= "+cidos.hashCode());
        
        if(ciuno.equals(cidos)){
            System.out.println("objetos iguales");
        }

        HashSet<ConexionInicial> lst_busca = new HashSet<ConexionInicial>();

        lst_busca.add(ciuno);        
        lst_busca.add(cidos);

       List<ConexionInicial> lst_c= new ArrayList<>(lst_busca);
        
       for(ConexionInicial ci : lst_c){
           System.out.println(ci.nodo);
       }
       
       
       List<ConexionInicial> ci=new ArrayList<>();
       ci.add(ciuno);
       ci.add(cidos);
       LinkedHashSet<ConexionInicial> lst_buscad = new LinkedHashSet<ConexionInicial>();
       lst_buscad.addAll(ci);
        ci.clear();
       for(ConexionInicial cia : lst_buscad){
           System.out.println(cia.nodo);
       }
       
        //System.out.println(lst_busca.size());
        
        
        
    }
    
}

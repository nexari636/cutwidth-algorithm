/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class busqueda_binaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> lstint=new ArrayList<>();
//        lstint.add(8);
//        lstint.add(10);
//        lstint.add(12);
//        lstint.add(14);
//        lstint.add(23);
//        lstint.add(30);
//        lstint.add(35);
//        lstint.add(42);
//        lstint.add(45);
//        lstint.add(45);
//        lstint.add(56);
//        lstint.add(56);
//        lstint.add(56);
//        lstint.add(56);
//        lstint.add(56);
//        lstint.add(63);
//        lstint.add(67);
//        lstint.add(77);
//        lstint.add(78);
//        lstint.add(89);
//        lstint.add(91);
            lstint.add(1);
            lstint.add(1);
            lstint.add(2);
                        lstint.add(3);

 
        int nume[]=obtener_rango(lstint,1);
        System.out.println(nume[0]+" - "+nume[1]);
    }
    
    
    public static int[] obtener_rango(List<Integer> numeros, int valor_buscar)
    {
        int pos_inicial=0;
        int pos_final=numeros.size()-1;
        int pos_lista=(pos_inicial+pos_final)/2;
        Boolean encontrado=false;
        
        while(pos_inicial<=pos_final)
        {
            pos_lista=(pos_inicial+pos_final)/2;
            
            if(valor_buscar>numeros.get(pos_lista))
                pos_inicial=pos_lista+1;          
            else if(valor_buscar<numeros.get(pos_lista))
                pos_final=pos_lista-1;
            else{ 
               encontrado=true;
               break;
            }
        }
        Boolean posI=false;
        if(encontrado){
            while(true)
            {
                if(!posI)
                {
                    if((pos_lista-1)>-1){
                                    System.out.println(pos_lista+"fff");

                        if(numeros.get(pos_lista-1)==valor_buscar)
                            pos_lista--;
                        else{
                            posI=true;
                            pos_inicial=pos_lista;
                        }
                    }else{
                       posI=true;
                        pos_inicial=pos_lista; 
                    }
                }
                else if((pos_lista+1)<=(numeros.size()-1))
                {
                    if(numeros.get(pos_lista+1)==valor_buscar)
                        pos_lista++;
                    else
                    {
                        pos_final=pos_lista;
                        break;
                    }
                }else
                    break;
                
            }
        }else
            return new int[]{-1,-1};

        return new int[]{pos_inicial,pos_final};
    }
    
    
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.Arrays;

/**
 *
 * @author USUARIO
 */
public class prueba_strings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String numero="1,256,";
        
        String h[]=numero.split(",");
        
        
        if(Arrays.asList(h).indexOf("256")!=-1){
            System.out.println("si esta");
        }else{
            System.out.println("NO ESTA");
        }
        
    }
    
}

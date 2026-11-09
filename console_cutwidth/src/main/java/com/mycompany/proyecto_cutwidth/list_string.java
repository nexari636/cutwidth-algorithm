/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class list_string {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        List<String> list = Arrays.asList("A", "B", "C");
        String delim = ",";
        
        String res = String.join(delim, list);
 
        System.out.println(res);
        
    }
    
}

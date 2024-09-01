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
public class pruebarefarray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        int[] array = {23, 43, 55, 12, 65, 88, 92};
        int[]arraycp=null;
        //arraycp=Arrays.copyOf(array, array.length);
        arraycp=array.clone();

        array=null;
        for(int a: arraycp)
        {
            System.out.println(a);
        }

    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class trascribir_archivo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        escribir_archivo();
    }

    private static void escribir_archivo() throws IOException
    {
       String directorio=System.getProperty("user.dir");
       //String ruta="\\src\\main\\java\\com\\mycompany\\proyecto_cutwidth\\"+NOMBRE_ARCHIVO;
       String ruta="\\conjuntosDatos\\nn\\";
       //\\datos10.txt
       File archivo = new File(directorio+ruta+"texto.txt");
       FileWriter escribir = new FileWriter(archivo, true);
       try 
       {
            FileReader fr = new FileReader(directorio+ruta+"datos10.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
               //System.out.println("LINEA= "+linea.trim());
               linea=linea.trim();
               String vec[]=linea.split(";");
               
               escribir.write(vec[0]+" "+vec[1]);
               escribir.write("\r\n"); 
               //if (vec.length>2) continue;
            }
             escribir.close();
             fr.close();
       }
       catch(Exception ex){
         System.out.println(ex.getMessage());
      }
        
    }


    
}

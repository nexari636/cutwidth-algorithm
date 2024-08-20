/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth.implementacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Utilidades 
{
    
    public static List<ConexionInicial> leer_archivo(String SEPARADOR,String NOMBRE_ARCHIVO)
    {
       String directorio=System.getProperty("user.dir");
       //String ruta="\\src\\main\\java\\com\\mycompany\\proyecto_cutwidth\\"+NOMBRE_ARCHIVO;
       String ruta="\\conjuntosDatos\\nn\\"+NOMBRE_ARCHIVO;
       try 
       {
            FileReader fr = new FileReader(directorio+ruta);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            List<ConexionInicial> lst_conexion_inicial=new ArrayList<>();
            while((linea=br.readLine())!=null){
               //System.out.println("LINEA= "+linea.trim());
               linea=linea.trim();
               String vec[]=linea.split(SEPARADOR);
               if (vec.length>2) continue;
               lst_conexion_inicial.add(new ConexionInicial(vec[0].trim(),vec[1].trim(),1));
            }
            return lst_conexion_inicial;
       }
       catch(Exception ex){
         System.out.println(ex.getMessage());
      }
       return null;
    }
    
    public static int MAX(int [] array)
    {
       try
       {
           Arrays.sort(array);
           return array[array.length-1];
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
       return 0;
    }
   
    public static int MIN(int [] array)
    {
       try
       {
           Arrays.sort(array);
           return array[0];
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
       }
       return 0;
    }
    
    public static void algoritmo_burbuja(List<ConexionInicial> lst_con) 
    {
        String nodo=lst_con.get(0).nodo.toLowerCase();
        boolean es_numero=nodo.matches("[0-9,;]*"); //FALSE SI ES STRING Y TRUE SI ES NUMERICO
        //System.out.println(es_numero); 
        
        for(int x=0;x<lst_con.size()-1;x++)
        {
            for(int i=0;i<lst_con.size()-1;i++)
            {
                Boolean valor_condicion=false;
                if(es_numero)
                {
                    int nodo_pos_uno=Integer.parseInt(lst_con.get(i).nodo);                
                    int nodo_pos_dos=Integer.parseInt(lst_con.get(i+1).nodo);
                    valor_condicion=nodo_pos_uno>nodo_pos_dos;
                }else
                {
                    char nodo_pos_uno=lst_con.get(i).nodo.toLowerCase().charAt(0);                
                    char nodo_pos_dos=lst_con.get(i+1).nodo.toLowerCase().charAt(0);
                    valor_condicion=nodo_pos_uno>nodo_pos_dos;
                }
                
                
                if(valor_condicion)
                {
                    ConexionInicial aux=lst_con.get(i);
                    lst_con.set(i, lst_con.get(i+1));
                    lst_con.set(i+1, aux);
                }
            }
        } 
    }
  
}

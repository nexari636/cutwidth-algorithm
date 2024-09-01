/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.optmza.cutwidthproject.cutwidthUT;
import com.optmza.cutwidthproject.utilidades.Separadores;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author USUARIO
 */
public class Utilidades 
{
    
    public static String[] nodos_grafo_orden;
    
    
    
    public static List<ConexionInicial> leer_archivo_web(byte [] bytes)
    {
        try
        {
            List<ConexionInicial> lst_conexion_inicial=new ArrayList<>();
            HashSet<String> lst_busca = new HashSet<String>();

            UUID uuid = UUID.randomUUID();
            byte[] archivobyte=bytes;
            File f = File.createTempFile("temp_file"+uuid,".txt");
            OutputStream  out = new FileOutputStream(f);
            out.write(archivobyte);
            out.close();
            
            String linea="";
            BufferedReader br = new BufferedReader(new FileReader(f));
            while((linea = br.readLine()) != null) 
            {
                linea=linea.trim();
                String vec[]=linea.split(Separadores.SEPARADOR_ARCHIVO);
                //System.out.println(vec[0]);
                if (vec.length>2) continue;
                lst_conexion_inicial.add(new ConexionInicial(vec[0].trim(),vec[1].trim(),1));

                lst_busca.add(vec[0].trim());
                lst_busca.add(vec[1].trim());
            }
            br.close();
            f.delete();
            nodos_grafo_orden=ordenar_vector(lst_busca.stream().toArray(String[]::new));
            ordenar_conexion_inicial(lst_conexion_inicial);
            return lst_conexion_inicial;
            
        }
        catch(Exception ex)
        {
           System.out.println(ex.getMessage());
        }
        return null;
    }

    
    
    
    public static List<ConexionInicial> leer_archivo(String NOMBRE_ARCHIVO)
    {
       String directorio=System.getProperty("user.dir");
       String ruta="\\conjuntosDatos\\nn\\"+NOMBRE_ARCHIVO;
       HashSet<String> lst_busca = new HashSet<String>();
       try 
       {
            FileReader fr = new FileReader(directorio+ruta);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            List<ConexionInicial> lst_conexion_inicial=new ArrayList<>();
         
            while((linea=br.readLine())!=null){
                //System.out.println("LINEA= "+linea.trim());
                linea=linea.trim();
                String vec[]=linea.split(Separadores.SEPARADOR_ARCHIVO);
                //System.out.println(vec[0]);
                if (vec.length>2) continue;
                lst_conexion_inicial.add(new ConexionInicial(vec[0].trim(),vec[1].trim(),1));

                lst_busca.add(vec[0].trim());
                lst_busca.add(vec[1].trim());

            }
            nodos_grafo_orden=ordenar_vector(lst_busca.stream().toArray(String[]::new));
            ordenar_conexion_inicial(lst_conexion_inicial);
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
    
    
    
    public static void ordenar_conexion_inicial(List<ConexionInicial> lst_con)
    {
        boolean es_numero=Utilidades.verificar_numero(lst_con.get(0).nodo);
        
        if(es_numero)
            lst_con.sort(Comparator.comparing(ConexionInicial::getNodoNumero));
        else
            lst_con.sort(Comparator.comparing(ConexionInicial::getNodoLetra));

    }
    

    private static String[] ordenar_vector(String [] nodos)
    {
        String [] nod=nodos;
        boolean es_numero=Utilidades.verificar_numero(nodos[0]);
        if(es_numero)
        {
            int[] nodos_integer = Arrays.stream(nod).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(nodos_integer);
            Arrays.toString(nodos_integer);
            
            nod=Arrays.stream(nodos_integer)
                                .mapToObj(String::valueOf)
                                .toArray(String[]::new);
        }else
        {
           Arrays.sort(nod);
        }
        return nod;
    }
    
    public static Boolean verificar_numero(String cadena)
    {
        return cadena.matches("[0-9,;]*"); //FALSE SI ES STRING Y TRUE SI ES NUMERICO
    }
    
    
    public static Double probabilidad(Integer DELTA,Double temperatura)
    {
        return Math.exp(-DELTA/temperatura);
    }
    
    public static double Aleatorio()
    {
        try{
            Random ramdom =  SecureRandom.getInstance("SHA1PRNG");
            return ramdom.nextDouble();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
    
    
}

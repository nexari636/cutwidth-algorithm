/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USUARIO
 */
public class Cutwidth 
{
    public static String SEPARADOR=",";
    public static Integer CANTIDAD_NODOS;
    
    public static void main(String[] args) 
    {
        List<ConexionInicial> lst_conexion_inicial=leer_archivo();
        String [] nod=null;
        if(lst_conexion_inicial!=null)
        {
            String nodos=obtener_nodos(lst_conexion_inicial);
            nod=ordenar_nodos(nodos);
        }else return;
        
        CANTIDAD_NODOS=nod.length;
        Map<String, Integer> nodos_indentificador=identificar_nodos(nod);
        Nodo[]Nodos=crear_nodos(nod);
        

    }
    
    
    private static void Cutwidth(Nodo[][] matriz_nodos,Map<String, Integer> nodos_indentificador)
    {
        
    }
    
    private static Nodo[][] crear_matriz_nodo(Nodo[]Nodos)
    {
        try
        {
            Nodo[][]matriz_nodos=new Nodo[CANTIDAD_NODOS][CANTIDAD_NODOS];
            for(int fila=0;fila<matriz_nodos.length;fila++)
            {
               for(int columna=0;columna<matriz_nodos[0].length;columna++)
                   matriz_nodos[fila][columna]=Nodos[columna];
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    private static Nodo[] crear_nodos(String [] nodos)
    {
        try
        {
            Nodo[]Nodos=new Nodo[CANTIDAD_NODOS];
            for(int x=0;x<Nodos.length;x++)
                Nodos[x]=new Nodo(nodos[x]);
            return Nodos;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    private static Map<String, Integer> identificar_nodos(String [] nodos)
    {
        try
        {
            Map<String, Integer> nodos_indentificador=new HashMap();
            for(int x=0;x<nodos.length;x++)
                nodos_indentificador.put(nodos[x], x);
            return nodos_indentificador;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    private static String obtener_nodos(List<ConexionInicial> lst_conexion_inicial)
    {
        try
        {
            String nodos="";
            for(ConexionInicial ci : lst_conexion_inicial)
            {
                if(!nodos.contains(ci.nodo))
                    nodos+=ci.nodo+SEPARADOR;
                if(!nodos.contains(ci.conexion))
                    nodos+=ci.conexion+SEPARADOR;
            }
            nodos=nodos.substring(0, nodos.length()-1);
            return nodos;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
    private static String[] ordenar_nodos(String nodos)
    {
        String [] nod=nodos.split(SEPARADOR);
        String nodos_ordenados="";
        boolean es_numero=nodos.matches("[0-9,;]*"); //FALSE SI ES STRING Y TRUE SI ES NUMERICO
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
    
    
    //C:\Users\USUARIO\Desktop\cutwidth\proyecto_cutwidth\src\main\java\com\mycompany\proyecto_cutwidth\datos.txt
    private static List<ConexionInicial> leer_archivo()
    {
       String directorio=System.getProperty("user.dir");
       String ruta="\\src\\main\\java\\com\\mycompany\\proyecto_cutwidth\\datos.txt";
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
               lst_conexion_inicial.add(new ConexionInicial(vec[0],vec[1],0));
            }
            return lst_conexion_inicial;
       }
       catch(Exception ex){
         System.out.println(ex.getMessage());
      }
       return null;
    }
    
    
}

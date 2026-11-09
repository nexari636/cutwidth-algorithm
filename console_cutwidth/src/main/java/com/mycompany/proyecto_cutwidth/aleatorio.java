/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author USUARIO
 */
public class aleatorio {

    /**
     * @param args the command line arguments
     */
    //https://chuidiang.org/index.php?title=Generar_n%C3%BAmeros_aleatorios_en_Java
    //https://www.juntadeandalucia.es/servicios/madeja/contenido/recurso/607
    //https://docs.oracle.com/cd/E17802_01/j2se/j2se/1.5.0/jcp/beta1/apidiffs/java/security/SecureRandom.html
    //https://www.digitalocean.com/community/tutorials/shuffle-array-java
    //https://www.javatpoint.com/java-collections-shuffle-method
    public static Random ramdom;
    
    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
        //produce numeros aleatorios fuertos es decir de calidad
        ramdom =  SecureRandom.getInstance("SHA1PRNG");
        System.out.println(ramdom.nextInt(1,10));
        //verificar_barajar_array_prueba();
        
        List<ConexionInicial> lst_con=new ArrayList<>();
        lst_con.add(new ConexionInicial("a","b",1));        
        lst_con.add(new ConexionInicial("a","d",1));
        lst_con.add(new ConexionInicial("c","b",1));
        lst_con.add(new ConexionInicial("d","c",1));
        List<List<ConexionInicial>> lst_iteracciones=conexiones_baraja(lst_con);
        imprimir(lst_iteracciones);
    }

        
    private static List<List<ConexionInicial>> conexiones_baraja(List<ConexionInicial> lst_conexion_inicial)
    {
        String nodos_data[]={"a","a","c","d"};
        Boolean conjunto_falloso=false;
        List<List<ConexionInicial>> lst_iteracciones=new ArrayList<>();
        List<ConexionInicial> copia_original=copy_list_conexiones(lst_conexion_inicial);
        
        int cont=0;
        while(cont<15)
        {
            conjunto_falloso=false;
            Collections.shuffle(copia_original,ramdom);
            List<ConexionInicial> lst_copia=copy_list_conexiones(copia_original);
            for(int x=0;x<lst_copia.size();x++)
                lst_copia.get(x).nodo=nodos_data[x];
                
            for(int x=0;x<lst_copia.size();x++)
            {
                
                for(int i=0;i<lst_copia.size();i++)
                {
                    if(lst_copia.get(i).nodo.equals(lst_copia.get(i).conexion)){
                            conjunto_falloso=true;
                                                System.out.println("hola1");

                            break;
                    }
                    
                    if(i!=x)
                    {
                         
                        if(lst_copia.get(x).nodo.equals(lst_copia.get(i).nodo) && 
                                lst_copia.get(x).conexion.equals(lst_copia.get(i).conexion))
                        {
                                                    System.out.println("hola2");

                            conjunto_falloso=true;
                            break;
                        }
                    }
                    
                }
                if(conjunto_falloso) break;
            }
            
         
            if(!conjunto_falloso){
                lst_iteracciones.add(lst_copia);
                cont++;  
                System.out.println("siii");

            }else{
                 System.out.println("noooo");
            }
            System.out.println("contador= "+cont);
        }

        return lst_iteracciones;
    }


    public static List<ConexionInicial> copy_list_conexiones(List<ConexionInicial> list_conexionInicial)
    {
        List<ConexionInicial> lst_conex=new ArrayList<>();
        for(ConexionInicial ci : list_conexionInicial)
        {
            lst_conex.add(new ConexionInicial(ci.nodo,ci.conexion,ci.peso));
        }
        return lst_conex;
    }
    
    
    public static void imprimir(List<List<ConexionInicial>> lst_iteracciones)
    {
        for(int x=0;x<lst_iteracciones.size();x++)
        {
            for(int i=0;i<lst_iteracciones.get(x).size();i++)
            {
                System.out.println(lst_iteracciones.get(x).get(i).nodo+" - "+lst_iteracciones.get(x).get(i).conexion);
            }
            System.out.println("*************");
        }
    }        
            
    
    
    
    
    public static void barajar_array()
    {
        Integer[] numeros = { 1, 2, 3, 4, 5, 6, 7 };
	Collections.shuffle(Arrays.asList(numeros));
	System.out.println(Arrays.toString(numeros)); 
    }
    
    
    public static void verificar_barajar_array_prueba()
    {
        List<Integer[]> lst_integer=new ArrayList<>();
        Integer[] numeros = { 1, 2, 3, 4, 5, 6, 7 };
        int cont=10000;

        while(cont>1)
        {
            Collections.shuffle(Arrays.asList(numeros));
            cont--;
            lst_integer.add(Arrays.copyOf(numeros, numeros.length));
            //System.out.println(Arrays.toString(numeros));

        }
	System.out.println("SE CREARON "+lst_integer.size()+" BARAJAS");
        Boolean vector_repetido=false;
        for(int x=0;x<lst_integer.size();x++)
        {
            //System.out.println(Arrays.toString(lst_integer.get(x)));
            for(int i=0;i<lst_integer.size();i++)
            {
                if(x!=i){
                    vector_repetido=igual(lst_integer.get(x),lst_integer.get(i)); 
                    if(vector_repetido){
                        break;
                    }
                }
            }
            if(vector_repetido){
                break;
            }
        }
        
        if(vector_repetido)
        {
            System.out.println("EXISTE UN VECTOR O MAS REPETIDOS");
        }
        else
        {
            System.out.println("NO EXISTEN VECTORES REPETIDOS");
        }
        
	//System.out.println(Arrays.toString(numeros)); 
    }
    
    
    private static Boolean igual(Integer numeros1[],Integer numeros2[])
    {
        int cont=0;
        for(int x=0;x<numeros1.length;x++)
        {
            if(numeros1[x].equals(numeros2[x]))
            {
                cont++;
            }
        }
        if(cont==numeros1.length){
            System.out.println("los vectores son iguales: "+cont+" - "+numeros1.length);
            return true;
        }else{
            //System.out.println("los vectores no son iguales: "+cont+" - "+numeros1.length);
            return false;
        }
    }
    
    

            
            
    
    
    
    
    
//    private static void vector_numero_aleatorio_no_repetidos(int cant_nodos)
//    {
//        int num_aleatorio=ramdom.nextInt(0,cant_nodos);
//        int pos[]=new int[cant_nodos];
//        int cont=0;
//        if(cont!=0)
//        {
//            while(true)
//            {
//                
//                        Arrays.
//            } 
//        }
//        else
//        {
//            cont++;
//            pos[0]=num_aleatorio;
//        }
//    }  
//    
    
}

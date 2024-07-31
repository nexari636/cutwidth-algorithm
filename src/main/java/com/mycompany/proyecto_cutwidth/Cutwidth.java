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
 * @author JHON LETURNE
 */
public class Cutwidth 
{
    public static String SEPARADOR=",";
    public static String NOMBRE_ARCHIVO="datos2.txt";
    public static Integer CANTIDAD_NODOS;
    
    public static void main(String[] args) 
    {
        long startTime = System.currentTimeMillis();
        
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
        Nodo [][] matriz_nodos=crear_matriz_nodo(Nodos);
        Cutwidth(matriz_nodos,nodos_indentificador,lst_conexion_inicial);
        contar_particiones(Nodos);
        
        long endTime = System.currentTimeMillis();
        System.out.println("Tardo en ejecutarse= "+((double)(endTime-startTime)/1000));
        
    }
    
    private static void Cutwidth(Nodo[][] matriz_nodos,Map<String, 
            Integer> nodos_indentificador,List<ConexionInicial> lst_conexion_inicial)
    {
        for(int x=0;x<matriz_nodos.length;x++)
        {
            for(int i=0;i<lst_conexion_inicial.size();i++)
            {
                int n=0;
                if(!lst_conexion_inicial.get(i).nodo.equals(matriz_nodos[x][x].getValue())) continue;
                
                int rango_inicial=nodos_indentificador.get(lst_conexion_inicial.get(i).getNodo());
                int rango_final=nodos_indentificador.get(lst_conexion_inicial.get(i).getConexion());
                
                if(rango_inicial>rango_final)
                {
                    int aux=rango_inicial;
                    rango_inicial=rango_final;
                    rango_final=aux;
                }
                
                for(n=rango_inicial;n<=rango_final;n++)
                {
                    //pa=particion a ; pb=particion b
                    int pa=0,pb=0;
                    
                    if(n==rango_inicial)
                        pb=1;
                    else if(n==matriz_nodos[0].length-1 || n==rango_final)
                        pa=1;
                    else{
                        pa=1;
                        pb=1;
                    }
                    
                    insertar_particiones(matriz_nodos[x][n],pa,pb);
                }
                
                n=n-1;
                //se uine de a hasta d
                conexion_lado_lado(matriz_nodos,rango_inicial,rango_inicial,rango_final);
                //se une de d hasta a
                conexion_lado_lado(matriz_nodos,rango_final,rango_final,rango_inicial);

            }
        }
    }
    
    public static void insertar_particiones(Nodo nodo,int particion_a,int particion_b)
    {
        if(particion_a==1)
        {
            if(nodo.getParticionA()!=null){
                nodo.getParticionA().add(particion_a);
            }else
            {
                List<Integer> lstpa=new ArrayList<>();
                nodo.setParticionA(lstpa);
                nodo.getParticionA().add(particion_a); 
            }
        }
                    
        if(particion_b==1)
        {
           if(nodo.getParticionB()!=null){
                nodo.getParticionB().add(particion_b);
            }else
            {
                List<Integer> lstpb=new ArrayList<>();
                nodo.setParticionB(lstpb);
                nodo.getParticionB().add(particion_b); 
            } 
        }
    }
    
    public static void conexion_lado_lado(Nodo [][] matriz_nodos,int x,int i,int n)
    {
        List<Nodo> linked=matriz_nodos[x][i].getLinked();

        if(linked==null){
            linked=new ArrayList();
            linked.add(matriz_nodos[x][n]);
            
            matriz_nodos[x][i].setLinked(linked);
            
        }else
            linked.add(matriz_nodos[x][n]);

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
            return matriz_nodos;
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
       String ruta="\\src\\main\\java\\com\\mycompany\\proyecto_cutwidth\\"+NOMBRE_ARCHIVO;
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
    
    
    
    //otros recursos
    public static void contar_particiones(Nodo [] nodos)
    {
        int k=1;
        for(int x=0;x<nodos.length;x++)
        {
            int []cantidad_particiones_A_B=cantidad_buscar_conexiones(nodos,x,k);
            System.out.println("("+nodos[x].getValue()+")= "+cantidad_particiones_A_B[0]+"- ("+nodos[k].getValue()+") ="+cantidad_particiones_A_B[1]);
            if(k==nodos.length-1)
                break;
            k++;
        }
    }
    
    public static int[] cantidad_buscar_conexiones(Nodo [] nodos,Integer x,Integer k)
    {
        int a=0,b=0;
        if(nodos[x].getParticionB()!=null && nodos[k].getParticionA()!=null){
            a=nodos[x].getParticionB().size();
            b=nodos[k].getParticionA().size();
        }
        return new int[]{a,b};
    }

    
}

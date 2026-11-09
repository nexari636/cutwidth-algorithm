package com.mycompany.proyecto_cutwidth.implementacionRS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * RECOCIDO SIMULADO
 * @author JHON LETURNE
 */
public class Cutwidth
{
    public static String SEPARADOR_ARCHIVO=" ";
    public static String SEPARADOR=",";
    public static String NOMBRE_ARCHIVO="datos3.txt";
    public static Integer CANTIDAD_NODOS;
    
    //RECOCIDO SIMULADO
    public static Double TEMPERATURA_INICIAL=60000d;
    public static Double TEMPERATURA_FINAL=1d;
    public static Integer L=20;
    public static Double ALFA=0.99d;
    
    public static List<ConexionInicial> lst_solucionActual;    
    public static List<ConexionInicial> lst_solucionCandidata;
    public static Integer FO_SOLUCION_ACTUAL=0;
    public static Integer FO_SOLUCION_CANDIDATA=0;

    
    public static void main(String[] args) 
    {
        long startTime = System.currentTimeMillis();
        
        List<ConexionInicial> lst_conexion_inicial=Utilidades.leer_archivo(NOMBRE_ARCHIVO);
        
        InteraccionesData itd=new InteraccionesData(lst_conexion_inicial);
        itd.conexionesBaraja=itd.copy_list_conexiones(lst_conexion_inicial);
            
        String [] nod=Utilidades.nodos_grafo_orden;
        CANTIDAD_NODOS=nod.length;
        Map<String, Integer> nodos_indentificador=identificar_nodos(nod);
        
        //SOLUCION INICIAL
        Nodo[]Nodos=crear_nodos(nod);
        Cutwidth(nodos_indentificador,itd.conexionesBaraja,Nodos);
        FO_SOLUCION_ACTUAL=contar_particiones(Nodos);
        lst_solucionActual=itd.copy_list_conexiones(itd.conexionesBaraja);
        
        System.out.println("SOLUCION ACTUAL= "+FO_SOLUCION_ACTUAL);

        while(TEMPERATURA_INICIAL>=TEMPERATURA_FINAL)
        {
            for(int x=0;x<L;x++)
            {
                Nodos=crear_nodos(nod);
                itd.baraja_lista();
                Cutwidth(nodos_indentificador,itd.conexionesBaraja,Nodos);
                FO_SOLUCION_CANDIDATA=contar_particiones(Nodos);
                lst_solucionCandidata=itd.conexionesBaraja;
                int DELTA=FO_SOLUCION_CANDIDATA-FO_SOLUCION_ACTUAL;
                
                if(DELTA<0 || Utilidades.Aleatorio()<Utilidades.probabilidad(DELTA,TEMPERATURA_INICIAL))
                {
                    FO_SOLUCION_ACTUAL=FO_SOLUCION_CANDIDATA;
                    lst_solucionActual=itd.conexionesBaraja;
                    itd.lst_conexion_inicial=lst_solucionActual;
                }
                Nodos=null;
                System.out.println("SOLUCION ACTUAL= "+FO_SOLUCION_ACTUAL);
            }
            TEMPERATURA_INICIAL=TEMPERATURA_INICIAL*ALFA;
        }
        
        System.out.println("El menor numero de conexiones entre todos los maximos es: "+FO_SOLUCION_ACTUAL+"");

        long endTime = System.currentTimeMillis();
        System.out.println("Tardo en ejecutarse= "+((double)(endTime-startTime)/1000));
        
    }
    
    private static void Cutwidth(Map<String, 
            Integer> nodos_indentificador,List<ConexionInicial> lst_conexion_inicial,Nodo[] Nodos)
    {
        for(int x=0;x<Nodos.length;x++)
        {
            int[] rango_conexion_inicial=obtener_rango(lst_conexion_inicial,Nodos[x].getValue());
            if(rango_conexion_inicial[0]==-1) continue;
            for(int i=rango_conexion_inicial[0];i<=rango_conexion_inicial[1];i++)
            {
                int n=0;
                if(lst_conexion_inicial.get(i).nodo.equals(lst_conexion_inicial.get(i).conexion)) continue;
                
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
                    
                    Nodo nodo=Nodos[n];
                    if(n==rango_inicial)
                        pb=nodo.getPeso();
                    else if(n==Nodos.length-1 || n==rango_final)
                        pa=nodo.getPeso();
                    else{
                        pa=nodo.getPeso();
                        pb=nodo.getPeso();
                    }
                    insertar_particiones(Nodos[n],pa,pb);
                }
                
            }
        }
        //System.out.println("Fin cutwidth");
    }
    
    //OBTIENE RANGO POR BUSQUEDA BINARIA
    public static int[] obtener_rango(List<ConexionInicial> lst_conexion_inicial,String valor_buscar)
    {
        boolean es_numero=Utilidades.verificar_numero(valor_buscar);
        int pos_inicial=0;
        int pos_final=lst_conexion_inicial.size()-1;
        Boolean posI=false;
        
        if(es_numero)
        {
            int pos_lista=(pos_inicial+pos_final)/2;
            Boolean encontrado=false;
            
            while(pos_inicial<=pos_final)
            {
                pos_lista=(pos_inicial+pos_final)/2;

                if(Integer.parseInt(valor_buscar)>Integer.parseInt(lst_conexion_inicial.get(pos_lista).getNodo().trim()))
                    pos_inicial=pos_lista+1;          
                else if(Integer.parseInt(valor_buscar)<Integer.parseInt(lst_conexion_inicial.get(pos_lista).getNodo().trim()))
                    pos_final=pos_lista-1;
                else{ 
                   encontrado=true;
                   break;
                }
            }
            
            if(encontrado){
                while(true)
                {
                    if(!posI)
                    {
                        if((pos_lista-1)>-1)
                        {
                           if(lst_conexion_inicial.get(pos_lista-1).getNodo().trim().equals( valor_buscar))
                                pos_lista--;
                            else{
                                posI=true;
                                pos_inicial=pos_lista;
                            } 
                        }
                        else{
                            posI=true;
                            pos_inicial=pos_lista; 
                        }
                    }
                    else if((pos_lista+1)<=(lst_conexion_inicial.size()-1))
                    {
                        if(lst_conexion_inicial.get(pos_lista+1).getNodo().trim().equals(valor_buscar))
                            pos_lista++;
                        else
                        {
                            pos_final=pos_lista;
                            break;
                        }
                    }else{
                        
                        pos_final=pos_lista;
                        break;
                    }
                }
            }else
                return new int[]{-1,-1};
        }else
        {
            pos_inicial=-1;
            pos_final=-1;
            //letras
            for(int x=0;x<lst_conexion_inicial.size();x++)
            {
                if(!posI)
                {
                    if(lst_conexion_inicial.get(x).nodo.equals(valor_buscar))
                    {
                        posI=true;
                        pos_inicial=x;
                        pos_final=x;
                    }
                }
                else
                {
                    if(!lst_conexion_inicial.get(x).nodo.equals(valor_buscar))
                    {
                        pos_final=x-1;
                        break;
                    }
                    pos_final=x;
                }
            }        
        }
        return new int[]{pos_inicial,pos_final};
    }
    
    public static void insertar_particiones(Nodo nodo,int particion_a,int particion_b)
    {
        if(particion_a>=1)
            nodo.setCant_vertices_particionA(nodo.getCant_vertices_particionA()+particion_a);
                    
        if(particion_b>=1)
            nodo.setCant_vertices_particionB(nodo.getCant_vertices_particionB()+particion_b);
    }
    
    private static Nodo[] crear_nodos(String [] nodos)
    {
        try
        {
            Nodo[]Nodos=new Nodo[CANTIDAD_NODOS];
            for(int x=0;x<Nodos.length;x++)
                Nodos[x]=new Nodo(nodos[x],1);
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
    
    public static int contar_particiones(Nodo [] nodos)
    {
        int mayor=0;
        for (Nodo nodo : nodos) {
            int particion_b = nodo.getCant_vertices_particionB();
            if(particion_b>mayor)
                mayor=particion_b;
        }       
        return mayor;
    }
  
}
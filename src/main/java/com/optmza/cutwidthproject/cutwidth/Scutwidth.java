/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.optmza.cutwidthproject.cutwidth;

import com.optmza.cutwidthproject.cutwidthUT.ConexionInicial;
import com.optmza.cutwidthproject.cutwidthUT.InteraccionesData;
import com.optmza.cutwidthproject.cutwidthUT.Nodo;
import com.optmza.cutwidthproject.cutwidthUT.Utilidades;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USUARIO
 */

public class Scutwidth 
{
    List<ConexionInicial> lst_conexion_inicial;
    Integer CANTIDAD_NODOS;
    Integer POBLACION=25;
    Integer ITERACCIONES=5;
    public static int CONTADOR=0;
    
    //SOLO WEB
    public Nodo[]NodosActualSolucion;
    public List<ConexionInicial> lst_solucionActualGLOBAL;  
    public Nodo[]NodosActualSolucionGLOBAL;
    String [] nod;
    
    //RECOCIDO SIMULADO
    Double TEMPERATURA_INICIAL=100d;
    Double TEMPERATURA_FINAL=13d;
    Integer L=5;
    Double ALFA=0.5d;
    
    List<ConexionInicial> lst_solucionActual;    
    List<ConexionInicial> lst_solucionCandidata;
    Integer FO_SOLUCION_ACTUAL=0;
    Integer FO_SOLUCION_CANDIDATA=0;
    
    
    
    public Scutwidth(List<ConexionInicial> lst_conexion_inicial,Integer poblacion,Integer iteracciones) //RS O NN
    {
        this.lst_conexion_inicial=lst_conexion_inicial; 
        this.POBLACION=poblacion;
        this.ITERACCIONES=iteracciones;
        this.CONTADOR=0;
        resolver_normal();
    }
    
    
    public Scutwidth(List<ConexionInicial> lst_conexion_inicial,Double TEMPERATURA_INICIAL,
            Double TEMPERATURA_FINAL,Integer L,Double alfa)
    {
        this.lst_conexion_inicial=lst_conexion_inicial; 
        this.CONTADOR=0;
        this.TEMPERATURA_INICIAL=TEMPERATURA_INICIAL;
        this.TEMPERATURA_FINAL=TEMPERATURA_FINAL;
        this.L=L;
        this.ALFA=alfa;
        resolver_recocido_simulado();
    }

    public List<ConexionInicial> getLst_solucionActual() {
        return lst_solucionActual;
    }

    public List<ConexionInicial> getLst_solucionActualGLOBAL() {
        return lst_solucionActualGLOBAL;
    }

    public Nodo[] getNodosActualSolucionGLOBAL() {
        return NodosActualSolucionGLOBAL;
    }

    public String[] getNod() {
        return nod;
    }

   
    public void resolver_recocido_simulado()
    {
        InteraccionesData itd=new InteraccionesData(lst_conexion_inicial);
        itd.setConexionesBaraja(itd.copy_list_conexiones(lst_conexion_inicial));
        int [] menor_corte=null; 
        nod=Utilidades.nodos_grafo_orden;
        CANTIDAD_NODOS=nod.length;
        Map<String, Integer> nodos_indentificador=identificar_nodos(nod);
        
        //SOLUCION INICIAL
        Nodo[]Nodos=crear_nodos(nod);
        Cutwidth(nodos_indentificador,itd.getConexionesBaraja(),Nodos);
        FO_SOLUCION_ACTUAL=contar_particiones(Nodos);
        lst_solucionActual=itd.getConexionesBaraja();
        
        while(TEMPERATURA_INICIAL>=TEMPERATURA_FINAL)
        {
            for(int x=0;x<L;x++)
            {
                Nodos=crear_nodos(nod);
                itd.baraja_lista();
                Cutwidth(nodos_indentificador,itd.getConexionesBaraja(),Nodos);
                FO_SOLUCION_CANDIDATA=contar_particiones(Nodos);
                lst_solucionCandidata=itd.getConexionesBaraja();
                int DELTA=FO_SOLUCION_CANDIDATA-FO_SOLUCION_ACTUAL;
                
                if(DELTA<0 || Utilidades.Aleatorio()<Utilidades.probabilidad(DELTA,TEMPERATURA_INICIAL))
                {
                    FO_SOLUCION_ACTUAL=FO_SOLUCION_CANDIDATA;
                    lst_solucionActual=itd.getConexionesBaraja();
                    NodosActualSolucionGLOBAL=Nodos.clone(); //web
                }
               
                itd.setLst_conexion_inicial(lst_solucionActual);
                Nodos=null;
                System.out.println("SOLUCION ACTUAL= "+FO_SOLUCION_ACTUAL);
            }
            TEMPERATURA_INICIAL=TEMPERATURA_INICIAL*ALFA;
        }

    }
    
    public void resolver_normal()
    {
        int menor=999999999;        
        int menorGlobal=999999999;
        int num=0;
        
        InteraccionesData itd=new InteraccionesData(lst_conexion_inicial);
        itd.setConexionesBaraja(itd.copy_list_conexiones(lst_conexion_inicial));
        int [] menor_corte=null; 
        nod=Utilidades.nodos_grafo_orden;
        CANTIDAD_NODOS=nod.length;
        Map<String, Integer> nodos_indentificador=identificar_nodos(nod);
        
        for(int i=1;i<=ITERACCIONES;i++)
        {     
            System.out.println("INTERACCION= "+i);
            menor_corte=new int[POBLACION+1];
            CONTADOR=0;
            for(int x=0;x<=POBLACION;x++)
            {
                Nodo[]Nodos=crear_nodos(nod);
                Cutwidth(nodos_indentificador,itd.getConexionesBaraja(),Nodos);
                contar_particiones(Nodos,menor_corte);
                
                 if(num<menor){
                    menor=num;
                    lst_solucionActual=itd.copy_list_conexiones(itd.getConexionesBaraja());
                    NodosActualSolucion=Nodos.clone();
                }
                
                
                itd.getConexionesBaraja().clear();
                itd.setConexionesBaraja(new ArrayList<>());
                Nodos=null;
                itd.baraja_lista();
            }
            
            if(menor<menorGlobal)
            {
                menorGlobal=menor;
                lst_solucionActualGLOBAL=itd.copy_list_conexiones(lst_solucionActual);
                NodosActualSolucionGLOBAL=NodosActualSolucion.clone();             
            }
            num=0;
            menor=999999999;
            lst_solucionActual.clear();
            NodosActualSolucion=null;    
        }
    }
    
    
    private void Cutwidth(Map<String, 
            Integer> nodos_indentificador,List<ConexionInicial> lst_conexion_inicial,Nodo[] Nodos)
    {
        for(int x=0;x<Nodos.length;x++)
        {
            int[] rango_conexion_inicial=obtener_rango(lst_conexion_inicial,Nodos[x].getValue());
            if(rango_conexion_inicial[0]==-1) continue;
            for(int i=rango_conexion_inicial[0];i<=rango_conexion_inicial[1];i++)
            {
                int n=0;
                if(lst_conexion_inicial.get(i).getNodo().equals(lst_conexion_inicial.get(i).getConexion())) continue;
                
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
    public int[] obtener_rango(List<ConexionInicial> lst_conexion_inicial,String valor_buscar)
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
            //letras
            for(int x=0;x<lst_conexion_inicial.size();x++)
            {
                if(!posI)
                {
                    if(lst_conexion_inicial.get(x).getNodo().equals(valor_buscar))
                    {
                        posI=true;
                        pos_inicial=x;
                        pos_final=x;
                    }
                }
                else
                {
                    if(!lst_conexion_inicial.get(x).getNodo().equals(valor_buscar))
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
    
    public void insertar_particiones(Nodo nodo,int particion_a,int particion_b)
    {
        if(particion_a>=1)
            nodo.setCant_vertices_particionA(nodo.getCant_vertices_particionA()+particion_a);
                    
        if(particion_b>=1)
            nodo.setCant_vertices_particionB(nodo.getCant_vertices_particionB()+particion_b);
    }
    
    private Nodo[] crear_nodos(String [] nodos)
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
    
    private Map<String, Integer> identificar_nodos(String [] nodos)
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
    
    public int contar_particiones(Nodo [] nodos)
    {
        int mayor=0;
        for (Nodo nodo : nodos) {
            int particion_b = nodo.getCant_vertices_particionB();
            if(particion_b>mayor)
                mayor=particion_b;
        }       
        return mayor;
    }
    
    public static void contar_particiones(Nodo [] nodos,int[]menor_corte)
    {
        int mayor=0;
        for (Nodo nodo : nodos) {
            int particion_b = nodo.getCant_vertices_particionB();
            if(particion_b>mayor)
                mayor=particion_b;
        }       
        menor_corte[CONTADOR]=mayor;
        CONTADOR++;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proyecto_cutwidth;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author USUARIO
 */




public class Proyecto_cutwidth {

    public static void main(String[] args) 
    {
        
        //a=0, b=1, c=2, d=3
        
        //rango inicial=2, rango final=1
        
        List<ConexionInicial> lst_con=new ArrayList<>();
//        lst_con.add(new ConexionInicial("a","b"));        
//        lst_con.add(new ConexionInicial("a","d"));
//        lst_con.add(new ConexionInicial("c","b"));
//        lst_con.add(new ConexionInicial("d","c"));
        
        
        
        lst_con.add(new ConexionInicial("1","2",1));
        lst_con.add(new ConexionInicial("2","4",1));
        lst_con.add(new ConexionInicial("2","5",1));
        lst_con.add(new ConexionInicial("3","7",1));
        lst_con.add(new ConexionInicial("4","6",1));
        lst_con.add(new ConexionInicial("5","6",1));
        lst_con.add(new ConexionInicial("7","8",1));
        lst_con.add(new ConexionInicial("7","9",1));
        lst_con.add(new ConexionInicial("8","9",1));
        lst_con.add(new ConexionInicial("9","3",1));
        lst_con.add(new ConexionInicial("95","5",1));

        
        String nodos="";
        
        for(int x=0;x<lst_con.size();x++)
        {
            if(!nodos.contains(lst_con.get(x).nodo))
                nodos+=lst_con.get(x).nodo+",";
            if(!nodos.contains(lst_con.get(x).conexion))
                nodos+=lst_con.get(x).conexion+","; 
        }
        
        nodos=nodos.substring(0, nodos.length()-1);
        System.out.println("Nodos= "+nodos);
        String [] nod=nodos.split(",");
        Arrays.sort(nod);
        
        //asignar numeros a las letras o nodos
        Map<String, Integer> map=new HashMap();
        for(int x=0;x<nod.length;x++){
            System.out.println("Nodo= "+nod[x]+" Posicion= "+x);
            map.put(nod[x], x);
        }
        
        
        int cantidad_nodos=10;
        Nodo [][] grafo=new Nodo[cantidad_nodos][cantidad_nodos];
       
        
        //inicializar matriz de conexion
        
        //Nodo nodos_vec [] ={new Nodo("a"),new Nodo("b"),new Nodo("c"),new Nodo("d")};
        Nodo nodos_vec[] = {
            new Nodo("1"),
            new Nodo("2"),
            new Nodo("3"),
            new Nodo("4"),
            new Nodo("5"),
            new Nodo("6"),
            new Nodo("7"),
            new Nodo("8"),
            new Nodo("9"),
            new Nodo("95")
        };
        for(int x=0;x<grafo.length;x++)
        {
            for(int i=0;i<grafo[0].length;i++)
            {
                grafo[x][i]=nodos_vec[i];
            }   
        }
            
        //************************conexiones del grafo****************************
        
        //FILAS
        for(int x=0;x<grafo.length;x++)
        {
            //System.out.println("holi= "+x);
            List<Particiones> lst_particiones=null;
                //&& lst_con.get(j).nodo.equals(grafo[x][x].getValue())
            for(int j=0;j<lst_con.size() ;j++)
            {
                //System.out.println("holi entree= "+grafo[x][x].getValue()+" x= "+x);
                if(!lst_con.get(j).nodo.equals(grafo[x][x].getValue())) continue;
                
                int n=0;
                
                //conexion a--> d
                ConexionInicial ci=lst_con.get(j);
                int rango_inicial=map.get(ci.nodo);
                int rango_final=map.get(ci.conexion);

                
                if(rango_inicial>rango_final)
                {
                    int aux=rango_inicial;
                    rango_inicial=rango_final;
                    rango_final=aux;
                }

                for(n=rango_inicial;n<=rango_final;n++)
                {
                    //System.out.println("n= "+n);
                    int pa=0,pb=0;
                    
                    Particiones particiones;
                    if(n==rango_inicial){
                        particiones=new Particiones(0,1);
                        System.out.println("ENTRE AQUI 1");
                        pb=1;
                    }
                    else if(n==grafo[0].length-1 || n==rango_final){
                        particiones=new Particiones(1,0);
                        System.out.println("ENTRE AQUI 2");
                        pa=1;
                    }
                    else
                    {
                        particiones=new Particiones(1,1);
                        System.out.println("ENTRE AQUI 3");
                        pa=1;
                        pb=1;
                    }
                     //System.out.println("se va a imprimir el grafooooo= "+n);

                    if(grafo[x][n].getLst_particiones()!=null)
                        grafo[x][n].getLst_particiones().add(particiones);
                    else
                    {
                        lst_particiones=new ArrayList<>();
                        grafo[x][n].setLst_particiones(lst_particiones);
                        grafo[x][n].getLst_particiones().add(particiones);
                    }
                    
                    if(pa!=0 || (pa==1 && pb==1)){
                        if(grafo[x][n].getParticionA()!=null){
                            grafo[x][n].getParticionA().add(pa);
                        }else
                        {
                            List<Integer> lstpa=new ArrayList<>();
                            grafo[x][n].setParticionA(lstpa);
                            grafo[x][n].getParticionA().add(pa); 
                        }
                    }
                    
                    if(pb!=0 || (pa==1 && pb==1)){
                        if(grafo[x][n].getParticionB()!=null){
                            grafo[x][n].getParticionB().add(pb);
                        }else
                        {
                            List<Integer> lstpb=new ArrayList<>();
                            grafo[x][n].setParticionB(lstpb);
                            grafo[x][n].getParticionB().add(pb); 
                        }
                    }
                    
                    
                }
                if (ci.getNodo().equals("c") && ci.getConexion().equals("b") && x==2)   
                    System.out.println("n= "+n+" x="+x);
                //Conexiones de lado y lado
                //variable n-1 porque al analizar el for le sumara uno por delante
                 //por logica al finalizar el bucle n va a tener el ultimo valor de recorrido
                n=n-1;

                //se uine de a hasta d
                conexion_lado_lado(grafo,rango_inicial,rango_inicial,rango_final);
                //se une de d hasta a
                conexion_lado_lado(grafo,rango_final,rango_final,rango_inicial);
            }
        }
        
        System.out.println("*********************************************************");
//        for(int x=0;x<grafo.length;x++)
//        {
//            for(int i=0;i<grafo[0].length;i++)
//            {
//                List<Particiones> lst_particiones=grafo[x][i].getLst_particiones();
//                if(lst_particiones!=null)
//                {
//                    
//                    for(Particiones part : lst_particiones)
//                    {
//                        System.out.println("************************************");
//                        System.out.println("FILA= "+x);
//                        System.out.println("TAMAÑO DE LISTA= "+grafo[x][i].getLst_particiones().size());
//                        System.out.println("GRAFO= "+grafo[x][i].getValue());
//                        System.out.println("Particion 1="+ part.getPartition1());                    
//                        System.out.println("Particion 2="+ part.getPartition2());
//                        System.out.println("************************************");
//                    }
//                    
//                }
//                
//            }   
//        }
        
        contar_particiones(nodos_vec,lst_con);
       
 
        //System.out.println("Hello World! ");        

    }
    
    
    //x=0, i=0, n=3
    //x=2, i=2, n=3-1=2 
    public static void conexion_lado_lado(Nodo [][] grafo,int x,int i,int n)
    {
        List<Nodo> linked=grafo[x][i].getLinked();

        if(linked==null){
            linked=new ArrayList();
            linked.add(grafo[x][n]);
            
            grafo[x][i].setLinked(linked);
            
        }else
            linked.add(grafo[x][n]);

    }
    
    
    public static void contar_particiones(Nodo [] vec,List<ConexionInicial> ci)
    {
//        for(int x=0;x<vec.length;x++)
//        {
//           System.out.println("Nodo= "+vec[x].getValue()+" Con tamaño de lista= "+vec[x].getLinked().size());
//        }
        System.out.println("********** contar particiones ***************"+vec.length);
        int k=1;
        for(int x=0;x<vec.length;x++)
        {
            int []ums=cant_buscar_conexiones(vec,x,k);

           // if(vec[x].getLst_particiones().size()==vec[k].getLst_particiones().size()){
           System.out.println("("+vec[x].getValue()+")= "+ums[0]+"- ("+vec[k].getValue()+") ="+ums[1]);
           System.out.println("||||||||||||||||||||||||||||");
           // }
            if(k==9)
                break;
            k++;
        }

        
    }
    
    
    public static int[] cant_buscar_conexiones(Nodo [] vec,Integer x,Integer k)
    {
        int a=0,b=0;
        if(vec[x].getParticionB()!=null && vec[k].getParticionA()!=null){
            System.out.println("holaaaaa");
            a=vec[x].getParticionB().size();
            b=vec[k].getParticionA().size();
        }
        return new int[]{a,b};
    }
    
    

//    public static Boolean analizar_conexion(List<Nodo> nodosLinked,String a)
//    {
//        if(nodosLinked!=null)
//        {
//            for(int i=0;i<nodosLinked.size();i++)
//            {
//                if(nodosLinked.get(i).getValue().equals(a))
//                {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    
    
    
    
    
    
}




//
// List<Nodo> linked=grafo[x][x].getLinked();
//
//                     if(linked==null){
//                         linked=new ArrayList();
//                         linked.add(grafo[x][n]);
//                     }else
//                         linked.add(grafo[x][n]);
//
//                     //se une a hasta d
//                    Nodo n1= grafo[x][x];
//                    n1.setLinked(linked);
//                    grafo[x][x]=n1;
//
//
//                     //se une de d hasta a
//                     List<Nodo> linked_dos=grafo[x][n].getLinked();
//                     if(linked_dos==null){
//                         linked_dos=new ArrayList();
//                         linked_dos.add(grafo[x][x]);
//                     }else
//                         linked_dos.add(grafo[x][x]);
//
//                    Nodo n2= grafo[x][n];
//                    n2.setLinked(linked_dos);
//                    grafo[x][n]=n2;



//        //FILAS
//        for(int x=0;x<grafo.length;x++)
//        {
//            //COLUMNAS
//            for(int i=0;i<grafo[0].length;i++)
//            {
//                List<Particiones> lst_particiones=null;
//                
//                for(int j=0;j<lst_con.size() && lst_con.get(j).nodo.equals(grafo[x][i].getValue()) ;j++)
//                {
//                    //conexion a--> d
//                    ConexionInicial ci=lst_con.get(j);
//                    int rango_inicial=map.get(ci.nodo);
//                    int rango_final=map.get(ci.conexion);
//                   
//                    int n=0;
//                    
//                    //si es a no es d y si es d no es a
//                    String bsc="";
//                    if(ci.getNodo()==grafo[x][i].getValue()){
//                        bsc=ci.conexion;
//                    }else{
//                        bsc=ci.nodo;
//                    }
//
//                    Boolean c1=analizar_conexion(grafo[x][i].getLinked(),bsc); 
//                    
//                    if(!c1)
//                    {
//                       for(n=rango_inicial;n<=rango_final;n++)
//                       {
//                           System.out.println("n= "+n);
//                           lst_particiones=new ArrayList<>();
//
//                           if(n==rango_inicial){
//                               lst_particiones.add(new Particiones(0,1));
//                                System.out.println("ENTRE AQUI 1");
//
//                           }
//                           else if(n==grafo[0].length || n==rango_final){
//                               lst_particiones.add(new Particiones(1,0));
//                                System.out.println("ENTRE AQUI 2");
//
//                           }
//                           else{
//                               lst_particiones.add(new Particiones(1,1));
//                                System.out.println("ENTRE AQUI 3");
//
//                           }
//                           grafo[x][n].setLst_particiones(lst_particiones);
//                       }
//                    
//                        n=n-1;
//                        //por logica al finalizar el bucle n va a tener el ultimo valor de recorrido
//                        List<Nodo> linked=grafo[x][i].getLinked();
//                        
//                         if(linked==null){
//                             linked=new ArrayList();
//                             linked.add(grafo[x][n]);
//                         }else
//                             linked.add(grafo[x][n]);
//
//                         //se une a hasta d
//                        Nodo n1= grafo[x][i];
//                        n1.setLinked(linked);
//                        grafo[x][i]=n1;
//                                
//
//                         //se une de d hasta a
//                         List<Nodo> linked_dos=grafo[x][n].getLinked();
//                         if(linked_dos==null){
//                             linked_dos=new ArrayList();
//                             linked_dos.add(grafo[x][i]);
//                         }else
//                             linked_dos.add(grafo[x][i]);
//
//                        Nodo n2= grafo[x][n];
//                        n2.setLinked(linked_dos);
//                        grafo[x][n]=n2;
//                         
//                    
//                    }else
//                    {
//                        System.out.println("no entro porque ya se encuentra vinculado:(");
//                    }
//                   
//                }
//            }
//        }

//
//        for(int x=0;x<grafo.length;x++)
//        {
//            List<Particiones> lst_particiones=null;
//                //&& lst_con.get(j).nodo.equals(grafo[x][x].getValue())
//            for(int j=0;j<lst_con.size() ;j++)
//            {
//                if(!lst_con.get(j).nodo.equals(grafo[x][x].getValue())) break;
//                
//                int n=0;
//                
//                //conexion a--> d
//                ConexionInicial ci=lst_con.get(j);
//                int rango_inicial=map.get(ci.nodo);
//                int rango_final=map.get(ci.conexion);
//
//                
//                //si es a no es d y si es d no es a
////                String bsc="";
////                if(ci.getNodo().equals(grafo[x][x].getValue())){
////                    bsc=ci.conexion;
////                }else{
////                    bsc=ci.nodo;
////                }
//
////                Boolean c1=analizar_conexion(grafo[x][x].getLinked(),bsc); 
////                System.out.print("GRAFO CONECTADO= "+c1+" NODO= "+ ci.nodo+" CONECCION= "+ci.conexion);
////                
////                if(!c1)
////                {
//                   for(n=rango_inicial;n<=rango_final;n++)
//                   {
//                       System.out.println("n= "+n);
//                       
//                       Particiones particiones;
//                       if(n==rango_inicial){
//                            particiones=new Particiones(0,1);
//                            System.out.println("ENTRE AQUI 1");
//                       }
//                       else if(n==grafo[0].length || n==rango_final){
//                            particiones=new Particiones(1,0);
//                            System.out.println("ENTRE AQUI 2");
//                       }
//                       else
//                       {
//                            particiones=new Particiones(1,1);
//                            System.out.println("ENTRE AQUI 3");
//                       }
//                        System.out.println("se va a imprimir el grafooooo= "+n);
//                        
//                        if(grafo[x][n].getLst_particiones()!=null){
//                            grafo[x][n].getLst_particiones().add(particiones);
//                        }else
//                        {
//                            lst_particiones=new ArrayList<>();
//                            grafo[x][n].setLst_particiones(lst_particiones);
//                            grafo[x][n].getLst_particiones().add(particiones);
//                        }
//                   }
//                   
//                   System.out.println("n= "+n);
//                   //Conexiones de lado y lado
//                   //variable n-1 porque al analizar el for le sumara uno por delante
//                    n=n-1;
//                    //por logica al finalizar el bucle n va a tener el ultimo valor de recorrido
//                    
//                    //se uine de a hasta d
//                    conexion_lado_lado(grafo,x,x,n);
//                    //se une de d hasta a
//                    conexion_lado_lado(grafo,x,n,x);
//
////                }
////                else
////                {
////                    System.out.println("no entro porque ya se encuentra vinculado:(");
////                }
//            }
//            
//        }
//


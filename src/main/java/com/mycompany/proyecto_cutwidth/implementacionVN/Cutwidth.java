package com.mycompany.proyecto_cutwidth.implementacionVN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NORMAL
 *
 * @author JHON LETURNE
 */
public class Cutwidth {

    public static String SEPARADOR_ARCHIVO = " ";
    public static String SEPARADOR = ",";
    public static String NOMBRE_ARCHIVO = "will199.mtx.txt";
    public static Integer CANTIDAD_NODOS;
    public static Integer POBLACION = 25;
    public static Integer ITERACCIONES = 5;
    public static int CONTADOR = 0;

    public static List<ConexionInicial> lst_solucionActual;
    public static Nodo[] NodosActualSolucion;

    public static List<ConexionInicial> lst_solucionActualGLOBAL;
    public static Nodo[] NodosActualSolucionGLOBAL;

    public static void main(String[] args) 
    {
        long startTime = System.currentTimeMillis();

        int menor = 0;
        int menorGlobal = 0;
        int num = 0;

        List<ConexionInicial> lst_conexion_inicial = Utilidades.leer_archivo(NOMBRE_ARCHIVO);

        InteraccionesData itd = new InteraccionesData(lst_conexion_inicial);
        itd.conexionesBaraja = itd.copy_list_conexiones(lst_conexion_inicial);

        int[] menor_corte = null;

        String[] nod = Utilidades.nodos_grafo_orden;
        CANTIDAD_NODOS = nod.length;
        Map<String, Integer> nodos_indentificador = identificar_nodos(nod);

        for (int i = 1; i <= ITERACCIONES; i++) {
            System.out.println("INTERACCION= " + i);
            menor_corte = new int[POBLACION + 1];
            CONTADOR = 0;
            for (int x = 0; x <= POBLACION; x++) {
                Nodo[] Nodos = crear_nodos(nod);
                Cutwidth(nodos_indentificador, itd.conexionesBaraja, Nodos);
                num = contar_particiones(Nodos, menor_corte);

                
                if (num < menor || menor==0) {
                    menor = num;
                    lst_solucionActual = itd.copy_list_conexiones(itd.conexionesBaraja);
                    NodosActualSolucion = Nodos.clone();
                }

                itd.conexionesBaraja.clear();
                itd.conexionesBaraja = new ArrayList<>();
                itd.baraja_lista();
                System.out.println("SOLUCION GRAFO= " + x + " con valor de corte= " + num +" -- "+menor);

                Nodos = null;

            }
            if (menor < menorGlobal || i==1) {
                menorGlobal = menor;
                lst_solucionActualGLOBAL = itd.copy_list_conexiones(lst_solucionActual);
                NodosActualSolucionGLOBAL = NodosActualSolucion.clone();
            }
            num = 0;
            menor = 0;
            lst_solucionActual.clear();
            NodosActualSolucion = null;

            System.out.println("El menor numero de conexiones entre todos los maximos es: " + Utilidades.MIN(menor_corte) + "\n\n");
        }

        for (ConexionInicial ci : lst_solucionActualGLOBAL) 
            System.out.println(ci.nodo + " - " + ci.conexion);
        
        System.out.println("El cutwidth mejor encontrado es de valor: " + menorGlobal);

        long endTime = System.currentTimeMillis();
        System.out.println("Tardo en ejecutarse= " + ((double) (endTime - startTime) / 1000));
    }

    private static void Cutwidth(Map<String, Integer> nodos_indentificador, List<ConexionInicial> lst_conexion_inicial, Nodo[] Nodos) {
        for (int x = 0; x < Nodos.length; x++) 
        {
            int[] rango_conexion_inicial = obtener_rango(lst_conexion_inicial, Nodos[x].getValue());
            if (rango_conexion_inicial[0] == -1) continue;
            
            for (int i = rango_conexion_inicial[0]; i <= rango_conexion_inicial[1]; i++) {
                int n = 0;
                if (lst_conexion_inicial.get(i).nodo.equals(lst_conexion_inicial.get(i).conexion)) {
                    continue;
                }

                int rango_inicial = nodos_indentificador.get(lst_conexion_inicial.get(i).getNodo());
                int rango_final = nodos_indentificador.get(lst_conexion_inicial.get(i).getConexion());

                if (rango_inicial > rango_final) {
                    int aux = rango_inicial;
                    rango_inicial = rango_final;
                    rango_final = aux;
                }

                for (n = rango_inicial; n <= rango_final; n++) {
                    //pa=particion a ; pb=particion b
                    int pa = 0, pb = 0;

                    Nodo nodo = Nodos[n];
                    if (n == rango_inicial) {
                        pb = nodo.getPeso();
                    } else if (n == Nodos.length - 1 || n == rango_final) {
                        pa = nodo.getPeso();
                    } else {
                        pa = nodo.getPeso();
                        pb = nodo.getPeso();
                    }
                    insertar_particiones(Nodos[n], pa, pb);
                }

            }
        }
        //System.out.println("Fin cutwidth");
    }

    //OBTIENE RANGO POR BUSQUEDA BINARIA (PARA CONJUNTOS DE NUMEROS) y BUSQUEDA NORMAL (SOLO PARA CONJUNTOS DE LETRAS)
    public static int[] obtener_rango(List<ConexionInicial> lst_conexion_inicial, String valor_buscar) {
        boolean es_numero = Utilidades.verificar_numero(valor_buscar);
        int pos_inicial = 0;
        int pos_final = lst_conexion_inicial.size() - 1;
        Boolean posI = false;
        if (es_numero) {
            int pos_lista = (pos_inicial + pos_final) / 2;
            Boolean encontrado = false;

            while (pos_inicial <= pos_final) {
                pos_lista = (pos_inicial + pos_final) / 2;

                if (Integer.parseInt(valor_buscar) > Integer.parseInt(lst_conexion_inicial.get(pos_lista).getNodo().trim())) {
                    pos_inicial = pos_lista + 1;
                } else if (Integer.parseInt(valor_buscar) < Integer.parseInt(lst_conexion_inicial.get(pos_lista).getNodo().trim())) {
                    pos_final = pos_lista - 1;
                } else {
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                while (true) {
                    if (!posI) {
                        if ((pos_lista - 1) > -1) {
                            if (lst_conexion_inicial.get(pos_lista - 1).getNodo().trim().equals(valor_buscar)) {
                                pos_lista--;
                            } else {
                                posI = true;
                                pos_inicial = pos_lista;
                            }
                        } else {
                            posI = true;
                            pos_inicial = pos_lista;
                        }
                    } else if ((pos_lista + 1) <= (lst_conexion_inicial.size() - 1)) {
                        if (lst_conexion_inicial.get(pos_lista + 1).getNodo().trim().equals(valor_buscar)) {
                            pos_lista++;
                        } else {
                            pos_final = pos_lista;
                            break;
                        }
                    } else {

                        pos_final = pos_lista;
                        break;
                    }
                }
            } else {
                return new int[]{-1, -1};
            }
        } else {
            //letras
            for (int x = 0; x < lst_conexion_inicial.size(); x++) {
                if (!posI) {
                    if (lst_conexion_inicial.get(x).nodo.equals(valor_buscar)) {
                        posI = true;
                        pos_inicial = x;
                        pos_final = x;
                    }
                } else {
                    if (!lst_conexion_inicial.get(x).nodo.equals(valor_buscar)) {
                        pos_final = x - 1;
                        break;
                    }
                    pos_final = x;
                }
            }
        }
        return new int[]{pos_inicial, pos_final};
    }

    public static void insertar_particiones(Nodo nodo, int particion_a, int particion_b) {
        if (particion_a >= 1) {
            nodo.setCant_vertices_particionA(nodo.getCant_vertices_particionA() + particion_a);
        }

        if (particion_b >= 1) {
            nodo.setCant_vertices_particionB(nodo.getCant_vertices_particionB() + particion_b);
        }
    }

    private static Nodo[] crear_nodos(String[] nodos) {
        try {
            Nodo[] Nodos = new Nodo[CANTIDAD_NODOS];
            for (int x = 0; x < Nodos.length; x++) {
                Nodos[x] = new Nodo(nodos[x], 1);
            }
            return Nodos;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static Map<String, Integer> identificar_nodos(String[] nodos) {
        try {
            Map<String, Integer> nodos_indentificador = new HashMap();
            for (int x = 0; x < nodos.length; x++) {
                nodos_indentificador.put(nodos[x], x);
            }
            return nodos_indentificador;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static int contar_particiones(Nodo[] nodos, int[] menor_corte) {
        int mayor = 0;
        for (Nodo nodo : nodos) {
            int particion_b = nodo.getCant_vertices_particionB();
            if (particion_b > mayor) {
                mayor = particion_b;
            }
        }
        menor_corte[CONTADOR] = mayor;
        CONTADOR++;
        return mayor;
    }

}

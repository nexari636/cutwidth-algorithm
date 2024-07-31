/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;
import java.util.List;
import lombok.*;
/**
 *
 * @author USUARIO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nodo 
{
    private String value;
    private List<Nodo> linked;
   // private List<Particiones> lst_particiones;
    
    private List<Integer> particionA;
    private List<Integer> particionB;
    
    private String peso;
    
    public Nodo(String nodo_name_value)
    {
        this.value=nodo_name_value;
    }
    
    
}


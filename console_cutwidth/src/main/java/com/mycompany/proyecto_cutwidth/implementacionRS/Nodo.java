/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth.implementacionRS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int cant_vertices_particionA;    
    private int cant_vertices_particionB;
    private int peso;
    
    public Nodo(String nodo_name_value,int peso)
    {
        this.value=nodo_name_value;
        this.peso=peso;
        this.cant_vertices_particionA=0;
        this.cant_vertices_particionB=0;
    }
   
}

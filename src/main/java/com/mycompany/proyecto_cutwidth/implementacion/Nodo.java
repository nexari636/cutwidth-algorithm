/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth.implementacion;

import java.util.List;
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
    private List<Nodo> linked;
    private List<Integer> particionA;
    private List<Integer> particionB;
    private int peso;
    
    public Nodo(String nodo_name_value,int peso)
    {
        this.value=nodo_name_value;
        this.peso=peso;
    }
   
}

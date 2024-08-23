/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_cutwidth.implementacionRS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author USUARIO
 */
@Getter
@Setter
@NoArgsConstructor
class ConexionInicial
{
    String nodo;
    String conexion;
    int peso;

    public ConexionInicial(String nodo,String conexion,int peso)
    {
        this.nodo=nodo;
        this.conexion=conexion;
        this.peso=peso;
    }
    
    public String getNodoLetra(){
        return this.nodo;
    }
    
    public Integer getNodoNumero(){
        return Integer.parseInt(nodo);
    }

}

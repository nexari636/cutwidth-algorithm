/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.optmza.cutwidthproject.cutwidthUT;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
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
public class ConexionInicial
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
    
    @JsonIgnore
    public Integer getNodoNumero(){
        return Integer.parseInt(nodo);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash = 17  + Objects.hashCode(this.nodo);
        hash = 17  + Objects.hashCode(this.conexion)+hash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConexionInicial other = (ConexionInicial) obj;
        
        if(Objects.equals(this.nodo, other.conexion) && Objects.equals(this.conexion, other.nodo))
            return true;
  
        return Objects.equals(this.nodo, other.nodo) && Objects.equals(this.conexion, other.conexion);
    }
    
    

}

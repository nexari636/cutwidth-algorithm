/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto_cutwidth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ordenamiento_pruebados {

    /**
     * @param args the command line arguments
     */
    
    static class persona{
        String id;
        public persona(String id){
            this.id=id;
        }

        public Integer getId() {
            return Integer.parseInt(id);
        }

        public void setId(String id) {
            this.id = id;
        }
        
        
        
    }
    
    public static void main(String[] args) {
        String vec[]=new String[3];
        vec[0]="1,99";
        vec[1]="10,1";
        vec[2]="8,2";
        Arrays.sort(vec);
        System.out.println(vec[0]);        
        System.out.println(vec[1]);
        System.out.println(vec[2]);
        
        List<persona> lst_persona=new ArrayList<persona>();
        lst_persona.add(new persona("3"));        
        lst_persona.add(new persona("6"));
        lst_persona.add(new persona("1"));
        lst_persona.add(new persona("10"));


        lst_persona.sort(Comparator.comparing(persona::getId));

        
        for(persona person: lst_persona){
            System.out.println(person.getId());
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_compi;

import java.util.Stack;

/**
 *
 * @author emmanuel
 */
public class Tokenizer {
    AFD automata;
    
    public Tokenizer(AFD au){
        automata = au;
    }
    
    public boolean analizador(String cadena){
        int i = 0;
        int estado_actual;
        boolean finalizado = false; ///variable para salir del ciclo
        Stack<Integer> pila = new Stack();
        
        while(!finalizado){
            estado_actual = 0;
            pila.push(-10); //-10 representará el fondo de la pila
            
            while(i<cadena.length() && automata.transicion(estado_actual, cadena.charAt(i)) >= 0){
                pila.push(estado_actual);
                estado_actual = automata.transicion(estado_actual, cadena.charAt(i));
                i++;
            }//fin while
            
            //backtrack
            while(!automata.F.contains(estado_actual)){//mientras estado_actual no sea final
                estado_actual = pila.pop();
                i--;
                if(estado_actual==-10){
                    finalizado = true;
                    return false;
                }
            }//fin while
            
            if(i>=cadena.length()){
                finalizado = true;
                return true;
            }
            
            pila.clear();
        }//Fin del loop
        
        return true;
    }
}

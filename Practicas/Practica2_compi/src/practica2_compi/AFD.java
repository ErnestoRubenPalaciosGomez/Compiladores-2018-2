/*
 * Esta clase representa un autómata finito determinista.
 */
package practica2_compi;

import java.util.LinkedList;

/**
 *
 * @author emmanuel
 */
public class AFD {
    LinkedList<Character> alfabeto; //Alfabeto de entrada del autómata.
    int q0; //Estado inicial
    LinkedList<Integer> F; //Estados finales
    
    LinkedList<Par_cn> transiciones; //Representación de una "tabla" de transiciones
    /**
     * Constructor
     * @param inicial: estado inicial
     */
    public AFD(int inicial){
        alfabeto = new LinkedList();
        q0 = inicial;
    }
    
    /**
     * Recibe un símbolo y devuelve la lista de estados asociados a este.
     * @param simb
     * @return 
     */
    public LinkedList getEstadosTran(char simb){
        LinkedList<Integer> retorno = new LinkedList();
        
        for(int i=0;i<transiciones.size();i++){
            if(transiciones.get(i).simbolo==simb){
                retorno.addAll(transiciones.get(i).estados);
            }
        }
        
        return retorno;
    }
    
    /**
     * Función de transición
     * @param q estado
     * @param x símbolo
     * @return estado resultante
     */
    public int transicion(int q, char x){
        LinkedList<Integer> lista;
        lista = getEstadosTran(x);
        int valor = -2;
        if(lista.size()>0){
            valor = lista.get(q); //Le asignamos a la variable valor el estado resultante.
        }else{
            System.out.println("Caracter fuera del alfabeto :'v");
        }
        
        return valor;
    }
}
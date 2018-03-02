/*
 * Clase que representa un par (caracter, lista de números)
 * Esta será una fila de la tabla de transiciones.
 */
package practica2_compi;

import java.util.LinkedList;

/**
 *
 * @author emmanuel
 */
public class Par_cn {
    char simbolo;
    LinkedList<Integer> estados; //el -1 representará una transición no válida.

    public Par_cn(char sim, int[] est) {
        estados = new LinkedList();
        simbolo = sim;
        for(int i=0;i<est.length;i++){
            estados.add(est[i]);
        }
    }
}

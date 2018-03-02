/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_compi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author emmanuel
 */
public class Practica2_compi {

    public static AFD generaEjemplar(){
        AFD ejemplar = new AFD(0);
        ejemplar.alfabeto.add('a');
        ejemplar.alfabeto.add('b');
        ejemplar.alfabeto.add('c');
        
        int[] arr1 = {1,-1,3,-1,-1,3}; //fila 1
        ejemplar.transiciones.add(new Par_cn('a',arr1));
        int[] arr2 = {-1,2,-1,5,-1,-1}; //fila 2
        ejemplar.transiciones.add(new Par_cn('b',arr2));
        int[] arr3 = {4,-1,4,-1,-1,4}; //fila 3
        ejemplar.transiciones.add(new Par_cn('c',arr3));
        
        ejemplar.F.add(2);
        ejemplar.F.add(4);
        return ejemplar;
    }
    
    public static void main(String[] args) {
        AFD ej = generaEjemplar();
        boolean cond;
        Tokenizer al = new Tokenizer(ej);
        
        try{
            FileReader fr1 = new FileReader("entrada.txt"); //Leo el archivo
            BufferedReader br1 = new BufferedReader(fr1);
            
            String linea = br1.readLine();
            
            while(linea!=null){
                System.out.println("cadena: "+linea);
                cond = al.analizar(linea);
                if(cond){
                    System.out.println("Ok: [(AB)*C][AB]");
                }else{
                    System.out.println("Error: cadena mal formada");
                }
                
                linea = br1.readLine();
            }
        }catch(Exception ex){}
    }
    
}

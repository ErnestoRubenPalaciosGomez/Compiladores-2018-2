package com.mycompany.practica_3;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto Palacios
 */  
public class Prueba {
    
     public static void main(String args[]){
         System.out.println("hola");
        try {
            Reader reader = new FileReader("src/main/resources/test.txt");
            Yylex y = new Yylex(reader);
            y.yylex();
            System.out.println("he terminado de ver el archivo");
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " No se encontró el archivo;");
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

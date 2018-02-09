/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1;

/**
 *
 * @author Ernesto Palacios
 */
import java.io.*;
    
public class HelloWorld {

    public static void main(String args[]){
        try {
            Reader reader = new FileReader("src/main/resources/test.txt");
            Yylex y = new Yylex(reader);
            y.yylex();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " No se encontró el archivo;");
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}


package com.mycompany.proyecto_1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto Palacios
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorLexico {
    Flexer lexer;

    public AnalizadorLexico(String archivo){
        try {
            Reader lector = new FileReader(archivo);
                lexer = new Flexer(lector);
                lexer.inicializa();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " No se encontró el archivo;");
        }
    }

    public void analiza(){
        try{
          lexer.yylex();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        lexer.imprime();
    }
    


}
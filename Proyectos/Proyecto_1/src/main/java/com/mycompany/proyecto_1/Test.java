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
   
public class Test {
    public static String[] archivos = { "fizzbuzz" , "fz_error_cadena" , "fz_error_lexema" , "fz_error_indentacion" , "fz_error_inicio"};
    public static String nombre_archivo;
    public static void main (String[] args){
        int i = 4;
        AnalizadorLexico al = new AnalizadorLexico("src/main/resources/" + archivos[i] + ".p");
        nombre_archivo = archivos[i];
        al.analiza();
    }
}

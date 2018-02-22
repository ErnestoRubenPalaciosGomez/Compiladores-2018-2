package com.mycompany.practica2;

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

    public static void main (String[] args){
        AnalizadorLexico al = new AnalizadorLexico("src/main/resources/test.txt");
        al.analiza();
    }
}

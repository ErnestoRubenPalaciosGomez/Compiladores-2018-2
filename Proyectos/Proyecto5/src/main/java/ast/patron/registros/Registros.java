/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.patron.registros;

import java.util.Arrays;

/**
 *
 * @author Ernesto Palacios
 */
public class Registros{

  int objetivoEntero;
  int objetivoFlotante;
  // Todos los registros enteros disponibles
  String[] E_registros = {"$t0", "$t1" ,"$t2" , "$t3" , "$t4" , "$t5", "$t6", "$t7", "$t8" ,"$t9"};
  // Todos los registros flotantes disponibles
  String[] F_registros = {"$f4", "$f5" , "$f6"  , "$f7" , "$f8" , "$f9" , "$f10"};

  public void setObjetivo(int o, boolean entero){
      if(entero){
          objetivoEntero = o % E_registros.length;
      }else{
          objetivoFlotante = o % F_registros.length;
      }

  }

  public void setObjetivo(String o, boolean entero){
      String[] registros;

      if(entero){
         registros = E_registros;
      }else{
         registros = F_registros;
      }
      int nvo_objetivo = Arrays.asList(registros).indexOf(o);
      setObjetivo(nvo_objetivo, entero);
  }

  public String getObjetivo(boolean entero){
      if(entero){
          return E_registros[objetivoEntero];
      }else{
          return F_registros [objetivoFlotante];
      }
  }

  /* Regresa los n registos siguientes "disponibles" */
  public String[] getNsiguientes(int n, boolean enteros){
      String[] siguientes = new String[n];
      int objetivo;
      String[] registros;

      if(enteros){
          objetivo = objetivoEntero;
          registros = E_registros;
      }else{
          objetivo = objetivoFlotante;
          registros = F_registros;
      }

      for(int i = 0; i < n; i++){
          siguientes[i] = registros[(objetivo + i) % E_registros.length];
      }
      return siguientes;
  }
}

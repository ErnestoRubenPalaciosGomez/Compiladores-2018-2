package com.mycompany.proyecto_1;
import java.util.Stack;
import java.io.*;
%%
%{
public int num_linea = 1; // numero de linea
public boolean es_inicio_linea = true; // nos dice si la linea esta empezando
public boolean necesita_identacion = false; // nos dice si la linea necesita indentacion
public int anterior = 0; // los espacios anteriores 
public Stack<Integer> espacios_bloque = new Stack<>(); // la pila de los espacios
public String salida = ""; // la cadena de salida del analizador

//inicializa la pila de la cantidad de espacios 
public void inicializa(){
    espacios_bloque.push(0);
}

// funcion que sale del programa pero primero escribe el archivo
public void  cerrar(){
    imprime();
    System.exit(0); 
}

//Funcion que verifica que la cadena este bien formada
public void VerificaCadena(String cadena){
    String cadena_aux = cadena.substring(1, cadena.length()-1); // hace una subcadena que empieza en 1 al final de la linea
    if(cadena_aux.contains("\"") || cadena.contains("\\")){ // verifica si la cadena tiene comillas o tiene una diagonal
        salida +=  "Error: Cadena mal Formada en la linea " + num_linea; // se le agrega mensaje de error a la salida 
        cerrar(); // se cierra e imprime la salida
    }else{
       salida += "CADENA(" + cadena_aux + ")"; // se acepta la cadena y sea grega el token a la salida
    }
}

//Funcion que imprime la salida en un archivo de texto
public void imprime(){
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("out/" + Test.nombre_archivo + ".plx"));
            escritor.write(salida);
            escritor.flush();
        } catch (IOException ex) {
            System.err.println("No se ha podido abrir el archivo");
        }
}

//Funcion que cuenta los espacios y los saltos de linea
public void VerificaEspacios(String cadena){
    if(!necesita_identacion && cadena.length() > 1){ //verifica si necesita indentacion si no lo necesita manda error
        salida += "Error de identacion en la linea :" + num_linea;
        cerrar();
    }else{
        if(necesita_identacion && es_inicio_linea){ // si necesita identacion y la linea empieza checa la cantidad de espacios 
            int num_espacios = 0; // variable para el numero de espacoios
            cadena = cadena.substring(1,cadena.length()); // checamos la cadena y quitamos el primer caracter que es "\n"
            for(int i = 0 ; i < cadena.length() ; i++){ // contamos la cantidad de espacios de la cadena las cadenas que entran aqui son cadenas de puros espacios y tabuladores
                if(cadena.charAt(i) == ' '){
                    num_espacios++;
                }else{
                    num_espacios+=4; // los tabuladores se toman por 4 espacios
                }

            }
            /*
            * si el numero de espacios actual es menor que el anterior entonces checamos en la pila si existe ese numero de espacios si no existe mandamos error de indentacion si 
            * el numero si se encuentra extraemos de la pila y marcamos los deindenta  si al finalizar quedamos que el unico elemento en la pila es 0 entonces marcamos
            * necesita_indentacion como falso dado que para este punto es como si todos los bloques estuvieran cerrados
            *
            * Si el numero de espacios actual es mayor que el anterior (la parte del else) entonces metemos el numero a la pila y el colocamos en salida el indenta correspondietnte
            */
            if(num_espacios < anterior){ 
                int aux;
                boolean condicion = true;
                if(espacios_bloque.search(num_espacios)== -1){
                    salida += "Error de identacion en la linea :" + num_linea;
                    cerrar();
                }else{
                    while(condicion){
                        aux = espacios_bloque.pop();
                        salida += "DEINDENTA(" + aux + ")\n";
                        if (num_espacios == espacios_bloque.peek()){                            
                            condicion = false;
                            anterior = espacios_bloque.peek();
                            if(anterior == 0){
                              necesita_identacion = false;
                            }
                        }  
                    }
                }        
            }else{
                if(num_espacios > anterior){
                    espacios_bloque.push(num_espacios);
                    salida +="INDENTA(" + num_espacios + ")\n";
                    anterior = num_espacios;
                }
            }
            
        }
        
    }
}

// funcion que verifica si el inicio tiene identacion
public void VerificaEspaciosInicio(){
    if (es_inicio_linea){
        salida+= "Error de identacion en la linea :" + num_linea;
        cerrar();
    }
}

/*
SALTO = "\n"(" " | "\t")* es el caracter salto de linea seguido de cero o mas espacios o tabulacios
INDENTA = (" " | "\t")*  indenta es para el punto extra para solo el incio no
LINEA_VACIA = "\n"(" " | "\t")*"\n"  | "\n"(" " | "\t")*#~"\n"  una linea vacia es un salto de linea seguido de cero o mas tabulaciones o espacios o 
es un salto de linea seguido de tabulaciones o espacios seguido de un comentario seguido de un salto del linea ( son lineas que solo tienen un comentario con una sucacion de espacios antes de empezar) 
COMENTARIO = #~"\n"  el caracter de gato seguido de cualquier cosa que termina con un salto de linea 


cuando entra a la parte de linea vacio y comentario tiene yypushback(1); lo que hace es quitar el ultimo salto de linea
*
%}
%eof{
    // Quita todos los espacios que quedaon el pila para que haga los deindenta
    boolean condicion = !espacios_bloque.isEmpty();
    int aux;
    while(condicion){
        aux = espacios_bloque.pop();    
        if (aux != 0){      
            salida += "\nDEINDENTA(" + aux + ")";
        }else{
            condicion = false;
        }
    }
%eof}
%class Flexer
%public
%standalone
%unicode
IDENTIFICADOR = ([a-zA-Z]|_)([a-zA-Z]|_|[0-9])*
BOOLEANO = "True" | "False"
ENTERO = [1-9][0-9]* | 0+
REAL = \.[0-9]+ | ENTERO\.[0-9]+ | ENTERO\.
CADENA = \" ~\"
PALABRA_RESERVADA = "and" | "or" | "not" | "for" | "while" | "if" | "else" | "elif" | "print"
OPERADOR = "+" | "-" | "*" | "**" | "/" | "//" | "%" | "<" | "<=" | ">" | ">=" | "=" | "!" 
SEPARADOR = ":"   
SALTO = "\n"(" " | "\t")*
INDENTA = (" " | "\t")* 
LINEA_VACIA = "\n"(" " | "\t")*"\n"  | "\n"(" " | "\t")*#~"\n" 
COMENTARIO = #~"\n" 
%%
{BOOLEANO}  {salida+="BOOLEANO("+yytext() + ")"; es_inicio_linea = false;}
{PALABRA_RESERVADA}  {salida += "PALABRA_RESERVADA("+yytext() + ")"; es_inicio_linea = false;}
{OPERADOR}      {salida+="OPERADOR("+yytext() + ")"; es_inicio_linea = false;}
{SEPARADOR}     {salida+="SEPARADOR("+yytext() + ")";
                 es_inicio_linea = false; 
                 necesita_identacion = true;
                 }
{IDENTIFICADOR} {salida += "IDENTIFICADOR("+yytext() + ")"; es_inicio_linea = false;}
{CADENA}    {VerificaCadena(yytext()); es_inicio_linea = false;}
{ENTERO}    {salida += "ENTERO("+yytext() + ")"; es_inicio_linea = false;}
{REAL}      {salida += "REAL("+yytext() + ")"; es_inicio_linea = false;}
{LINEA_VACIA} { yypushback(1);num_linea++;}
{COMENTARIO} {yypushback(1);}
{SALTO}     {   salida += "SALTO \n"; 
                es_inicio_linea = true;
                num_linea++;
                VerificaEspacios(yytext());}

{INDENTA}   {VerificaEspaciosInicio(); }
.             {salida+= "Error : palabra no encontrada en la linea " + num_linea; cerrar();}


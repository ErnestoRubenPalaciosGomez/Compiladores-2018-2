package com.mycompany.proyecto_1;
import java.util.Stack;
import java.io.*;
%%
%{
public int num_linea = 1;
public boolean es_inicio_linea = true;
public boolean necesita_identacion = false;
public int anterior = 0;
public Stack<Integer> espacios_bloque = new Stack<>();
public boolean es_vacia = true;
public String salida = "";

public void inicializa(){
    espacios_bloque.push(0);
}

public void  cerrar(){
    imprime();
    System.exit(0); 
}
public void VerificaCadena(String cadena){
    String cadena_aux = cadena.substring(1, cadena.length()-1);
    if(cadena_aux.contains("\"") || cadena.contains("\\")){
        salida +=  "Error: Cadena mal Formada en la linea " + num_linea;
        cerrar();
    }else{
       salida += "CADENA(" + cadena_aux + ")";
    }
}

public void imprime(){
        System.out.println(salida);
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("out/" + Test.nombre_archivo + ".plx"));
            escritor.write(salida);
            escritor.flush();
        } catch (IOException ex) {
            System.err.println("No se ha podido abrir el archivo");
        }
}

public void VerificaEspacios(String cadena){
    if(!necesita_identacion && cadena.length() > 1){
        salida += "Error de identacion en la linea :" + num_linea;
        cerrar();
    }else{
        if(necesita_identacion && es_inicio_linea){
            int num_espacios = 0;
            cadena = cadena.substring(1,cadena.length());
            for(int i = 0 ; i < cadena.length() ; i++){
                if(cadena.charAt(i) == ' '){
                    num_espacios++;
                }else{
                    num_espacios+=4;
                }

            }
            if(num_espacios < anterior){
                
                Stack<Integer> pila_aux = new Stack<>();
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

public void VerificaEspaciosInicio(){
    if (es_inicio_linea){
        salida+= "Error de identacion en la linea :" + num_linea;
        cerrar();
    }
}

public void VerificaComentario(String cadena){
    int z =-1;
    int i = cadena.length()-1;
    while(z == -1){
        if(cadena.charAt(i) == '\n')
            z = i;
        i--;
    }   
    System.out.println(cadena.substring(z , cadena.length()).length());
    VerificaEspacios(cadena.substring(z , cadena.length()));
}
%}
%eof{
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
LINEA_VACIA = "\n"(" " | "\t")*"\n"(" " | "\t")* 
LINEA_COMENTARIO = "\n"(" " | "\t")*#~"\n"(" " | "\t")* 
COMENTARIO = #~"\n"(" " | "\t")* 
%%
{BOOLEANO}  {salida+="BOOLEANO("+yytext() + ")"; es_inicio_linea = false;}
{PALABRA_RESERVADA}  {salida += "PALABRA_RESERVADA("+yytext() + ")"; es_inicio_linea = false; es_vacia = false;}
{OPERADOR}      {salida+="OPERADOR("+yytext() + ")"; es_inicio_linea = false; es_vacia = false;}
{SEPARADOR}     {salida+="SEPARADOR("+yytext() + ")";
                 es_inicio_linea = false; 
                 necesita_identacion = true;
                 es_vacia = false;}
{IDENTIFICADOR} {salida += "IDENTIFICADOR("+yytext() + ")"; es_inicio_linea = false; es_vacia = false;}
{CADENA}    {VerificaCadena(yytext()); es_inicio_linea = false; es_vacia = false;}
{ENTERO}    {salida += "ENTERO("+yytext() + ")"; es_inicio_linea = false; es_vacia = false;}
{REAL}      {salida += "REAL("+yytext() + ")"; es_inicio_linea = false; es_vacia = false;}
{LINEA_VACIA} {salida += "SALTO \n";VerificaComentario(yytext()); num_linea+=2;}
{LINEA_COMENTARIO} {salida += "SALTO \n";VerificaComentario(yytext()); num_linea+=2;}
{SALTO}     {if(!es_vacia){
                salida += "SALTO \n"; 
                es_inicio_linea = true;
                num_linea++;
                VerificaEspacios(yytext());
                es_vacia = true;}else{ num_linea++;}}

{INDENTA}   {VerificaEspaciosInicio(); }
{COMENTARIO}   {salida += "SALTO \n"; es_inicio_linea = true; VerificaComentario(yytext()); es_vacia = true; num_linea++; }
.             {salida+= "Error : palabra no encontrada en la linea " + num_linea; cerrar();}


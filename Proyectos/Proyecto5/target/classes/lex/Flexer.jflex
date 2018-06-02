package com.mycompany.proyecto5;
import java.util.Stack;
import java.io.*;
import ast.patron.compuesto.*;
%%
%byaccj
%class AnalizadorLexico
%public
%standalone
%line
%state INDENTA CADENA CODIGO DEINDENTA
%unicode
%{
    private Parser yyparser;

  public AnalizadorLexico(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
    
    /* Acumula todos los átomos de DEINDENTA que deben ser devueltos  */
    static int dedents = 0;
    static int indents = 0;
    /* Estructura auxiliar para almacenar los bloques de indentación */
    static Stack<Integer> pila = new Stack<Integer>();
    /* Guarda el nivel actual de indentación */
    static Integer actual = 0;
    /** Función que maneja los niveles de indetación e imprime
    * átomos INDENTA y DEINDENTA.
    * @param int espacios - nivel de indetación actual.
    * @return boolean - true en caso que no haya errores léxicos,
    * 	      	      	 false en otro caso.
    */
    public void indentacion(int espacios){
        if(pila.empty()){ //ponerle un cero a la pila si esta vacia
             pila.push(new Integer(0));
        }

        Integer tope = pila.peek();

        if(tope != espacios){
	    //Se debe emitir un DEDENT por cada nivel mayor al actual
            if(tope > espacios){
                while(pila.peek() > espacios &&  pila.peek()!=0 ){
                   pila.pop();
                   dedents ++;
                }
                if(pila.peek() == espacios){
                   yybegin(DEINDENTA);
                }
                return;
            }
   	    //El nivel actual de indentación es mayor a los anteriores.
            pila.push(espacios);
            yybegin(CODIGO);
            indents = 1;
        }else yybegin(CODIGO);
    }

    public int VerificaPalabraReservada(String palabra){
        switch (palabra){
            case "and": return Parser.AND;
            case "or" : return Parser.OR;
            case "not": return Parser.NOT; 
            case "for": return Parser.FOR;
            case "while": return Parser.WHILE;
            case "if" : return Parser.IF;
            case "else": return Parser.ELSE;
            case "elif": return Parser.ELIF;
            case "print": return Parser.PRINT;
            default: return -3;

        }

    }

    public int VerificaOperador(String operador){
        switch(operador){
            case "+":
            case "-":
            case "*":
            case "/":
            case "=":
            case "%":
            case "<":
                return (int) yycharat(0);
            case "**":
                return Parser.POW;
            case "//":
                return Parser.DIVISION_ENTERA;
            case "<=":
                return Parser.MENOR_IGUAL;
            case ">=":
                return Parser.MAYOR_IGUAL;
            case "==":
                return Parser.IGUAL_IGUAL;
            case "!=":
                return Parser.DIFERENTE;
            default: return -4;
        }

    }
    //Funcion que verifica que la cadena este bien formada
    public void VerificaCadena(String cadena){
        String cadena_aux = cadena.substring(1, cadena.length()-1); // hace una subcadena que empieza en 1 al final de la linea
        if(cadena_aux.contains("\"") || cadena.contains("\\")){ // verifica si la cadena tiene comillas o tiene una diagonal
            System.out.print("Error: Cadena mal Formada en la linea " + (yyline+1)); // se le agrega mensaje de error a la salida 
            System.exit(0);
        }
    }

    public int line(){
        return yyline+1;
    }

%}
PUNTO			=	\.
DIGIT           	=       [0-9]
CERO             	=        0+
ENTERO			= 	{CERO} | {DIGIT}+
REAL			= 	{ENTERO}? {PUNTO} {ENTERO} | {ENTERO} {PUNTO} {ENTERO}?
RESERVADA         	=       ("and" | "not" | "while" | "for" |  "elif" | "or" | "else" | "if" | "print")
OPERADOR  		=       ("+" | "-" | "*" | "**" | "/" | "//" | "%" |
			         "<" | ">" | "<=" | ">=" | "==" | "!=" | "=" )
SEPARADOR  		=       ":" | "("| ")" 
SALTO          	        =        "\n"
IDENTIFICADOR       	= 	([:letter:] | "_" )([:letter:] | "_" | [0-9])*
ESC              	= 	(\\)
CHAR_LITERAL   	        = 	([:letter:] | [:digit:] | "_" | "$" | " " | "#" | {OPERADOR} | {SEPARADOR})
COMENTARIO 		=     	(" " | "\t")*"#" {CHAR_LITERAL}*{SALTO}? 
BOOLEANO		=	("True" | "False")
%%
{COMENTARIO}      			{}
<CADENA>{
  ~\"                                  { VerificaCadena(yytext());
  					 yybegin(CODIGO); 
                                         yyparser.yylval = new CadenaHoja(yytext());
                                         return Parser.CADENA;}
  {SALTO}				{ System.out.println("Cadena mal construida, linea " + line() ); System.exit(1);}
}
<YYINITIAL>{
  
  " "+                        		{ System.out.println("Error de indentación. Línea " + line()); System.exit(1);}
  .                               	{ yypushback(1); yybegin(CODIGO);}
}
<CODIGO>{
  \"					{ yybegin(CADENA); }
  {ENTERO}				{ yyparser.yylval = new IntHoja(Integer.parseInt(yytext())); return Parser.ENTERO; }
  {REAL}   				{ yyparser.yylval = new RealHoja(Double.parseDouble(yytext())); return Parser.REAL;}
  {OPERADOR}				{ return VerificaOperador(yytext()); }
  {SEPARADOR}				{ return (int) yycharat(0);}
  {RESERVADA}				{ return VerificaPalabraReservada(yytext());}
  {BOOLEANO}                 		{ yyparser.yylval = new BooleanHoja(yytext().equals("True"));return Parser.BOOLEANO; }
  {IDENTIFICADOR}           		{ yyparser.yylval = new IdentifierHoja(yytext()); return Parser.IDENTIFICADOR;}
  {SALTO}                 		{ yybegin(INDENTA); actual=0; return Parser.SALTO;}
  " "                        		{   }
}

<DEINDENTA>{
  .                                       { yypushback(1);
  					    if(dedents > 0){
						dedents--;
						return Parser.DEINDENTA;
  					    }
					    yybegin(CODIGO);}
}

<INDENTA>{
  {SALTO}				{ actual = 0;}
  " "                            	{ actual++;}
  \t                             	{ actual += 4;}
  .                               	{ yypushback(1);
                                            this.indentacion(actual);
					    if(indents == 1){
					      indents = 0;
					      return Parser.INDENTA;
					    }

					}
}
<<EOF>>                                   { this.indentacion(0);
					    if(dedents > 0){
                                              dedents--;
					      return Parser.DEINDENTA;
					    }else{
                                                if(dedents == 0){
                                                   dedents--;
                                                   return Parser.SALTO;
                                                }else{
                                                   return 0;
                                                }                                     
				            }
					  }

[^]					{ System.out.println("Error de sintáxis: caractér inválido: " + yytext() + "\nLínea "+ line());
					  System.exit(1); }

package com.mycompany.proyecto2;
import java.util.Stack;
import java.io.*;
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
                return Parser.DIVISION_SUELO;
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
%}
PUNTO			=	\.
DIGIT           	=       [0-9]
CERO             	=        0+
ENTERO			= 	{CERO} | {DIGIT}+
REAL			= 	{ENTERO}? {PUNTO} {ENTERO} | {ENTERO} {PUNTO} {ENTERO}?
RESERVADA         	=       ("and" | "not" | "while" | "for" |  "elif" | "or" | "else" | "if" | "print")
OPERADOR  		=       ("+" | "-" | "*" | "**" | "/" | "//" | "%" |
			         "<" | ">" | "<=" | ">=" | "==" | "!=" | "=" )
SEPARADOR  		=       ":" 
SALTO          	        =        "\n"
IDENTIFICADOR       	= 	([:letter:] | "_" )([:letter:] | "_" | [0-9])*
ESC              	= 	(\\)
CHAR_LITERAL   	        = 	([:letter:] | [:digit:] | "_" | "$" | " " | "#" | {OPERADOR} | {SEPARADOR}) | "\\"
COMENTARIO 		=     	"#".*{SALTO}
BOOLEANO		=	("True" | "False")
%%
{COMENTARIO}      			{}
<CADENA>{
  {CHAR_LITERAL}*\"			{ System.out.print("CADENA(" + yytext().substring(0,yylength()-1) +  ")");
  					 yybegin(CODIGO); return Parser.CADENA;}
  {SALTO}				{ System.out.println("Cadena mal construida, linea " + (yyline+1) ); System.exit(1);}
}
<YYINITIAL>{
  " "+                        		{ System.out.println("Error de indentación. Línea " + (yyline+1) ); System.exit(1);}
  .                               	{ yypushback(1); yybegin(CODIGO);}
}
<CODIGO>{
  \"					{ yybegin(CADENA); }
  {ENTERO}				{ System.out.print("ENTERO("+yytext()+")"); return Parser.ENTERO; }
  {REAL}   				{ System.out.print("REAL("+yytext()+")" ); return Parser.REAL;}
  {OPERADOR}				{ System.out.print("OPERADOR("+yytext()+")"); return VerificaOperador(yytext()); }
  {SEPARADOR}				{ System.out.print("SEPARADOR("+yytext()+")"); return (int) yycharat(0);}
  {RESERVADA}				{ System.out.print("RESERVADA("+yytext()+")"); return VerificaPalabraReservada(yytext());}
  {BOOLEANO}                 		{ System.out.print("BOOLEANO("+yytext()+")"); return Parser.BOOLEANO; }
  {IDENTIFICADOR}           		{ System.out.print("IDENTIFICADOR("+yytext()+")"); return Parser.IDENTIFICADOR;}
  {SALTO}                 		{ yybegin(INDENTA); actual=0; System.out.print("SALTO"+yytext()); return Parser.SALTO;}
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
                                              return 0;
				            }
					  }

[^]					{ System.out.println("Error de sintáxis: caractér inválido: " + yytext() + "\nLínea "+(yyline+1));
					  System.exit(1); }

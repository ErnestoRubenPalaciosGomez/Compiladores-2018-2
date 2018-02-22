package com.mycompany.practica2;
%%
%class Alexico
%public
%standalone
%unicode
PUNTO   = \.
ENTERO  = [1-9][0-9]* | 0+
COMENTARIO_NORMAL = --(\ |[:jletterdigit:])*
COMENTARIO_MULTI = \{-([:jletterdigit:] | \n |\ )*-\}
PALABRA_RESERVADA = [A-Z][:jletterdigit:]* | if | else | where | then | case | of | deriving | data | error | let | in
ID_HASKELL = [:jletter:] [:jletterdigit:]*
%%
{ENTERO}      { System.out.print("ENTERO("+yytext() + ")"); }
{ENTERO} {PUNTO} {ENTERO} { System.out.print("FLOTANTE("+yytext() + ")"); }                              
{PALABRA_RESERVADA}     { System.out.print("PALABRA_RESERVADA("+yytext() + ")"); }
{COMENTARIO_NORMAL} { System.out.print("COMENTARIO_NORMAL("+yytext() + ")"); }
{COMENTARIO_MULTI} { System.out.print("COMENTARIO_MULTI("+yytext() + ")"); }
{ID_HASKELL}     { System.out.print("ID_HASKELL("+yytext() + ")"); }
.             { }


package com.mycompany.practica_3;

%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NUM = (-)?[0-9]+ ("." [0-9]+)?
NL = "\n" | "\r"  | "\r\n"

%%

"+" | 
"-" | 
"*" | 
"/"     { return (int) yycharat(0); }

{NL}   {return Parser.NL;}

{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
         return Parser.NUM; }

[ \t]+ { }

.   { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }

package com.mycompany.practica_3;
%%
%public
%standalone
%unicode
%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NL  = \n | \r | \r\n
NUM = [0-9]+ ("." [0-9]+)?

%%

.  {System.out.println("no he reconocido nada");}

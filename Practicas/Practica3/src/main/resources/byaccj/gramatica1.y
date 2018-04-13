
%{
  import java.io.*;
%}


      
%token <dval> NUM 

%type <dval> exp
%type <dval> texp
%type <dval> fexp
%left '-' '+'
%left '*' '/'
     
%%
exp  : texp '+' exp { $$ = ($1 + $3); }
     | texp '-' exp { $$ = ($1 - $3); }
     | texp
     ;

texp  :  fexp '*' texp {$$  = ($1 * $3);}
      |  fexp '/' texp {$$  = ($1 / $3);}
      | fexp

fexp : NUM;
%%

  private Yylex lexer;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("[ERROR] " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }



  public static void main(String args[]) throws IOException {
    Parser yyparser;
    yyparser = new Parser(new FileReader("src/main/resources/test.txt"));
    yyparser.yydebug = true;
    int condicion = yyparser.yyparse();
    if(condicion == 0){
        System.out.println("[ok]" + yyparser.yyval.dval);
    }else{
        System.err.print ("[ERROR] ");
        yyparser.yyerror("La expresión aritmética no esta bien formada.");
    }  
  }

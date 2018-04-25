
%{
  import java.io.*;
%}


%token SALTO OR WHILE FOR NOT AND IF PRINT ELSE BOOLEANO ENTERO REAL IDENTIFICADOR ELIF CADENA POW MENOR_IGUAL MAYOR_IGUAL DIVISION_SUELO IGUAL_IGUAL DIFERENTE  ENDMARKER

%left '-' '+'
%left '*' '/'
     
%%

file_input: | file_input1 | SALTO | file_input SALTO
            ; 

file_input1 : stmt | file_input stmt;

stmt : simple_stmt 
     | compound_stmt
     ;

simple_stmt : small_stmt
            ;

small_stmt : expr_stmt 
           | print_stmt 
           ;

expr_stmt : expr_stmt1 test
          ;

expr_stmt1 : test '='
           ;

print_stmt : print_stmt1 test
           ;

print_stmt1 : PRINT

compound_stmt : if_stmt
              | while_stmt
              ;


if_stmt : if_stmt1 suite | if_stmt3  suite
        ;

if_stmt1 : if_stmt2 test ':'
         ;

if_stmt2 : IF
         ;

if_stmt3 : if_stmt4 ELSE ':'
         ;

if_stmt4 : if_stmt1 suite
         ;


while_stmt : while_stmt1 suite
           ;

while_stmt1 : while_stmt2 test ':'
            ;

while_stmt2 : WHILE
            ;

suite : simple_stmt | suite1 suite2;

suite1 : SALTO;

suite2 : suite2 stmt | stmt;

test: or_test;

or_test : and_test | or_test1 or_test;

or_test1 : and_test OR;

and_test : not_test | and_test1 and_test;

and_test1 : not_test AND;

not_test : not_test1 not_test | comparison;

not_test1 : NOT;

comparison: expr comparison1 | expr;

comparison1 : comparison1 comp_op expr | comp_op expr ;

comp_op : '<' | '>' | IGUAL_IGUAL | MENOR_IGUAL | MAYOR_IGUAL | DIFERENTE;

expr : term | term expr1 ;

expr1 : expr1 op term | op term;

op : '+' | '-';

term : factor | factor term1 ;

term1 : term1 op1 factor | op1 factor;

op1 : '*' | '/' | '%' | DIVISION_SUELO;

factor : power | op factor;

power : power1 factor | atom;

power1 : atom POW;

atom :  IDENTIFICADOR | ENTERO | CADENA | REAL  | BOOLEANO  | test;




            
%%

  private AnalizadorLexico lexer;
  public static short linea = 0 ;
  public static String nombre_archivo = "test";

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
    lexer = new AnalizadorLexico(r, this);
  }



  public static void main(String args[]) throws IOException {
    Parser yyparser;
    yyparser = new Parser(new FileReader("src/main/resources/test.txt"));
    yyparser.yydebug = true;
    int condicion = yyparser.yyparse();
    if(condicion != 0){
      linea++;
      System.err.print ("[ERROR] ");
      yyparser.yyerror("Expresion mal fomrada");
    }else{
        System.out.println ("[OK] Expresion bien formada");

    }
  }

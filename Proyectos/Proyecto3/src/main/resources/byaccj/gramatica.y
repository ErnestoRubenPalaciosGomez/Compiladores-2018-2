
%{
  import java.io.*;
  import ast.patron.compuesto.*;  
%}


%token SALTO OR WHILE FOR NOT AND IF PRINT ELSE BOOLEANO ENTERO REAL IDENTIFICADOR ELIF CADENA POW MENOR_IGUAL MAYOR_IGUAL DIVISION_ENTERA IGUAL_IGUAL DIFERENTE INDENTA DEINDENTA

%%

input_file : /*cadena vacia*/ {raiz = $$; System.out.println("Reconocimiento Exitoso");}
           | file_input {raiz = $1; System.out.println("Reconocimiento Exitoso");};

file_input: SALTO 
          | stmt {$$ = new NodoStmts($1);}
          | file_input SALTO  {$$ = $1;}
          | file_input stmt {$1.agregaHijoFinal($2); $$ = $1;};

stmt : simple_stmt {$$ = $1;}
     | compound_stmt {$$ = $1;}
     ;

simple_stmt : small_stmt SALTO {$$ = $1;};


small_stmt : expr_stmt {$$ = $1;}
           | print_stmt {$$ = $1;}
           ;

expr_stmt : test {$$ = $1;}
          | test '=' test {$$ = new AsigNodo($1 , $2);}
          ;

print_stmt : PRINT test {$$ = new NodoPrint($1);}
           ;

compound_stmt : if_stmt {$$ = $1;}
              | while_stmt {$$ = $1;}
              ;

if_stmt : if_stmt1 suite | if_stmt3  suite
        ;

if_stmt1 : if_stmt2 test ':'
         ;

if_stmt2 : IF
         ;

if_stmt3 : if_stmt4 if_stmt5 ':'
         ;

if_stmt4 : if_stmt1 suite
         ;

if_stmt5 : ELSE;

while_stmt : WHILE test ':' suite
           ;

suite : simple_stmt {$$ = $1;}
      | SALTO INDENTA suite1 DEINDENTA {$$ = $3;}   
      ; 

suite1 : stmt {$$ = new NodoStmts($1);}
       | suite1 stmt {$1.agregaHijoFinal($2); $$ = $1;}
       ;

test: or_test {$$ = $1;};

or_test : and_test {$$ = $1;}
        | or_test1 and_test {$1.agregaHijoFinal($2); $$ = $1;};

or_test1 : and_test OR {$$ = new OrNodo($1 , null);}
         | or_test1 and_test OR {$1.agregaHijoFinal($2); $$ = new OrNodo($1 , null);}
         ;

and_test : not_test {$$ = $1;}
         | and_test1 not_test {$1.agregaHijoFinal($2); $$ = $1;}
         ;

and_test1 : not_test AND {$$ = new AndNodo($1 , null);}
          | and_test1 not_test AND {$1.agregaHijoFinal($2); $$ = new AndNodo($1 , null);}
          ;

not_test : NOT not_test {$$ = new NotNodo($1);}
         | comparison {$$ = $1;}
         ;

comparison: expr {$$ = $1;}
         | comparison1 expr;

comparison1 : expr '<' {$$ = new MenorNodo($1 , null);}
            | expr '>' {$$ = new MayorNodo($1 , null);}
            | expr IGUAL_IGUAL {$$ = new IgualIgualNodo($1 , null);}
            | expr MENOR_IGUAL {$$ = new MenorIgualNodo($1 , null);}
            | expr MAYOR_IGUAL {$$ = new MayorIgualNodo($1 , null);}
            | expr DIFERENTE {$$ = new DiferenteNodo($1 , null);}
            | comparison1 expr '<' {$1.agregaHijoFinal($2); $$ = new MenorNodo($1 , null);}
            | comparison1 expr '>' {$1.agregaHijoFinal($2); $$ = new MayorNodo($1 , null);} 
            | comparison1 expr IGUAL_IGUAL {$1.agregaHijoFinal($2); $$ = new IgualIgualNodo($1 , null);}
            | comparison1 expr MENOR_IGUAL {$1.agregaHijoFinal($2); $$ = new MenorIgualNodo($1 , null);}
            | comparison1 expr MAYOR_IGUAL {$1.agregaHijoFinal($2); $$ = new MayorIgualNodo($1 , null);}
            | comparison1 expr DIFERENTE {$1.agregaHijoFinal($2); $$ = new DiferenteNodo($1 , null);}
            ;

expr: term {$$ = $1;}
    | expr1 term {$$ = $1; $$.agregaHijoFinal($2);}
    ;
expr1: term '+' {$$ = new AddNodo($1,null);}
    | term '-' {$$ = new DifNodo($1,null);}
    | expr1 term '+' {$1.agregaHijoFinal($2); $$ = new AddNodo($1,null);}
    | expr1 term '-' {$1.agregaHijoFinal($2); $$ = new DifNodo($1,null);}

term: factor {$$ = $1;}
    | term1 factor {$$ = $1; $$.agregaHijoFinal($2);}
    ;
term1: factor '*' {$$ = new PorNodo($1 , null);}
    | factor DIVISION_ENTERA {$$ = new DivisionEnteraNodo($1 , null);}
    | factor '%' {$$ = new ModuloNodo($1 , null);}
    | factor '/' {$$ = new DivNodo($1 , null);}
    | term1 factor '*' {$1.agregaHijoFinal($2); $$ = new PorNodo($1 , null);}
    | term1 factor DIVISION_ENTERA {$1.agregaHijoFinal($2); $$ = new DivisionEnteraNodo($1 , null);}
    | term1 factor '%' {$1.agregaHijoFinal($2); $$ = new ModuloNodo($1 , null);}
    | term1 factor '/' {$1.agregaHijoFinal($2); $$ = new DivNodo($1 , null);}

factor : power {$$ = $1;}
       | '-' factor {$$ = new DifNodo(null , $1);} 
       | '+' factor {$$ = new AddNodo(null , $1);} 
       ;

power : atom POW factor {$$ = new PowNodo($1 , $2);}
      | atom {$$ = $1;};


atom :  IDENTIFICADOR {$$ = $1;}
     | ENTERO {$$ = $1;}
     | CADENA {$$ = $1;}
     | REAL  {$$ = $1;}
     | BOOLEANO {$$ = $1;}
     | '(' test ')' {$$ = $2;};
        
%%

  private AnalizadorLexico lexer;
  public static short linea = 0 ;
  public static String nombre_archivo = "test";
  public Nodo raiz;

  public int line(){
    return lexer.line();
    
  }
  private int yylex () {
    int yyl_return = -1;
    try {
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

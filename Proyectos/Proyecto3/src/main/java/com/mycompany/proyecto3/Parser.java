//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package com.mycompany.proyecto3;



//#line 3 "../../../../../../src/main/resources/byaccj/gramatica.y"
  import java.io.*;
  import ast.patron.compuesto.*;  
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Nodo
String   yytext;//user variable to return contextual strings
Nodo yyval; //used to return semantic vals from action routines
Nodo yylval;//the 'lval' (result) I got from yylex()
Nodo valstk[] = new Nodo[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Nodo();
  yylval=new Nodo();
  valptr=-1;
}
final void val_push(Nodo val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Nodo[] newstack = new Nodo[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Nodo val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Nodo val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Nodo dup_yyval(Nodo val)
{
  return val;
}
//#### end semantic value section ####
public final static short SALTO=257;
public final static short OR=258;
public final static short WHILE=259;
public final static short FOR=260;
public final static short NOT=261;
public final static short AND=262;
public final static short IF=263;
public final static short PRINT=264;
public final static short ELSE=265;
public final static short BOOLEANO=266;
public final static short ENTERO=267;
public final static short REAL=268;
public final static short IDENTIFICADOR=269;
public final static short ELIF=270;
public final static short CADENA=271;
public final static short POW=272;
public final static short MENOR_IGUAL=273;
public final static short MAYOR_IGUAL=274;
public final static short DIVISION_ENTERA=275;
public final static short IGUAL_IGUAL=276;
public final static short DIFERENTE=277;
public final static short INDENTA=278;
public final static short DEINDENTA=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    2,    2,    3,    5,
    5,    6,    6,    7,    4,    4,    9,    9,   11,   14,
   13,   15,   16,   10,   12,   12,   17,   17,    8,   18,
   18,   20,   20,   19,   19,   22,   22,   21,   21,   23,
   23,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   24,   24,   27,   27,   27,   27,   26,
   26,   29,   29,   29,   29,   29,   29,   29,   29,   28,
   28,   28,   30,   30,   31,   31,   31,   31,   31,   31,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    2,    1,
    1,    1,    3,    2,    1,    1,    2,    2,    3,    1,
    3,    2,    1,    4,    1,    4,    1,    2,    1,    1,
    2,    2,    3,    1,    2,    2,    3,    2,    1,    1,
    2,    2,    2,    2,    2,    2,    2,    3,    3,    3,
    3,    3,    3,    1,    2,    2,    2,    3,    3,    1,
    2,    2,    2,    2,    2,    3,    3,    3,    3,    1,
    2,    2,    3,    1,    1,    1,    1,    1,    1,    3,
};
final static short yydefred[] = {                         0,
    3,    0,    0,   20,    0,   79,   76,   78,   75,   77,
    0,    0,    0,    0,    0,    4,    7,    8,    0,   10,
   11,    0,   15,   16,    0,    0,    0,    0,   29,    0,
    0,    0,    0,   39,    0,    0,    0,    0,    0,    0,
   70,    0,    0,   38,   14,   72,   71,    0,    5,    6,
    9,    0,    0,   25,    0,   18,    0,   23,    0,   32,
    0,   36,    0,   45,   46,   44,   47,   42,   43,    0,
   56,   57,    0,   63,   62,   64,   65,    0,    0,    0,
   80,   13,    0,   19,   21,   33,   37,   51,   52,   50,
   53,   48,   49,   58,   59,   67,   66,   68,   69,   73,
   24,   27,    0,   26,   28,
};
final static short yydgoto[] = {                         14,
   15,   16,   17,   18,   19,   20,   21,   22,   23,   24,
   25,   55,   26,   27,   28,   59,  103,   29,   30,   31,
   32,   33,   34,   35,   36,   37,   38,   39,   40,   41,
   42,
};
final static short yysindex[] = {                        89,
    0,  -12,  -12,    0,  -12,    0,    0,    0,    0,    0,
  -25,  -25,  -12,    0,  102,    0,    0,    0, -246,    0,
    0,  -45,    0,    0,  126,  126,  -12, -243,    0, -228,
  -12, -227,  -12,    0,   25,  -25,  -16,  -25,  -30,  -25,
    0, -232,  -15,    0,    0,    0,    0,    7,    0,    0,
    0,  -12, -215,    0,    0,    0,   10,    0,   12,    0,
 -189,    0, -190,    0,    0,    0,    0,    0,    0,   53,
    0,    0,   16,    0,    0,    0,    0,  -28,  -25,  126,
    0,    0,  113,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   15,    0,    0,
};
final static short yyrindex[] = {                        74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   76,    0,    0,    0,    0,    0,
    0, -179,    0,    0,    0,    0,    0,    0,    0,   -9,
    0,   -7,    0,    0,  141,    0,   60,    0,   30,    0,
    0,  -37,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
    4,    0,   -5,    0,    0,    0,    0,    0,    0,  143,
    0,    0,   66,    0,    0,    0,    0,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   11,  -23,    0,    0,    0,    0,   37,    0,    0,
    0,  -13,    0,    0,    0,    0,    0,    0,   49,    0,
   44,    0,    0,   46,    0,   48,    0,   26,    0,    0,
    0,
};
final static int YYTABLESIZE=405;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         74,
   17,   54,   54,   74,   74,   74,   76,   74,   98,   74,
   51,   75,   56,   97,   13,   52,   77,   11,   99,   12,
   74,   58,   74,   74,   74,   50,   71,   13,   72,   60,
   11,   30,   12,   34,   62,   35,   46,   47,   43,   79,
   17,   45,   80,   17,   31,   17,   44,   81,   30,   48,
   34,   30,   35,   34,   13,   35,   54,   11,   94,   12,
   95,   31,   83,   57,   31,   78,  101,   84,   86,   85,
   60,   87,   60,    1,   60,    2,   63,   12,   61,   61,
   61,   70,   61,    0,   68,   73,   69,   60,   82,   60,
   60,   60,    0,  102,    0,   61,    0,   61,   61,   61,
   54,    0,    0,    0,  100,    0,   55,    0,    0,    0,
    0,    0,   92,  105,   93,    0,    0,   54,    0,   54,
   54,   54,    0,   55,    0,   55,   55,   55,   13,    0,
    0,   11,    0,   12,    0,    0,    0,    0,    0,    0,
    0,   13,    0,    0,   11,    0,   12,    0,    0,    0,
    0,    0,   13,    0,    0,   11,    0,   12,    0,    0,
    0,    0,    0,    0,    0,   13,    0,    0,   11,    0,
   12,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   40,    0,   41,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   40,    0,
   41,   40,    0,   41,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   74,
   74,    0,    0,    0,   74,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   74,   74,   74,   74,   74,
    6,    7,    8,    9,   74,   10,   96,   30,    3,   34,
   34,   35,   35,    6,    7,    8,    9,   17,   10,   17,
   31,   17,    0,   17,   17,   22,   17,   17,   17,   17,
    0,   17,    0,    2,    0,    3,    0,    4,    5,   17,
    6,    7,    8,    9,    0,   10,   60,   60,    0,    0,
    0,   60,    0,  104,   61,   61,    0,   64,   65,   61,
   66,   67,   60,   60,    0,   60,   60,    0,    0,    0,
   61,   61,    0,   61,   61,    0,   54,   54,    0,    0,
    0,   54,   55,   55,    0,   88,   89,   55,   90,   91,
    0,    0,   54,   54,    0,   54,   54,    0,   55,   55,
    0,   55,   55,    0,    0,    1,    0,    2,    0,    3,
    0,    4,    5,    0,    6,    7,    8,    9,   49,   10,
    2,    0,    3,    0,    4,    5,    0,    6,    7,    8,
    9,    2,   10,    3,    0,    4,    5,    0,    6,    7,
    8,    9,   53,   10,    0,    0,    3,    0,    0,    5,
    0,    6,    7,    8,    9,    0,   10,   40,   40,   41,
   41,    0,   40,    0,   41,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   25,   26,   41,   42,   43,   37,   45,   37,   47,
  257,   42,   26,   42,   40,   61,   47,   43,   47,   45,
   58,  265,   60,   61,   62,   15,   43,   40,   45,  258,
   43,   41,   45,   41,  262,   41,   11,   12,    2,  272,
   40,    5,   58,   43,   41,   45,    3,   41,   58,   13,
   58,   61,   58,   61,   40,   61,   80,   43,   43,   45,
   45,   58,  278,   27,   61,   40,   80,   58,  258,   58,
   41,  262,   43,    0,   45,    0,   33,  257,   41,   31,
   43,   36,   45,   -1,   60,   38,   62,   58,   52,   60,
   61,   62,   -1,   83,   -1,   58,   -1,   60,   61,   62,
   41,   -1,   -1,   -1,   79,   -1,   41,   -1,   -1,   -1,
   -1,   -1,   60,  103,   62,   -1,   -1,   58,   -1,   60,
   61,   62,   -1,   58,   -1,   60,   61,   62,   40,   -1,
   -1,   43,   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   43,   -1,   45,   -1,   -1,   -1,
   -1,   -1,   40,   -1,   -1,   43,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   43,   -1,
   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   41,   -1,   41,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   -1,
   58,   61,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,  262,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  266,  267,  268,  269,  275,  271,  275,  257,  261,  257,
  258,  257,  258,  266,  267,  268,  269,  257,  271,  259,
  257,  261,   -1,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,  259,   -1,  261,   -1,  263,  264,  279,
  266,  267,  268,  269,   -1,  271,  257,  258,   -1,   -1,
   -1,  262,   -1,  279,  257,  258,   -1,  273,  274,  262,
  276,  277,  273,  274,   -1,  276,  277,   -1,   -1,   -1,
  273,  274,   -1,  276,  277,   -1,  257,  258,   -1,   -1,
   -1,  262,  257,  258,   -1,  273,  274,  262,  276,  277,
   -1,   -1,  273,  274,   -1,  276,  277,   -1,  273,  274,
   -1,  276,  277,   -1,   -1,  257,   -1,  259,   -1,  261,
   -1,  263,  264,   -1,  266,  267,  268,  269,  257,  271,
  259,   -1,  261,   -1,  263,  264,   -1,  266,  267,  268,
  269,  259,  271,  261,   -1,  263,  264,   -1,  266,  267,
  268,  269,  257,  271,   -1,   -1,  261,   -1,   -1,  264,
   -1,  266,  267,  268,  269,   -1,  271,  257,  258,  257,
  258,   -1,  262,   -1,  262,
};
}
final static short YYFINAL=14;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",null,
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",null,
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"SALTO","OR","WHILE","FOR","NOT","AND","IF",
"PRINT","ELSE","BOOLEANO","ENTERO","REAL","IDENTIFICADOR","ELIF","CADENA","POW",
"MENOR_IGUAL","MAYOR_IGUAL","DIVISION_ENTERA","IGUAL_IGUAL","DIFERENTE",
"INDENTA","DEINDENTA",
};
final static String yyrule[] = {
"$accept : input_file",
"input_file :",
"input_file : file_input",
"file_input : SALTO",
"file_input : stmt",
"file_input : file_input SALTO",
"file_input : file_input stmt",
"stmt : simple_stmt",
"stmt : compound_stmt",
"simple_stmt : small_stmt SALTO",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test",
"expr_stmt : test '=' test",
"print_stmt : PRINT test",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : if_stmt1 suite",
"if_stmt : if_stmt3 suite",
"if_stmt1 : if_stmt2 test ':'",
"if_stmt2 : IF",
"if_stmt3 : if_stmt4 if_stmt5 ':'",
"if_stmt4 : if_stmt1 suite",
"if_stmt5 : ELSE",
"while_stmt : WHILE test ':' suite",
"suite : simple_stmt",
"suite : SALTO INDENTA suite1 DEINDENTA",
"suite1 : stmt",
"suite1 : suite1 stmt",
"test : or_test",
"or_test : and_test",
"or_test : or_test1 and_test",
"or_test1 : and_test OR",
"or_test1 : or_test1 and_test OR",
"and_test : not_test",
"and_test : and_test1 not_test",
"and_test1 : not_test AND",
"and_test1 : and_test1 not_test AND",
"not_test : NOT not_test",
"not_test : comparison",
"comparison : expr",
"comparison : comparison1 expr",
"comparison1 : expr '<'",
"comparison1 : expr '>'",
"comparison1 : expr IGUAL_IGUAL",
"comparison1 : expr MENOR_IGUAL",
"comparison1 : expr MAYOR_IGUAL",
"comparison1 : expr DIFERENTE",
"comparison1 : comparison1 expr '<'",
"comparison1 : comparison1 expr '>'",
"comparison1 : comparison1 expr IGUAL_IGUAL",
"comparison1 : comparison1 expr MENOR_IGUAL",
"comparison1 : comparison1 expr MAYOR_IGUAL",
"comparison1 : comparison1 expr DIFERENTE",
"expr : term",
"expr : expr1 term",
"expr1 : term '+'",
"expr1 : term '-'",
"expr1 : expr1 term '+'",
"expr1 : expr1 term '-'",
"term : factor",
"term : term1 factor",
"term1 : factor '*'",
"term1 : factor DIVISION_ENTERA",
"term1 : factor '%'",
"term1 : factor '/'",
"term1 : term1 factor '*'",
"term1 : term1 factor DIVISION_ENTERA",
"term1 : term1 factor '%'",
"term1 : term1 factor '/'",
"factor : power",
"factor : '-' factor",
"factor : '+' factor",
"power : atom POW factor",
"power : atom",
"atom : IDENTIFICADOR",
"atom : ENTERO",
"atom : CADENA",
"atom : REAL",
"atom : BOOLEANO",
"atom : '(' test ')'",
};

//#line 145 "../../../../../../src/main/resources/byaccj/gramatica.y"

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
//#line 434 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 12 "../../../../../../src/main/resources/byaccj/gramatica.y"
{raiz = yyval; System.out.println("Reconocimiento Exitoso");}
break;
case 2:
//#line 13 "../../../../../../src/main/resources/byaccj/gramatica.y"
{raiz = val_peek(0); System.out.println("Reconocimiento Exitoso");}
break;
case 4:
//#line 16 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new NodoStmts(val_peek(0));}
break;
case 5:
//#line 17 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1);}
break;
case 6:
//#line 18 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 7:
//#line 20 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 8:
//#line 21 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 9:
//#line 24 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1);}
break;
case 10:
//#line 27 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 28 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 12:
//#line 31 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 13:
//#line 32 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AsigNodo(val_peek(2) , val_peek(1));}
break;
case 14:
//#line 35 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new NodoPrint(val_peek(1));}
break;
case 15:
//#line 38 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 16:
//#line 39 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 25:
//#line 62 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 63 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1);}
break;
case 27:
//#line 66 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new NodoStmts(val_peek(0));}
break;
case 28:
//#line 67 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 29:
//#line 70 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 30:
//#line 72 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 31:
//#line 73 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 32:
//#line 75 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new OrNodo(val_peek(1) , null);}
break;
case 33:
//#line 76 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new OrNodo(val_peek(2) , null);}
break;
case 34:
//#line 79 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 35:
//#line 80 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 36:
//#line 83 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AndNodo(val_peek(1) , null);}
break;
case 37:
//#line 84 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new AndNodo(val_peek(2) , null);}
break;
case 38:
//#line 87 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new NotNodo(val_peek(1));}
break;
case 39:
//#line 88 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 40:
//#line 91 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 42:
//#line 94 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MenorNodo(val_peek(1) , null);}
break;
case 43:
//#line 95 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MayorNodo(val_peek(1) , null);}
break;
case 44:
//#line 96 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new IgualIgualNodo(val_peek(1) , null);}
break;
case 45:
//#line 97 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MenorIgualNodo(val_peek(1) , null);}
break;
case 46:
//#line 98 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MayorIgualNodo(val_peek(1) , null);}
break;
case 47:
//#line 99 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DiferenteNodo(val_peek(1) , null);}
break;
case 48:
//#line 100 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MenorNodo(val_peek(2) , null);}
break;
case 49:
//#line 101 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MayorNodo(val_peek(2) , null);}
break;
case 50:
//#line 102 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new IgualIgualNodo(val_peek(2) , null);}
break;
case 51:
//#line 103 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MenorIgualNodo(val_peek(2) , null);}
break;
case 52:
//#line 104 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MayorIgualNodo(val_peek(2) , null);}
break;
case 53:
//#line 105 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DiferenteNodo(val_peek(2) , null);}
break;
case 54:
//#line 108 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 55:
//#line 109 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1); yyval.agregaHijoFinal(val_peek(0));}
break;
case 56:
//#line 111 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AddNodo(val_peek(1),null);}
break;
case 57:
//#line 112 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DifNodo(val_peek(1),null);}
break;
case 58:
//#line 113 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new AddNodo(val_peek(2),null);}
break;
case 59:
//#line 114 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DifNodo(val_peek(2),null);}
break;
case 60:
//#line 116 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 61:
//#line 117 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1); yyval.agregaHijoFinal(val_peek(0));}
break;
case 62:
//#line 119 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new PorNodo(val_peek(1) , null);}
break;
case 63:
//#line 120 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DivisionEnteraNodo(val_peek(1) , null);}
break;
case 64:
//#line 121 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new ModuloNodo(val_peek(1) , null);}
break;
case 65:
//#line 122 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DivNodo(val_peek(1) , null);}
break;
case 66:
//#line 123 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new PorNodo(val_peek(2) , null);}
break;
case 67:
//#line 124 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DivisionEnteraNodo(val_peek(2) , null);}
break;
case 68:
//#line 125 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new ModuloNodo(val_peek(2) , null);}
break;
case 69:
//#line 126 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DivNodo(val_peek(2) , null);}
break;
case 70:
//#line 128 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 71:
//#line 129 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DifNodo(null , val_peek(1));}
break;
case 72:
//#line 130 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AddNodo(null , val_peek(1));}
break;
case 73:
//#line 133 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new PowNodo(val_peek(2) , val_peek(1));}
break;
case 74:
//#line 134 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 75:
//#line 137 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 76:
//#line 138 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 77:
//#line 139 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 78:
//#line 140 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 79:
//#line 141 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 80:
//#line 142 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1);}
break;
//#line 863 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################

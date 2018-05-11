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
    5,    6,    6,    7,    4,    4,    9,    9,   10,   11,
   11,   12,   12,    8,   13,   13,   15,   15,   14,   14,
   17,   17,   16,   16,   18,   18,   20,   20,   20,   20,
   20,   20,   20,   20,   20,   20,   20,   20,   19,   19,
   22,   22,   22,   22,   21,   21,   24,   24,   24,   24,
   24,   24,   24,   24,   23,   23,   23,   25,   25,   26,
   26,   26,   26,   26,   26,
};
final static short yylen[] = {                            2,
    0,    1,    1,    1,    2,    2,    1,    1,    2,    1,
    1,    1,    3,    2,    1,    1,    7,    4,    4,    1,
    4,    1,    2,    1,    1,    2,    2,    3,    1,    2,
    2,    3,    2,    1,    1,    2,    2,    2,    2,    2,
    2,    2,    3,    3,    3,    3,    3,    3,    1,    2,
    2,    2,    3,    3,    1,    2,    2,    2,    2,    2,
    3,    3,    3,    3,    1,    2,    2,    3,    1,    1,
    1,    1,    1,    1,    3,
};
final static short yydefred[] = {                         0,
    3,    0,    0,    0,    0,   74,   71,   73,   70,   72,
    0,    0,    0,    0,    0,    4,    7,    8,    0,   10,
   11,    0,   15,   16,   24,    0,    0,    0,    0,   34,
    0,    0,    0,    0,    0,    0,   65,    0,    0,   33,
    0,   14,   67,   66,    0,    5,    6,    9,    0,   27,
    0,   31,    0,   40,   41,   39,   42,   37,   38,    0,
   51,   52,    0,   58,   57,   59,   60,    0,    0,    0,
    0,   75,   13,   28,   32,   46,   47,   45,   48,   43,
   44,   53,   54,   62,   61,   63,   64,   68,    0,   20,
   19,    0,    0,    0,   22,    0,    0,   21,   23,   17,
};
final static short yydgoto[] = {                         14,
   15,   16,   17,   18,   19,   20,   21,   22,   23,   24,
   91,   96,   25,   26,   27,   28,   29,   30,   31,   32,
   33,   34,   35,   36,   37,   38,
};
final static short yysindex[] = {                        89,
    0,  -12,  -12,  -12,  -12,    0,    0,    0,    0,    0,
  -25,  -25,  -12,    0,  102,    0,    0,    0, -244,    0,
    0,  -39,    0,    0,    0, -232,  -12, -227,  -12,    0,
   25,  -25,    5,  -25,  -30,  -25,    0, -242,  -20,    0,
  -15,    0,    0,    0,    6,    0,    0,    0,  -12,    0,
 -197,    0, -199,    0,    0,    0,    0,    0,    0,   53,
    0,    0,   14,    0,    0,    0,    0,  -28,  -25,  126,
  126,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -212,    0,
    0, -193,  113,   10,    0,   15,  126,    0,    0,    0,
};
final static short yyrindex[] = {                        74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   76,    0,    0,    0,    0,    0,
    0, -180,    0,    0,    0,   -9,    0,   -7,    0,    0,
  141,    0,   60,    0,   30,    0,    0,  -37,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    4,    0,   -5,    0,    0,    0,    0,    0,    0,  143,
    0,    0,   66,    0,    0,    0,    0,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   12,  -68,    0,    0,    0,    0,   65,    0,    0,
  -60,    0,    0,   55,    0,   13,    0,    0,   48,    0,
   50,    0,   28,    0,    0,    0,
};
final static int YYTABLESIZE=405;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         69,
   18,   90,   90,   69,   69,   69,   66,   69,   86,   69,
   92,   65,   48,   85,   13,   40,   67,   11,   87,   12,
   69,   49,   69,   69,   69,   50,   47,   13,   90,   69,
   11,   25,   12,   29,   52,   30,  100,   70,   43,   44,
   18,   53,   71,   18,   26,   18,   72,   61,   25,   62,
   29,   25,   30,   29,   13,   30,   82,   11,   83,   12,
   74,   26,   75,   68,   26,   93,   39,   97,   41,   42,
   55,   94,   55,    1,   55,    2,   12,   45,   56,   60,
   56,   51,   56,   63,   58,    0,   59,   55,    0,   55,
   55,   55,    0,    0,    0,   56,   88,   56,   56,   56,
   49,    0,    0,    0,   95,    0,   50,   99,    0,    0,
    0,    0,   80,   73,   81,    0,    0,   49,    0,   49,
   49,   49,    0,   50,    0,   50,   50,   50,   13,    0,
    0,   11,    0,   12,    0,    0,    0,    0,    0,    0,
    0,   13,    0,    0,   11,    0,   12,    0,    0,    0,
    0,    0,   13,    0,    0,   11,    0,   12,    0,    0,
    0,    0,    0,    0,    0,   13,    0,    0,   11,    0,
   12,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   35,    0,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   35,    0,
   36,   35,    0,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   69,
   69,    0,    0,    0,   69,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   69,   69,   69,   69,   69,
    6,    7,    8,    9,   64,   10,   84,   25,    3,   29,
   29,   30,   30,    6,    7,    8,    9,   18,   10,   18,
   26,   18,    0,   18,   18,    0,   18,   18,   18,   18,
    0,   18,    0,    2,    0,    3,    0,    4,    5,   18,
    6,    7,    8,    9,    0,   10,   55,   55,    0,    0,
    0,   55,    0,   98,   56,   56,    0,   54,   55,   56,
   56,   57,   55,   55,    0,   55,   55,    0,    0,    0,
   56,   56,    0,   56,   56,    0,   49,   49,    0,    0,
    0,   49,   50,   50,    0,   76,   77,   50,   78,   79,
    0,    0,   49,   49,    0,   49,   49,    0,   50,   50,
    0,   50,   50,    0,    0,    1,    0,    2,    0,    3,
    0,    4,    5,    0,    6,    7,    8,    9,   46,   10,
    2,    0,    3,    0,    4,    5,    0,    6,    7,    8,
    9,    2,   10,    3,    0,    4,    5,    0,    6,    7,
    8,    9,   89,   10,    0,    0,    3,    0,    0,    5,
    0,    6,    7,    8,    9,    0,   10,   35,   35,   36,
   36,    0,   35,    0,   36,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   70,   71,   41,   42,   43,   37,   45,   37,   47,
   71,   42,  257,   42,   40,    3,   47,   43,   47,   45,
   58,   61,   60,   61,   62,  258,   15,   40,   97,  272,
   43,   41,   45,   41,  262,   41,   97,   58,   11,   12,
   40,   29,   58,   43,   41,   45,   41,   43,   58,   45,
   58,   61,   58,   61,   40,   61,   43,   43,   45,   45,
  258,   58,  262,   36,   61,  278,    2,   58,    4,    5,
   41,  265,   43,    0,   45,    0,  257,   13,   41,   32,
   43,   27,   45,   34,   60,   -1,   62,   58,   -1,   60,
   61,   62,   -1,   -1,   -1,   58,   69,   60,   61,   62,
   41,   -1,   -1,   -1,   93,   -1,   41,   96,   -1,   -1,
   -1,   -1,   60,   49,   62,   -1,   -1,   58,   -1,   60,
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
  257,  261,   -1,  263,  264,   -1,  266,  267,  268,  269,
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
"if_stmt : IF test ':' suite ELSE ':' suite",
"if_stmt : IF test ':' suite",
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

//#line 132 "../../../../../../src/main/resources/byaccj/gramatica.y"

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
//#line 424 "Parser.java"
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
{yyval = new AsigNodo(val_peek(2) , val_peek(1)); System.out.println("Entre a asign");}
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
case 17:
//#line 42 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(5).agregaHijoFinal(val_peek(3)); val_peek(5).agregaHijoFinal(val_peek(1)); yyval = new IfStmts(val_peek(6));}
break;
case 18:
//#line 43 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(0)); yyval = new IfStmts(val_peek(3));}
break;
case 19:
//#line 46 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new WhileNodo(val_peek(2) , val_peek(0));}
break;
case 20:
//#line 49 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 21:
//#line 50 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1);}
break;
case 22:
//#line 53 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new NodoStmts(val_peek(0));}
break;
case 23:
//#line 54 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 24:
//#line 57 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 25:
//#line 59 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 26:
//#line 60 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 27:
//#line 62 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new OrNodo(val_peek(1) , null);}
break;
case 28:
//#line 63 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new OrNodo(val_peek(2) , null);}
break;
case 29:
//#line 66 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 30:
//#line 67 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 31:
//#line 70 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AndNodo(val_peek(1) , null);}
break;
case 32:
//#line 71 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new AndNodo(val_peek(2) , null);}
break;
case 33:
//#line 74 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new NotNodo(val_peek(1));}
break;
case 34:
//#line 75 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 35:
//#line 78 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 36:
//#line 79 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(1).agregaHijoFinal(val_peek(0)); yyval = val_peek(1);}
break;
case 37:
//#line 81 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MenorNodo(val_peek(1) , null);}
break;
case 38:
//#line 82 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MayorNodo(val_peek(1) , null);}
break;
case 39:
//#line 83 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new IgualIgualNodo(val_peek(1) , null);}
break;
case 40:
//#line 84 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MenorIgualNodo(val_peek(1) , null);}
break;
case 41:
//#line 85 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new MayorIgualNodo(val_peek(1) , null);}
break;
case 42:
//#line 86 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DiferenteNodo(val_peek(1) , null);}
break;
case 43:
//#line 87 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MenorNodo(val_peek(2) , null);}
break;
case 44:
//#line 88 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MayorNodo(val_peek(2) , null);}
break;
case 45:
//#line 89 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new IgualIgualNodo(val_peek(2) , null);}
break;
case 46:
//#line 90 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MenorIgualNodo(val_peek(2) , null);}
break;
case 47:
//#line 91 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new MayorIgualNodo(val_peek(2) , null);}
break;
case 48:
//#line 92 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DiferenteNodo(val_peek(2) , null);}
break;
case 49:
//#line 95 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 50:
//#line 96 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1); yyval.agregaHijoFinal(val_peek(0));}
break;
case 51:
//#line 98 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AddNodo(val_peek(1),null);}
break;
case 52:
//#line 99 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DifNodo(val_peek(1),null);}
break;
case 53:
//#line 100 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new AddNodo(val_peek(2),null);}
break;
case 54:
//#line 101 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DifNodo(val_peek(2),null);}
break;
case 55:
//#line 103 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 56:
//#line 104 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1); yyval.agregaHijoFinal(val_peek(0));}
break;
case 57:
//#line 106 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new PorNodo(val_peek(1) , null);}
break;
case 58:
//#line 107 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DivisionEnteraNodo(val_peek(1) , null);}
break;
case 59:
//#line 108 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new ModuloNodo(val_peek(1) , null);}
break;
case 60:
//#line 109 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DivNodo(val_peek(1) , null);}
break;
case 61:
//#line 110 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new PorNodo(val_peek(2) , null);}
break;
case 62:
//#line 111 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DivisionEnteraNodo(val_peek(2) , null);}
break;
case 63:
//#line 112 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new ModuloNodo(val_peek(2) , null);}
break;
case 64:
//#line 113 "../../../../../../src/main/resources/byaccj/gramatica.y"
{val_peek(2).agregaHijoFinal(val_peek(1)); yyval = new DivNodo(val_peek(2) , null);}
break;
case 65:
//#line 115 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 66:
//#line 116 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new DifNodo(null , val_peek(1));}
break;
case 67:
//#line 117 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new AddNodo(null , val_peek(1));}
break;
case 68:
//#line 120 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = new PowNodo(val_peek(2) , val_peek(1));}
break;
case 69:
//#line 121 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 70:
//#line 124 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0); System.out.println("Es un identidficador");}
break;
case 71:
//#line 125 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 72:
//#line 126 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 73:
//#line 127 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 74:
//#line 128 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(0);}
break;
case 75:
//#line 129 "../../../../../../src/main/resources/byaccj/gramatica.y"
{yyval = val_peek(1);}
break;
//#line 869 "Parser.java"
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

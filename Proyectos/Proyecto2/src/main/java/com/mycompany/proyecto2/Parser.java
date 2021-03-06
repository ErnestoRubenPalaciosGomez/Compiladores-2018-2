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



package com.mycompany.proyecto2;



//#line 3 "../../../../../../src/main/resources/byaccj/gramatica.y"
  import java.io.*;
//#line 19 "Parser.java"




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
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
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
public final static short DIVISION_SUELO=275;
public final static short IGUAL_IGUAL=276;
public final static short DIFERENTE=277;
public final static short INDENTA=278;
public final static short DEINDENTA=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    2,    2,    3,    3,    4,    6,
    6,    7,    9,    8,   11,    5,    5,    5,   12,   12,
   15,   18,   17,   19,   20,   13,   21,   22,   14,   23,
   24,   16,   16,   27,   27,   26,   28,   25,   10,   29,
   29,   31,   30,   30,   33,   32,   32,   34,   35,   35,
   37,   38,   38,   38,   38,   38,   38,   36,   36,   40,
   41,   41,   39,   39,   43,   44,   44,   44,   44,   42,
   42,   45,   45,   46,   47,   47,   47,   47,   47,   47,
   48,   49,
};
final static short yylen[] = {                            2,
    0,    1,    1,    2,    1,    2,    1,    1,    2,    1,
    1,    2,    2,    2,    1,    1,    1,    1,    2,    2,
    3,    1,    3,    2,    1,    2,    3,    1,    2,    3,
    1,    1,    3,    2,    1,    2,    1,    1,    1,    1,
    2,    2,    1,    2,    2,    2,    1,    1,    1,    2,
    2,    1,    1,    1,    1,    1,    1,    1,    2,    2,
    1,    1,    1,    2,    2,    1,    1,    1,    1,    1,
    2,    2,    1,    2,    1,    1,    1,    1,    1,    2,
    2,    1,
};
final static short yydefred[] = {                         0,
   28,   31,   48,   22,   15,   79,   76,   78,   75,   77,
   61,   62,   82,    0,    0,    3,    5,    7,    8,    0,
   10,   11,    0,    0,    0,   16,   17,   18,    0,    0,
    0,    0,    0,    0,    0,    0,   39,    0,    0,    0,
    0,    0,    0,   49,    0,    0,    0,   63,    0,   70,
    0,    0,    0,    0,    4,    6,    9,   12,   13,   14,
   38,   32,    0,    0,   20,    0,   25,    0,   26,    0,
   29,    0,   42,   41,   45,   44,   46,   55,   56,   54,
   57,   52,   53,   51,   50,   69,   66,   67,   68,   59,
    0,   65,   71,   64,   72,   74,   80,   81,   37,    0,
    0,   21,   23,   27,   30,   60,   33,   35,    0,   34,
};
final static short yydgoto[] = {                         14,
   15,   16,   17,   18,   19,   20,   21,   22,   23,   24,
   25,   26,   27,   28,   29,   63,   30,   31,   32,   68,
   33,   34,   35,   36,   64,  100,  109,  101,   37,   38,
   39,   40,   41,   42,   43,   44,   45,   84,   46,   90,
   47,   48,   49,   92,   50,   51,   52,   53,   54,
};
final static short yysindex[] = {                        31,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   18,    0,    0,    0,    0, -246,
    0,    0,   53,  -32,   53,    0,    0,    0,   44,   44,
   53, -229,   44,   53,   44,   53,    0, -221,   53, -223,
   53,   53,  -22,    0,   59,  -30,   59,    0,   59,    0,
   59, -230,    2,   53,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -224,    0,   -2,    0,   -1,    0,    7,
    0,    8,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   59,    0,    0,    0,    0,    0,    0,    0,    0, -219,
   31,    0,    0,    0,    0,    0,    0,    0,   31,    0,
};
final static short yyrindex[] = {                        55,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   67,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -13,    0,   49,
    0,    0,  -39,    0,    0,  -27,    0,    0,    0,    0,
    0,  -37,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -211,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   -6,   -3,    0,    0,    0,    0,    0,   28,
    0,    0,    0,    0,    0,   17,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   30,   29,
    0,   33,    0,    0,    0,  -42,    0,    0,    0,    0,
   26,  -31,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=330;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         73,
   19,   47,   85,   73,   73,   73,   89,   73,   56,   73,
   57,   87,   12,   58,   11,   93,   88,   94,   47,   95,
   73,   47,   73,   73,   73,   62,   62,   40,   59,   62,
   58,   62,   58,   58,   58,   67,   73,   82,   75,   83,
   19,   96,   97,   19,   40,   19,   65,   40,  106,   69,
   58,   71,   60,   99,    1,  102,  103,   13,   66,  107,
   12,   70,   11,   72,  104,  105,    2,   36,   74,   76,
   13,   91,    0,   12,   77,   11,    0,    0,    0,    0,
    0,   98,    0,   13,    0,    0,   12,    0,   11,   43,
    0,    0,   13,    0,  108,   12,    0,   11,   13,    0,
    0,   12,  110,   11,    0,    0,   43,    0,    0,   43,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   47,   47,   73,
   73,    0,   47,    0,   73,    0,    0,    0,    0,   58,
   58,    0,    0,    0,   58,   73,   73,   73,   73,   73,
    0,    0,    0,   40,   86,   58,   58,    0,   58,   58,
   78,   79,    0,   80,   81,    0,    0,   19,    0,   19,
   19,   19,    0,   19,   19,   24,   19,   19,   19,   19,
    0,   19,    0,    0,   55,    0,    1,    2,    3,   19,
    4,    5,    0,    6,    7,    8,    9,    0,   10,    1,
    2,    3,    0,    4,    5,    0,    6,    7,    8,    9,
   61,   10,    0,    0,    3,   43,   43,    5,    0,    6,
    7,    8,    9,    3,   10,    0,    0,    0,    6,    7,
    8,    9,    0,   10,    6,    7,    8,    9,    0,   10,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   41,   45,   41,   42,   43,   37,   45,   15,   47,
  257,   42,   43,   41,   45,   47,   47,   49,   58,   51,
   58,   61,   60,   61,   62,   29,   30,   41,   61,   33,
   58,   35,   60,   61,   62,  265,  258,   60,  262,   62,
   40,  272,   41,   43,   58,   45,   30,   61,   91,   33,
   23,   35,   25,  278,    0,   58,   58,   40,   31,  279,
   43,   34,   45,   36,   58,   58,    0,  279,   39,   41,
   40,   46,   -1,   43,   42,   45,   -1,   -1,   -1,   -1,
   -1,   54,   -1,   40,   -1,   -1,   43,   -1,   45,   41,
   -1,   -1,   40,   -1,  101,   43,   -1,   45,   40,   -1,
   -1,   43,  109,   45,   -1,   -1,   58,   -1,   -1,   61,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  257,
  258,   -1,  262,   -1,  262,   -1,   -1,   -1,   -1,  257,
  258,   -1,   -1,   -1,  262,  273,  274,  275,  276,  277,
   -1,   -1,   -1,  257,  275,  273,  274,   -1,  276,  277,
  273,  274,   -1,  276,  277,   -1,   -1,  257,   -1,  259,
  260,  261,   -1,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,   -1,  257,   -1,  259,  260,  261,  279,
  263,  264,   -1,  266,  267,  268,  269,   -1,  271,  259,
  260,  261,   -1,  263,  264,   -1,  266,  267,  268,  269,
  257,  271,   -1,   -1,  261,  257,  258,  264,   -1,  266,
  267,  268,  269,  261,  271,   -1,   -1,   -1,  266,  267,
  268,  269,   -1,  271,  266,  267,  268,  269,   -1,  271,
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
"MENOR_IGUAL","MAYOR_IGUAL","DIVISION_SUELO","IGUAL_IGUAL","DIFERENTE",
"INDENTA","DEINDENTA",
};
final static String yyrule[] = {
"$accept : input_file",
"input_file :",
"input_file : file_input",
"file_input : file_input1",
"file_input : file_input SALTO",
"file_input1 : stmt",
"file_input1 : file_input stmt",
"stmt : simple_stmt",
"stmt : compound_stmt",
"simple_stmt : small_stmt SALTO",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : expr_stmt1 test",
"expr_stmt1 : test '='",
"print_stmt : print_stmt1 test",
"print_stmt1 : PRINT",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"compound_stmt : for_stmt",
"if_stmt : if_stmt1 suite",
"if_stmt : if_stmt3 suite",
"if_stmt1 : if_stmt2 test ':'",
"if_stmt2 : IF",
"if_stmt3 : if_stmt4 if_stmt5 ':'",
"if_stmt4 : if_stmt1 suite",
"if_stmt5 : ELSE",
"while_stmt : while_stmt1 suite",
"while_stmt1 : while_stmt2 test ':'",
"while_stmt2 : WHILE",
"for_stmt : for_stmt1 suite",
"for_stmt1 : for_stmt2 test ':'",
"for_stmt2 : FOR",
"suite : simple_stmt",
"suite : suite5 suite3 DEINDENTA",
"suite2 : suite2 stmt",
"suite2 : stmt",
"suite3 : suite4 suite2",
"suite4 : INDENTA",
"suite5 : SALTO",
"test : or_test",
"or_test : and_test",
"or_test : or_test1 or_test",
"or_test1 : and_test OR",
"and_test : not_test",
"and_test : and_test1 and_test",
"and_test1 : not_test AND",
"not_test : not_test1 not_test",
"not_test : comparison",
"not_test1 : NOT",
"comparison : expr",
"comparison : comparison1 expr",
"comparison1 : comparison comp_op",
"comp_op : '<'",
"comp_op : '>'",
"comp_op : IGUAL_IGUAL",
"comp_op : MENOR_IGUAL",
"comp_op : MAYOR_IGUAL",
"comp_op : DIFERENTE",
"expr : term",
"expr : term expr1",
"expr1 : op expr",
"op : '-'",
"op : '+'",
"term : factor",
"term : term1 factor",
"term1 : term op1",
"op1 : '*'",
"op1 : '/'",
"op1 : '%'",
"op1 : DIVISION_SUELO",
"factor : power",
"factor : op factor",
"power : power1 factor",
"power : atom",
"power1 : atom POW",
"atom : IDENTIFICADOR",
"atom : ENTERO",
"atom : CADENA",
"atom : REAL",
"atom : BOOLEANO",
"atom : atom1 ')'",
"atom1 : atom2 test",
"atom2 : '('",
};

//#line 137 "../../../../../../src/main/resources/byaccj/gramatica.y"

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
//#line 420 "Parser.java"
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

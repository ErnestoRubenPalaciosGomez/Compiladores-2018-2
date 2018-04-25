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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    0,    1,    1,    2,    2,    3,    5,
    5,    6,    8,    7,   10,    4,    4,   11,   11,   13,
   16,   15,   17,   12,   18,   19,   14,   14,   20,   21,
   21,    9,   22,   22,   24,   23,   23,   26,   25,   25,
   27,   28,   28,   30,   30,   31,   31,   31,   31,   31,
   31,   29,   29,   33,   33,   34,   34,   32,   32,   36,
   36,   37,   37,   37,   37,   35,   35,   38,   38,   39,
   40,   40,   40,   40,   40,   40,
};
final static short yylen[] = {                            2,
    0,    1,    1,    2,    1,    2,    1,    1,    2,    1,
    1,    2,    2,    2,    1,    1,    1,    2,    2,    3,
    1,    3,    2,    2,    3,    1,    1,    2,    1,    2,
    1,    1,    1,    2,    2,    1,    2,    2,    2,    1,
    1,    2,    1,    3,    2,    1,    1,    1,    1,    1,
    1,    1,    2,    3,    2,    1,    1,    1,    2,    3,
    2,    1,    1,    1,    1,    1,    2,    2,    1,    2,
    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    3,   26,   41,   21,   15,   75,   72,   74,   71,   73,
   57,   56,    0,    2,    5,    7,    8,    0,   10,   11,
    0,    0,    0,   16,   17,    0,    0,    0,    0,    0,
    0,   32,    0,    0,    0,    0,    0,   40,    0,    0,
    0,    0,   66,    0,    0,    4,    6,    9,    0,   13,
    0,   29,   27,   18,    0,   19,    0,    0,   24,    0,
   35,   76,   32,   38,    0,    0,   49,   50,   48,   51,
   46,   47,    0,    0,    0,    0,    0,   65,   62,   63,
   64,    0,    0,    0,   70,   31,    0,   20,   22,   25,
    0,    0,    0,    0,    0,    0,   30,    0,    0,    0,
};
final static short yydgoto[] = {                         13,
   14,   15,   16,   17,   18,   19,   20,   21,   62,   23,
   24,   25,   26,   54,   27,   28,   29,   30,   31,   55,
   87,   32,   33,   34,   35,   36,   37,   38,   39,   73,
   74,   40,   75,   41,   42,   82,   83,   43,   44,   45,
};
final static short yysindex[] = {                       130,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  143,    0,    0,    0,    0, -254,    0,    0,
  176,  -57,  176,    0,    0,  167,  167,  176, -243,  167,
  176,    0, -231,  176, -233,  176,  176,    0,  -44,  -10,
  176,    9,    0,  176, -236,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  154,    0,  -13,  -11,    0,   -9,
    0,    0,    0,    0, -231, -233,    0,    0,    0,    0,
    0,    0,  -44,  176,  -10,  176,    9,    0,    0,    0,
    0,    9,  176,    9,    0,    0,  154,    0,    0,    0,
  176,  -44,  176,  -10,  176,    9,    0,  -44,  -10,    9,
};
final static short yyrindex[] = {                        50,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   62,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -37,    0,  -30,    0,    0,    0,   83,    6,
    0,  102,    0,    0,   -3,    0,    0,    0,   28,    0,
   35,    0,    0,    0,    0,    0,   62,    0,    0,   62,
    0,    0,    0,    0,  -37,  -30,    0,    0,    0,    0,
    0,    0,   90,    0,   56,    0,  102,    0,    0,    0,
    0,  109,    0,  102,    0,    0,    1,    0,    0,    0,
    0,   83,    0,    6,    0,  102,    0,   83,    6,  102,
};
final static short yygindex[] = {                         0,
    0,    7,   11,    0,    0,    0,    0,    0,  427,    0,
    0,    0,    0,  -16,    0,    0,    0,    0,    0,    0,
    0,   20,   24,    0,   26,    0,    0,    0,  -72,    0,
  -12,  -67,    0,   12,   48,    0,   -8,    0,    0,    0,
};
final static int YYTABLESIZE=514;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   28,   92,   48,   50,   33,   33,   36,   33,   94,   33,
   56,   36,   36,   59,   36,   71,   36,   72,   98,   47,
   33,   58,   33,   33,   33,   99,   61,   36,   64,   36,
   36,   36,   12,   69,   11,   85,   53,   53,   69,   69,
   53,   69,   52,   69,   88,   81,   89,   52,   90,    1,
   79,   76,   52,   63,   69,   80,   69,   69,   69,   65,
   91,   86,   66,   52,   76,   52,   52,   52,    0,   76,
   76,   76,   76,   95,   76,    0,   76,   76,    0,   76,
    0,   76,    0,    0,    0,    0,   93,   76,   77,   76,
    0,   84,   53,   97,   76,    0,   76,   53,   76,    0,
    0,    0,   53,   76,   76,   76,   76,    0,   76,    0,
   76,    0,    0,   53,    0,   53,   53,   53,    0,   43,
    0,   76,    0,   76,   43,   43,   42,   43,    0,   43,
   96,   42,   42,    0,   42,    0,   42,    0,    0,    0,
   43,    0,  100,   43,   58,    0,   58,   42,    0,    0,
   42,   59,    0,   59,    0,    0,    0,    0,    0,   58,
    0,   58,   58,   58,    0,    0,   59,    0,   59,   59,
   59,    0,   12,    0,   11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   12,    0,   11,    0,    0,
    0,    0,    0,    0,    0,    0,   12,    0,   11,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   12,
    0,   11,    0,    0,    0,    0,    0,    0,   12,   33,
   11,    0,    0,    0,   33,    0,   36,   36,   67,   68,
    0,   69,   70,    0,   33,   33,   33,   33,   33,   33,
    0,   36,   36,   36,   36,   36,   36,    0,    0,    0,
    0,    0,    0,   69,   69,    0,    0,   28,   69,    0,
    0,    0,   52,   52,    0,   28,    0,   52,    0,   69,
   69,   69,   69,   69,    0,    0,    0,   52,   52,   52,
   52,   52,   52,   78,   12,   76,    0,    0,    0,   76,
    0,   14,   76,    0,    0,    0,   76,    0,    0,   76,
   76,   76,   76,   76,   76,    0,   76,   76,   76,   76,
   76,   76,   53,   53,    0,    0,    0,   53,    0,   76,
    0,    0,    0,   76,    0,    0,    0,   53,   53,   53,
   53,   53,   53,   76,   76,   76,   76,   76,   76,   43,
   43,    0,    0,    0,   43,    0,   42,   42,    0,    0,
    0,   42,    0,    0,   43,    0,    0,   43,   58,   58,
    0,   42,    0,   58,   42,   59,   59,    0,    0,    0,
   59,    0,    0,   58,   58,   58,    0,   58,   58,    0,
   59,   59,   59,    0,   59,   59,    1,    0,    2,    0,
    3,    0,    4,    5,    0,    6,    7,    8,    9,   46,
   10,    2,    0,    3,    0,    4,    5,    0,    6,    7,
    8,    9,    2,   10,    3,    0,    4,    5,    0,    6,
    7,    8,    9,   52,   10,    0,   22,    3,    0,    0,
    5,    0,    6,    7,    8,    9,    3,   10,    0,   22,
    0,    6,    7,    8,    9,    0,   10,   49,    0,   51,
    0,    0,   22,   22,   57,    0,   22,   60,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   22,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   74,  257,   61,   42,   43,   37,   45,   76,   47,
   27,   42,   43,   30,   45,   60,   47,   62,   91,   13,
   58,  265,   60,   61,   62,   93,  258,   58,  262,   60,
   61,   62,   43,   37,   45,  272,   26,   27,   42,   43,
   30,   45,   37,   47,   58,   37,   58,   42,   58,    0,
   42,   40,   47,   34,   58,   47,   60,   61,   62,   36,
   73,   55,   37,   58,   37,   60,   61,   62,   -1,   42,
   43,   37,   45,   82,   47,   -1,   42,   43,   -1,   45,
   -1,   47,   -1,   -1,   -1,   -1,   75,   60,   41,   62,
   -1,   44,   37,   87,   60,   -1,   62,   42,   37,   -1,
   -1,   -1,   47,   42,   43,   94,   45,   -1,   47,   -1,
   99,   -1,   -1,   58,   -1,   60,   61,   62,   -1,   37,
   -1,   60,   -1,   62,   42,   43,   37,   45,   -1,   47,
   83,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,
   58,   -1,   95,   61,   43,   -1,   45,   58,   -1,   -1,
   61,   43,   -1,   45,   -1,   -1,   -1,   -1,   -1,   58,
   -1,   60,   61,   62,   -1,   -1,   58,   -1,   60,   61,
   62,   -1,   43,   -1,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   43,   -1,   45,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   43,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   43,
   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,   43,  257,
   45,   -1,   -1,   -1,  262,   -1,  257,  258,  273,  274,
   -1,  276,  277,   -1,  272,  273,  274,  275,  276,  277,
   -1,  272,  273,  274,  275,  276,  277,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,   -1,   -1,  257,  262,   -1,
   -1,   -1,  257,  258,   -1,  265,   -1,  262,   -1,  273,
  274,  275,  276,  277,   -1,   -1,   -1,  272,  273,  274,
  275,  276,  277,  275,  257,  258,   -1,   -1,   -1,  262,
   -1,  257,  258,   -1,   -1,   -1,  262,   -1,   -1,  272,
  273,  274,  275,  276,  277,   -1,  272,  273,  274,  275,
  276,  277,  257,  258,   -1,   -1,   -1,  262,   -1,  258,
   -1,   -1,   -1,  262,   -1,   -1,   -1,  272,  273,  274,
  275,  276,  277,  272,  273,  274,  275,  276,  277,  257,
  258,   -1,   -1,   -1,  262,   -1,  257,  258,   -1,   -1,
   -1,  262,   -1,   -1,  272,   -1,   -1,  275,  257,  258,
   -1,  272,   -1,  262,  275,  257,  258,   -1,   -1,   -1,
  262,   -1,   -1,  272,  273,  274,   -1,  276,  277,   -1,
  272,  273,  274,   -1,  276,  277,  257,   -1,  259,   -1,
  261,   -1,  263,  264,   -1,  266,  267,  268,  269,  257,
  271,  259,   -1,  261,   -1,  263,  264,   -1,  266,  267,
  268,  269,  259,  271,  261,   -1,  263,  264,   -1,  266,
  267,  268,  269,  257,  271,   -1,    0,  261,   -1,   -1,
  264,   -1,  266,  267,  268,  269,  261,  271,   -1,   13,
   -1,  266,  267,  268,  269,   -1,  271,   21,   -1,   23,
   -1,   -1,   26,   27,   28,   -1,   30,   31,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   55,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   87,
};
}
final static short YYFINAL=13;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,null,null,"'*'","'+'",null,
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
};
final static String yyrule[] = {
"$accept : file_input",
"file_input :",
"file_input : file_input1",
"file_input : SALTO",
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
"if_stmt : if_stmt1 suite",
"if_stmt : if_stmt3 suite",
"if_stmt1 : if_stmt2 test ':'",
"if_stmt2 : IF",
"if_stmt3 : if_stmt4 ELSE ':'",
"if_stmt4 : if_stmt1 suite",
"while_stmt : while_stmt1 suite",
"while_stmt1 : while_stmt2 test ':'",
"while_stmt2 : WHILE",
"suite : simple_stmt",
"suite : suite1 suite2",
"suite1 : SALTO",
"suite2 : suite2 stmt",
"suite2 : stmt",
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
"comparison : expr comparison1",
"comparison : expr",
"comparison1 : comparison1 comp_op expr",
"comparison1 : comp_op expr",
"comp_op : '<'",
"comp_op : '>'",
"comp_op : IGUAL_IGUAL",
"comp_op : MENOR_IGUAL",
"comp_op : MAYOR_IGUAL",
"comp_op : DIFERENTE",
"expr : term",
"expr : term expr1",
"expr1 : expr1 op term",
"expr1 : op term",
"op : '+'",
"op : '-'",
"term : factor",
"term : factor term1",
"term1 : term1 op1 factor",
"term1 : op1 factor",
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
"atom : test",
};

//#line 122 "../../../../../../src/main/resources/byaccj/gramatica.y"

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
//#line 442 "Parser.java"
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

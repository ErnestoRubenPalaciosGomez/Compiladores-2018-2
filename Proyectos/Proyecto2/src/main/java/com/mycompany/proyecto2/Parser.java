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
public final static short SALTOINDENTA=278;
public final static short SALTODEINDENTA=279;
public final static short DEINDENTA=280;
public final static short INDENTA=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    0,    1,    1,    2,    2,    3,    3,
    5,    5,    6,    8,    7,   10,    4,    4,   11,   11,
   13,   16,   15,   17,   12,   18,   19,   14,   14,   22,
   22,   21,   23,   20,    9,   24,   24,   26,   25,   25,
   28,   27,   27,   29,   30,   30,   32,   32,   33,   33,
   33,   33,   33,   33,   31,   31,   35,   35,   36,   36,
   34,   34,   38,   38,   39,   39,   39,   39,   37,   37,
   40,   40,   41,   42,   42,   42,   42,   42,
};
final static short yylen[] = {                            2,
    0,    1,    1,    2,    1,    2,    1,    1,    2,    1,
    1,    1,    2,    2,    2,    1,    1,    1,    2,    2,
    3,    1,    3,    2,    2,    3,    1,    1,    3,    2,
    1,    2,    1,    1,    1,    1,    2,    2,    1,    2,
    2,    2,    1,    1,    2,    1,    3,    2,    1,    1,
    1,    1,    1,    1,    1,    2,    3,    2,    1,    1,
    1,    2,    3,    2,    1,    1,    1,    1,    1,    2,
    2,    1,    2,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    3,   27,   44,   22,   16,   78,   75,   77,   74,   76,
   59,   60,    0,    2,    5,    7,    8,    0,   11,   12,
    0,    0,    0,   17,   18,    0,    0,    0,    0,    0,
    0,   35,    0,    0,    0,    0,    0,   43,    0,    0,
    0,    0,   69,    0,    0,    4,    6,    9,   13,   14,
   15,   34,   28,    0,    0,   20,    0,    0,   25,    0,
   38,   37,   41,   40,   42,   52,   53,   51,   54,   49,
   50,    0,    0,    0,    0,   70,   68,   65,   66,   67,
    0,    0,   71,   73,   33,    0,    0,   21,   23,   26,
    0,   48,    0,   58,    0,   64,   29,   31,    0,   47,
   57,   63,   30,
};
final static short yydgoto[] = {                         13,
   14,   15,   16,   17,   18,   19,   20,   21,   22,   23,
   24,   25,   26,   54,   27,   28,   29,   30,   31,   55,
   86,   99,   87,   32,   33,   34,   35,   36,   37,   38,
   39,   72,   73,   40,   74,   41,   42,   81,   82,   43,
   44,   45,
};
final static short yysindex[] = {                       -34,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -16,    0,    0,    0,    0, -237,    0,    0,
  300,  -38,  300,    0,    0,  276,  276,  300, -241,  276,
  300,    0, -230,  300, -231,  300,  300,    0,  -56,  -27,
  168,  -37,    0,  168, -239,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -247,    0,  -23,  -22,    0,  -19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -56,  168,  -27,  168,    0,    0,    0,    0,    0,
  -37,  168,    0,    0,    0, -238,  252,    0,    0,    0,
  168,    0,  168,    0,  168,    0,    0,    0,  252,    0,
    0,    0,    0,
};
final static short yyrindex[] = {                        45,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  228,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  181,    0,  161,    0,    0,    0,  121,   73,
    0,   25,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,  201,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  141,    0,   97,    0,    0,    0,    0,    0,    0,
   49,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -233,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -11,  -13,    0,    0,    0,    0,    0,    9,    0,
    0,    0,    0,   -8,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   16,   15,    0,   17,    0,    0,    0,
  -70,    0,  -20,  -67,    0,  -33,  -29,    0,  -26,    0,
    0,    0,
};
final static int YYTABLESIZE=571;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         80,
   72,   47,   92,   70,   78,   71,   75,   94,   11,   79,
   12,   76,   53,   53,   83,   11,   53,   12,   56,   48,
  100,   59,   50,   58,   61,  101,   11,   61,   12,   49,
   63,   51,   84,   85,   88,   89,   57,   72,   90,   60,
   93,   97,   72,   72,    1,   72,   32,   72,   62,   62,
   64,   91,   96,   65,   95,    0,    0,    0,   72,    0,
   72,   72,   72,    0,    0,  102,    0,   61,    0,   61,
    0,    0,   55,    0,    0,   98,    0,    0,    0,    0,
    0,    0,   61,    0,   61,   61,   61,  103,    0,    0,
    0,   62,    0,   62,    0,    0,   56,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   62,    0,   62,   62,
   62,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   46,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,    0,   55,   55,   55,    0,    0,    0,    0,    0,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   56,    0,   56,   56,   56,    0,
   39,    0,    0,   46,    0,   46,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   46,    0,
   36,   46,    0,   45,    0,   45,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
   19,   45,    0,   39,    0,   39,    0,    0,    0,    0,
   11,    0,   12,    0,    0,    0,   66,   67,   39,   68,
   69,   39,    1,   36,    2,   36,    3,   10,    4,    5,
    0,    6,    7,    8,    9,    0,   10,   77,   36,    0,
   46,   36,    2,   19,    3,   19,    4,    5,    0,    6,
    7,    8,    9,    0,   10,    0,    0,   72,   72,   72,
    0,   72,   72,   72,   72,   72,   72,   72,   72,   72,
   10,   72,   10,   72,   72,   72,   72,   72,    0,    0,
   72,   61,   61,   61,    0,   61,   61,   61,   61,   61,
   61,   61,   61,   61,   11,   61,   12,   61,   61,    0,
   61,   61,    0,    0,   61,   62,   62,   62,    0,   62,
   62,   62,   62,   62,   62,   62,   62,   62,   11,   62,
   12,   62,   62,    0,   62,   62,    0,    0,   62,   55,
   55,   55,    0,   55,   55,   55,   55,   55,   55,   55,
   55,   55,   11,   55,   12,   55,   55,    0,   55,   55,
    0,    0,   55,   56,   56,   56,    0,   56,   56,   56,
   56,   56,   56,   56,   56,   56,    0,   56,    0,   56,
   56,    0,   56,   56,    0,    0,   56,   46,   46,   46,
    0,   46,   46,   46,   46,   46,   46,   46,   46,   46,
    0,   46,    0,    0,    0,    0,    0,   45,   45,   45,
   46,   45,   45,   45,   45,   45,   45,   45,   45,   45,
    0,   45,    0,    0,    0,    0,    0,   39,   39,   39,
   45,   39,    0,   39,   39,   39,   39,   39,   39,   39,
    0,   39,    0,    6,    7,    8,    9,   36,   10,   36,
   39,   36,    0,   36,   36,   36,   36,   36,   36,   36,
    0,   36,    0,    0,    0,    0,    0,   19,    0,   19,
   36,   19,    0,   19,   19,   24,   19,   19,   19,   19,
    0,   19,    0,    0,    0,    0,    0,    0,    0,    0,
   19,    0,    0,    0,    0,    0,   10,    0,   10,    0,
   10,   10,   10,   10,   10,   10,   10,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,   10,    0,    0,
    2,    0,    3,    0,    4,    5,    0,    6,    7,    8,
    9,    0,   10,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,    0,    0,    0,    3,    0,    0,    5,
    0,    6,    7,    8,    9,    0,   10,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    3,    0,    0,    0,    0,    6,    7,    8,    9,    0,
   10,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    0,   13,   73,   60,   42,   62,   40,   75,   43,   47,
   45,   41,   26,   27,   44,   43,   30,   45,   27,  257,
   91,   30,   61,  265,    0,   93,   43,  258,   45,   21,
  262,   23,  272,  281,   58,   58,   28,   37,   58,   31,
   74,  280,   42,   43,    0,   45,  280,   47,    0,   34,
   36,   72,   82,   37,   81,   -1,   -1,   -1,   58,   -1,
   60,   61,   62,   -1,   -1,   95,   -1,   43,   -1,   45,
   -1,   -1,    0,   -1,   -1,   87,   -1,   -1,   -1,   -1,
   -1,   -1,   58,   -1,   60,   61,   62,   99,   -1,   -1,
   -1,   43,   -1,   45,   -1,   -1,    0,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   58,   -1,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
    0,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
    0,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   58,   -1,   60,   61,   62,   -1,
    0,   -1,   -1,   43,   -1,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   -1,
    0,   61,   -1,   43,   -1,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   -1,
    0,   61,   -1,   43,   -1,   45,   -1,   -1,   -1,   -1,
   43,   -1,   45,   -1,   -1,   -1,  273,  274,   58,  276,
  277,   61,  257,   43,  259,   45,  261,    0,  263,  264,
   -1,  266,  267,  268,  269,   -1,  271,  275,   58,   -1,
  257,   61,  259,   43,  261,   45,  263,  264,   -1,  266,
  267,  268,  269,   -1,  271,   -1,   -1,  257,  258,  259,
   -1,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   43,  271,   45,  273,  274,  275,  276,  277,   -1,   -1,
  280,  257,  258,  259,   -1,  261,  262,  263,  264,  265,
  266,  267,  268,  269,   43,  271,   45,  273,  274,   -1,
  276,  277,   -1,   -1,  280,  257,  258,  259,   -1,  261,
  262,  263,  264,  265,  266,  267,  268,  269,   43,  271,
   45,  273,  274,   -1,  276,  277,   -1,   -1,  280,  257,
  258,  259,   -1,  261,  262,  263,  264,  265,  266,  267,
  268,  269,   43,  271,   45,  273,  274,   -1,  276,  277,
   -1,   -1,  280,  257,  258,  259,   -1,  261,  262,  263,
  264,  265,  266,  267,  268,  269,   -1,  271,   -1,  273,
  274,   -1,  276,  277,   -1,   -1,  280,  257,  258,  259,
   -1,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  280,  261,  262,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  280,  261,   -1,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,  266,  267,  268,  269,  257,  271,  259,
  280,  261,   -1,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,   -1,   -1,   -1,   -1,  257,   -1,  259,
  280,  261,   -1,  263,  264,  265,  266,  267,  268,  269,
   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  280,   -1,   -1,   -1,   -1,   -1,  259,   -1,  261,   -1,
  263,  264,  265,  266,  267,  268,  269,   -1,  271,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  280,   -1,   -1,
  259,   -1,  261,   -1,  263,  264,   -1,  266,  267,  268,
  269,   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,   -1,   -1,   -1,  261,   -1,   -1,  264,
   -1,  266,  267,  268,  269,   -1,  271,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  261,   -1,   -1,   -1,   -1,  266,  267,  268,  269,   -1,
  271,
};
}
final static short YYFINAL=13;
final static short YYMAXTOKEN=281;
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
"SALTOINDENTA","SALTODEINDENTA","DEINDENTA","INDENTA",
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
"simple_stmt : small_stmt",
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
};

//#line 124 "../../../../../../src/main/resources/byaccj/gramatica.y"

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
//#line 466 "Parser.java"
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

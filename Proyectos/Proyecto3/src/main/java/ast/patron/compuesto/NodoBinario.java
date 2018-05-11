package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NodoBinario extends Compuesto
{
    public NodoBinario(Nodo l, Nodo r){
        super();
	this.agregaHijoPrincipio(l);
	this.agregaHijoFinal(r);
        System.out.println("Entre al Nodo Binario");
    }

    public NodoBinario(Nodo l){
        super();
	this.agregaHijoPrincipio(l);
    }

    public NodoBinario(){
	super();
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}

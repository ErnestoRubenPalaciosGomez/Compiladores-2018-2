package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NodoBinario extends Compuesto
{
    public NodoBinario(Nodo l, Nodo r){
        super();
	this.agregaHijoPrincipio(l);
	this.agregaHijoFinal(r);
    }
    

    public NodoBinario(){
	super();
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
    
}

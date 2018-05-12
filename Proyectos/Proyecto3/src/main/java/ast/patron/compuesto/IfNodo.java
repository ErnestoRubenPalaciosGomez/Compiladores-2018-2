/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.patron.compuesto;

import ast.patron.visitante.Visitor;

/**
 *
 * @author Ernesto Palacios
 */
public class IfNodo extends Compuesto
{

    public IfNodo(Nodo t , Nodo l , Nodo r){
        super(t);
        this.getHijos().agregaHijoFinal(l);
        this.getHijos().agregaHijoFinal(r);       
    }
    public IfNodo(Nodo t , Nodo l){
        super(t);
        this.getHijos().agregaHijoFinal(l);
    }
    
    
    public void accept(Visitor v){
     	v.visit(this);
    }
    
}

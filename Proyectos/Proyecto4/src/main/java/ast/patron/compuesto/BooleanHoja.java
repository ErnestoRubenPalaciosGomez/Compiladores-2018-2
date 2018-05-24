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
public class BooleanHoja extends Hoja{
    public BooleanHoja(boolean i){
	valor = new Variable(i);
	tipo = 3;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}

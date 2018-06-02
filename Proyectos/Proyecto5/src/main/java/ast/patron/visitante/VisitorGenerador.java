/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.patron.visitante;

import ast.patron.compuesto.*;
import ast.patron.registros.Registros;
import com.mycompany.proyecto5.Compilador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ernesto Palacios
 */
public class VisitorGenerador implements Visitor{
    public Registros reg = new Registros();
    public String salida = "";
    @Override
    public void visit(AddNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo!=2;

        String objetivo = reg.getObjetivo(entero);
        String[] siguientes = reg.getNsiguientes(2,entero);

        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);
        String nombre = hi.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[0] + ", " + nombre + "\n";
        }

        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        nombre = hd.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[1] + ", " + nombre + "\n";
        }

        String opcode =  tipo==2 ? "add.s" : "add";

        salida += opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1] + "\n";
    }

    @Override
    public void visit(AsigNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo!=2;

        String[] siguientes = reg.getNsiguientes(2,entero);

        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);
        String nombre = hi.getNombre();
        reg.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        tipo = hd.getType();
        
        if ( nombre != null && tipo == 1){
            String opcode =  tipo==2 ? "sw" : "sw";
            salida += opcode + " " + siguientes[1]+ ", " + nombre + "\n";       
        }
       

       
    }

    @Override
    public void visit(DifNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo!=2;

        String objetivo = reg.getObjetivo(entero);
        String[] siguientes = reg.getNsiguientes(2,entero);

        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);
        String nombre = hi.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[0] + ", " + nombre + "\n";
        }

        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        nombre = hd.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[1] + ", " + nombre + "\n";
        }

        String opcode =  tipo==2 ? "sub.s" : "sub";

        salida += opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1] + "\n";
    }

    @Override
    public void visit(DivNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(DivisionEnteraNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo!=2;

        String objetivo = reg.getObjetivo(entero);
        String[] siguientes = reg.getNsiguientes(2,entero);

        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);
        String nombre = hi.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[0] + ", " + nombre + "\n";
        }

        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        nombre = hd.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[1] + ", " + nombre + "\n";
        }

        String opcode =  tipo==2 ? "div.s" : "div";

        salida += opcode  + " " +
                            siguientes[0] + ", " + siguientes[1] + "\n";
        
        salida += "mflo " + objetivo + "\n";
    }

    @Override
    public void visit(ModuloNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(PorNodo n) {
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo!=2;

        String objetivo = reg.getObjetivo(entero);
        String[] siguientes = reg.getNsiguientes(2,entero);
        
        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);
        String nombre = hi.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[0] + ", " + nombre + "\n";
        }

        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1], entero);
        hd.accept(this);
        nombre = hd.getNombre();
        if(nombre != null){
           salida += "lw " + siguientes[1] + ", " + nombre + "\n";
        }

        String opcode =  tipo==2 ? "mult.s" : "mult" ;

        salida += opcode  + " " +
                            siguientes[0] + ", " + siguientes[1] + "\n"; 
        
        salida +="mflo " + objetivo + "\n";
    }

    @Override
    public void visit(PowNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AndNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(OrNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(DiferenteNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IgualIgualNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MenorIgualNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MayorIgualNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MenorNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MayorNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NotNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(IdentifierHoja n) {
        ;
    }

    @Override
    public void visit(IntHoja n) {
       String[] siguientes = reg.getNsiguientes(1, true);
       salida += "li ," + siguientes[0] + " , " + n.getValor().ival + "\n";
    }

    @Override
    public void visit(RealHoja n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(CadenaHoja n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(BooleanHoja n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Nodo n) {
        VisitorType auxiliar = new VisitorType();
        for (Iterator i = n.getHijos().iterator(); i.hasNext(); ) {
            Nodo hijo = (Nodo) i.next();
            if ( hijo != null){
                hijo.accept(this);    
            }    
        }
    }

    @Override
    public void visit(NodoStmts n) {
        VisitorType auxiliar = new VisitorType();
        salida +=".data" + "\n";
        for (String x : auxiliar.tabla_de_tipos.keySet()){
            if(auxiliar.tabla_de_tipos.get(x) == 1){
                salida += x + ":" + "\t.word 0" + "\n";
            }
            
        }
        salida +=".text" + "\n";
        
        for (Iterator i = n.getHijos().iterator(); i.hasNext(); ) {
            Nodo hijo = (Nodo) i.next();
            if ( hijo != null){
                hijo.accept(this);    
            }    
        }
        this.escribe_codigo_objeto();
        
    }

    @Override
    public void visit(IfNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NodoPrint n) {
        Nodo hi = n.getPrimerHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo!=2;

        String objetivo = reg.getObjetivo(entero);
        String[] siguientes = reg.getNsiguientes(1,entero);

        // Genero el código del hijo
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);
        String nombre = hi.getNombre();
        if(nombre != null){
           salida +="lw " + objetivo + ", " + nombre + "\n";
        }
        
        salida +="li $v0,1\n";
        salida +="add  $a0, $zero, " + objetivo + "\n";
        salida +="syscall" + "\n";
        

        
    }

    @Override
    public void visit(WhileNodo n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void escribe_codigo_objeto(){
        BufferedWriter escritor = null;
        try {
            String ruta = "src/main/resources/out/" + Compilador.NOMBRE_ARCHIVO+ ".asm";
            escritor = new BufferedWriter (new FileWriter (ruta));
            escritor.write(salida);
        } catch (IOException ex) {
            Logger.getLogger(VisitorGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritor.close();
            } catch (IOException ex) {
                Logger.getLogger(VisitorGenerador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

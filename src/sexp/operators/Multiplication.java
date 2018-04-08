/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sexp.operators;

import sexp.Operator;
import sexp.Symbol;

/**
 *
 * @author hermes.espinola
 */
public class Multiplication extends Operator {
    
    static {
        operator = '*';
        identity = 1;
    }
    
    public Multiplication(Symbol l, Symbol r) { super(l, r); }
    
    @Override
    public double eval() {
        return this.left.eval() * this.right.eval();
    }

}

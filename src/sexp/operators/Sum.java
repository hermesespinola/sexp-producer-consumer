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
public class Sum extends Operator {

    static {
        operator = '+';
        identity = 0;
    }
    
    public Sum(Symbol l) { super(l, null); }
    public Sum(Symbol l, Symbol r) { super(l, r); }
    
    @Override
    public double eval() {
        if (this.right == null) {
            return this.left.eval();
        } else {
            return this.left.eval() + this.right.eval();
        }
    }
    
}

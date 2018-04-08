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
public class Difference extends Operator {
    
    public Difference(Symbol l) { super(l, '-'); }
    public Difference(Symbol l, Symbol r) { super(l, r, '-'); }
    public Difference(double l) { super(l, '-'); }
    public Difference(double l, double r) { super(l, r, '-'); }
    public Difference(double l, Symbol r) { super(l, r, '-'); }
    public Difference(Symbol l, double r) { super(l, r, '-'); }
    
    @Override
    public double eval() {
        if (this.right == null) {
            return -this.left.eval();
        } else {
            return this.left.eval() - this.right.eval();
        }
    }
    
}


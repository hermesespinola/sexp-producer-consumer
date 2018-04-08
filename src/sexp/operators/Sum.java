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
    public Sum(Symbol l) { super(l, '+'); }
    public Sum(Symbol l, Symbol r) { super(l, r, '+'); }
    public Sum(double l) { super(l, '+'); }
    public Sum(double l, double r) { super(l, r, '+'); }
    public Sum(double l, Symbol r) { super(l, r, '+'); }
    public Sum(Symbol l, double r) { super(l, r, '+'); }
    
    @Override
    public double eval() {
        if (this.right == null) {
            return this.left.eval();
        } else {
            return this.left.eval() + this.right.eval();
        }
    }
    
}

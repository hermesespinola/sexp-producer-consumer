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
public class Division extends Operator {
    
    public Division(Symbol l, Symbol r) { super(l, r, '/'); }
    public Division(double l, double r) { super(l, r, '/'); }
    public Division(double l, Symbol r) { super(l, r, '/'); }
    public Division(Symbol l, double r) { super(l, r, '/'); }
    
    @Override
    public double eval() {
        return this.left.eval() / this.right.eval();
    }
    
}

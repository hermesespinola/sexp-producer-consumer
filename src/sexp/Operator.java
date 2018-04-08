/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sexp;

/**
 *
 * @author hermes.espinola
 */
public abstract class Operator implements Symbol {
    protected final Symbol left;
    protected final Symbol right;
    protected final char operator;
    
    protected Operator(Symbol l, char operator) {
        this.left = l;
        this.right = null;
        this.operator = operator;
    }
    
    protected Operator(Symbol l, Symbol r, char operator) {
        this.left = l;
        this.right = r;
        this.operator = operator;
    }
    
    protected Operator(double l, char operator) {
        this.left = new SNumber(l);
        this.right = null;
        this.operator = operator;
    }
    
    protected Operator(double l, double r, char operator) {
        this.left = new SNumber(l);
        this.right = new SNumber(r);
        this.operator = operator;
    }
    
    protected Operator(double l, Symbol r, char operator) {
        this.left = new SNumber(l);
        this.right = r;
        this.operator = operator;
    }
    
    protected Operator(Symbol l, double r, char operator) {
        this.left = l;
        this.right = new SNumber(r);
        this.operator = operator;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(this.operator);
        sb.append(' ');
        sb.append(this.left.toString());
        if (this.right != null) {
            sb.append(' ');
            sb.append(this.right.toString());   
        }
        sb.append(')');
        return sb.toString();
    }
}

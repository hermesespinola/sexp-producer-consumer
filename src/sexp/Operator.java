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
    protected static char operator;
    protected static double identity;
    protected final Symbol left;
    protected final Symbol right;
    
    protected Operator(Symbol l) {
        if (l == null) {
            throw new Error("Left symbol in operator '" + operator + "' cannot be null");
        }
        this.left = l;
        this.right = null;
    }
    
    protected Operator(Symbol l, Symbol r) {
        if (l == null) {
            throw new Error("Left symbol in operator '" + operator + "' cannot be null");
        }
        this.left = l;
        if (r == null) {
            throw new Error("Right symbol in operator '" + operator + "' cannot be null");
        }
        this.right = r;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(operator);
        sb.append(' ');
        sb.append(this.left.toString());
        if (this.right != null) {
            sb.append(' ');
            sb.append(this.left.toString());   
        }
        sb.append(')');
        return sb.toString();
    }
}

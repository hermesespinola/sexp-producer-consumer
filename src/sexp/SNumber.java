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
public class SNumber implements Symbol {

    double value;
    public SNumber(double value) {
        this.value = value;
    }
    
    @Override
    public double eval() {
        return this.value;
    }
 
    @Override
    public String toString() {
        return Integer.toString((int)this.value);
    }
}

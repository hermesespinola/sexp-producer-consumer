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
public class Number implements Symbol {

    double value;
    Number(double value) {
        this.value = value;
    }
    
    @Override
    public double eval() {
        return this.value;
    }
    
}

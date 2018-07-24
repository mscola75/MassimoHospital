/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m256people;

/**
 *
 * @author massi
 */
public enum Grade {
    One,
    Two,
    Three;
    
    public String toString() {
        switch(this) {
            case One: return "1";
            case Two: return "2";
            default: return "3";
        }
    }
}

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
public enum Sex {
    m,
    f;

    @Override
    public String toString() {
       switch(this)
       {
           case m: return "male";
           default: return "female";        
       }
    }
      
   

}

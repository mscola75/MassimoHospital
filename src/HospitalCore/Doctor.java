/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HospitalCore;

import m256people.*;

/**
 *
 * @author massi
 */
public abstract class Doctor {
    private Person person;
    
    public Doctor() {
        
    }
    
    public Name getName() {
        return person.getName();
    }
    
    public void setName(Name aName) {
        this.person.setName(aName);
    }
    
    @Override
    public String toString() {
        return this.person.toString();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HospitalCore;

import m256people.Grade;

/**
 *
 * @author massi
 */
public class JuniorDoctor extends Doctor {
    private Grade grade;
    
    public JuniorDoctor() {
        
    }
    
    public Grade getGrade() {
        return this.grade;
    }
}

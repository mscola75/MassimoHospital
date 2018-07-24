/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HospitalCore;

import java.util.*;
import m256date.*;
import m256people.*;

/**
 *
 * @author massi
 */
public class Patient {
    
    private Person person;
    private Ward ward;
    private Collection<Doctor> doctors;
    private ConsultantDoctor consultantDoctor;
    
    public Patient(Name aName, Sex aSex, Date aDate) {
        person = new Person(aName, aSex, aDate);
        doctors = new HashSet<Doctor>();
    }
    
    /**
     * Returns the name of the patient
     * @return name
     */
    public Name getName() {
        return person.getName();
    }
    
}

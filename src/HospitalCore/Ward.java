/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HospitalCore;

import java.util.*;
import java.util.HashSet;
import m256people.*;

/**
 *
 * @author massi
 */
public class Ward {
    private Collection<Patient> patients;
    private Sex type;
    private String name;
    private int capacity;
    
    public Ward(){
        
    }

    Ward(String aName, Sex aSex, int aCapacity)
    {
        name = aName;
        type = aSex;
        capacity = aCapacity;
        patients = new HashSet<Patient>();
    }

    public Collection<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Collection<Patient> patients) {
        this.patients = patients;
    }
    
    /**
     * 
     * @return type
     */
    public Sex getType() {
        return type;
    }

    
    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return numberOfFreeBeds
     */
    public int getNumberOfFreeBeds() {
        return capacity - patients.size();
    }

   
    
    
}

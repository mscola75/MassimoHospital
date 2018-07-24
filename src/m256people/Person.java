/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m256people;

import java.util.*;
import m256date.M256Date;

/**
 *
 * @author massi
 */
public class Person {
    private Name name;
    private Sex sex;
    private Date dateOfBirth;
    
    public Person(Name aName, Sex aSex, Date aDateOfBirth) {
        name = aName;
        sex = aSex;
        dateOfBirth = aDateOfBirth;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
}

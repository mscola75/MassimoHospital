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
public class Name {
    private String title;
    private String firstName;
    private String lastName;
    
    public Name(String aTitle, String aFirstName, String aLastName) {
        title = aTitle;
        firstName = aFirstName;
        lastName = aLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}

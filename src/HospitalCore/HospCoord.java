/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HospitalCore;

import java.util.*;
import m256people.*;
import m256date.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Massimo Scola
 * @version 0.1
 */
public class HospCoord implements java.io.Serializable {
    
    
    private Collection<Ward> wards;
    private Collection<Patient> patients;
    private Collection<Team> teams;
    //constructor

    /**
     * Initialises a new HospCoord object with default wards, teams and doctors.
     * All the wards will initially be empty.
     */
    private HospCoord()
    {
        teams = new HashSet();
        wards = new HashSet();
        System.out.println("Initiliase the wards, teams and doctors.");
        readHospitalDetails("hospital.csv");  // initialise the wards, teams and doctors using the default file.
    }
    
    private Sex getType(Ward aWard) {
           return aWard.getType();

    }
   
    /**
     * Returns a string representation of all teams and wards.
     *
     * @return a String object representing the receiver
     */
    public String toString()
    {
        return teams.toString() + wards.toString();
    }


    /**
     * Creates and returns a new HospCoord object.
     * Reads in the state of the object from the
     * file Hospital.data; if there is no such file,
     * or if it is not compatible, returns the object
     * in its initial state.
     *
     * @return a new HospCoord object
     */
public static HospCoord getHospital()
{
   HospCoord hospital = null;
   FileInputStream fis = null;

   try
   {
      fis = new FileInputStream("Hospital.data");
      ObjectInputStream ois = new ObjectInputStream(fis);
      hospital = (HospCoord)ois.readObject();
   }
   catch(Exception ex)
   {
      // let user know that previous data file does not exist or is not compatible
      System.out.println("Data file does not exist or is incompatible with this version of the software.");
      System.out.println("Hospital will be initialised to default state");
      hospital = new HospCoord(); // initialise hospital to default state.
      hospital.save(); //and save it
   }
   finally // as we are not exiting make sure the fis stream is closed.
   {
      try
      {
         if (fis != null)
         {
            fis.close();
         }
      }
      catch (Exception ex)
      {
         System.out.println("Error closing file.");
      }
   }
   return hospital;
}


    /**
     * Saves the state of the receiver
     * to the file Hospital.data.
     */
    public void save()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("Hospital.data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        }
        catch(Exception ex)
        {
            System.out.println("Problem storing state of hospital");
            System.exit(1);
        }
    }


    //private protocol

    /**
     * Sets up a hospital with teams, doctors, wards and patients detailed in setupFile. This should be invoked
     * only by the constructor.
     */
    private void readHospitalDetails(String setupFile)
    {
        //Scanner fileScanner = null;
        Connection myConnection = null;
        String lineDetails;
        String fieldName;
        String teamName = null;
        Scanner lineScanner;
        ConsultantDoctor cd1 = null;
        Collection<Doctor> doctors = new HashSet<Doctor>();
        List<Object> patientInfo = new ArrayList<Object>();
        Team aTeam;
        
        try
        {
            //fileScanner = new Scanner(new BufferedReader(new FileReader(setupFile)));
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost/hospital?useLegacyDatetimeCode=false&serverTimezone=Europe/Amsterdam", "root", "massimo1975");
            Statement stmt = myConnection.createStatement();
            
            //get wards
            ResultSet rsWards = stmt.executeQuery("SELECT * FROM ward");
            while (rsWards.next()) {
                String wardName = rsWards.getString("WardName");
                System.out.println(wardName);
                String wardType = rsWards.getString("Type");
                int capacity = rsWards.getInt("Capacity");
                wards.add(new Ward(wardName, wardType.equals("male")?Sex.m:Sex.f, capacity));                
            }
            System.out.println("Wards: " + wards);
            
            //get patients
            ResultSet rsPatients = stmt.executeQuery("SELECT * FROM patient");
            while (rsPatients.next()) {
                int patientID = rsPatients.getInt("ID");
                String patientFirstName = rsPatients.getString("FirstName");
                String patientLastName = rsPatients.getString("LastName");
                String patientTitle = rsPatients.getString("Title");
                String patientGender = rsPatients.getString("Sex");
                Date patientDOB = rsPatients.getDate("DOB");

                
                Name tmpName = new Name(patientTitle, patientFirstName, patientLastName);
              
                Patient tmpPatient = new Patient(tmpName, patientGender.equals("male")?Sex.m:Sex.f, patientDOB);
                patients.add(tmpPatient);
            }
            
           //get teams
           ResultSet rsTeams = stmt.executeQuery("SELECT * FROM team");
           while (rsTeams.next()) {
               int teamID = rsTeams.getInt("ID");
               String teamNamers = rsTeams.getString("Name");
               teams.add()
           }
            
                   
            
//            
//            while (fileScanner.hasNextLine())
//            {
//                lineDetails = fileScanner.nextLine();
//                lineScanner = new Scanner(lineDetails);
//                lineScanner.useDelimiter(",");
//                try
//                {
//                    fieldName = lineScanner.next();
//                    if (fieldName.compareToIgnoreCase("Ward") == 0)
//                    {
//                        wards.add(new Ward(lineScanner.next(), Sex.valueOf(lineScanner.next()), Integer.parseInt(lineScanner.next())));
//                    }
//                    else if (fieldName.compareToIgnoreCase("Team") == 0)
//                    {
//                        if (teamName != null)
//                        {
//                            aTeam = new Team(teamName, doctors, cd1);
//                            teams.add(aTeam);
//                            addPatients(patientInfo, aTeam);
//                        }
//                        teamName = lineScanner.next();
//                        doctors = new HashSet<Doctor>();
//                        patientInfo = new ArrayList<Object>();
//                    }
//                    else if (fieldName.compareToIgnoreCase("Consultant") == 0)
//                    {
//                        cd1 = new ConsultantDoctor(new Name(lineScanner.next(), lineScanner.next(), lineScanner.next()));
//                        doctors.add(cd1);
//                    }
//                    else if (fieldName.compareToIgnoreCase("Junior") == 0)
//                    {
//                        doctors.add(new JuniorDoctor(new Name(lineScanner.next(), lineScanner.next(), lineScanner.next()),Grade.valueOf(lineScanner.next())));
//                    }
//                    else if (fieldName.compareToIgnoreCase("Patient") == 0)
//                    {
//                        patientInfo.add(new Name(lineScanner.next(), lineScanner.next(), lineScanner.next()));
//                        patientInfo.add(lineScanner.next());
//                        patientInfo.add(new M256Date(lineScanner.next()));
//                    }
//                }
//                catch (Exception anException)
//                {
//                    System.out.println(anException + ": Data corrupted");
//                }
//            }
//            if (teamName != null)
//            {
//                aTeam = new Team(teamName, doctors, cd1);
//                teams.add(aTeam);
//                addPatients(patientInfo, aTeam);
//            }
        }
        catch (Exception anException)
        {
            System.out.println("Error: " + anException);
        }
        finally
        {
            try {
                myConnection.close();
            } catch (SQLException ex) {
                Logger.getLogger(HospCoord.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
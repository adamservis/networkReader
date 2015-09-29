
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this class is responsible for making sure the topology is ready for export to cfg
 * @author Adam
 */
public class saveCheck {
    private final ArrayList<Integer> problemVMS = new ArrayList<>();
    private final ArrayList<Integer> problemHUBS = new ArrayList<>();
    
    public boolean cfgReady(){
        return (problemVMS.isEmpty()) && (problemHUBS.isEmpty());     //returns true if both lists are empty, indicates error free cfg    
    }
    
    public ArrayList<Integer> getProblemVMS(){  //returns a list of the indexes of VMs with issues
        return problemVMS;
    }
    
    public ArrayList<Integer> getProblemHUBS(){ //returns a list of the indexes of hubs with issues
        return problemHUBS;
    }
    
    public void saveCheck(){ //constructor, checks for null entries and
        for ( virtualMachine VM : NetworkReader.vmList ){ //for each vm
            
            int nullCounter = 0;
             for (String address : VM.addressList){
                 if ( address == null){//finds number of empty ethernet ports, used later to check address list
                     nullCounter++;
                 }
             } 
             if ( (VM.getName() == null) ||         //if no entry has been specified
                  ( VM.getOS() == null ) ||
                  ( VM.getVersion() == null ) ||
                  ( VM.getSource() == null ) ||
                  (VM.getName().equals("")) ||      //or if the entry is blank
                  ( VM.getOS().equals("")) ||       //THIS RESULTS IN AN ERROR IF A NUMBER OF SPACE CHARACTERS ARE SAVED
                  ( VM.getVersion().equals("")) ||  //not enough time to figure out the regex to fix it
                  ( VM.getSource().equals("")) ||
                  ( VM.addressList.isEmpty()) ||            //will catch list with no values, but not a list with all null entries
                  ( nullCounter == VM.addressList.size())){ //last part returns an error if the list has all null entries
                 problemVMS.add(VM.getIndex());    //add the machines index to the problem list
             }
        }
        
        for ( hub hub : NetworkReader.hubList ){    //for each hub
            if ( (hub.getName() == null) ||         //if the name has no value
                 (hub.getName().equals("")) ||      //if the name is blank THIS RESULTS IN AN ERROR IF A NUMBER OF SPACE CHARACTERS ARE SAVED
                 (hub.getSubnetFull() == null) ||   //if the subnet has no value 
                 (hub.infList.isEmpty())){          //makes sure the hub has one connection to it
                problemHUBS.add(hub.getIndex());    //adds the machines index to the problem list
            }
        }
    }   
}

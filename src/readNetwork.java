 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this class takes a cfg and turns it into software objects
 * @author Adam
 */
public class readNetwork {
    
    private final Scanner s;
    ArrayList<virtualMachine> vmList = new ArrayList<>();  //defines list of virtual machines
    ArrayList<hub> hubList = new ArrayList<>();            //defines list of hubs
    private List<String> addressList = new ArrayList<>();  //defines list of addresses
    private String[] netTemp; 
    //returns vmList to main program
    
    public ArrayList<virtualMachine> getvmList(){
        return vmList;
    }
    //returns hublist to main program
    public ArrayList<hub> gethubList(){
        return hubList;
    }
    
    //called after initial data is saved to vm and hub objects.
    //allocates address information to network devices required for errorchecking
    //keeps track of addresses used on each subnet
    
    private void initializeConnections(){  
        int eth;
        int i;
        int j;
        int subnet;
        int address;
        
        String fullAddress;
        String vmName;
        
        //goes through vm list starting at 0
        i = 0;
        while ( i < vmList.size()){
            addressList = vmList.get(i).getAddress();
            vmName = vmList.get(i).getName();
            Iterator itr = addressList.iterator();
            while ( itr.hasNext() ){//while there is an address in the address list
                j = 0;
                fullAddress = (String) itr.next();
                eth = addressList.indexOf(fullAddress); 
                if( fullAddress != null ) {
                    //splits address into subnet and address integer values
                    //example: 192.168.10.2 becomes 10 and 2
                    subnet = NetworkReader.getthirdOctet(fullAddress);
                    address = NetworkReader.getlastOctet(fullAddress);
                        //saves the correct info to the hub with the specified subnet value
                        while( j < hubList.size()){ 
                            hubList.get(j).setHubConnections(subnet, address, vmName, eth);
                            j++;
                        }       
                }
            }
            i++;
        }
        
    }
    
    //converts .cfg file found at fileName into software objects
    //lots of room for improvement, assumes perfect cfg file
    //little spacing errors in cfg example throw it way off
    readNetwork(String fileName) throws FileNotFoundException{
        String ethernet;
        String subnet;
        String name;
        String netmask;
        String OS;
        String source;
        String version; 
        String address[];
        ArrayList<String> netAddress = new ArrayList<>();
        
        int portNumber;
        
        s = new Scanner(new BufferedReader(new FileReader(fileName)));

            while (s.hasNext()) { //while there is something in the scanner
                if (s.hasNext("vm")){
                    netAddress.clear();
                    s.next();
                    name = s.next();
                    s.next();
                    s.next();
                    s.next();
                    OS = s.next();
                    s.next();
                    s.next();
                    version = s.next();
                    s.next();
                    s.next();
                    source = s.next();
                    // reads addresses from cfg, saves each in an arraylist
                    // each addresses index should equal the ethernet port number
                    while (s.hasNext("eth[0-9]")){
                        ethernet = s.next();
                        String[] netTemp = ethernet.split("eth");
                        portNumber = Integer.parseInt(netTemp[1]);
                        s.next();
                        while(portNumber > netAddress.size()){//adds null values to array until value can be added
                            netAddress.add(null);   
                        }
                        netAddress.add(portNumber, s.next());
                    }
                    vmList.add( new virtualMachine( name, OS, version, source, netAddress ));//Create VM object from .cfg file entry
                } 
                    s.next();
                //beginning of hub info reading    
                if (s.hasNext("hub")){
                    s.next();
                    name = s.next();
                    while (!s.hasNext("subnet")){//ignores inf data, compiles it at a later time
                        s.next();
                    }
                    s.next();
                    s.next();
                    subnet = s.next();
                    s.next();
                    s.next();
                    netmask = s.next();
                    hubList.add( new hub( name, subnet, netmask));
                }
            }            
           initializeConnections();
    }
}


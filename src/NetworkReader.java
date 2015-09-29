



import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//this is the "main" class of the software
//houses many important static variables and additional methods used by the software
public class NetworkReader {
        //instantiates the lists used to manage network devices
        static List<virtualMachine> vmList = new ArrayList<>(); //list of virtual machines used by the software
        static List<hub> hubList = new ArrayList<>(); //list of hubs used by the software
        static HomeForm form = new HomeForm();
        static Connection connections = new Connection(); //list of lines representing network connections
        //defines a point variable used when transitioning between subnet and
        //vm related forms
        static Point p = new Point();
        
    public static void main(String[] args) throws IOException {
        form.setVisible(true);
    }
    
    //method to call setConnectionData on each vm object
    //required to draw network
    static void setNetworkConnections(){
        int i = 0;
        while (i < NetworkReader.vmList.size()){
            NetworkReader.vmList.get(i).setConnectionData();
            i++;
        }
    }
    
    //method to remove an address from a given vm,
    //vmIndex corresponds to a number on the vmlist, index corresponds to the
    //ethernet port of the address to remove
    //Port(index) on vm(vm)
    static void removeAddress(int vmIndex, int port){
        if(vmList.get(vmIndex).getAddress(port) != null){
          //gets last octet from address  
          int address = getlastOctet(vmList.get(vmIndex).getAddress(port));
          //name of the vm the address exists on
          String name = vmList.get(vmIndex).getName();
          //subnet of the address, used to find hub        
          int subnet = vmList.get(vmIndex).removeAddress(port);
          //a counter
          int j = 0;
          
          while( j < hubList.size()){
             if( subnet == hubList.get(j).getSubnet()){ //identifies hub by subnet
                 hubList.get(j).removeAddress(address); //removes corresponding inf entry
                 hubList.get(j).removeInf(name, null);
                 j++;
             }
             j++;
          }   
        }
    }
    //erases all network objects, redraws network, resets indexes
    public static void clearNetwork(){
        for ( virtualMachine vm : vmList ){
            vm.icon.removeClickListeners();
            vm.removeIcon();
        }
        for ( hub hub : hubList){
            hub.icon.removeClickListeners();
            hub.removeIcon();
        }
        hubList.clear();
        vmList.clear();
        setIndex();
        setNetworkConnections();
    }
    //calls relevant methods to draw the network connections
    static void drawConnections(){
        NetworkReader.connections.clearLines();
        NetworkReader.setNetworkConnections();
        HomeForm.JPanel1.repaint();
    }
    //adds a fresh hub object to the hub list
    static void addHub(){
        hubList.add(new hub(null, null, null));
        setIndex();
    }
    //adds a fresh vm object to the vm list
    static void addVM(){
        vmList.add(new virtualMachine(null, null, null, null, null));
        setIndex();
    }
    //removes hub at specified index
    static void removeHub(int index){
        for ( virtualMachine vm : vmList ){
            vm.hubRemoved(hubList.get(index).getSubnet()); //passes the removed hubs subnet to the virtual machine object for processing
        }
        for ( hub hub : hubList){
            hub.icon.removeClickListeners(); //removes all click listeners from hub objects
        }
        hubList.get(index).removeIcon();  
        hubList.remove(index);
        setIndex();
        setNetworkConnections();
    }
    //removes vm object at specified index
    static void removeVM(int index){
        for ( hub hub : hubList){//goes through hub list
            for ( String address : vmList.get(index).getAddress()){
                if (address != null){ //if there is an address on this port
                    if ( hub.getSubnet() == getthirdOctet(address) ){ //if the address is on the current hub
                        hub.removeAddress(getlastOctet(address));     //remove the address from the allocated address list
                    }
                }
            }
            hub.removeInf(vmList.get(index).getName(), null); //remove infs corresponding to removed vm
        }
        for ( virtualMachine vm : vmList ){
            vm.icon.removeClickListeners(); //removes all click listeners from vm objects
        }
        vmList.get(index).removeIcon();
        vmList.remove(index);
        setIndex();
        setNetworkConnections();
    }

    //returns the third octet of a given ip
    public static int getthirdOctet(String s){
        String[] Temp = s.split("\\.");
        int i = Integer.parseInt(Temp[2]);
        return i;  
    }
    //returns the last octet of a given ip
    static int getlastOctet(String s){
        String[] Temp = s.split("\\.");
        s = Temp[3];
        s = s.replaceAll("\\D+","");
        int i = Integer.parseInt(s);
        return i; 
    }
    //sets the indexes for vm and hub objects, required for various operations
    static void setIndex(){
        int i = 0;
        while( i < NetworkReader.vmList.size()){
            NetworkReader.vmList.get(i).setIndex(i);
            i++;
        }
        i = 0;
        while( i < NetworkReader.hubList.size()){
            NetworkReader.hubList.get(i).setIndex(i);
            i++;
        }
    }


}  
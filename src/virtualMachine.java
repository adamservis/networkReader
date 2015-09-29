
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this defines our virtual machine object and it's related methods
 * @author Adam
 */
public class virtualMachine {
    //string values for machine name, OS, version, and source
    
    String machineName;
    String OS; 
    String version; 
    String source;
    //list of network addresses used by the machine, position in list corresponds to ethernet port
    public LinkedList<String> addressList = new LinkedList<>();
    //list of subnets virtual machine belongs to, will be used to map out network
    public ArrayList<Integer> subnetList = new ArrayList<>();
    int index;//location of object on vm list
    DraggableImageComponent icon = new DraggableImageComponent();//defines the icon for this vm
  
    
    public virtualMachine(String name, String O, String vers, String src, List<String> address){
        machineName = name;
        OS = O;
        version = vers;
        source = src;
        
        if (address != null){  //if loaded from cfg (objects created from gui will have address as null)
            int i = 0;
            addressList = new LinkedList<>(address);
            while (i < addressList.size()){ //goes through address list
                if (addressList.get(i) != null){ //if value is not null
                    subnetList.add(NetworkReader.getthirdOctet(addressList.get(i)));     //adds the subnet of that address to the subnet list
                }
                i++;
            }
        }
        
        //sets the image for this network object
        Image img = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/images/1.jpeg"));
        //Creates a draggableImageComponent and adds loaded image
        icon.setImage(img);//Sets image
        icon.setAutoSize(true);//The component get ratio w/h of source image
        icon.setOverbearing(true);//On click ,this panel gains lowest z-buffer
        icon.setBorder(new LineBorder(Color.black, 1));
        icon.setName(machineName);

        //A small randomization of object size/position
        int delta = HomeForm.JPanel1.getWidth() / 4;
        int cx = HomeForm.JPanel1.getWidth() / 2;
        int cy = HomeForm.JPanel1.getHeight() / 2;
        icon.setSize(delta / 4 , delta / 4);
        icon.setLocation(cx + getRandom(delta) - icon.getWidth() / 2, cy + getRandom(delta) - icon.getHeight() / 2);
        
        //adds icon to form upon creation
        HomeForm.JPanel1.add(icon);
        NetworkReader.form.repaint();
    }
    //used in icons initial placement
    public static int getRandom(int range) {
        int r = (int) (Math.random() * range) - range;
        return r;
    }
    
    //TEST METHOD
    //part of testset to make sure objects are being manipulated properly
    void printInfo(){
        System.out.println(machineName);   
        System.out.println(OS);
        System.out.println(version);
        System.out.println(source);
        System.out.println(addressList);
        System.out.println(subnetList);
    }
    
    //opens a form for this vm object when called
    public void form(){
        VMdata s = new VMdata(index); //opens form on button click
        s.setVisible(true);
    }
    
    //sets the index of this vm object
    void setIndex(int i){
        index = i;
        icon.addMouseListenersVM(i);
    }
   
    //sets the name of the vm object, alters hub inf information accordingly
    public void setName(String name){
        
        for ( hub hub : NetworkReader.hubList){
            hub.removeInf(machineName, name);
        }
        
        machineName = name;
        icon.setName(machineName);
        NetworkReader.form.repaint();
        
    }
    
    //sets the os of the vm object
    public void setOS(String O){
        OS = O;
    }
    
    //sets the version of the vm object
    public void setVersion(String vers){
        version = vers;
    }
    //sets the source of the vm object
    public void setSource(String src){
        source = src; 
    }
    //hands the designated vm icon off
    DraggableImageComponent getIcon(){    
        return icon;
    }
    //returns the name of the vm object
    String getName(){
        return machineName;
    }
    //returns the os of the vm object
    String getOS(){
        return OS;
    }
    //returns the IP addresses of the vm object
    public List<String> getAddress(){
        return addressList;
    }
    //gets the ip address and ethernet port i
    public String getAddress(int i){
        return addressList.get(i);
    }
    //gets the vm objects version
    String getVersion(){
        return version;
    }
    //gets the vm objects source
    String getSource(){
        return source;
    }
    //gets the vm objects index
    int getIndex(){
        return index;
    }
    //removes subnet value from lists of subnets this vm is a member of
    //removes all addresses tied to that subnet
    //called when hub is deleted
    public void hubRemoved(int subnet){
        int i = 0;
        while ( i < subnetList.size()){
            if ( subnetList.get(i) == subnet ){
                subnetList.remove(i);
            }
            i++;
        }
        i = 0;
        while ( i < addressList.size()){
            if ((addressList.get(i) != null ))
                if ( subnet == NetworkReader.getthirdOctet( addressList.get(i) ) ){
                    addressList.remove(i);
                }
            i++;
        }
    }
    
    //removes address at ethernet port, returns subnet value of address
    
    int removeAddress(int index){
        int subnet = 0;
        int i = 0;
        
        if (addressList.get(index) != null){
               subnet = NetworkReader.getthirdOctet(addressList.get(index));
        }
        //removes subnet value   
        while (i < subnetList.size()){
            if (subnetList.get(i) == subnet){
                subnetList.remove(i);
            }
            i++;
        }   addressList.set(index, null);
        return subnet;
    }
    
    //adds line data for each connection this vm is a part of
    //adds a line object representing each connection
    public void setConnectionData(){
        int i = 0;
        while ( i < NetworkReader.hubList.size()){
            for ( int  j : subnetList)
                if ( j == NetworkReader.hubList.get(i).getSubnet()){
                    NetworkReader.connections.addLine( 
                        icon.getX(),
                        icon.getY(),
                        NetworkReader.hubList.get(i).getIcon().getX(),
                        NetworkReader.hubList.get(i).getIcon().getY()
                    );         
                }
            i++;
        } 
    }
    //removes the icon for this vm object
    public void removeIcon(){
        HomeForm.JPanel1.remove(icon);
    }
}

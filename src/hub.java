 



import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import static java.util.regex.Pattern.matches;
import javax.swing.border.LineBorder;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this is the hub object and its related methods and parts
 * @author Adam
 */
public class hub {
    
    private String hubName;//name of hub
    private String subnetFull; //full subnet address eg: 192.168.20.0
    private String netmask;//full netmask (not used in this iteration)
    private int subnet;//subnet would be 20 for the full subnet of 192.168.20.0
    private int index;//location of object on hub list
    
    
    DraggableImageComponent icon = new DraggableImageComponent();//defines the hub icon
    public ArrayList<Integer> clientaddressList = new ArrayList<>();//list of integers for clients added to list eg: 192.168.20.3 would store 3
    public ArrayList<String> infList = new ArrayList<String>(); //stores list of inf data for info output and saving to cfg purposes
    
    public hub( String n, String s, String m){
        hubName = n;
        netmask = m;
        subnetFull = s;
        
        if (subnetFull != null){//if subnetFull is specified (used only if read from file)
            subnet = NetworkReader.getthirdOctet(subnetFull);//sets value for subnet
        }
        
        Image img = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/images/2.jpeg"));//sets hub icon image
        
        //logic for creation of the hubs icon
        icon.setImage(img);//Sets image
        icon.setAutoSize(true);//The component get ratio w/h of source image
        icon.setOverbearing(true);//On click ,this panel gains lowest z-buffer
        icon.setBorder(new LineBorder(Color.black, 1));
        icon.setName(hubName);
        //A small randomization of object size/position  
        int delta = HomeForm.JPanel1.getWidth() / 4;
        int cx = HomeForm.JPanel1.getWidth() / 2;
        int cy = HomeForm.JPanel1.getHeight() / 2;
        icon.setSize(delta / 4 , delta / 4);
        icon.setLocation(cx + getRandom(delta / 2) - icon.getWidth() / 2, cy + getRandom(delta / 2) - icon.getHeight() / 2);
        HomeForm.JPanel1.add(icon);
        NetworkReader.form.repaint();
    }
    //used in locating icon
    public static int getRandom(int range) {
        int r = (int) (Math.random() * range) - range;
        return r;
    }
    //creates a modification form for this object    
    public void form(){
        HUBdata s = new HUBdata(index); //opens form on button click
        s.setVisible(true);
    }
    //NOT USED
    //prints information stored in a hub object 
    //used for testing
    public void printInfo(){
        System.out.println(hubName);
        System.out.println(netmask);
        System.out.println(subnetFull);
        System.out.println(subnet);
        System.out.println(clientaddressList);
        System.out.println(infList);
    }
    
    void setIndex(int i){ //sets the hubs index, adds the mouse listener
        index = i;
        icon.addMouseListenersHUB(i);
    }
    
    public void setName(String name){ //sets the hubs name
        hubName = name;
        icon.setName(hubName); //changes the icons name to the hubs name
        NetworkReader.form.repaint();
    }
    //NOT USED
    public void setMask(String mask){ //sets the mask
        netmask = mask; 
    }
    
    public void setSubnet(int s){//sets the subnet int value
        subnet = s;
    }
    
    public void setSubnetFull(String subnet){//sets full subnet value
        subnetFull = subnet;
    }
    
    void addAddress(int address){//adds an address to the allocated address list
        clientaddressList.add(address);
    }
    
    public void removeIcon(){//removes this hubs icon
        HomeForm.JPanel1.remove(icon);
    }
    
    DraggableImageComponent getIcon(){//gets the icon component for this hub    
        return icon;
    }
    //sets the client address list and inf entry for a hub
    //called at file read
    public void setHubConnections(int sub, int add, String vmName, int eth){
        if (sub == subnet) { //if the subnet passed to this method is this hubs subnet
                String inf = vmName + ".eth" + eth; //figure out the eth file
            if (!clientaddressList.contains(add)){ //if the address is not on the list
                clientaddressList.add(add); //add the address
                infList.add(inf);//add the inf
            } 
        } 
    }
    //NOT USED
    String getMask(){//returns the netmask
        return netmask;
    }
    
    public int getSubnet(){//returns the subnet
        return subnet;
    }
    public String getSubnetFull(){//returns the string value for the full subnet
        return subnetFull;
    }
    String getName(){ //returns the name of the hub
        return hubName;
    }
    public int getIndex(){//returns the index of the hub
        return index;
    }
    public void removeAddress(int i){//removes specified address from the address list
        int j = 0;
        while(j < clientaddressList.size()){
            if (clientaddressList.get(j) == i){
                clientaddressList.remove(j);
                j++;
            }
            j++;
        } 
    }
    
    //removes infToRemove
    //if vmName is not null replace removed value with vmName
    //if vmName is null just remove infToRemove
    public void removeInf(String infToRemove, String vmName){
        int j = 0;
        while (j < infList.size()){ //for the inf list
            String i = infList.get(j); 
            if ( matches(infToRemove + "\\.eth[0-9]" , i)){ //if the inf to remove is on the inflist 
                infList.remove(j);//remove it
                if (vmName != null){
                    String[] netTemp = i.split("\\.");    
                    infList.add(vmName + "." + netTemp[1]);
                }
            }
            j++;
        }
    }
}


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this class is responsible for creating a .cfg file and populating it 
 * with the vm and hub objects in the NetworkReader class
 * @author Adam
 */
public class CreateCFG {
    private File path;
    private boolean append = false;
    
    public CreateCFG(File filePath){
        path = filePath;
    }
    
    public CreateCFG (File filePath, boolean appendValue ){
        path = filePath;
        append = appendValue;
    }
    
    public void writeToCFG () throws IOException{
        int i;
        FileWriter CFGFile = new FileWriter(path, append);
        PrintWriter s = new PrintWriter(CFGFile);
        //prints virtual machine information from objects
        for ( virtualMachine vm : NetworkReader.vmList ){
            i = 0;
            s.println("vm " + vm.getName() + " {");
            s.println("\t" + "os : " + vm.getOS());
            s.println("\t" + "ver : " + vm.getVersion());
            s.println("\t" + "src : " + vm.getSource());
            while ( i < vm.addressList.size() ){
                if ( vm.addressList.get(i) != null ){
                    s.println("\t" + "eth" + i + " : " + vm.getAddress(i));
                }
                i++;
            }
            s.println("}");     
        }
        //prints hub information from objects
        for ( hub hub : NetworkReader.hubList ){
            i = 0;
            s.println("hub " + hub.getName() + " {");
            s.print("\t" + "inf : ");
            while ( i < (hub.infList.size() - 1)){
                s.print(hub.infList.get(i) + ", ");
                i++;
            }
            s.print(hub.infList.get(i));
            s.println();
            s.println("\t" + "subnet : " + hub.getSubnetFull());
            s.println("\t" + "netmask : " + hub.getMask());
            s.println("}");
        }

        i = 0;
        //prints the partial solution
        s.println("partial_solution {");
        while ( i < NetworkReader.hubList.size() ){
            for(String inf : NetworkReader.hubList.get(i).infList){
                s.println("(" + inf + " v2.vinf2" + i + ")");
            }
            i++;
        }
        s.println("}");
        s.close();
        
    }
    
}

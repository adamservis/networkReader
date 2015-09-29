
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this is the class defining our load menu
 * @author Adam
 */
public class LoadChooser {
    
    public LoadChooser() throws FileNotFoundException {
        
        JFileChooser chooser = new JFileChooser();
        //filters non cfg files by default
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "CFG FILE", "cfg");
        
        chooser.setFileFilter(filter);
        int input = chooser.showOpenDialog(NetworkReader.form);
        if (input == JFileChooser.APPROVE_OPTION){ 
            File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            if (!filename.endsWith(".cfg")){ //run if file is not of cfg type
                Proceed s = new Proceed("File is not of .cfg type.  Attempt to load anyway?", filename, null);
                s.setVisible(true);
            } else { //run if file is of cfg type
                //clears workspace
                NetworkReader.clearNetwork();
                NetworkReader.connections.clearLines();
                NetworkReader.form.repaint();
                //loads new file
                readNetwork r = new readNetwork(filename);
                NetworkReader.vmList = r.getvmList();
                NetworkReader.hubList = r.gethubList();
                NetworkReader.setIndex();
                NetworkReader.setNetworkConnections();
            } 
        }      
    }  
}


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * class defining the save menu
 * @author Adam
 */
public class SaveChooser {
    
    public SaveChooser() throws FileNotFoundException, IOException { 
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter( //filters extensions
            "CFG FILE", "cfg");
        
        chooser.setFileFilter(filter);
        
        int input = chooser.showSaveDialog(NetworkReader.form);
        if (input == JFileChooser.APPROVE_OPTION){ //if we hit okay
            if(chooser.getSelectedFile().getName().endsWith(".cfg")){ //if the file ends with cfg
                if(chooser.getSelectedFile().exists()){ //if the file we want to save as already exists
                    Proceed p = new Proceed("File exists, overwrite?", null, 
                            chooser.getSelectedFile());
                    p.setVisible(true);
                }
                CreateCFG cfg = new CreateCFG(chooser.getSelectedFile());
                cfg.writeToCFG();
            } else {
                Error e = new Error("Type must be of .cfg");
                e.setVisible(true);
            }
        }
    }
}

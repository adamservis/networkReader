
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this glass pane panel is called to block out undesired user input
 * @author Adam
 */
public class glassPane extends JPanel{
    public glassPane(){

        setBounds( 0, 0, 1000, 1000);
        setVisible(false);
        setOpaque(false);
        setBackground(Color.RED);
        
        addMouseListener(new MouseAdapter() {
                

        @Override
        public void mousePressed(MouseEvent e) {

        }

        });
   
    }
}
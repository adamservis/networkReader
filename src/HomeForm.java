


import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//Main form for the software
public class HomeForm extends javax.swing.JFrame {
    //glass pane used to keep icons from being manipulated while a form is open
    public glassPane glassPane = new glassPane();   
    //lists used in error checking
    private ArrayList<Integer> problemVMS = new ArrayList<>();
    private ArrayList<Integer> problemHUBS = new ArrayList<>();
    //initial setting for save check
    //if true allows the ui to pain circles around specified vms
    static boolean saveCheck = false;
        
        
    public HomeForm() {
        //adds the glass pane
        add(glassPane);
        initComponents();
        super.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel1 = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g)
            {
                int i = 25;
                super.paintComponent(g); // Do the original draw

                if (NetworkReader.connections.lines != null)
                for ( Connection.Line line : NetworkReader.connections.lines ) {
                    g.setColor(line.color);
                    g.drawLine(line.x1 + i, line.y1 + i, line.x2 + i, line.y2 + i);
                }
                if (saveCheck){
                    if ((!problemVMS.isEmpty())){
                        for (Integer vmIndex : problemVMS){
                            g.drawOval(NetworkReader.vmList.get(vmIndex).getIcon().getX() - i,
                                NetworkReader.vmList.get(vmIndex).getIcon().getY() - i,
                                100, 100);
                        }
                    }
                    if ((!problemHUBS.isEmpty())){
                        for (Integer hubIndex : problemHUBS){
                            g.drawOval(NetworkReader.hubList.get(hubIndex).getIcon().getX() - i,
                                NetworkReader.hubList.get(hubIndex).getIcon().getY() - i,
                                100, 100);
                        }
                    }
                    problemVMS.clear();
                    problemHUBS.clear();
                }
            }
        };
        loadbutton = new javax.swing.JButton();
        addbutton = new javax.swing.JButton();
        clearbutton = new javax.swing.JButton();
        savebutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Network Setup");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        JPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout JPanel1Layout = new javax.swing.GroupLayout(JPanel1);
        JPanel1.setLayout(JPanel1Layout);
        JPanel1Layout.setHorizontalGroup(
            JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );
        JPanel1Layout.setVerticalGroup(
            JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        loadbutton.setLabel("Load Existing");
        loadbutton.setPreferredSize(new java.awt.Dimension(125, 23));
        loadbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadbuttonActionPerformed(evt);
            }
        });

        addbutton.setText("Add Device");
        addbutton.setPreferredSize(new java.awt.Dimension(125, 23));
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
            }
        });

        clearbutton.setText("Clear Workspace");
        clearbutton.setPreferredSize(new java.awt.Dimension(125, 23));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });

        savebutton.setText("Save");
        savebutton.setPreferredSize(new java.awt.Dimension(125, 23));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(loadbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(savebutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clearbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(savebutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //begins load operations
    private void loadbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadbuttonActionPerformed
        try{
            LoadChooser s = new LoadChooser();
        } catch (FileNotFoundException e){
        }
    }//GEN-LAST:event_loadbuttonActionPerformed
    //calls adddevice.java
    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed

        AddDevice s = new AddDevice(); //opens form on button click
        s.setVisible(true);
    }//GEN-LAST:event_addbuttonActionPerformed
    
    private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbuttonActionPerformed

        Proceed p = new Proceed("Clearing without saving will cause all data to be lost, proceed?", null, null);
        p.setVisible(true);
    }//GEN-LAST:event_clearbuttonActionPerformed
    //save button runs a check prompting the user if a machine has blank fields
    //if okay allows save
    private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttonActionPerformed
        if (!(NetworkReader.vmList.isEmpty() && NetworkReader.hubList.isEmpty())){//wont do anything if no objects are on the workspace
        saveCheck s = new saveCheck();
        //runs the save check for the current network config
        s.saveCheck();
        
        if (s.cfgReady()){ //if everything comes back OKAY try to run the save chooser
            try {
                SaveChooser k = new SaveChooser();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //if there is a problem, show an error, and circle the vms in question
            problemVMS = s.getProblemVMS();
            problemHUBS = s.getProblemHUBS();
            repaint();
            
            String S = "WARNING: CIRCLED NETWORK DEVICES ARE MISSING REQUIRED FIELDS";
            Error e = new Error(S);
            e.setVisible(true);
        }
        }
    }//GEN-LAST:event_savebuttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel JPanel1;
    private javax.swing.JButton addbutton;
    private javax.swing.JButton clearbutton;
    private javax.swing.JButton loadbutton;
    private javax.swing.JButton savebutton;
    // End of variables declaration//GEN-END:variables
}



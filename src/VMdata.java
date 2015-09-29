
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this form handles the virtual machine objects data manipulation
 * @author Joe/Adam
 */
public class VMdata extends javax.swing.JFrame {
    //y correlates to the y coordinate of the first ethernet add/remove button
    int y = 167; 
    //index is the index of the virtual machine that called the form
    int index;
    //instantiates remove port button
    JButton removePortButton = new JButton();
    //list of address buttons
    public ArrayList<JButton> buttonList = new ArrayList<>();
    //makes sure an action listener is only added once
    int removeThing = 0;
    

   
    
    public VMdata(int i) {
        
        //sets the location at the mouse pointer
        this.setLocation(NetworkReader.p);
        //removes border
        this.setUndecorated(true);
        //sets the glass pane visible
        NetworkReader.form.glassPane.setVisible(true); 
        index = i;
        initComponents(); 
        addressList();
    }
    
    //instantiates the remove port button
    private void removePortButton(final int i){

        if ( ( i == NetworkReader.vmList.get(index).getAddress().size() - 1 ) 
                && ( NetworkReader.vmList.get(index).addressList.getLast() == null ) ){
            
            removePortButton.setBounds ( 45 , (y + 15 * i), 14, 14);
            removePortButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon3.jpg")));
            add(removePortButton);
            repaint();
           
            if (removeThing == 0){ //makes sure action listeners are only added once
                removePortButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) { 
                        
                        NetworkReader.vmList.get(index).addressList.remove(NetworkReader.vmList.get(index).addressList.size() - 1);//removes the last address
                        remove(removePortButton);//removes this button
                        
                        remove(buttonList.get(NetworkReader.vmList.get(index).addressList.size()));//removes address button associated with removed address from the form
             
                        buttonList.remove(NetworkReader.vmList.get(index).addressList.size());//removes address button associated with removed address from the button list
                        
                        //if something is in the address list, and the last entry is null, create a new button.
                        if (!NetworkReader.vmList.get(index).addressList.isEmpty()){
                            if (NetworkReader.vmList.get(index).addressList.getLast() == null){
                                removePortButton(NetworkReader.vmList.get(index).addressList.size() - 1);
                            }
                        }
                        //draws network
                        NetworkReader.drawConnections();
                        //redraws list
                        refreshList();            
                    }   
                });
                populateList();
                removeThing++;
            }
        }
    }
    //method to refresh the list
    public void refreshList(){
        populateList();
        repaint();
    }
    //method to populate the form list with eth addresses
    public void populateList(){
        int i = 0;
        list1.clear();
        while ( i < NetworkReader.vmList.get(index).getAddress().size()){
            list1.add("Eth" + i + ": " + NetworkReader.vmList.get(index).getAddress(i));
            i++;
        }
    }
    //populates the form list, and also adds the address and remove address buttons
    private void addressList(){
        int i = 0;
        while ( i < NetworkReader.vmList.get(index).getAddress().size()){
            list1.add("Eth" + i + ": " + NetworkReader.vmList.get(index).getAddress(i));  
            addressButton(i);
            removePortButton(i);
            i++;
        } 
    }

    private void addressButton(final int i){//defines the buttons used for address manipulation
        final JButton addButton = new javax.swing.JButton();
        addButton.setBounds ( 205 , (y + 15 * i), 14, 14);
        
        buttonList.add(addButton);
        if(NetworkReader.vmList.get(index).addressList.get(i) == null){//sets images corresponding to the address buttons current operation
            addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon4.jpg")));
        } else {
            addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon5.jpg")));
        }         
        
        add(addButton);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (NetworkReader.vmList.get(index).addressList.get(i) == null){//eth port corresponding to this button is empty
                    save();//save current text input
                    addConnection s = new addConnection(index, i); //opens next form for address selection
                    s.setVisible(true); 
                    dispose();
                }  else { //if there is an address at the current port
                    NetworkReader.removeAddress(index, i); //calls remove address for the current vm on the current port
                    removePortButton(i); //creates a remove ethernet address button on this port
                    NetworkReader.drawConnections(); //redraws the connections to reflect changes
                    addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon4.jpg"))); //swaps image
                    populateList();
                    repaint();      
                }
            }   
        }); 

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OSlabel = new javax.swing.JLabel();
        Versionlabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        OSTextField = new javax.swing.JTextField();
        VersionTextField = new javax.swing.JTextField();
        SourceTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        list1 = new java.awt.List();
        VMRemoveButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        namefield = new javax.swing.JTextField();
        addEthernetPort = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        OSlabel.setText("OS:");

        Versionlabel.setText("Version:");

        jLabel1.setText("Source:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        OSTextField.setText(NetworkReader.vmList.get(index).getOS());

        VersionTextField.setText(NetworkReader.vmList.get(index).getVersion());
        VersionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VersionTextFieldActionPerformed(evt);
            }
        });

        SourceTextField.setText(NetworkReader.vmList.get(index).getSource());
        SourceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SourceTextFieldActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        VMRemoveButton.setText("Remove");
        VMRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VMRemoveButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Connections:");

        jLabel4.setText("Name:");

        namefield.setText(NetworkReader.vmList.get(index).getName());
        namefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namefieldActionPerformed(evt);
            }
        });

        addEthernetPort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon4.jpg"))); // NOI18N
        addEthernetPort.setMaximumSize(new java.awt.Dimension(14, 14));
        addEthernetPort.setMinimumSize(new java.awt.Dimension(14, 14));
        addEthernetPort.setPreferredSize(new java.awt.Dimension(14, 14));
        addEthernetPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEthernetPortActionPerformed(evt);
            }
        });

        jLabel5.setText("Add Ethernet Port");

        cancelButton.setText("Close");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(OSlabel)
                                .addComponent(Versionlabel))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SourceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VersionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namefield, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(VMRemoveButton)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(addEthernetPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(91, 91, 91)
                                            .addComponent(saveButton))
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(cancelButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(namefield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OSlabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VersionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Versionlabel))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SourceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addEthernetPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(VMRemoveButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SourceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SourceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SourceTextFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        save();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void VersionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VersionTextFieldActionPerformed
    
    }//GEN-LAST:event_VersionTextFieldActionPerformed
    //vm removal stuff
    private void VMRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VMRemoveButtonActionPerformed
        NetworkReader.removeVM(index);
        NetworkReader.drawConnections();
        NetworkReader.form.glassPane.setVisible(false);
        super.dispose();
    }//GEN-LAST:event_VMRemoveButtonActionPerformed

    private void namefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namefieldActionPerformed
    //logic for the add ethernet port button
    private void addEthernetPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEthernetPortActionPerformed
        if ( NetworkReader.vmList.get(index).addressList.size() < 7 ){ //only allows ports 0-6 because having more would be a huge pain to implement
            NetworkReader.vmList.get(index).addressList.add(null);  //adds a null entry to the address list
            addressButton(NetworkReader.vmList.get(index).addressList.size() - 1); //calls address button for the new entry
            remove(removePortButton); //removes the remove port button for the previous entry(if there is one)
            removePortButton(NetworkReader.vmList.get(index).addressList.size() - 1);//creates a new remove port button
            populateList();
            repaint();
        }
    }//GEN-LAST:event_addEthernetPortActionPerformed
    //cancels out of the form
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        NetworkReader.form.glassPane.setVisible(false);
        super.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    //writes data to object
    private void save(){
        NetworkReader.vmList.get(index).setName(namefield.getText());
        NetworkReader.vmList.get(index).setOS(OSTextField.getText());
        NetworkReader.vmList.get(index).setVersion(VersionTextField.getText());
        NetworkReader.vmList.get(index).setSource(SourceTextField.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField OSTextField;
    private javax.swing.JLabel OSlabel;
    private javax.swing.JTextField SourceTextField;
    private javax.swing.JButton VMRemoveButton;
    private javax.swing.JTextField VersionTextField;
    private javax.swing.JLabel Versionlabel;
    private javax.swing.JButton addEthernetPort;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private java.awt.List list1;
    private javax.swing.JTextField namefield;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

}


import java.awt.MouseInfo;
import javax.swing.JButton;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *this calls creates a data form for the hub at a specified index
 * @author Joe and adam
 */
public class HUBdata extends javax.swing.JFrame {
    //index of hub
    int index;
    //button used for subnet manipulation
    JButton subnetButton = new javax.swing.JButton();
    
    public HUBdata(int i) {
        this.setLocation(NetworkReader.p);
        this.setUndecorated(true);
        NetworkReader.form.glassPane.setVisible(true);
        index = i;
        initComponents();
        subnetButton();
        populateList();
    }
    
    private void subnetButton(){
        //sets the location for the subnet button
        //couldn't get it just right with the layout manager
        subnetButton.setBounds ( 200 , 162, 14, 14);
        add(subnetButton); 
        
        subnetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //sets the name to the currently typed name
                    NetworkReader.hubList.get(index).setName(nametext.getText());
                    dispose();
                    editSubnet s = new editSubnet(index); //opens form on button click
                    s.setVisible(true); 
            }   
        }); 

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Versionlabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Versionlabel1 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        HUBRemoveButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        nametext = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        Versionlabel.setText("Subnet:");

        jLabel1.setText("Netmask:");

        Versionlabel1.setText("INF:");

        HUBRemoveButton.setText("Remove");
        HUBRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HUBRemoveButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Name:");

        nametext.setText(NetworkReader.hubList.get(index).getName());
        nametext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nametextActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        String s;

        if ( NetworkReader.hubList.get(index).getSubnetFull() != null ){
            s = NetworkReader.hubList.get(index).getSubnetFull();
        } else {
            s = "Assign Subnet";
        }
        jLabel3.setText(s);

        jLabel4.setText("255.255.255.0");

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
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(Versionlabel)
                    .addComponent(Versionlabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(nametext)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HUBRemoveButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nametext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Versionlabel1)
                    .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Versionlabel)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(HUBRemoveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //logic for the remove button
    private void HUBRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HUBRemoveButtonActionPerformed
        NetworkReader.removeHub(index);
        NetworkReader.drawConnections();
        NetworkReader.form.glassPane.setVisible(false);
        super.dispose(); // TODO add your handling code here:
    }//GEN-LAST:event_HUBRemoveButtonActionPerformed

    private void nametextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nametextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nametextActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        //sets the name when 'save' is selected
        NetworkReader.hubList.get(index).setName(nametext.getText());

    }//GEN-LAST:event_saveButtonActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        NetworkReader.form.glassPane.setVisible(false);
        super.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    //populates the list of 'inf' information
    private void populateList(){
        int i = 0;
        list1.clear();
        while ( i < NetworkReader.hubList.get(index).infList.size()){
            list1.add(NetworkReader.hubList.get(index).infList.get(i));
            i++;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HUBRemoveButton;
    private javax.swing.JLabel Versionlabel;
    private javax.swing.JLabel Versionlabel1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private java.awt.List list1;
    private javax.swing.JTextField nametext;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}

package GUI;


import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win 10
 */
public class Replace extends javax.swing.JDialog {

    /**
     * Creates new form Replace
     */
    MyNotepad dad;
    int fromIndex=0;
    
    public Replace(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.dad=(MyNotepad) parent;
        initComponents();
        this.setTitle("Replace");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfFindWhat = new javax.swing.JTextField();
        tfReplaceWith = new javax.swing.JTextField();
        btnFindNext = new javax.swing.JButton();
        btnReplace = new javax.swing.JButton();
        btnReplaceAll = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cbMatchCase = new javax.swing.JCheckBox();
        cbWrapAround = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Find what:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Replace with:");

        btnFindNext.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnFindNext.setText("Find Next");
        btnFindNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindNextActionPerformed(evt);
            }
        });

        btnReplace.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnReplace.setText("Replace");
        btnReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplaceActionPerformed(evt);
            }
        });

        btnReplaceAll.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnReplaceAll.setText("Replace All");
        btnReplaceAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplaceAllActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cbMatchCase.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbMatchCase.setText("Match case");

        cbWrapAround.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbWrapAround.setText("Wrap around");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfFindWhat, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfReplaceWith, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbWrapAround)
                            .addComponent(cbMatchCase))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReplace, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReplaceAll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfFindWhat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfReplaceWith, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnFindNext, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReplace, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReplaceAll, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cbMatchCase)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbWrapAround)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindNextActionPerformed
        String findWhat=tfFindWhat.getText();
        String textDad = dad.txtContent.getText();
        String replaceWith = tfReplaceWith.getText();
        if(!findWhat.isEmpty())
        {
            if(!cbMatchCase.isSelected())//không phân biệt hoa thường
            {
                textDad=textDad.toLowerCase();
                findWhat=findWhat.toLowerCase();
            }
            if(cbWrapAround.isSelected())
            {
                int pos=textDad.indexOf(findWhat, fromIndex);
                if(pos>=0)
                {
                    dad.txtContent.select(pos, pos + findWhat.length());
                    fromIndex = pos + findWhat.length();
                }
                else{
                    fromIndex=0;
                    btnFindNextActionPerformed(evt);
                }
            }
            else{
                int pos=textDad.indexOf(findWhat, fromIndex);
                if(pos>=0)
                {
                    dad.txtContent.select(pos, pos + findWhat.length());
                    fromIndex = pos + findWhat.length();
                }
                else
                    JOptionPane.showMessageDialog(this, "Can not find "+findWhat);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Find what is empty");
        }
    }//GEN-LAST:event_btnFindNextActionPerformed

    private void btnReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplaceActionPerformed
        String findWhat = tfFindWhat.getText();
        String textDad = dad.txtContent.getText();
        String replaceWith = tfReplaceWith.getText();
//        if(cbWrapAround.isSelected())
//            JOptionPane.showMessageDialog(this, "Wrap around is not supported");
        if(!findWhat.isEmpty() && !replaceWith.isEmpty())
        {
            if(!cbMatchCase.isSelected())//không phân biệt hoa thường
            {
                textDad=textDad.toLowerCase();
                findWhat=findWhat.toLowerCase();
            }
            int pos = textDad.indexOf(findWhat, 0);
            if (pos >= 0) {
                dad.txtContent.select(pos, pos + findWhat.length());
                dad.txtContent.replaceRange(replaceWith, pos, pos + findWhat.length());
                fromIndex = pos + findWhat.length();
            } else {
                JOptionPane.showMessageDialog(this, "Can not find " + findWhat);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Find what or replace with is empty");
        }
    }//GEN-LAST:event_btnReplaceActionPerformed

    private void btnReplaceAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplaceAllActionPerformed
        String findWhat = tfFindWhat.getText();
        String textDad = dad.txtContent.getText();
        String replaceWith = tfReplaceWith.getText();
//        if(cbWrapAround.isSelected())
//            JOptionPane.showMessageDialog(this, "Wrap around is not supported");
        if(!findWhat.isEmpty() && !replaceWith.isEmpty())
        {
            if(!cbMatchCase.isSelected())//không phân biệt hoa thường
            {
                textDad=textDad.toLowerCase();
                findWhat=findWhat.toLowerCase();
            }
            int pos = textDad.indexOf(findWhat, 0);
            System.out.println(pos);
            while(pos >= 0) {
                dad.txtContent.select(pos, pos + findWhat.length());
                dad.txtContent.replaceRange(replaceWith, pos, pos + findWhat.length());
                fromIndex = pos + findWhat.length();
                pos = textDad.indexOf(findWhat, fromIndex);
                if(pos<0)
                {
                    btnReplaceAllActionPerformed(evt);
                    if(pos<0)
                        break;
                }
            } 
            if(pos<0){
                JOptionPane.showMessageDialog(this, "Can not find " + findWhat);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Find what or replace with is empty");
        }
        
    }//GEN-LAST:event_btnReplaceAllActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Replace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Replace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Replace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Replace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Replace dialog = new Replace(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFindNext;
    private javax.swing.JButton btnReplace;
    private javax.swing.JButton btnReplaceAll;
    private javax.swing.JCheckBox cbMatchCase;
    private javax.swing.JCheckBox cbWrapAround;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tfFindWhat;
    private javax.swing.JTextField tfReplaceWith;
    // End of variables declaration//GEN-END:variables
}

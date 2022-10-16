/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Manager;

import database.User;
import static View.Manager.them_nguoi_dung.encodeBase64String;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ADMIN
 */
public class chinh_sua_nguoi_dung extends javax.swing.JDialog {

    /**
     * Creates new form EditUser
     */
    public chinh_sua_nguoi_dung(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        m = (quan_ly_frame) parent;
        this.setLocationRelativeTo(null);
    }
    
    void getValueFromTable(User user){
        System.out.println("okeeeeeeee");
        tfCMND.setText(user.getCmnd());
        tfName.setText(user.getFullName());
        tfPhone.setText(user.getPhone()+"");
        tfAddress.setText(user.getAddress());
        lbImageUser.setIcon(new ImageIcon(user.getCmnd()+".jpg"));
        //
        lastCMND=user.getCmnd();
        base64Image=user.getAvt();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTitleImage = new javax.swing.JLabel();
        btnChooseImage = new javax.swing.JButton();
        tfCMND = new javax.swing.JTextField();
        lbImageUser = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        btnEditUser = new javax.swing.JButton();
        tfPhone = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        tfAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Di động:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 126, -1, -1));

        jLabel4.setText("Địa chỉ:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 166, -1, -1));

        lbTitleImage.setText("Ảnh");
        jPanel1.add(lbTitleImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, -1, -1));

        btnChooseImage.setText("Chọn Ảnh");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });
        jPanel1.add(btnChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 150, -1));

        tfCMND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCMNDActionPerformed(evt);
            }
        });
        jPanel1.add(tfCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 40, 162, -1));

        lbImageUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(lbImageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 150, 150));
        jPanel1.add(tfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 79, 162, -1));

        btnEditUser.setText("Sửa");
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 230, -1, -1));
        jPanel1.add(tfPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 123, 162, -1));

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 230, -1, -1));

        tfAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAddressActionPerformed(evt);
            }
        });
        jPanel1.add(tfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 163, 162, -1));

        jLabel1.setText("CMND:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 43, -1, -1));

        jLabel2.setText("Họ và tên:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 82, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ảnh PNG | JPG | JPEG", "jpg", "png", "jpeg");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int values = fileChooser.showOpenDialog(null);
        if (values == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
            Image image = null;
            try {
                image = ImageIO.read(f);
                BufferedImage resizedImage = new BufferedImage(lbImageUser.getWidth(),
                        lbImageUser.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = resizedImage.createGraphics();
                graphics2D.drawImage(image, 0, 0, lbImageUser.getWidth(), lbImageUser.getHeight(), null);
                graphics2D.dispose();
                lbImageUser.setIcon(new ImageIcon(resizedImage));
                ImageIO.write(resizedImage, "jpg", new File("test.jpg"));
                base64Image = encodeBase64String("test.jpg");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("đã hủy");
        }

    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void tfCMNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCMNDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCMNDActionPerformed

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        // TODO add your handling code here:
        if(
            tfCMND.getText().isEmpty()||
            tfName.getText().isEmpty()||
            tfPhone.getText().isEmpty()||
            tfAddress.getText().isEmpty()
        ){
            JOptionPane.showMessageDialog(this,"vui lòng không để trống!");
        }else{
            if(base64Image==null){
                JOptionPane.showMessageDialog(this,"Vui lòng chọn ảnh!");
            }else{
                User user = new User(tfCMND.getText()
                        ,tfName.getText(),
                        Integer.parseInt(tfPhone.getText()),
                        tfAddress.getText(),
                        base64Image);
                m.editUser(lastCMND, user);
            }
        }
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAddressActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(EditUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EditUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EditUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EditUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EditUser dialog = new EditUser(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    private String base64Image=null;
    private quan_ly_frame m;
    private String lastCMND;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbImageUser;
    private javax.swing.JLabel lbTitleImage;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfCMND;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    // End of variables declaration//GEN-END:variables
}

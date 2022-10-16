/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Manager;

import View.Main.Trang_chu;
import dieu_khien.trinh_dieu_khien;
import database.Card;
import database.User;
//import static View.Manager.them_nguoi_dung.encodeBase64String;
//import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



/**
 *
 * @author NguyenDuong
 */
public final class quan_ly_frame extends javax.swing.JFrame {

    /**
     * Creates new form ManagerFrame 
     */
    public quan_ly_frame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("QUẢN LÝ NGÂN HÀNG");
        getDataUser();
        getDataCard();
        formatter = new DecimalFormat("###,###,###");
        File f1 = new File(".jpg");
        File f2 = new File(".jpg");
        File f3 = new File(".jpg");
        Image image1=null,image2=null,image3 = null;
        try {
            image1 = ImageIO.read(f1);
            image2 = ImageIO.read(f2);
            image3 = ImageIO.read(f3);
        } catch (IOException ex) {
        }
        
    }
    
    public void getDataUser(){
        List<User> listUser = new ArrayList<>();
        tableModel = (DefaultTableModel) tblCustomer.getModel();
        tableModel.setRowCount(0);
        trinh_dieu_khien m = new trinh_dieu_khien();
        listUser =m.ShowUser(listUser);
        System.out.println(listUser.get(0).getCmnd());
        for(int i=0; i< listUser.size();i++){
            User user = listUser.get(i);
            Vector row = new Vector<>();
            row.add(user.getCmnd());
            row.add(user.getFullName());
            row.add(user.getPhone());
            row.add(user.getAddress());
            row.add(user.getAvt());
            tableModel.addRow(row);
            decoder(user.getAvt(), user.getCmnd()+".jpg");
        }
        TableColumnModel tableColumnModel = tblCustomer.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(60);
        tableColumnModel.getColumn(1).setPreferredWidth(120);
        tableColumnModel.getColumn(2).setPreferredWidth(50);
        tableColumnModel.getColumn(3).setPreferredWidth(60);
        tableColumnModel.getColumn(4).setPreferredWidth(120);
        tblCustomer.setRowHeight(40);
        tblCustomer.setModel(tableModel);
        tblCustomer.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tblCustomer.getSelectedRow()>=0){
                    lbCMND.setText(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString());
                    lbName.setText(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 1).toString());
                    lbPhone.setText(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 2).toString());
                    lbAddress.setText(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 3).toString());
                    //show image
                    lbImage.setIcon(new ImageIcon(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString()+".jpg"));
                }     
            }    
        });
    };
    
    public void addUser(User user){
        trinh_dieu_khien m = new trinh_dieu_khien();
        if(m.AddUser(user)){
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            adduser.dispose();
            getDataUser();
        }else{
            JOptionPane.showMessageDialog(this, "Lỗi thêm!");
        }
    }
    
    public void editUser(String lastCMND, User user){
        trinh_dieu_khien m = new trinh_dieu_khien();
       if(m.EditUser(lastCMND, user)){
           JOptionPane.showMessageDialog(this, "Sửa thành công!");
           edituser.dispose();
           getDataUser();
           lbCMND.setText(user.getCmnd());
           lbName.setText(user.getFullName());
           lbPhone.setText(user.getPhone()+"");
           lbAddress.setText(user.getAddress());
           //show image  
            ImageIcon imageIcon = new ImageIcon(user.getCmnd()+".jpg");
            imageIcon.getImage().flush();
            lbImage.setIcon(imageIcon);
           
       }else{
           JOptionPane.showMessageDialog(this, "Sửa thất bại!");
       }
    }
    
    public void deleteUser(String CMND){
        trinh_dieu_khien m = new trinh_dieu_khien();
        if(m.DeleteUser(CMND)){
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            getDataUser();
        }
    } 
    
    
    public void searchUser(int searchBy, String key) {
        trinh_dieu_khien m = new trinh_dieu_khien();
        List<User> listUser = new ArrayList<>();
        listUser = m.SearchUser(listUser, searchBy, key);
        tableModel.setRowCount(0);
        for (int i = 0; i < listUser.size(); i++) {
            User u = listUser.get(i);
            Vector row = new Vector<>();
             row.add(u.getCmnd());
            row.add(u.getFullName());
            row.add(u.getPhone());
            row.add(u.getAddress());
            row.add(u.getAvt());
            tableModel.addRow(row);
        }
    }
    
    public void getDataCard(){
        List<Card> lstcard = new ArrayList<>();
        tableModelCard = (DefaultTableModel) tblCard.getModel();
        tableModelCard.setRowCount(0);
        trinh_dieu_khien m = new trinh_dieu_khien();
        lstcard = m.ShowCard(lstcard);
        for (int i = 0; i < lstcard.size(); i++) {
            Card c = lstcard.get(i);
            Vector row = new Vector<>();
            row.add(c.getId());
            row.add(c.getNameBoss());
            row.add(c.getBankName());
            row.add(c.getCreated_at());
            row.add(c.getBlance());
            tableModelCard.addRow(row);
        }
        tblCard.setRowHeight(30);
        tblCard.setModel(tableModelCard);
        tblCard.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(tblCard.getSelectedRow()>=0){
                lbNameBoss.setText(tblCard.getValueAt(tblCard.getSelectedRow(), 1).toString());
                lbSTK.setText(tblCard.getValueAt(tblCard.getSelectedRow(), 0).toString());
                lbBankName.setText(tblCard.getValueAt(tblCard.getSelectedRow(), 2).toString());
                lbDateOpen.setText(tblCard.getValueAt(tblCard.getSelectedRow(), 3).toString());
                lbBlance.setText(formatter.format(tblCard.getValueAt(tblCard.getSelectedRow(), 4))+" VND");
                lastBlance= new BigInteger(tblCard.getValueAt(tblCard.getSelectedRow(), 4).toString());
            }
        });
    }
    
    public void addCard(String cmnd, String bankName, String pin){
        trinh_dieu_khien m = new trinh_dieu_khien();
        if (m.AddCard(cmnd, bankName, pin)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            addcard.dispose();
            getDataCard();
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi thêm!");
        }
    }
    
    public void deleteCard(String cardID){
        trinh_dieu_khien m = new trinh_dieu_khien();
        if(m.DeleteCard(cardID)){
            JOptionPane.showMessageDialog(this, "Hủy thẻ thành công!");
            getDataCard();
        }
    }
    
    public void searchCard(int searchBy, String key){
        trinh_dieu_khien m = new trinh_dieu_khien();
        List<Card> lcard = new ArrayList<>();
        lcard = m.SearchCard(lcard, searchBy, key);
        tableModelCard.setRowCount(0);
        for (int i = 0; i < lcard.size(); i++) {
            Card c = lcard.get(i);
            Vector row = new Vector<>();
            row.add(c.getId());
            row.add(c.getNameBoss());
            row.add(c.getBankName());
            row.add(c.getCreated_at());
            row.add(c.getBlance());
            tableModelCard.addRow(row);
        }
    }
    
    public void payAcc(BigInteger cardID, BigInteger money){
        trinh_dieu_khien m = new trinh_dieu_khien();
        if (m.PayAcc(cardID, money)){
            JOptionPane.showMessageDialog(this, "Nạp tiền thành công!");
            payacc.dispose();
            getDataCard();    
            BigInteger newmoney = money.add(lastBlance);
            lbBlance.setText(formatter.format(newmoney)+" VND");
        }else{
            JOptionPane.showMessageDialog(this, "Nạp tiền thất bại!");
        }

    }
            
    public static String encodeBase64String(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return base64Image;
    }

    public static void decoder(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        btnEditUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lbCMND = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbPhone = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();
        btnRefeshUser = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        tfKeySearchUser = new javax.swing.JTextField();
        cbbSearchUser = new javax.swing.JComboBox<>();
        btnSearchUser = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAddUser1 = new javax.swing.JButton();
        btnPayAcc = new javax.swing.JButton();
        btnDeleteCard = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCard = new javax.swing.JTable();
        lb5 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        lb7 = new javax.swing.JLabel();
        lb8 = new javax.swing.JLabel();
        lbNameBoss = new javax.swing.JLabel();
        lbSTK = new javax.swing.JLabel();
        lbBankName = new javax.swing.JLabel();
        lbDateOpen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbBlance = new javax.swing.JLabel();
        btnRefeshCard = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbbSearchByCard = new javax.swing.JComboBox<>();
        tfKeySearchCard = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSeachCard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        btnAddUser.setText("Thêm");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnEditUser.setText("Sửa");
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setText("Xóa");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });

        tblCustomer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cmt", "HỌ VÀ TÊN", "DI ĐỘNG", "ĐỊA CHỈ", "AVT"
            }
        ));
        jScrollPane1.setViewportView(tblCustomer);
        if (tblCustomer.getColumnModel().getColumnCount() > 0) {
            tblCustomer.getColumnModel().getColumn(4).setHeaderValue("AVT");
        }

        lb1.setText("cmt:");

        lb2.setText("Họ và tên:");

        lb3.setText("Di động:");

        lb4.setText("Địa chỉ:");

        lbCMND.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbCMND.setText("________________________");

        lbName.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbName.setText("________________________");

        lbPhone.setText("___________________________");

        lbAddress.setText("___________________________");

        lbImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRefeshUser.setText("Làm tươi");
        btnRefeshUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshUserActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 51, 51));

        tfKeySearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKeySearchUserActionPerformed(evt);
            }
        });

        cbbSearchUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cmt", "Họ tên", "Địa chỉ" }));

        btnSearchUser.setText("Tìm Kiếm");
        btnSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchUserActionPerformed(evt);
            }
        });

        jLabel7.setText("Tìm kiếm");

        jLabel8.setText("Tìm theo:");

        jLabel9.setText("Từ khóa:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(76, 76, 76))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfKeySearchUser)
                    .addComponent(cbbSearchUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearchUser, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addGap(0, 140, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfKeySearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSearchUser)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jButton1.setText("quay lại trang chủ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("picture");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb2)
                            .addComponent(lb1)
                            .addComponent(lb3)
                            .addComponent(lb4))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAddUser)
                            .addGap(11, 11, 11)))
                    .addComponent(btnEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbName)
                            .addComponent(lbCMND)
                            .addComponent(lbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(493, 493, 493)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDeleteUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRefeshUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb1)
                                    .addComponent(lbCMND))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb2)
                                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbPhone)
                                    .addComponent(lb3))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbAddress)
                                    .addComponent(lb4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddUser)
                            .addComponent(btnRefeshUser))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditUser)
                            .addComponent(btnDeleteUser)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("manager khách ", jPanel1);

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(800, 400));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAddUser1.setText("create thẻ");
        btnAddUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUser1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        btnPayAcc.setText("Nạp tiền");
        btnPayAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayAccActionPerformed(evt);
            }
        });
        jPanel4.add(btnPayAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 120, 30));

        btnDeleteCard.setText("cancel thẻ");
        btnDeleteCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCardActionPerformed(evt);
            }
        });
        jPanel4.add(btnDeleteCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        tblCard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblCard.setForeground(new java.awt.Color(204, 153, 255));
        tblCard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STK", "CHỦ TÀI KHOẢN", "NGÂN HÀNG", "NGÀY ACTIVE", "SỐ DƯ"
            }
        ));
        jScrollPane2.setViewportView(tblCard);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 860, 160));

        lb5.setText("owner");
        jPanel4.add(lb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 39, -1, -1));

        lb6.setText("Số tk");
        jPanel4.add(lb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 85, -1, -1));

        lb7.setText("banking");
        jPanel4.add(lb7, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 141, -1, -1));

        lb8.setText("Số dư:");
        jPanel4.add(lb8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));

        lbNameBoss.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel4.add(lbNameBoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 37, 151, -1));

        lbSTK.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel4.add(lbSTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 83, 739, -1));
        jPanel4.add(lbBankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 141, -1, -1));
        jPanel4.add(lbDateOpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 193, -1, -1));

        jLabel1.setText("Ngày active thẻ: ");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 193, -1, -1));

        lbBlance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbBlance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBlance.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(lbBlance, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 150, 30));

        btnRefeshCard.setText("Làm tươi");
        btnRefeshCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCardActionPerformed(evt);
            }
        });
        jPanel4.add(btnRefeshCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, -1, -1));

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Tìm theo:");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        cbbSearchByCard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số tk", "Tên CTK", "Ngân hàng" }));
        jPanel5.add(cbbSearchByCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 150, -1));

        tfKeySearchCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKeySearchCardActionPerformed(evt);
            }
        });
        jPanel5.add(tfKeySearchCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 150, 30));

        jLabel3.setText("Từ khóa:");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        btnSeachCard.setText("Tìm kiếm");
        btnSeachCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeachCardActionPerformed(evt);
            }
        });
        jPanel5.add(btnSeachCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 150, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 280, 130));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 940, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("manager thẻ", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUser1ActionPerformed
        // TODO add your handling code here:
        addcard = new them_the(this, rootPaneCheckingEnabled);
        addcard.setVisible(true);
    }//GEN-LAST:event_btnAddUser1ActionPerformed

    private void btnPayAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayAccActionPerformed
        // TODO add your handling code here:
         if (tblCard.getSelectedRow() >= 0) {
            payacc = new buff_mau(this, rootPaneCheckingEnabled);
            payacc.getValueCardID(new BigInteger(tblCard.getValueAt(tblCard.getSelectedRow(), 0).toString()));
            payacc.setVisible(true);
        }else{
             JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần nạp!");
         }
    }//GEN-LAST:event_btnPayAccActionPerformed

    private void btnDeleteCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCardActionPerformed
        // TODO add your handling code here:
            if (tblCard.getSelectedRow() >= 0) {
            String cardID = tblCard.getValueAt(tblCard.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(this, "Xác nhân hủy thẻ " 
                    + tblCard.getValueAt(tblCard.getSelectedRow(), 0).toString() +
                    " ?", "CTK: " + tblCard.getValueAt(tblCard.getSelectedRow(), 1).toString(),
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                deleteCard(cardID);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thẻ cần hủy!");
        }
    }//GEN-LAST:event_btnDeleteCardActionPerformed

    private void btnRefeshCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCardActionPerformed
        // TODO add your handling code here:
        getDataCard();
        lbNameBoss.setText(null);
        lbSTK.setText(null);
        lbBankName.setText(null);
        lbDateOpen.setText(null);
        lbBlance.setText(null);
        tfKeySearchCard.setText(null);
        cbbSearchByCard.setSelectedIndex(0);
    }//GEN-LAST:event_btnRefeshCardActionPerformed

    private void tfKeySearchCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKeySearchCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeySearchCardActionPerformed

    private void btnSeachCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeachCardActionPerformed
        // TODO add your handling code here:
        searchCard(cbbSearchByCard.getSelectedIndex(), tfKeySearchCard.getText());
    }//GEN-LAST:event_btnSeachCardActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.hide();
        Trang_chu nm = new Trang_chu();
        nm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchUserActionPerformed
        // TODO add your handling code here:
        searchUser(cbbSearchUser.getSelectedIndex(), tfKeySearchUser.getText());
    }//GEN-LAST:event_btnSearchUserActionPerformed

    private void tfKeySearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKeySearchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKeySearchUserActionPerformed

    private void btnRefeshUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshUserActionPerformed
        // TODO add your handling code here:
        getDataUser();
        lbCMND.setText("________________________");
        lbName.setText("________________________");
        lbPhone.setText("__________________________");
        lbAddress.setText("__________________________");
        lbImage.setIcon(null);
    }//GEN-LAST:event_btnRefeshUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        // TODO add your handling code here:
        if(tblCustomer.getSelectedRow()>=0){
            String CMND = tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(this, "Xác nhận xoá "
                +tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 1).toString()+" ?", "CMND: "
                +tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString(),
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            deleteUser(CMND);
        }
        }else{
            JOptionPane.showMessageDialog(this, "Vui lòng chọn user cần xóa!");
        }
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        // TODO add your handling code here:

        if(tblCustomer.getSelectedRow()>=0){
            edituser = new chinh_sua_nguoi_dung(this, rootPaneCheckingEnabled);
            User user = new User(
                tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 0).toString(),
                tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 1).toString(),
                Integer.parseInt(tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 2).toString()),
                tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 3).toString(),
                tblCustomer.getValueAt(tblCustomer.getSelectedRow(), 4).toString()
            );
            edituser.getValueFromTable(user);
            edituser.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng user cần sửa trong bảng!");
        }
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        adduser = new them_nguoi_dung(this,rootPaneCheckingEnabled);
        adduser.setVisible(true);
    }//GEN-LAST:event_btnAddUserActionPerformed

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ManagerFrame().setVisible(true);
//            }
//        });
//    }
    
    private List<User> listUser;
    private List<Card> listCard;
    private DefaultTableModel tableModel,tableModelCard;
    private them_nguoi_dung adduser;
    private them_the addcard;
    private chinh_sua_nguoi_dung edituser;
    private buff_mau payacc;
    private DecimalFormat formatter;
    private BigInteger lastBlance;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnAddUser1;
    private javax.swing.JButton btnDeleteCard;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JButton btnPayAcc;
    private javax.swing.JButton btnRefeshCard;
    private javax.swing.JButton btnRefeshUser;
    private javax.swing.JButton btnSeachCard;
    private javax.swing.JButton btnSearchUser;
    private javax.swing.JComboBox<String> cbbSearchByCard;
    private javax.swing.JComboBox<String> cbbSearchUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbBankName;
    private javax.swing.JLabel lbBlance;
    private javax.swing.JLabel lbCMND;
    private javax.swing.JLabel lbDateOpen;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbNameBoss;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbSTK;
    private javax.swing.JTable tblCard;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField tfKeySearchCard;
    private javax.swing.JTextField tfKeySearchUser;
    // End of variables declaration//GEN-END:variables


}

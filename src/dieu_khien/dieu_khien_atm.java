 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dieu_khien;

import Ket_noi_database.ket_noi_db;
import database.Card;
import database.Transaction;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author NguyenDuong
 */
public class dieu_khien_atm {
    private Connection cn;
    private PreparedStatement ps=null,ps1=null,ps2=null,ps3=null;
    private ResultSet rs=null;

      public List<Card> checkLogin(List<Card> listCard, BigInteger cardID, int pin){
          ket_noi_db c = new ket_noi_db();
          cn = c.getConnection();
          String sql = "SELECT card.id,user.cmnd, user.fullname, card.bank_name,card.blance,card.pin,card.created_at"
                  + " FROM card INNER JOIN user ON card.user_cmnd = user.cmnd "
                  + "WHERE card.id ="+cardID +" AND card.pin="+pin;
          try {
              ps = cn.prepareStatement(sql);
              rs = ps.executeQuery();
              if (rs.next()) {
                   Card card = new Card(
                           new BigInteger(rs.getString("id")),
                           rs.getString("cmnd"),
                           rs.getString("fullname"),
                           rs.getString("bank_name"),
                           new BigInteger(rs.getString("blance")),
                           rs.getInt("pin"),
                           rs.getDate("created_at"));
                   listCard.add(card);
                   return listCard;
              }else{
                  return listCard;
              }
          } catch (SQLException e) {
              return listCard;
          }
        
      }
      
      public BigInteger ViewBlance(BigInteger cardID){
          ket_noi_db c = new ket_noi_db();
          BigInteger sd = null;
          cn = c.getConnection();
          String sql = "SELECT blance FROM card where id="+cardID;
          try {
              ps = cn.prepareStatement(sql);
              rs = ps.executeQuery();
              if (rs.next()) {   
                   return new BigInteger(rs.getString("blance"));
              }else{
                  return sd;
              }
          } catch (SQLException e) {
              return sd;
          }
       
      }
      
      public boolean RutTien(BigInteger cardID, BigInteger money){
          ket_noi_db c = new ket_noi_db();
          cn = c.getConnection();
          String sql1 = "UPDATE card set blance=blance-" + money + " WHERE id=" + cardID;
          String sql2 = "INSERT INTO transaction(card_id,money,note)"
                  + " VALUES(" + cardID + "," + money + ",'-" + money + " rut tien tai ATM')";
          try {
              ps = cn.prepareStatement(sql1);
              ps1 = cn.prepareStatement(sql2);
              return ps.executeUpdate()>0 && ps1.executeUpdate()>0;
              
          } catch (SQLException e) {
            return false;
          }
      }
      
      public boolean ChangePin(BigInteger cardID, int newpin){
          ket_noi_db c = new ket_noi_db();
          cn = c.getConnection();
          String sql = "UPDATE card set pin=" + newpin + " WHERE id=" + cardID;
          try {
              ps = cn.prepareStatement(sql);
              return ps.executeUpdate() > 0;

          } catch (SQLException e) {
              return false;
          }
      }
      
    public boolean Transfer(BigInteger sender_cardID, BigInteger receiver_cardID, BigInteger money, String note) {
        ket_noi_db c = new ket_noi_db();
        cn = c.getConnection();
        String sql1 = "UPDATE card set blance=blance-" + money + " WHERE id=" + sender_cardID;
        String sql2 = "UPDATE card set blance=blance+" + money + " WHERE id=" + receiver_cardID;
        String sql3 = "INSERT INTO transaction(card_id,money,note)"
                + " VALUES(" + sender_cardID + "," + money + ",'-" + money + " " + note + "')";
        String sql4 = "INSERT INTO transaction(card_id,money,note)"
                + " VALUES(" + receiver_cardID + "," + money + ",'+" + money + " " + note + "')";
        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
        System.out.println(sql4);
        try {
            ps = cn.prepareStatement(sql1);
            ps1 = cn.prepareStatement(sql2);
            ps2 = cn.prepareStatement(sql3);
            ps3 = cn.prepareStatement(sql4);

            return ps.executeUpdate() > 0 && ps1.executeUpdate() > 0 && ps2.executeUpdate() > 0 && ps3.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public String CheckCardID(BigInteger cardID) {
        String ten = null;
        ket_noi_db n = new ket_noi_db();
        cn = n.getConnection();
        String sql = "SELECT user.fullname FROM card INNER JOIN user ON card.user_cmnd = user.cmnd WHERE id ="+cardID;
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getString("fullname");
            }
        } catch (SQLException e) {
        }
        return ten;
    }
    
    public List<Transaction> ViewHistoryTransaction(BigInteger cardID) {
        List<Transaction> listTran = new ArrayList<>();
        ket_noi_db c = new ket_noi_db();
        BigInteger sd = null;
        cn = c.getConnection();
        String sql = "SELECT * FROM transaction where card_id=" + cardID;
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Transaction gd = new Transaction(
                        new BigInteger(rs.getString("card_id")),
                        new BigInteger(rs.getString("money")),
                        rs.getString("note"),
                        rs.getDate("created_at"));
                listTran.add(gd);
            }
        } catch (SQLException e) {
        }
        return listTran;
    }
      
}


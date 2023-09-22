package bank.management.system;
 
import javax.swing.*;
import java.awt.*;
import java.sql.*;

 
public class Ministatement extends JFrame{
String pinnumber;
    Ministatement(String pinnumber){
        this.pinnumber=pinnumber;
        setTitle("MINI STATEMENT");
        setLayout(null);
        
        JLabel data=new JLabel();
        data.setBounds(20,140,420,200);
        add(data);
        
        JLabel bank =new JLabel("INDIAN BANK");
        bank.setBounds(150,20,100,20);
        add(bank);
        
        JLabel card =new JLabel();
        card.setBounds(120,80,300,20);
        add(card);
        
        JLabel balance=new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);
        
        try{
            Conn con=new Conn();
           ResultSet rs= con.s.executeQuery("select * from login where pin='"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card Number: "+rs.getString("cardnumber").substring(0,4)+"********"+rs.getString(12));
            }
        }catch(Exception e){
            System.out.println(e);
            
        }
        
        try{
            Conn con=new Conn();
            int bal=0;
           ResultSet rs= con.s.executeQuery("select * from bank where pin='"+pinnumber+"'");
            while(rs.next()){
                data.setText(data.getText()+"<html>"+rs.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><br>");
                if(rs.getString("type").equals("Deposit")){
                          bal +=Integer.parseInt(rs.getString("amount"));
                          
                      }else{
                          bal -=Integer.parseInt(rs.getString("amount"));
                      }
            }
            balance.setText("YOUR CURRENT ACCOUNT BALANCE IS"+bal);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
     
    public static void main(String args[]) {
        // TODO code application logic here
        new Ministatement("");
    }
}
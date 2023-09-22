 package bank.management.system;

import java.awt.*;
import javax.swing.*;
 import java.awt.event.*;
 import java.util.*;
 public class Deposit  extends JFrame implements ActionListener{
JTextField amount;
JButton deposit,back;
String pinnumber;
 Deposit(String pinnumber){
     this.pinnumber=pinnumber;
     
     setLayout(null);
     ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
     Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
     ImageIcon i3=new ImageIcon(i2);
     JLabel image=new JLabel(i3);
     image.setBounds(0,0,900,900);
     add(image);
     
     
     JLabel note=new JLabel("enter the amount you want to deposit");
     note.setForeground(Color.WHITE);
     note.setFont(new Font("System",Font.BOLD,16));
     note.setBounds(170,300,400,20);
     image.add(note);
     
       amount=new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);

        
          deposit=new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this);
        image.add(deposit);
        
          back=new JButton("back");
      back.setBounds(355,520,150,30);
      back.addActionListener(this);
        image.add(back);
        
        
        
        


     setSize(900,900);
     setLocation(300,0);
     setVisible(true);
     
     
     
     
 }
 
@Override
 public void actionPerformed(ActionEvent ae){
     if(ae.getSource()==deposit){
         String number=amount.getText();
         Date date=new Date();
         if(number.equals("")){
             JOptionPane.showMessageDialog(null,"please enter the amount you want to deposit");
             
         }else{
             try{
             Conn con=new Conn();
             String query="insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+number+"')";
             con.s.executeUpdate(query);
             JOptionPane.showMessageDialog(null,"Rs."+number+"Deposited Successfully");
             setVisible(false);
             new Transaction(pinnumber).setVisible(true);
             }catch(Exception e){
                 System.out.println(e);
             }
                     
         }
     }else if(ae.getSource()==back)
     {
         setVisible(false);
         new Transaction(pinnumber).setVisible(true);
     }
 }
     public static void main(String args[]) {
        // TODO code application logic here
        new Deposit("");
    }
}
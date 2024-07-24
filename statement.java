package ASimulatorSystem;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniStatement extends JFrame implements ActionListener {
   JButton b1;
   JButton b2;
   JLabel l1;

   MiniStatement(String pin) {
      super("Mini Statement");
      this.getContentPane().setBackground(Color.WHITE);
      this.setSize(400, 600);
      this.setLocation(20, 20);
      this.l1 = new JLabel();
      this.add(this.l1);
      JLabel l2 = new JLabel("Indian Bank");
      l2.setBounds(150, 20, 100, 20);
      this.add(l2);
      JLabel l3 = new JLabel();
      l3.setBounds(20, 80, 300, 20);
      this.add(l3);
      JLabel l4 = new JLabel();
      l4.setBounds(20, 400, 300, 20);
      this.add(l4);

      try {
         Conn c = new Conn();
         ResultSet rs = c.s.executeQuery("select * from login where pin = '" + pin + "'");

         while(rs.next()) {
            l3.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
         }
      } catch (Exception var9) {
      }

      try {
         int balance = 0;
         Conn c1 = new Conn();
         ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pin + "'");

         while(rs.next()) {
            this.l1.setText(this.l1.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("mode") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
            if (rs.getString("mode").equals("Deposit")) {
               balance += Integer.parseInt(rs.getString("amount"));
            } else {
               balance -= Integer.parseInt(rs.getString("amount"));
            }
         }

         l4.setText("Your total Balance is Rs " + balance);
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      this.setLayout((LayoutManager)null);
      this.b1 = new JButton("Exit");
      this.add(this.b1);
      this.b1.addActionListener(this);
      this.l1.setBounds(20, 140, 400, 200);
      this.b1.setBounds(20, 500, 100, 25);
   }

   public void actionPerformed(ActionEvent ae) {
      this.setVisible(false);
   }

   public static void main(String[] args) {
      (new MiniStatement("")).setVisible(true);
   }
}

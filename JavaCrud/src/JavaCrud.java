import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtbid;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtedition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtqty;
	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from book");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(162, 249, 247));
		frame.getContentPane().setForeground(UIManager.getColor("Button.focus"));
		frame.setBounds(100, 100, 1053, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Store Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(389, 0, 370, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(163, 189, 250));
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 60, 345, 242);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 23, 120, 33);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 79, 92, 33);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 123, 92, 33);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(140, 23, 187, 30);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(140, 126, 187, 30);
		panel.add(txtprice);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(140, 82, 187, 30);
		panel.add(txtedition);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Qty");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(10, 182, 92, 33);
		panel.add(lblNewLabel_1_2_1);
		
		txtqty = new JTextField();
		txtqty.setColumns(10);
		txtqty.setBounds(140, 182, 187, 30);
		panel.add(txtqty);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(new Color(163, 189, 250));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{			
				String bname,edition,price,qty;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				qty	  = txtqty.getText();
							
				 try {
					pst = con.prepareStatement("insert into book(Name,Edition,Price,Qty)values(?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, qty);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtqty.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
			}
		
		 });
		
		
		btnNewButton.setBounds(49, 328, 100, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(163, 189, 250));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				System.exit(0);
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		btnExit.setBounds(856, 403, 100, 53);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(163, 189, 250));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtqty.setText("");
				txtbid.setText("");
				txtbname.requestFocus();
			
			
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClear.setBounds(211, 328, 100, 53);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 64, 589, 317);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnExit_1 = new JButton("Update");
		btnExit_1.setBackground(new Color(163, 189, 250));
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid,qty;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				qty = txtqty.getText();
				bid = txtbid.getText();
							
				 try {
					pst = con.prepareStatement("update book set Name=?,Edition=?,Price=?,Qty=? where Id=?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, qty);
					pst.setString(5, bid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtqty.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
				
				
				
				
				
				
			}
		});
		btnExit_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit_1.setBounds(416, 403, 100, 53);
		frame.getContentPane().add(btnExit_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(163, 189, 250));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				
				bid = txtbid.getText();
							
				 try {
					pst = con.prepareStatement("delete from book where Id =?");
					
					pst.setString(1, bid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtqty.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(568, 403, 100, 53);
		frame.getContentPane().add(btnDelete);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(new Color(163, 189, 250));
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPrint.setBounds(716, 403, 100, 53);
		frame.getContentPane().add(btnPrint);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(163, 189, 250));
		panel_1.setForeground(new Color(163, 189, 250));
		panel_1.setBounds(10, 397, 345, 100);
		frame.getContentPane().add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setBounds(10, 31, 85, 25);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
					
					 try {
				          
				            String id = txtbid.getText();

				                pst = con.prepareStatement("select Name,Edition,Price,Qty from book where Id = ?");
				                pst.setString(1, id);
				                ResultSet rs = pst.executeQuery();

				            if(rs.next()==true)
				            {
				              
				                String name = rs.getString(1);
				                String edition = rs.getString(2);
				                String price = rs.getString(3);
				                String qty = rs.getString(4);
				                
				                txtbname.setText(name);
				                txtedition.setText(edition);
				                txtprice.setText(price);
				                txtqty.setText(qty);
				                
				                
				            }   
				            else
				            {
				            	txtbname.setText("");
				            	txtedition.setText("");
				                txtprice.setText("");
				                txtqty.setText("");
				                 
				            }
				            


				        } 
					
					 catch (SQLException ex) {
				           
				        }
					
					
					
					
				}
				
				
	
		});
		txtbid.setBounds(115, 31, 187, 30);
		panel_1.add(txtbid);
		txtbid.setColumns(10);
	}
}

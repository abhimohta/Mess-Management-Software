package MessGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class Dates extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String start = null;
	String end = null;
	boolean flag = false;
	static java.sql.Connection conn = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dates dialog = new Dates();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	JDateChooser dateChooserStart;
	JDateChooser dateChooserEnd;

	/**
	 * Create the dialog.
	 */
	public Dates() {
		setTitle("Leave Duration");
		setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblStartDate = new JLabel("Start Date");
			lblStartDate.setBounds(123, 63, 71, 14);
			contentPanel.add(lblStartDate);
		}
		{
			dateChooserStart = new JDateChooser();
			dateChooserStart.setBounds(199, 57, 125, 20);
			contentPanel.add(dateChooserStart);
		}
		{
			JLabel lblEndDate = new JLabel("End Date");
			lblEndDate.setBounds(123, 116, 67, 14);
			contentPanel.add(lblEndDate);
		}
		
		dateChooserEnd = new JDateChooser();
		dateChooserEnd.setBounds(199, 110, 125, 20);
		contentPanel.add(dateChooserEnd);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						try{
						start = dateChooserStart.getDate().toString();
						end = dateChooserEnd.getDate().toString();
						}
						catch(Exception e){
							JOptionPane.showMessageDialog(null, "Please enter the dates.", "", JOptionPane.ERROR_MESSAGE);
						}
						start = getDate(start);
						end = getDate(end);
						flag = true;
						String id = EmployeeGUI.id;
						Date current = new Date();
						String curr = current.toString();
						curr = getDate(curr);
						//System.out.println(curr);

						if(start.compareTo(curr)<0){
							//System.out.println("Invalid entry: Start date expected to be greater than or equal to Current date");
							JOptionPane.showMessageDialog(null, "Invalid entry: Start date expected to be greater than or equal to Current date", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if(end.compareTo(start)<=0){
							//System.out.println("Invalid entry: End date expected to be greater than Start date");
							JOptionPane.showMessageDialog(null, "Invalid entry: End date expected to be greater than Start date", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					    
						try {
							
							String query1 = "SELECT * FROM employee WHERE emp_id = '"+id+"'"; 
							conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
							Statement stmt = conn.createStatement();
							
							ResultSet rs = stmt.executeQuery(query1);
							
							while(rs.next()){
								
								if(rs.getInt("leave_applied") == 1){
									
									//System.out.println("Cannot apply for a new leave. Previous leave pending with leave id" + rs.getString("recent_leave_id"));
									JOptionPane.showMessageDialog(null, "Cannot apply for a new leave. Previous leave pending with leave id" + rs.getString("recent_leave_id"), "Error", JOptionPane.ERROR_MESSAGE);

									return;
								}
								
								else break;
								
								
							}	
							
							//conn1 = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
							String update = "INSERT INTO empleave_pending (start_date,end_date,emp_id) VALUES ('"+start+"','"+end+"','"+id+"')"; 
							Statement stmt1 = conn.createStatement();
							int rows = stmt1.executeUpdate(update);
							
							
							String query2 = "SELECT * FROM empleave_pending WHERE emp_id = '"+id+"'"; 
							Statement stmt2 = conn.createStatement();
							
							ResultSet rs1 = stmt2.executeQuery(query2);
							
							while(rs1.next()){
								
								int leave_id = rs1.getInt("leave_id");
								
								
								update = "UPDATE employee SET recent_leave_id = '"+leave_id+"',leave_applied = 1 WHERE emp_id = '"+id+"'";
								Statement stmt3 = conn.createStatement();
								rows = stmt3.executeUpdate(update);
								
							}
							
							
						
							
							//System.out.println("util start"+start+"util end" + end);
							//System.out.println("sql start"+sqlStartDate+"sql end" + sqlEndDate);
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Database disconnected.", "Error", JOptionPane.ERROR_MESSAGE);

						}
						
						JOptionPane.showMessageDialog(null, "Application for leave sent.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

						try {
							setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					private String getDate(String s) {
						// TODO Auto-generated method stub
						String y = s.substring(24,28);
						String d = s.substring(8, 10);
						String m = s.substring(4, 7);
						switch(m){
						case "Jan": m = "01";
									break;
						case "Feb": m = "02";
						break;
						case "Mar": m = "03";
						break;
						case "Apr": m = "04";
						break;
						case "May": m = "05";
						break;
						case "Jun": m = "06";
						break;
						case "Jul": m = "07";
						break;
						case "Aug": m = "08";
						break;
						case "Sep": m = "09";
						break;
						case "Oct": m = "10";
						break;
						case "Nov": m = "11";
						break;
						case "Dec": m = "12";
						break;
						default: break;				
						}
						String go = y+ "-"+ m+ "-"+ d;
						return go;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					flag = false;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

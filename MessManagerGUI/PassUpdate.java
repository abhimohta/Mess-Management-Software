package MessManagerGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Mess.EmployeeClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Font;

public class PassUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static String ID ;
	private static String PASS;
	static Connection conn = null;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	/**
	 * Launch the application.
	 */

	
	
	public static void main(String[] args) {
		try {
			PassUpdate dialog = new PassUpdate(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PassUpdate(final String ID, String PASS) {
		PassUpdate.ID = ID;
		PassUpdate.PASS = PASS;
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(83, 84, 117, 14);
		contentPanel.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(83, 133, 117, 14);
		contentPanel.add(lblConfirmPassword);
		
		JTextPane txtpnNewPasswordShould = new JTextPane();
		txtpnNewPasswordShould.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnNewPasswordShould.setEnabled(false);
		txtpnNewPasswordShould.setBackground(SystemColor.textInactiveText);
		txtpnNewPasswordShould.setEditable(false);
		txtpnNewPasswordShould.setForeground(SystemColor.infoText);
		txtpnNewPasswordShould.setText("New password should contain minimum 8 characters, both upper and lower case alphabets, atleast 1 numeric digit and special character.");
		txtpnNewPasswordShould.setBounds(83, 11, 290, 48);
		contentPanel.add(txtpnNewPasswordShould);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 81, 126, 20);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(210, 130, 126, 20);
		contentPanel.add(passwordField_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						String password, checker;
						int chk, confirm = 0;
						do{
							
								password = passwordField.getText();
							
								Pattern pass = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
								Matcher match = pass.matcher(password);
								
								if(match.find())
									chk = 1;
								else{
									//System.out.println("Invalid password, please try again.");
									JOptionPane.showMessageDialog(null, "Invalid password, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
									chk = 0;
									break;
								}
								
								checker = passwordField_1.getText();
								if(checker.equals(password)){
									try {
										conn = DriverManager.getConnection("jdbc:mysql://localhost/hello", "root", "");
										Statement stmt1 = conn.createStatement();
										String update1 = "UPDATE manager SET password = '"+password+"' WHERE id = '"+ID+"'";
										int rows1 = stmt1.executeUpdate(update1);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Database not connected.", "Error", JOptionPane.ERROR_MESSAGE);
										break;
									}
									confirm = 1;
									JOptionPane.showMessageDialog(null, "Password updated!", "", JOptionPane.INFORMATION_MESSAGE);

								}
								else{
									JOptionPane.showMessageDialog(null, "Not matching, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
									break;
								}
									//System.out.println("Not matching, please try again.");
						}while(confirm == 0);
						if(confirm == 1){
							try {
								setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								setVisible(false);
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Error", "", JOptionPane.ERROR_MESSAGE);
							}
						}
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
							JOptionPane.showMessageDialog(null, "Error", "", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

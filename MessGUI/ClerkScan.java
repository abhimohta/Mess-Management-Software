package MessGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;

import Mess.Clerk;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import java.awt.TextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClerkScan {

	public JFrame frmClerkClient;
	private JTextField txtEnterIdHere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkScan window = new ClerkScan();
					window.frmClerkClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClerkScan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClerkClient = new JFrame();
		frmClerkClient.setResizable(false);
		frmClerkClient.setTitle("Clerk Client");
		frmClerkClient.setBounds(100, 100, 450, 300);
		frmClerkClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtEnterIdHere = new JTextField();
		txtEnterIdHere.setText("Enter ID here...");
		txtEnterIdHere.setColumns(10);
		
		JButton btnScanCard = new JButton("Scan Card");
		btnScanCard.setToolTipText("To scan a student's ID card.");
		btnScanCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String getid = txtEnterIdHere.getText();
			try{
				Clerk.scanCard(getid);
			} 
			catch (SQLException | ParseException e) {
				JOptionPane.showMessageDialog(null,"Database connection problem.","Error",JOptionPane.PLAIN_MESSAGE);			
			}
				
			}
		});
		
		JButton btnGuestEntry = new JButton("Guest Entry");
		btnGuestEntry.setToolTipText("To add an entry in the guest bill database. ");
		btnGuestEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                try {
                    Clerk.takeMoney();
                } catch (ParseException | SQLException e) {
                    // TODO Auto-generated catch block
                	JOptionPane.showMessageDialog(null,"Database connection problem.","Error",JOptionPane.PLAIN_MESSAGE);
                }
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmClerkClient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(171)
							.addComponent(btnGuestEntry))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(94)
							.addComponent(txtEnterIdHere, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnScanCard)))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnScanCard)
						.addComponent(txtEnterIdHere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(64)
					.addComponent(btnGuestEntry)
					.addGap(75))
		);
		frmClerkClient.getContentPane().setLayout(groupLayout);
	}
}

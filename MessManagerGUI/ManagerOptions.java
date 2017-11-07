package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import Mess.MessManager;

public class ManagerOptions {

	JFrame frmManagerOptions;
	private static String ID;
	private static String PASS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerOptions window = new ManagerOptions("111", "wer");
					window.frmManagerOptions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerOptions(String ID, String PASS) {
		ManagerOptions.ID = ID;
		ManagerOptions.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagerOptions = new JFrame();
		frmManagerOptions.setResizable(false);
		frmManagerOptions.setTitle("Manager Options");
		frmManagerOptions.setBounds(100, 100, 300, 300);
		frmManagerOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManagerOptions.getContentPane().setLayout(null);
		
		JButton btnUpdateManagerDetails = new JButton("Update Manager Details");
		btnUpdateManagerDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerUpdateDetails window = new ManagerUpdateDetails(ID, PASS);
				window.frmUpdateManagerDetails.setVisible(true);
			}
		});
		btnUpdateManagerDetails.setBounds(42, 8, 201, 23);
		frmManagerOptions.getContentPane().add(btnUpdateManagerDetails);
		
		JButton btnRefreshMessDetails = new JButton("Refresh Mess Details");
		btnRefreshMessDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessManager.updateMess();
					JOptionPane.showMessageDialog(null, "Refreshed!", "", JOptionPane.INFORMATION_MESSAGE);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefreshMessDetails.setBounds(42, 39, 201, 23);
		frmManagerOptions.getContentPane().add(btnRefreshMessDetails);
		
		JButton btnManageEmployeeLeave = new JButton("Manage Employee Leave");
		btnManageEmployeeLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerEmployeeLeave window = new ManagerEmployeeLeave("111", "wer");
				window.frmEmployeeLeave.setVisible(true);
			}
		});
		btnManageEmployeeLeave.setBounds(42, 70, 201, 23);
		frmManagerOptions.getContentPane().add(btnManageEmployeeLeave);
		
		JButton btnUpdatePriceOf = new JButton("Update Price of Meals");
		btnUpdatePriceOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerUpdatePriceMeals window = new ManagerUpdatePriceMeals(ID, PASS);
				window.frmUpdatePriceOf.setVisible(true);
			}
		});
		btnUpdatePriceOf.setBounds(42, 101, 201, 23);
		frmManagerOptions.getContentPane().add(btnUpdatePriceOf);
		
		JButton btnViewPriceOf = new JButton("View Price of Meals");
		btnViewPriceOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerViewPriceMeals window = new ManagerViewPriceMeals(ID, PASS);
				window.frmPriceOfMeals.setVisible(true);
			}
		});
		btnViewPriceOf.setBounds(42, 132, 201, 23);
		frmManagerOptions.getContentPane().add(btnViewPriceOf);
		
		JButton btnViewGuest = new JButton("View Guest Details");
		btnViewGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerGuestDetails window = new ManagerGuestDetails(ID, PASS);
				window.frmGuestDetails.setVisible(true);
			}
		});
		btnViewGuest.setBounds(42, 163, 201, 23);
		frmManagerOptions.getContentPane().add(btnViewGuest);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerLoginGUI window = new ManagerLoginGUI();
				window.frmManagerLogin.setVisible(true);
				frmManagerOptions.dispose();
			}
			
		});
		btnLogout.setBounds(42, 225, 201, 23);
		frmManagerOptions.getContentPane().add(btnLogout);
		
		JButton btnSendSms = new JButton("Send SMS");
		btnSendSms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//code here
				try {
					SendSMS dialog = new SendSMS();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSendSms.setBounds(42, 194, 201, 23);
		frmManagerOptions.getContentPane().add(btnSendSms);
	}
}

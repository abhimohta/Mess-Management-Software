package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Mess.MessManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerPastLeave {

	JFrame frmPastLeaveRecords;
	private JTextField textField;
	private String ID;
	private String PASS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPastLeave window = new ManagerPastLeave("111", "wer");
					window.frmPastLeaveRecords.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param PASS 
	 * @param ID 
	 */
	public ManagerPastLeave(String ID, String PASS) {
		this.ID = ID;
		this.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPastLeaveRecords = new JFrame();
		frmPastLeaveRecords.setResizable(false);
		frmPastLeaveRecords.setTitle("Past Leave Records");
		frmPastLeaveRecords.setBounds(100, 100, 300, 300);
		frmPastLeaveRecords.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPastLeaveRecords.getContentPane().setLayout(null);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 77, 264, 149);
		frmPastLeaveRecords.getContentPane().add(textArea);
		
		JButton btnViewAllRecords = new JButton("View All Records");
		btnViewAllRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(MessManager.pastLeaveRecords2(textField.getText(), true));
			}
		});
		btnViewAllRecords.setBounds(10, 11, 168, 23);
		frmPastLeaveRecords.getContentPane().add(btnViewAllRecords);
		
		JButton btnViewRecordsBy = new JButton("View Records By ID");
		btnViewRecordsBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(MessManager.pastLeaveRecords2(textField.getText(), false));
			}
		});
		btnViewRecordsBy.setBounds(10, 43, 168, 23);
		frmPastLeaveRecords.getContentPane().add(btnViewRecordsBy);
		
		textField = new JTextField();
		textField.setBounds(188, 44, 86, 20);
		frmPastLeaveRecords.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(209, 20, 34, 14);
		frmPastLeaveRecords.getContentPane().add(lblId);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPastLeaveRecords.dispose();
			}
		});
		btnClose.setBounds(97, 227, 89, 23);
		frmPastLeaveRecords.getContentPane().add(btnClose);
	}
}

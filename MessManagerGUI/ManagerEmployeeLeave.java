package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Mess.MessManager;
import javax.swing.SwingConstants;

public class ManagerEmployeeLeave {

	JFrame frmEmployeeLeave;
	private String ID;
	private String PASS;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerEmployeeLeave window = new ManagerEmployeeLeave("111", "wer");
					window.frmEmployeeLeave.setVisible(true);
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
	public ManagerEmployeeLeave(String ID, String PASS) {
		this.ID = ID;
		this.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeLeave = new JFrame();
		frmEmployeeLeave.setResizable(false);
		frmEmployeeLeave.setTitle("Employee Leave");
		frmEmployeeLeave.setBounds(100, 100, 370, 370);
		frmEmployeeLeave.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmployeeLeave.getContentPane().setLayout(null);
		
		final JTextArea textArea = new JTextArea(MessManager.pendingLeaveRecords2());
		textArea.setBounds(22, 87, 310, 165);
		
		JButton btnApproveLeave = new JButton("Approve Leave");
		btnApproveLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessManager.approveLeave(textField.getText());
				textArea.setText(MessManager.pendingLeaveRecords2());
			}
		});
		btnApproveLeave.setBounds(53, 39, 114, 23);
		frmEmployeeLeave.getContentPane().add(btnApproveLeave);
		
		textField = new JTextField();
		textField.setBounds(195, 11, 86, 20);
		frmEmployeeLeave.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(154, 14, 46, 14);
		frmEmployeeLeave.getContentPane().add(lblId);
		
		frmEmployeeLeave.getContentPane().add(textArea);
		
		JButton btnPast = new JButton("Past Leave Records");
		btnPast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerPastLeave window = new ManagerPastLeave(ID, PASS);
				window.frmPastLeaveRecords.setVisible(true);
			}
		});
		btnPast.setBounds(85, 263, 183, 23);
		frmEmployeeLeave.getContentPane().add(btnPast);
		
		JLabel lblPendingLeaves = new JLabel("Pending Leaves");
		lblPendingLeaves.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendingLeaves.setBounds(65, 73, 156, 14);
		frmEmployeeLeave.getContentPane().add(lblPendingLeaves);
		
		JButton btnCancelLeave = new JButton("Cancel Leave");
		btnCancelLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessManager.cancelLeave(textField.getText());
				textArea.setText(MessManager.pendingLeaveRecords2());
			}
		});
		btnCancelLeave.setBounds(195, 39, 114, 23);
		frmEmployeeLeave.getContentPane().add(btnCancelLeave);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmployeeLeave.dispose();
			}
		});
		btnClose.setBounds(132, 297, 89, 23);
		frmEmployeeLeave.getContentPane().add(btnClose);
	}
}

package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import Mess.MessManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerGuestDetails {

	JFrame frmGuestDetails;
	private String ID;
	private String PASS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGuestDetails window = new ManagerGuestDetails("111", "wer");
					window.frmGuestDetails.setVisible(true);
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
	public ManagerGuestDetails(String ID, String PASS) {
		this.ID = ID;
		this.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuestDetails = new JFrame();
		frmGuestDetails.setTitle("Guest Details");
		frmGuestDetails.setBounds(100, 100, 300, 500);
		frmGuestDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuestDetails.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea(MessManager.showGuestList2());
		textArea.setBounds(23, 21, 240, 395);
		frmGuestDetails.getContentPane().add(textArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGuestDetails.dispose();
			}
		});
		btnClose.setBounds(97, 427, 89, 23);
		frmGuestDetails.getContentPane().add(btnClose);
	}

}

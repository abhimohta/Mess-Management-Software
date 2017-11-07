package MessManagerGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import Mess.MessManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SendSMS extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SendSMS dialog = new SendSMS();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SendSMS() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		final JTextPane txtpnS;
		{
			txtpnS = new JTextPane();
			txtpnS.setBounds(160, 50, 233, 120);
			contentPanel.add(txtpnS);
		}
		
		JLabel lblEnter = new JLabel("Enter Text to SMS");
		lblEnter.setBounds(51, 102, 102, 14);
		contentPanel.add(lblEnter);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String msg = txtpnS.getText();
						MessManager.sendsms(msg);
						try {
							setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
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
							e.printStackTrace();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

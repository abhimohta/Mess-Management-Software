package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Mess.MessManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerUpdatePriceMeals {

	JFrame frmUpdatePriceOf;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String ID;
	private String PASS;
	private JButton btnClose;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUpdatePriceMeals window = new ManagerUpdatePriceMeals("111","wer");
					window.frmUpdatePriceOf.setVisible(true);
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
	public ManagerUpdatePriceMeals(String ID, String PASS) {
		this.ID = ID;
		this.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUpdatePriceOf = new JFrame();
		frmUpdatePriceOf.setResizable(false);
		frmUpdatePriceOf.setTitle("Update Price of Meals");
		frmUpdatePriceOf.setBounds(100, 100, 300, 300);
		frmUpdatePriceOf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUpdatePriceOf.getContentPane().setLayout(null);
		
		int[] prices = MessManager.showPriceList2();
		
		JLabel lblBreakfast = new JLabel("Breakfast");
		lblBreakfast.setBounds(70, 60, 46, 17);
		frmUpdatePriceOf.getContentPane().add(lblBreakfast);
		
		textField = new JTextField(Integer.toString(prices[0]));
		textField.setBounds(138, 58, 86, 20);
		frmUpdatePriceOf.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLunch = new JLabel("Lunch");
		lblLunch.setBounds(70, 93, 46, 14);
		frmUpdatePriceOf.getContentPane().add(lblLunch);
		
		textField_1 = new JTextField(Integer.toString(prices[2]));
		textField_1.setBounds(138, 90, 86, 20);
		frmUpdatePriceOf.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDinner = new JLabel("Dinner");
		lblDinner.setBounds(70, 127, 46, 14);
		frmUpdatePriceOf.getContentPane().add(lblDinner);
		
		textField_2 = new JTextField(Integer.toString(prices[1]));
		textField_2.setBounds(138, 124, 86, 20);
		frmUpdatePriceOf.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() != 0)
					MessManager.updatePrice("breakfast", Integer.parseInt(textField.getText()));
				if(textField_1.getText().length() != 0)
					MessManager.updatePrice("lunch", Integer.parseInt(textField_1.getText()));
				if(textField_2.getText().length() != 0)
					MessManager.updatePrice("dinner", Integer.parseInt(textField_2.getText()));
			}
		});
		btnUpdate.setBounds(70, 168, 154, 23);
		frmUpdatePriceOf.getContentPane().add(btnUpdate);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUpdatePriceOf.dispose();
			}
		});
		btnClose.setBounds(97, 202, 89, 23);
		frmUpdatePriceOf.getContentPane().add(btnClose);
	}

}

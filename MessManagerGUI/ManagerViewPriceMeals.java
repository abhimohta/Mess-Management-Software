package MessManagerGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Mess.MessManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerViewPriceMeals {

	JFrame frmPriceOfMeals;
	private String ID;
	private String PASS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerViewPriceMeals window = new ManagerViewPriceMeals("111", "wer");
					window.frmPriceOfMeals.setVisible(true);
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
	public ManagerViewPriceMeals(String ID, String PASS) {
		this.ID = ID;
		this.PASS = PASS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPriceOfMeals = new JFrame();
		frmPriceOfMeals.setResizable(false);
		frmPriceOfMeals.setTitle("Price of Meals");
		frmPriceOfMeals.setBounds(100, 100, 300, 300);
		frmPriceOfMeals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPriceOfMeals.getContentPane().setLayout(null);
		
		JLabel lblBreakfast = new JLabel("Breakfast: ");
		lblBreakfast.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBreakfast.setBounds(27, 73, 77, 14);
		frmPriceOfMeals.getContentPane().add(lblBreakfast);
		
		JLabel label = new JLabel(Integer.toString(MessManager.showPriceList2()[0]));
		label.setBounds(158, 73, 46, 14);
		frmPriceOfMeals.getContentPane().add(label);
		
		JLabel lblLunch = new JLabel("Lunch: ");
		lblLunch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLunch.setBounds(58, 113, 46, 14);
		frmPriceOfMeals.getContentPane().add(lblLunch);
		
		JLabel label_1 = new JLabel(Integer.toString(MessManager.showPriceList2()[2]));
		label_1.setBounds(158, 113, 46, 14);
		frmPriceOfMeals.getContentPane().add(label_1);
		
		JLabel lblDinner = new JLabel("Dinner: ");
		lblDinner.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDinner.setBounds(58, 155, 46, 14);
		frmPriceOfMeals.getContentPane().add(lblDinner);
		
		JLabel label_2 = new JLabel(Integer.toString(MessManager.showPriceList2()[1]));
		label_2.setBounds(158, 155, 46, 14);
		frmPriceOfMeals.getContentPane().add(label_2);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPriceOfMeals.dispose();
			}
		});
		btnClose.setBounds(97, 204, 89, 23);
		frmPriceOfMeals.getContentPane().add(btnClose);
	}

}

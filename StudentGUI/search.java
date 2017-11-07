package StudentGUI;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import SWD.SWDAdmin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class search extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField nameField;
	private JTable table;
	private static final Object[][] rowData = {};
	private static final Object[] columnNames = {"ID NUmber","Name", "Mobile Number", "Email Address"};
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			search dialog = new search();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public search() {
		setResizable(false);
		setBounds(100, 100, 683, 304);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 229, 657, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JButton btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
						String id = idField.getText().toString();
						String name = nameField.getText().toString();
						
						ResultSet rs = SWDAdmin.find(id, name);
						
						DefaultTableModel listTableModel;
					    listTableModel = new DefaultTableModel(rowData, columnNames);
					    
					    
					  try {
						while(rs.next()) {
						       
						        listTableModel.addRow(new Object[]{ rs.getString("id"), rs.getString("name"),rs.getString("mobileno"),rs.getString("email")});
						    }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(20, 58, 622, 155);
						getContentPane().add(scrollPane);
						{
							table = new JTable(listTableModel);
							scrollPane.setViewportView(table);
							
						}
					
				
				
				}
			});
			btnSearch.setBounds(554, 26, 103, 23);
			getContentPane().add(btnSearch);
		}
		{
			idField = new JTextField();
			idField.setBounds(20, 27, 242, 20);
			getContentPane().add(idField);
			idField.setColumns(10);
		}
		{
			nameField = new JTextField();
			nameField.setBounds(286, 27, 242, 20);
			getContentPane().add(nameField);
			nameField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("ID Number");
			lblNewLabel.setBounds(20, 11, 126, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			lblNewLabel_1.setBounds(286, 11, 136, 14);
			getContentPane().add(lblNewLabel_1);
		}
		
		

	}
}

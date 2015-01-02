import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

/**
 *
 * @author Sven Marquardt
 *
 */
public class PatientAusgabe extends JDialog {
	private JTable table;
	private JButton btnAusgeben;
	private JTextField txtGewicht;
	private JTextField txtName;
	Connection dbConnect = (Connection) Login.dbConnect;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatientAusgabe dialog = new PatientAusgabe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Die Tabelle Patient ausgeben 
	 */
	public PatientAusgabe() {//Funktioniert 
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 981, 676);
		{
			btnAusgeben = new JButton("Tabelle ausgeben");
			btnAusgeben.setBackground(new Color(255, 69, 0));
			btnAusgeben.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String query =("Select * from Patient");
					try{
						PreparedStatement pr = dbConnect.prepareStatement(query);
						ResultSet rs = pr.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}catch(Exception e1){
						e1.printStackTrace();
						
					}
					
					
					
					
				}
			});
		}
		
		table = new JTable();
		table.setBackground(Color.GRAY);
		JButton btnGewichtaendern = new JButton("gewicht ändern");
		/**
		 * Um das Gewicht eines Patienten mit Transatkion zu ändern
		 */
		btnGewichtaendern.addActionListener(new ActionListener() {//Bleibt stecken weis noch nicht warum 
			public void actionPerformed(ActionEvent e) {
				String query = "call gewichtAendern(?,?)"; //Ist hier vielleicht der Fehler?
				CallableStatement call = null;
				String name = txtName.getText();
				int gewicht = Integer.parseInt(txtGewicht.getText());
				try{
					call = dbConnect.prepareCall(query);
					call.setString(1, name);
					call.setInt(2, gewicht);
					ResultSet ergebnis = call.executeQuery();
					ergebnis.close();
					JOptionPane.showMessageDialog(null, "Geschafft"); //Sollte erscheinen wenn erfolgreich 
				}catch(SQLException e1){
					JOptionPane.showMessageDialog(null, "Something went wrong");
				}
				
			}
		});
		btnGewichtaendern.setBackground(new Color(255, 69, 0));
		
		txtGewicht = new JTextField();
		txtGewicht.setText("Gewicht");
		txtGewicht.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAusgeben, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGewichtaendern)
						.addComponent(txtGewicht, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 696, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAusgeben)
							.addGap(18)
							.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(txtGewicht, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnGewichtaendern)))
					.addGap(87))
		);
		getContentPane().setLayout(groupLayout);
	}
}

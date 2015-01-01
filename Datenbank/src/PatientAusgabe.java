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


public class PatientAusgabe extends JDialog {
	private JTable table;

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
	 * Create the dialog.
	 */
	public PatientAusgabe() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton btnAusgeben = new JButton("Ausgeben");
			btnAusgeben.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						Connection dbConnect = (Connection) Login.dbConnect;
						String query =("Select * from Patient");
						PreparedStatement pr = dbConnect.prepareStatement(query);
						ResultSet rs = pr.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}catch(Exception e1){
						e1.printStackTrace();
						
					}
					
					
					
					
				}
			});
			btnAusgeben.setBounds(12, 12, 117, 25);
			getContentPane().add(btnAusgeben);
		}
		
		table = new JTable();
		table.setBounds(22, 49, 416, 204);
		getContentPane().add(table);
	}
}

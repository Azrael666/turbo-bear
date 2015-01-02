import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;


public class Selection extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Selection dialog = new Selection();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Oeffnet ein neues Fenster {@link #PatientAusagbe} 
	 */
	public Selection() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton btnTabellePatient = new JButton("Tabelle Patient");
			btnTabellePatient.setBackground(new Color(255, 69, 0));
			btnTabellePatient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PatientAusgabe ausgabe = new PatientAusgabe();
					try{
						ausgabe.setVisible(true);
					}catch(Exception e1)
					{
						System.out.println(e.getActionCommand());
					}
				}
			});
			btnTabellePatient.setBounds(12, 12, 142, 25);
			getContentPane().add(btnTabellePatient);
		}
	}
}

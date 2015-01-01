import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
	 * Create the dialog.
	 */
	public Selection() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton btnTabelle = new JButton("Tabelle Patient ausgeben");
			btnTabelle.addActionListener(new ActionListener() {
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
			btnTabelle.setBounds(12, 24, 274, 25);
			getContentPane().add(btnTabelle);
		}
	}

}

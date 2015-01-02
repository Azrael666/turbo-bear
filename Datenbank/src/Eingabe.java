import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Eingabe extends JDialog { //Für was weis ich noch nicht 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Eingabe dialog = new Eingabe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Eingabe() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
	}

}

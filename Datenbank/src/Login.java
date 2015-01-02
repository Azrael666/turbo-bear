import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Login {

	private JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;
	public static Connection dbConnect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login(dbConnect);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@SuppressWarnings("static-access")
	public Login(Connection dbConnect) {
		initialize();
		this.dbConnect=dbConnect;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userField = new JTextField();
		userField.setBounds(12, 61, 114, 19);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(38, 12, 70, 37);
		frame.getContentPane().add(lblUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(12, 177, 114, 19);
		frame.getContentPane().add(passwordField);
		
		JLabel lblS = new JLabel("Password");
		lblS.setBounds(12, 124, 94, 27);
		frame.getContentPane().add(lblS);
		
		JButton btnVerbinden = new JButton("Verbinden");
		btnVerbinden.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		btnVerbinden.setForeground(new Color(0, 0, 0));
		btnVerbinden.setBackground(new Color(255, 69, 0));
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Selection selection = new Selection();
				try{
				@SuppressWarnings("deprecation")
				MySQL mysql = new MySQL("slo.swe.fh-luebeck.de","3306","Fr1c",userField.getText(),passwordField.getText());
				
				dbConnect = mysql.openConnection();
				JOptionPane.showMessageDialog(null, "Verbindung erfolgreich");
				selection.setVisible(true);
				}catch(SQLException | ClassNotFoundException e1){
					JOptionPane.showMessageDialog(null, "Invalid User or Password");
				}
			}
		});
		btnVerbinden.setBounds(186, 174, 107, 25);
		frame.getContentPane().add(btnVerbinden);
	}
}

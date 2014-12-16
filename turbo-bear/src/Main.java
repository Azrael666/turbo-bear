import java.awt.Desktop;
import java.net.Socket;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Main {

	/**
	 * Prueft ob eine Verbindung zum Datenbank-Server möglich ist
	 * 
	 * @return available Die verfügbarkeit des Servers liefert true wenn dem so ist 
	 */
	public static boolean checkDatabase() {

		Socket socket = null;
		boolean available = false;
		try {
			socket = new Socket("slo.swe.fh-luebeck.de", 3306);
			available = true;
			socket.close();
		} catch (Exception e) {
		}

		return available;
	}

	/**
	 * Prueft, ob die Datenbank-Verbindung noch besteht
	 * 
	 * @param con Die Verbidnung zum Server 
	 * @return connected Wenn verbunden liefert es true sonst false
	 */
	// TODO - Funktioniert nicht ?!?
	public static boolean checkDatabaseConnection(Connection con) {
		boolean connected = true;

		try {
			// connected = con.isValid(5);
		} catch (Exception e) {
		}

		return connected;
	}

	/**
	 * Stellt die Verbindung zur Datenbank her
	 * 
	 * @param dbConnection Die verbidnung zum Server 
	 * @param user
	 *            - User zum zufgriff des Servers 
	 * @param password - Das Passwort des Servers 
	 * @return 
	 * @throws Exception
	 */
	public static java.sql.Connection connectDatabase(
			java.sql.Connection dbConnection, String user, String password)
			throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		dbConnection = DriverManager.getConnection(
				"jdbc:mysql://slo.swe.fh-luebeck.de/Fr1c", user, password);

		return dbConnection;

	}

	public static void userAuswahl() {

		String zeroString = "";
		Scanner in = new Scanner(System.in);
		int choice = 0;
		try {
			do {
				System.out.println("Bitte waehlen:");
				System.out.println("0 - Das Programm beenden");
				System.out
						.println("1 - Die komplette Tabelle Patient ausgeben.");
				System.out
						.println("2 - In die Tabelle Patient einen neuen Patienten einfuegen");
				choice = in.nextInt();

				switch (choice) {

				case 0: {
					System.out.println("Programm wird beendet.");
					System.exit(0);
				}
					break;

				case 1: {
					System.out.println("Methode 1");
					

				}
					break;

				case 2: {
					System.out.println("Methode 2");
					

				}
					break;

				case 21: {
					System.out.println("You're Stupid!");
					

				}
					break;

				case 42: {
					try {
						Desktop.getDesktop()
								.browse(new URL(
										"http://www.youtube.com/watch?v=8XfOH1OkrW0#t=84&rel=0&autoplay=1")
										.toURI());
					} catch (Exception e) {
					}

				}
					break;

				case 69: {
					System.out.println("( ¬‿¬)");
					in.next();

				}
					break;

				default: {
					System.out
							.println("\nBitte geben Sie nur Zahlen zwischen 0 und 2 ein.\n");
				}

				}

			}while(choice!=0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			in.close();
			
		}

	}

	/**
	 * Stellt verbindugn auf und fuehrt dann die weiteren Methoden aus {@link #userAuswahl()}
	 * @param args Werden nicht ausgewertet 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		java.sql.Connection dbConnection = null;
		Scanner in = new Scanner(System.in);
		String user = "";
		String password = "";
		boolean connected = false;
		int counter = 5;
		try{
		// Solange keine Verbindung aufgebaut werden konnte
		while (!connected && counter > 0) {

			System.out
					.println("Bitte authentifizieren Sie sich, um eine Verbindung mit der Datenbank herzustellen.");
			System.out.print("User:");
			user = in.next();
			System.out.print("Passwort:");
			password = in.next();
			System.out.println();

			// Wenn eine Verbindung zur Datenbank möglich ist
			if (checkDatabase()) {

				try {
					dbConnection = connectDatabase(dbConnection, user, password);
					System.out
							.println("Verbindung wurde erfolgreich hergestellt.\n");
					connected = true;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");

					if (counter == 0) {
						System.out.println("┌∩┐(◣_◢)┌∩┐");//Nochmal ueberdenken fuer Praeso :D 
						System.out.println("Programm wird beendet.");
						System.exit(0);
					}
					counter++;
				}

				// Wenn keine Verbindung möglich ist
			} else {
				counter--;
				System.out.println("Keine Verbindung zum Datenbank-Server moeglich.\n");
			}

		}
		

		 
			userAuswahl();

		// checkDatabaseConnection(dbConnection);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
/**
 * Gibtdie Tabelle Patient aus 
 * @param dbConnection Verindung zum Server 
 * @throws Exception
 */
	public static void tabellePatientAusgeben(Connection dbConnection)
			throws Exception {
		int anzahl = 0;
		String ausgabe = "select * from Patient";
		try {
			java.sql.Statement statement = dbConnection.createStatement();
			java.sql.ResultSet ergebnis = statement.executeQuery(ausgabe);
			while (ergebnis.next()) {
				String name = ergebnis.getString("Name");
				String vorname = ergebnis.getString("Vorname");
				// Wird zu String gewandelt um die Ausgabe auch von nicht
				// unterstützten java.util.date formaten auszugeben Beispiel:
				// 0000-00-00
				String geburtsdatum = String.valueOf(ergebnis
						.getLong("Geburtsdatum"));
				String geschlecht = ergebnis.getString("Geschlecht");
				int gewicht = ergebnis.getInt("Gewicht");
				int groesse = ergebnis.getInt("Größe");
				String telefonNummer = ergebnis.getString("Telefon");
				String aufnahmeDatum = String.valueOf(ergebnis
						.getDate("Aufnahmedatum"));
				String entlassDatum = String.valueOf(ergebnis
						.getDate("Entlassdatum"));
				anzahl++;

				System.out.println("Tupel " + anzahl + ": " + name + ","
						+ vorname + "," + geburtsdatum + "," + geschlecht + ","
						+ gewicht + "," + groesse + "," + telefonNummer + ","
						+ aufnahmeDatum + "," + entlassDatum);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

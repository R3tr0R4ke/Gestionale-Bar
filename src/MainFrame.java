import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton menuArtButton;
	private final JLabel lblGestionaleBar = new JLabel("Gestionale Bar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Menu principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		lblGestionaleBar.setBounds(15, 10, 186, 21);
		lblGestionaleBar.setFont(new Font("ROG Fonts", Font.PLAIN, 17));
		lblGestionaleBar.setHorizontalAlignment(SwingConstants.LEFT);
		lblGestionaleBar.setForeground(Color.BLACK);
		lblGestionaleBar.setBackground(Color.WHITE);
		
		//Bottone che riporta al menu della lista, aggiunta, rimozione articoli
		menuArtButton = new JButton("Menu Articoli");
		menuArtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Codice per la connessione al database
		        Connection connection = null;
		        try {
		        	// Connessione al database MySQL
		        	String url = "jdbc:mysql://localhost:3306/gestionalebar";
		        	String username = "root";
		        	String password = "root";
		            
		            connection = DriverManager.getConnection(url, username, password);
		            System.out.println("Connessione al database MySQL riuscita!");
		            // Ora puoi eseguire le query o altre operazioni sul database utilizzando questa connessione
		        }
		        catch (SQLException ex) {
		        	System.err.println("Errore durante la connessione al database: " + ex.getMessage());
				}
		        finally {
					try {
						if(connection != null) {
							connection.close(); // Assicurati di chiudere la connessione quando non è più necessaria
						}
					}
					catch (SQLException ex) {
						System.err.println("Errore durante la connessione al database: " + ex.getMessage());
					}
				}
				
				ArticoliFrame articoliFrame = ArticoliFrame.getIstance();	//Ottiene l'istanza del frame
				articoliFrame.showFrame();	//Mostra il menu articoli
			}
		});
		menuArtButton.setBounds(15, 91, 152, 39);
		
		//Bottone che porta al menu della lista di fornitori
		JButton menuFornitButton = new JButton("Fornitori");
		menuFornitButton.setBounds(267, 91, 152, 39);
		menuFornitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Bottone che porta al menu Entrate/spese
		JButton menuEntrSpesButton = new JButton("Entrate/Spese");
		menuEntrSpesButton.setBounds(15, 141, 152, 39);
		menuEntrSpesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Bottone menu ordini fornitori
		JButton menuOrdFornButton = new JButton("Ordini Fornitori");
		menuOrdFornButton.setBounds(267, 141, 152, 39);
		
		//Termina applicazione
		JButton btnNewButton = new JButton("Chiudi App");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(310, 227, 109, 23);
		btnNewButton.setForeground(new Color(255, 255, 0));
		btnNewButton.setBackground(new Color(255, 51, 0));
		contentPane.setLayout(null);
		contentPane.add(lblGestionaleBar);
		contentPane.add(menuArtButton);
		contentPane.add(menuFornitButton);
		contentPane.add(menuEntrSpesButton);
		contentPane.add(menuOrdFornButton);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Premi un bottone per navigare nel menu:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblNewLabel.setBounds(15, 60, 281, 14);
		contentPane.add(lblNewLabel);
	}
}

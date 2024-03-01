import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

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
		setTitle("Menu Principale");
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
				ArticoliFrame articoliFrame = ArticoliFrame.getIstance();	//Ottiene l'istanza del frame
				articoliFrame.showFrame();	//Mostra il menu articoli
			}
		});
		menuArtButton.setBounds(15, 91, 152, 23);
		
		//Bottone che porta al menu della lista di fornitori
		JButton menuFornitButton = new JButton("Fornitori");
		menuFornitButton.setBounds(15, 125, 152, 23);
		menuFornitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Bottone che porta al menu dei dipendenti
		JButton menuDipendButton = new JButton("Dipendenti");
		menuDipendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuDipendButton.setBounds(15, 159, 152, 23);
		
		//Bottone che porta al menu Entrate/spese
		JButton menuEntrSpesButton = new JButton("Entrate/Spese");
		menuEntrSpesButton.setBounds(15, 193, 152, 23);
		menuEntrSpesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Bottone menu ordini fornitori
		JButton menuOrdFornButton = new JButton("Ordini Fornitori");
		menuOrdFornButton.setBounds(15, 227, 152, 23);
		
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
		
		//Menu ordini cliente (aggiorna giacenze dopo che vendi articoli)
		JButton menuOrdClientButton = new JButton("Ordine Cliente");
		menuOrdClientButton.setBounds(264, 91, 155, 23);
		contentPane.setLayout(null);
		contentPane.add(lblGestionaleBar);
		contentPane.add(menuArtButton);
		contentPane.add(menuFornitButton);
		contentPane.add(menuDipendButton);
		contentPane.add(menuEntrSpesButton);
		contentPane.add(menuOrdFornButton);
		contentPane.add(btnNewButton);
		contentPane.add(menuOrdClientButton);
		
		JLabel lblNewLabel = new JLabel("Premi un bottone per navigare nel menu:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblNewLabel.setBounds(15, 60, 281, 14);
		contentPane.add(lblNewLabel);
	}
}

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Locale.Category;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;

public class ArticoliFrame extends JFrame {
	private static ArticoliFrame istance; //Dichiarazione della variabile di classe

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtInserisciNomeArt;
	private JButton btnBackToMenu;
	private JButton btnAddArt;
	private JButton btnRmvArticolo;
	

	//Metodo statico per ottenere l'istanza unica del frame degli articoli
	public static ArticoliFrame getIstance() {
		if(istance == null) {
			istance = new ArticoliFrame();
		}
		
		return istance;
	}

	//Crea il frame
	public ArticoliFrame() {
		setTitle("Menu Articoli");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String[] columnNames = {"ID_Articolo", "Nome", "Giacenza", "Prezzo Acquisto", "Prezzo Unitario", "Categoria"};
		Object[][] data = {
	            //TODO: Aggiungere articoli tramite query al db
		};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);	//Carica la tabella con i seguenti parametri
		table.setBounds(10, 11, 414, 129);
		JScrollPane scrollPane = new JScrollPane(table);	//Aggiungi un scroll pane alla tabella
		scrollPane.setBounds(10, 50, 684, 219);
		getContentPane().add(scrollPane);
		
		txtInserisciNomeArt = new JTextField();
		txtInserisciNomeArt.setText("inserisci nome o id...");
		txtInserisciNomeArt.setBounds(10, 11, 159, 28);
		contentPane.add(txtInserisciNomeArt);
		txtInserisciNomeArt.setColumns(10);
		
		JButton btnSearchArt = new JButton("Cerca");
		btnSearchArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchArt.setBounds(179, 18, 89, 23);
		contentPane.add(btnSearchArt);
		
		btnBackToMenu = new JButton("Indietro");
		btnBackToMenu.setForeground(new Color(255, 255, 0));
		btnBackToMenu.setBackground(new Color(255, 51, 0));
		btnBackToMenu.setBounds(567, 393, 127, 37);
		contentPane.add(btnBackToMenu);
		
		btnAddArt = new JButton("Aggiungi Articolo");
		btnAddArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Codice per gestire il click del bottone "Aggiungi Articolo"
		        // Mostra un form per l'aggiunta dell'articolo
		        JTextField nomeField = new JTextField(20);
		        JTextField giacenzaField = new JTextField(5);
		        JTextField prezzoAcquistoField = new JTextField(10);
		        JTextField prezzoVenditaField = new JTextField(10);
		        
		        String[] categorie = {"BIRRA", "GIN", "TONICA", "SPRITZ", "PROSECCO", "VERMOUTH", "RUM", "WHISKEY", "AMARI", "ALTRO", "VINO", "NONSOLOCARTA", "FOOD"};
		        JComboBox<String> categoriaComboBox = new JComboBox<>(categorie);
		        
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.add(new JLabel("Nome Articolo:"));
		        panel.add(nomeField);
		        panel.add(new JLabel("Giacenza:"));
		        panel.add(giacenzaField);
		        panel.add(new JLabel("Prezzo Acquisto:"));
		        panel.add(prezzoAcquistoField);
		        panel.add(new JLabel("Prezzo Vendita:"));
		        panel.add(prezzoVenditaField);
		        panel.add(new JLabel("Categoria:"));
		        panel.add(categoriaComboBox);
		        
		        int result = JOptionPane.showConfirmDialog(null, panel, "Inserisci i dettagli dell'articolo",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        
		        if (result == JOptionPane.OK_OPTION) {
		            // Raccogli i dettagli inseriti dall'utente
		            String nome = nomeField.getText();
		            int giacenza = Integer.parseInt(giacenzaField.getText());
		            BigDecimal prezzoAcquisto = new BigDecimal(prezzoAcquistoField.getText());
		            BigDecimal prezzoVendita = new BigDecimal(prezzoVenditaField.getText());
		            String categoria = (String) categoriaComboBox.getSelectedItem();
		            
		            // Creazione dell'oggetto Articolo con i dettagli inseriti dall'utente
		            Articolo nuovoArticolo;
		            
		            nuovoArticolo.setCategoria();
		            
		            // Aggiunta dell'articolo al database
		            aggiungiArticoloAlDatabase(nuovoArticolo);
		         
		         // Aggiorna la tabella degli articoli nell'interfaccia grafica
		            aggiornaTabellaArticoli();
		        }
			}

			private void aggiungiArticoloAlDatabase(Articolo nuovoArticolo) {
				// TODO Auto-generated method stub
				
			}

			private void aggiornaTabellaArticoli() {
				// TODO Auto-generated method stub
				
			}
		});
		btnAddArt.setBounds(10, 292, 159, 53);
		contentPane.add(btnAddArt);
		
		btnRmvArticolo = new JButton("Rimuovi Articolo");
		btnRmvArticolo.setBounds(10, 377, 159, 53);
		contentPane.add(btnRmvArticolo);
	}
	
	//Metodo per mostrare il frame degli articoli
	public void showFrame() {
		setVisible(true);
		toFront();
	}
}
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;

public class ArticoliFrame extends JFrame {
	private static ArticoliFrame istance; //Dichiarazione della variabile di classe

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField keywordField;
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
		
		//Creazione JTable con modello vuoto
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID_Articolo");
		model.addColumn("Nome");
		model.addColumn("Giacenza");
		model.addColumn("Prezzo acquisto");
		model.addColumn("Prezzo unitario");
		model.addColumn("Categoria");
		table = new JTable(model);	//Carica la tabella con i seguenti parametri
		table.setBounds(10, 11, 414, 129);
		JScrollPane scrollPane = new JScrollPane(table);	//Aggiungi un scroll pane alla tabella
		scrollPane.setBounds(10, 50, 684, 219);
		getContentPane().add(scrollPane);
		
		Articoli tmpArt = new Articoli();
		tmpArt.aggiornaTabellaArticoli(model);
		
		//SEZIONE CERCA ARTICOLO
		keywordField = new JTextField();
		keywordField.setBounds(10, 11, 159, 28);
		contentPane.add(keywordField);
		keywordField.setColumns(10);
		
		JButton btnSearchArt = new JButton("Cerca");
		btnSearchArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = keywordField.getText();
                tmpArt.ricercaArticolo(keyword, table);
			}
		});
		btnSearchArt.setBounds(176, 10, 89, 30);
		contentPane.add(btnSearchArt);
		
		//SEZIONE TORNA INDIETRO
		btnBackToMenu = new JButton("Indietro");
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	//Chiude l'istanza di ArticoliFrame
			}
		});
		btnBackToMenu.setForeground(new Color(255, 255, 0));
		btnBackToMenu.setBackground(new Color(255, 51, 0));
		btnBackToMenu.setBounds(567, 393, 127, 37);
		contentPane.add(btnBackToMenu);
		
		//SEZIONE ADD ARTICOLO
		btnAddArt = new JButton("Aggiungi Articolo");	
		btnAddArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Codice per gestire il click del bottone "Aggiungi Articolo"
		        // Mostra un form per l'aggiunta dell'articolo
		        JTextField nomeField = new JTextField(20);
		        JTextField giacenzaField = new JTextField(5);
		        JTextField prezzoAcquistoField = new JTextField(10);
		        JTextField prezzoVenditaField = new JTextField(10);
		        
		        String[] categorie = {"BIRRA", "GIN", "TONICA", "SPRITZ", "PROSECCO", "VERMOUTH", "RUM", "WHISKEY", "AMARI", "ALTRO", "VINO", "NONSOLOCARTA", "FOOD", "SINISI"};
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
		            String categoriaString = (String) categoriaComboBox.getSelectedItem();	//Estraggo la stringa per la categoria		       
		            Categoria categoria = Categoria.valueOf(categoriaString);	//Assegno alla variabile enum la stringa estratta
		            		           		        
		            // Creazione dell'oggetto Articolo con i dettagli inseriti dall'utente
		            Articolo nuovoArticolo = new Articolo(nome, giacenza, prezzoAcquisto, prezzoVendita, categoria);
		            		
		            Articoli tmpArticoli = new Articoli();
		            // Aggiunta dell'articolo al database
		            tmpArticoli.aggiungiArticoloAlDatabase(nuovoArticolo);
		         
		            // Aggiorna la tabella degli articoli nell'interfaccia grafica
		            tmpArticoli.aggiornaTabellaArticoli(model);	//TODO: CREA BOTTONE AGGIORNA TABELLA
		        }
			}
		});
		btnAddArt.setBounds(10, 292, 159, 53);
		contentPane.add(btnAddArt);
		
		//SEZIONE RIMUOVI ARTICOLO
		btnRmvArticolo = new JButton("Rimuovi Articolo");
		btnRmvArticolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tmpArt.rimuoviArticolo();
				tmpArt.aggiornaTabellaArticoli(model);
			}
		});
		btnRmvArticolo.setBounds(10, 377, 159, 53);
		contentPane.add(btnRmvArticolo);
		
		//SEZIONE REFRESH TABELLA
		JButton btnNewButton = new JButton("Refresh table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tmpArt.aggiornaTabellaArticoli(model);	//Refresha la JTable
			}
		});
		btnNewButton.setBounds(589, 14, 105, 28);
		contentPane.add(btnNewButton);
	}
	
	//Metodo per mostrare il frame degli articoli
	public void showFrame() {
		setVisible(true);
		toFront();
	}
}

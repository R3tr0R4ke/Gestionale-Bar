import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

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
				{"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
	            {"001", "Articolo 1", 10, 50.0, 5.0, "Categoria 1"},
	            {"002", "Articolo 2", 20, 60.0, 4.0, "Categoria 2"},
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
		txtInserisciNomeArt.setBounds(10, 19, 159, 20);
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

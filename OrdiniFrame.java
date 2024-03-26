import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class OrdiniFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdiniFrame frame = new OrdiniFrame();
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
	public OrdiniFrame() {
		setTitle("Ordini");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creazione JTable con modello vuoto
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID_Ordine");
				model.addColumn("ID_Fornitore");
				model.addColumn("DataOrd");				
				model.addColumn("Importo");
				table = new JTable(model);	//Carica la tabella con i seguenti parametri
				table.setBounds(10, 11, 414, 129);
				JScrollPane scrollPane = new JScrollPane(table);	//Aggiungi un scroll pane alla tabella
				scrollPane.setBounds(10, 11, 414, 123);
				getContentPane().add(scrollPane);
		
	}

}

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AggiungiOrdine extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxFornitori;
    private JTextField textFieldImporto;
    
    private String url = "jdbc:mysql://localhost:3306/gestionalebar";
	private String username = "root";
	private String password = "SESSOPAZZO39!";
	private JTextField txtInserireImporto;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AggiungiOrdine frame = new AggiungiOrdine();
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
	public AggiungiOrdine() {
		setTitle("Registra Ordine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JLabel lblFornitore = new JLabel("Fornitore :");
		lblFornitore.setBounds(10, 11, 57, 30);
		contentPane.add(lblFornitore);
		
		Fornitore fornitore = new Fornitore();
		List<String> nomiFornitori = fornitore.getNomiFornitori();
		comboBoxFornitori = new JComboBox<>(nomiFornitori.toArray(new String[0]));
		comboBoxFornitori.setBounds(77, 15, 139, 22);
		contentPane.add(comboBoxFornitori);
		
		JLabel lblImporto = new JLabel("Importo :");
		lblImporto.setBounds(226, 15, 46, 22);
		contentPane.add(lblImporto);
		
		txtInserireImporto = new JTextField();
		txtInserireImporto.setText("Inserire importo...");
		txtInserireImporto.setBounds(282, 16, 142, 20);
		contentPane.add(txtInserireImporto);
		txtInserireImporto.setColumns(10);
		
		JButton buttonSalva = new JButton("Salva");
		buttonSalva.addActionListener(new ActionListener() {
			@Override
			public void actionPerfomed(ActionEvent e) {
				String nomeFornitore = (String) comboBoxFornitori.getSelectedItem();
				float importo = Float.parseFloat(textFieldImporto.getText());
				
				if(importo < 0) {
					JOptionPane.showMessageDialog(null, "L'import deve essere > 0");
					return;
				}
				
				inserisciOrdine(nomeFornitore, importo);
			}
		});
		contentPane.add(buttonSalva);
		add(contentPane);	
	}
	
	private void inserisciOrdine(String nomeFornitore, float importo) {
		try (Connection conn = DriverManager.getConnection(url, username, password)){
			// Ottieni l'ID del fornitore
            String queryFornitore = "SELECT ID FROM fornitori WHERE nome = ?";
            try (PreparedStatement pstmtFornitore = conn.prepareStatement(queryFornitore)){
            	pstmtFornitore.setString(1, nomeFornitore);
                var rsFornitore = pstmtFornitore.executeQuery();
                if (!rsFornitore.next()) {
                    JOptionPane.showMessageDialog(null, "Fornitore non trovato nel database.");
                    return;
                }
                
                // Inserisci l'ordine nella tabella ordini
                String queryOrdine = "INSERT INTO ordini (ID_Fornitore, DataOrd, Importo) VALUES (?, ?, ?)";
                try (PreparedStatement pstmtOrdine = conn.prepareStatement(queryOrdine)){
                	pstmtOrdine.setInt(1, idFornitore);
                    pstmtOrdine.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                    pstmtOrdine.setFloat(3, importo);
                    pstmtOrdine.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Ordine inserito con successo nel database.");

                }
            }
		}
	}
}

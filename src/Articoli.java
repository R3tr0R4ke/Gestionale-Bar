import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Articoli extends Articolo{
	private HashMap<String, Articolo> articoliMap;
	private String url = "jdbc:mysql://localhost:3306/gestionalebar";
	private String username = "root";
	private String password = "root";
	
	public Articoli() {}
	
	public void addArticolo(Articolo articolo) {
		articoliMap.put(getNome(), articolo);	//Articolo viene aggiunto nella mappa
	}
	
	//Ritorna uan HashMap di articoli
	public HashMap<String, Articolo> getArticoli(){
		return articoliMap;
	}
	
	public void aggiungiArticoloAlDatabase(Articolo nuovoArticolo) {
		String query = "INSERT INTO articoli (nome, giacenza, prezzoAcquisto, prezzoVendita, categoria) VALUES (?, ?, ?, ? ,?)";
		
		try (Connection conn = DriverManager.getConnection(url, username, password);
	             PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setString(1, nuovoArticolo.getNome());
            pstmt.setInt(2, nuovoArticolo.getGiacenza());
            pstmt.setBigDecimal(3, nuovoArticolo.getPrezzoAcquisto());
            pstmt.setBigDecimal(4, nuovoArticolo.getPrezzoUnitario());
            pstmt.setString(5, nuovoArticolo.getCategoria().toString()); // Assuming Categoria is an enum
            
            pstmt.executeUpdate();
            
            System.out.println("Articolo aggiunto al database.");
		}
		catch (SQLException e) {
            System.err.println("Errore durante l'inserimento dell'articolo nel database: " + e.getMessage());
        }
	}
	
	public void aggiornaTabellaArticoli(DefaultTableModel model) {
		model.setRowCount(0);
		
		String query = "SELECT * FROM articoli";
        
		try(Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmt = conn.prepareStatement(query)){
				ResultSet rs = pstmt.executeQuery(query);	//Esecuzione della query
				
				//Popolamento del modello con i dati recuperati
				while(rs.next()) {
					model.addRow(new Object[]{
	                        rs.getString("Id_Articolo"),
	                        rs.getString("Nome"),
	                        rs.getInt("Giacenza"),
	                        rs.getDouble("PrezzoAcquisto"),
	                        rs.getDouble("PrezzoVendita"),
	                        rs.getString("Categoria")
	                });
				}
		}
		catch (SQLException e) {
            System.err.println("Errore Durante l'aggiornamento della tabella: " + e.getMessage());
        }
	}
	
	//Permette di rimuovere un articolo dalla tabella
	public void rimuoviArticolo() {
		// Creazione di un dialog panel per l'inserimento del nome dell'articolo
        JTextField txtNomeArticolo = new JTextField(20);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome dell'articolo:"));
        panel.add(txtNomeArticolo);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Inserisci il nome dell'articolo da rimuovere",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String nomeArticolo = txtNomeArticolo.getText().trim();
            if (!nomeArticolo.isEmpty()) {
            	PreparedStatement stmt = null;
            	Connection conn = null;
            	
                //Rimozione dalla tabella di MySQL
            	try {
                    // Connessione al database
                    conn = DriverManager.getConnection(url, username, password);

                    // Query di eliminazione per rimuovere l'articolo con il nome specificato
                    String sql = "DELETE FROM Articoli WHERE Nome = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, nomeArticolo);

                    // Esecuzione della query di eliminazione
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Articolo rimosso con successo.");
                    } else {
                        System.out.println("Nessun articolo rimosso.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    // Chiusura delle risorse
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            	
            	
            } else {
                JOptionPane.showMessageDialog(null, "Inserisci il nome dell'articolo.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }		     
    }
	
	//TODO: METODO RICERCA TRAMITE FILTRO
}

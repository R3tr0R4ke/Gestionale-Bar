import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Articoli extends Articolo{
	public Articoli() {}
		
	public void aggiungiArticoloAlDatabase(Articolo nuovoArticolo) {
		//Aggiungere controlli su inserimenti errati
		String query = "INSERT INTO articoli (nome, giacenza, prezzoAcquisto, prezzoVendita, categoria) VALUES (?, ?, ?, ? ,?)";
		
		try (Connection conn = DBConnector.getConnection();
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
        
		try(Connection conn = DBConnector.getConnection();
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
        JTextField txtIdArticolo = new JTextField(10);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome dell'articolo:"));
        panel.add(txtNomeArticolo);
        panel.add(new JLabel("ID dell'articolo:"));
        panel.add(txtIdArticolo);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Inserisci il nome dell'articolo da rimuovere",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String nomeArticolo = txtNomeArticolo.getText().trim();
            String idArticoloString = txtIdArticolo.getText().trim();
            if (!nomeArticolo.isEmpty() || !idArticoloString.isEmpty()) {
            	PreparedStatement stmt = null;
            	Connection conn = null;
            	
                //Rimozione dalla tabella di MySQL
            	try {
                    // Connessione al database
                    conn = DBConnector.getConnection();

                    if (!idArticoloString.isEmpty()) {
                        // Rimozione dall'ID se è stato inserito
                        int idArticolo = Integer.parseInt(idArticoloString);
                        String sql = "DELETE FROM Articoli WHERE ID_Articolo = ?";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, idArticolo);
                    } else {
                        // Rimozione dal nome se è stato inserito
                        String sql = "DELETE FROM Articoli WHERE Nome = ?";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, nomeArticolo);
                    }

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
                JOptionPane.showMessageDialog(null, "Inserisci il nome o l'ID dell'articolo.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }		     
    }
	
	//TODO: METODO RICERCA TRAMITE FILTRO
	public void ricercaArticolo(String keyword, JTable table) {
		String queryByID = "SELECT * FROM Articoli WHERE ID_Articolo = ?";
		String queryByName = "SELECT * FROM Articoli WHERE Nome LIKE ?";
		
		try (Connection conn = DBConnector.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(keyword.matches("\\d+") ? queryByID : queryByName)){
			
			if(keyword.matches("\\d+")) {
				//Se il parametro keyword può essere convertito in un ID, cerc per ID
				int id = Integer.parseInt(keyword);
				pstmt.setInt(1, id);
			}
			else {
	            // Altrimenti, cerca per nome
	            pstmt.setString(1, "%" + keyword + "%");
	        }
			
			//Esecuzione della query
			try(ResultSet rs = pstmt.executeQuery()){
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);	//Rimuove tutte le righe esistenti nella tabella
				
				while (rs.next()) {
                    int id = rs.getInt("ID_Articolo");
                    String nome = rs.getString("nome");
                    int giacenza = rs.getInt("giacenza");
                    // Aggiungi altre colonne se necessario
                    double prezzoAcquisto = rs.getDouble("prezzoAcquisto");
                    double prezzoVendita = rs.getDouble("prezzoVendita");
                    String categoriaString = rs.getString("categoria");
                    Categoria categoria = Categoria.valueOf(categoriaString);
                    
                    // Aggiungi riga alla tabella
                    model.addRow(new Object[]{id, nome, giacenza, prezzoAcquisto, prezzoVendita, categoria});
                }
			}
		}
		catch (SQLException e) {
            System.err.println("Errore durante la ricerca dell'articolo: " + e.getMessage());
        }
	}
	
	public void updateArticolo(int id, Articolo articolo) {
		//TODO: aggiungere controlli su inserimenti errati
		String query = "UPDATE Articoli SET Nome = ?, Giacenza = ?, PrezzoAcquisto = ?, PrezzoVendita = ?, Categoria = ? WHERE ID_Articolo = ?";
		
		try(Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)){
					
			// Imposta i parametri per l'aggiornamento
            pstmt.setString(1, articolo.getNome());
            pstmt.setInt(2, articolo.getGiacenza());
            pstmt.setBigDecimal(3, articolo.getPrezzoAcquisto());
            pstmt.setBigDecimal(4, articolo.getPrezzoUnitario());
            pstmt.setString(5, articolo.getCategoria().toString());
            pstmt.setInt(6, id);
            
            pstmt.executeUpdate();
		}
		catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento dell'articolo: " + e.getMessage());
        }
	}
}
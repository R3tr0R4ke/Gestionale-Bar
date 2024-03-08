import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Articoli extends Articolo{
	private HashMap<Integer, Articolo> articoliMap;
	private String url = "jdbc:mysql://localhost:3306/gestionalebar";
	private String username = "root";
	private String password = "SESSOPAZZO39!";
	
	public Articoli() {
	}
	
	/*public void addArticolo(Articolo articolo) {
		articoliMap.put(getIdArticolo(), articolo);	//Articolo viene aggiunto nella mappa
	}*/
	
	//Ritorna uan HashMap di articoli
	public HashMap<Integer, Articolo> getArticoli(){
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
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Fornitore {
	private int idFornitore;
	private String nomeFornitore, contatto;
	
	private String url = "jdbc:mysql://localhost:3306/gestionalebar";
	private String username = "root";
	private String password = "SESSOPAZZO39!";
	
	//Costruttore vuoto
	public Fornitore() {}
	
	public Fornitore(int idFornitore, String nomeFornitore, String contatto) {
		if(idFornitore < 0) {	//Introdurre controllo per idEsistenti
			idFornitore = 0;	//Default = 0
			throw new IllegalArgumentException("Id non valido");
		}
		
		if(nomeFornitore == null || nomeFornitore.isEmpty() || nomeFornitore.length() > 55) {		//TODO: ESPRESSIONI REGOLARI
			throw new IllegalArgumentException("Nome non valido");
		}
		
		if(contatto == null || contatto.isEmpty() || contatto.length() > 55) {
			throw new IllegalArgumentException("Contatto non valido");
		}
		
		this.idFornitore = idFornitore;
		this.nomeFornitore = nomeFornitore;
		this.contatto = contatto;
	}
	
	//Getters
	public int getIdFornitore() {
		return idFornitore;
	}
	
	public String getNomeFornitore() {
		String nomeFornitore = this.nomeFornitore.replaceAll("[^a-zA-Z0-9\\s]", "");	//Elimina i caratteri speciali
		nomeFornitore = nomeFornitore.length() >  55 ? nomeFornitore.substring(0, 55) : nomeFornitore;	//Setta la stringa a una lunghezza di 55 caratteri
		return String.format("%-55s", nomeFornitore);	//Ritorna il nome
	}
	
	public String getContatto() {
		String contatto = this.contatto.replaceAll("[^a-zA-Z0-9\\\\s]", "");
		contatto = contatto.length() > 55 ? contatto.substring(0, 55) : contatto;
		return String.format("%-55s", contatto);	//Ritorna il contatto
	}
	
	//Setters
	
	/*
	 * N.B.: Questo serve solo a livello sviluppo, utilizza le query di sql per aggiornare le tabelle
	 * 		Vedi funzione addFornitore()
	 */
	public void setIdFornitore(int idFornitore) {	
		if(idFornitore < 0) {
			throw new IllegalArgumentException("Id non valido");
		}
		this.idFornitore = idFornitore;
	}
	
	public void setNome(String nomeFornitore) {
		if(nomeFornitore == null || nomeFornitore.isEmpty() || nomeFornitore.length() > 55) {		//TODO: ESPRESSIONI REGOLARI
			throw new IllegalArgumentException("Nome non valido");
		}
		this.nomeFornitore = nomeFornitore;
	}
	
	public void setContatto(String contatto) {
		if(contatto == null || contatto.isEmpty() || contatto.length() > 55) {
			throw new IllegalArgumentException("Contatto non valido");
		}
		this.contatto = contatto;
	}
	
	//Metodo che permettere di aggiungere un record fornitore nel databse mysql
	public void addFornitore(Fornitore fornitore) {		
		String query = "INSERT INTO fornitori (nome, contatto) VALUES (?, ?)";
		
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement pstm = conn.prepareStatement(query);) {
			
			//Id si autoincrementa da solo con l'insert
			pstm.setString(1, getNomeFornitore());
			pstm.setString(2, getContatto());
		}
		catch (SQLException e) {
	        System.err.println("Errore durante l'inserimento del fornitore nel database: " + e.getMessage());
		}
	}
	
	//Metodo che permette di rimuovere un fornitore dal db fornendo l'id del fornitore
	public void removeFornitore(int idFornitore) {
		String query = "DELETE FROM fornitori WHERE ID_Fornitore = ?";
		
		try (Connection conn = DriverManager.getConnection(url, username, password);
		         PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setInt(1, idFornitore);
			
			int righeModificate = pstmt.executeUpdate();
			
			if(righeModificate > 0) {
				System.out.println("Fornitore rimosso con successo dal database");
			}
			else {
				System.out.println("Nessun fornitore trovato con l'ID specificato");
			}
		}
		catch (SQLException e) {
	        System.err.println("Errore durante l'inserimento del fornitore nel database: " + e.getMessage());
		}
	}
	
	//Ritorna una lista di fornitori
	public List<String> getNomiFornitori(){
		List<String> nomiFornitori = new ArrayList<String>();
		String query = "SELECT nomeFornit FROM fornitori";
		
		try (Connection conn = DriverManager.getConnection(url, username, password);
	        Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery(query)) {
	                     
	        while (rs.next()) {
	        	String nomeFornitore = rs.getString("nome");
	            nomiFornitori.add(nomeFornitore);
	        }    	 
	    }
	    catch (SQLException e) {
	    	 System.err.println("Errore durante il recupero dei nomi dei fornitori: " + e.getMessage());
		}
		return nomiFornitori;
	}
	             
}

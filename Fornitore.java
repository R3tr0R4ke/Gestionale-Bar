import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Fornitore {
	private String nome, contatto;
	private FornitoriEnum fornitEnum;
	
	//Costruttore vuoto
	public Fornitore() {}
	
	//Costruttore fornitore
	public Fornitore(String nome, String contatto, FornitoriEnum fornitEnum) {
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome articolo non presente");
		}
		
		if(contatto == null || contatto.isEmpty()) {
			throw new IllegalArgumentException("Contatto non valido");
		}
		
		this.nome = nome;
		this.contatto = contatto;
		this.fornitEnum = fornitEnum;
	}
	
	//Getters
	public String getNomeFornitore() {
		String nomeFornitore = this.nome.replaceAll("[^a-zA-Z0-9\\s]", "");	//Elimina i caratteri speciali
		nomeFornitore = nomeFornitore.length() >  55 ? nomeFornitore.substring(0, 55) : nomeFornitore;	//Setta la stringa a una lunghezza di 55 caratteri
		return String.format("%-55s", nomeFornitore);	//Ritorna il nome
	}
	
	public String getContatto() {
		String contatto = this.contatto.replaceAll("[^a-zA-Z0-9\\\\s]", "");
		contatto = contatto.length() > 55 ? contatto.substring(0, 55) : contatto;
		return String.format("%-55s", contatto);	//Ritorna il contatto
	}
	
	public FornitoriEnum getFornitEnum() {
		return fornitEnum;
	}
	
	//Setters
	public void setNome(String nomeFornitore) {
		if(nomeFornitore == null || nomeFornitore.isEmpty() || nomeFornitore.length() > 55) {		//TODO: ESPRESSIONI REGOLARI
			throw new IllegalArgumentException("Nome non valido");
		}
		this.nome = nomeFornitore;
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
		
		try (Connection conn = DBConnector.getConnection();
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
		
		try (Connection conn = DBConnector.getConnection();
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
}
import java.math.BigDecimal;

public class Articolo {
	private int giacenza;
	private String nome;
	private BigDecimal prezzoAcquisto, prezzoUnitario;	//Big decimal si usa per trattare operazioni che riguardano valore monetario
	private Categoria categoria;
	
	public Articolo() {}	//Costruttore vuoto
	
	//Costruttore che accetta solo id, nome, giacenza, categoria
	public Articolo(int idArticolo, String nome, int giacenza, Categoria categoria) {
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome articolo non presente");
		}
		if(giacenza < 0) {
			throw new IllegalArgumentException("Giacenza non può essere < 0");
		}
		this.nome = nome;
		this.giacenza = giacenza;
		this.categoria = categoria;
	}
	
	//Costruttore con tutti i parametri
	public  Articolo(String nome, int giacenza, BigDecimal prezzoAcquisto, BigDecimal prezzoUnitario, Categoria categoria) {
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome articolo non presente");
		}
		if(giacenza < 0) {
			this.giacenza = 0;
			throw new IllegalArgumentException("Giacenza non può essere < 0");
		}
		if(prezzoAcquisto == null || prezzoAcquisto.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Prezzo acquisto non valido");
		}
		if(prezzoUnitario == null || prezzoUnitario.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Prezzo unitario non valido");
		}
		
		this.nome = nome;
		this.giacenza = giacenza;
		this.prezzoAcquisto = prezzoAcquisto;
		this.prezzoUnitario = prezzoUnitario;
		this.categoria = categoria;
	}
	
	//Getters:
	/*public int getIdArticolo() {
		return idArticolo;
	}*/
	
	public String getNome() {
		return nome;
	}
	
	public int getGiacenza() {
		return giacenza;
	}
	
	public BigDecimal getPrezzoAcquisto() {
		return prezzoAcquisto;
	}
	
	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	//Setters:
	public void setNome(String nome) {
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome non valido");	//TODO: IMPLEMENTARE CONTROLLO SUI NOMI TRAMITE L'USO DI ESPRESSIONI REGOLARI
		}
		this.nome = nome;
	}
	
	public void setGiacenza(int giacenza) {
		if(giacenza < 0) {
			throw new IllegalArgumentException("Giacenza non può essere < 0");
		}
		this.giacenza = giacenza;
	}
	
	public void setPrezzoAcquisto(BigDecimal prezzoAcquisto) {
		if(prezzoAcquisto == null || prezzoAcquisto.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Prezzo acquisto non valido");
		}
		this.prezzoAcquisto = prezzoAcquisto;
	}
	
	public void setPrezzoVendita(BigDecimal prezzoUnitario) {
		if(prezzoUnitario == null || prezzoUnitario.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Prezzo unitario non valido");
		}
		this.prezzoUnitario = prezzoUnitario;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	//TODO: AGIGUNGERE ALTRI METODI EVENTUALI, CONTENITORI, VISTE
}
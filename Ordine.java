import java.math.BigDecimal;
import java.sql.Date;

public class Ordine {
	private int idFornitore;
	private Date dataOrdine;
	private BigDecimal importo;
	
	public Ordine() {}
	
	public Ordine(int idFornitore, Date dataOrdine, BigDecimal importo) {
		this.idFornitore = idFornitore;
		this.dataOrdine = dataOrdine;
		this.importo = importo;
	}
	
	//Getters
	public int getIdFornitore() {
		return idFornitore;
	}
	
	public Date getDataOrdine() {
		return dataOrdine;
	}
	
	public BigDecimal getImporto() {
		return importo;
	}
	
	//Setters
	/*public void () {
		this.fo
	}*/
}

import java.util.HashMap;
import java.util.List;

public class Articoli extends Articolo{
	private HashMap<Integer, Articolo> articoliMap;
	
	public Articoli() {
	}
	
	public void addArticolo(Articolo articolo) {
		articoliMap.put(getIdArticolo(), articolo);	//Articolo viene aggiunto nella mappa
	}
	
	//Ritorna uan HashMap di articoli
	public HashMap<Integer, Articolo> getArticoli(){
		return articoliMap;
	}
	
	//TODO: INSERIRE 
}

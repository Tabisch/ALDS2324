

/**
 * Vereinfachte Form der Schnittstelle java.util.Collection<E>
 * 
 * Die Schnittstelle legt fest, welche Operationen eine Klasse,
 * welche die Eigenschaften (den Typ) einer Collection<E> haben 
 * soll, zur Verfügung stellen muss.
 * 
 * Gegenüber der Schnittstelle java.util.Collection<E> in der 
 * Java-Klassenbibliothek sind einige Operationen entfallen.
 * 
 * @author Manfred Meyer
 *
 * @param <E> Typ der in der Collection zu speichernden Elemente
 */
public interface SimpleCollection<E>
{
	/**
	 * Hinzufügen eines Elements zu einer Collection
	 * 
	 * @param element das anzufügende Element
	 * @return true gdw. das Element angefügt werden konnte
	 */
	public boolean add(E element);

	/**
	 * Löschen eines Elements aus einer Collection
	 * 
	 * @param element das zu entfernende Element
	 * @return true gdw. wenn ein inhaltsgleiches Element aus
	 *         der Collection entfernt werden konnte          
	 */
	public boolean remove(E element);

	/**
	 * Prüfen, ob eine Collection leer ist
	 * 
	 * @return true gdw. die Collection leer ist
	 */
	public boolean isEmpty();

	/**
	 * Ermitteln der aktuellen Anzahl an Elementen 
	 * 
	 * @return die aktuelle Anzahl der Elemente in der Collection
	 */
	public int size();

	/**
	 * Prüfen, ob ein Element in der Collection enthalten ist
	 * 
	 * @param element das zu suchende Element
	 * @return true gdw. ein inhaltsgleiches Element enthalten ist
	 */
	public boolean contains(E element);

	/**
	 * Erzeugen eines Iterators zur Navigation durch die Collection
	 * 
	 * @return eine neue Instanz von SimpleIterator<E>, die vor 
	 *         dem ersten Element der Collection steht
	 */
	public SimpleIterator<E> iterator();
}
	
	
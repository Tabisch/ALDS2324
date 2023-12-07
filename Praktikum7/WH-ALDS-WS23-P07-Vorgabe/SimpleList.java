

/**
 * Vereinfachte Form der Schnittstelle java.util.List<E>
 * 
 * Die Schnittstelle legt fest, welche Operationen eine
 * Klasse, die eine Liste implementiert, zur Verfügung 
 * stellen muss.
 * 
 * Gegenüber der Schnittstelle java.util.List<E> in der 
 * Java-Klassenbibliothek sind einige Operationen entfallen.
 * 
 * @author Manfred Meyer
 *
 * @param <E> Typ der in der Liste zu speichernden Elemente
 */
public interface SimpleList<E>
extends SimpleCollection<E>
{
	/**
	 * Einfügen eines Elements in eine Liste
	 * 
	 * @param element das einzufügende Element
	 * @param index die Position, an der eingefügt werden soll
	 * @return true gdw. das Element eingefügt werden konnte
	 */
	public void add(int index, E element);
	
	/**
	 * Entfernen eines Elements aus einer Liste
	 * 
	 * @param index Position des zu entfernenden Elements
	 * @return
	 */
	public E remove(int index);
	
	/**
	 * Zugriff auf ein Element über seine Position (Index)
	 * 
	 * @param index Position des zu liefernden Elements
	 * @return das an der Position index gespeicherte Element
	 *         oder null, falls kein solches vorhanden
	 */
	public E get(int index);
	
	/**
	 * Ermitteln der Position eines Elements in der Liste
	 * @param element das zu suchende Element
	 * @return Position des ersten Vorkommens eines inhalts-
	 *         gleichen Elements in der Liste 
	 *         oder -1 falls kein solches Element enthalten
	 */
	public int indexOf(E element);
}
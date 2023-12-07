

public interface SimpleListIterator<E> extends SimpleIterator<E>
{
	public boolean hasPrevious();
	
	public E previous();
	
	public void set(E o);
	
	public void add(E o);	
}
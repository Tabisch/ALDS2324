import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({ "unchecked", "rawtypes" })
class LinProbHashTable<KeyType extends Comparable>
{
	ElementType<? extends Comparable>[]	ht;
	int																	n;
	
	LinProbHashTable ( int n )
	{
		this.n=n;
		ht=new ElementType[n];
	}
	
	public void add (ElementType<? extends Comparable> elem)
	{
		int hc=elem.key.hashCode()%this.n;
		if (hc<0)
			hc=-hc;
		int i=0;
		int pos=hc;
		Logger.getGlobal().log(Level.INFO, "add: Hashing fuer "+elem+" - Position: "+pos);
		while ((ht[pos]!=null)&&(i<n))
		{
			i++;
			pos=(hc+i)%n;
			Logger.getGlobal().log(Level.INFO,
			    "add: Kollisionsbehandlung fuer "+elem+" - neue Position: "+pos);
		}
		if (i==n)
			throw new RuntimeException("HashTable full.");
		ht[pos]=elem;
		Logger.getGlobal().log(Level.INFO, "add: Neues Element "+elem+" eingefuegt - Position: "+pos);
	}
	
	public ElementType<? extends Comparable> get (KeyType key)
	{
		int hc=key.hashCode()%this.n;
		if (hc<0)
			hc=-hc;
		int i=0;
		int pos=hc;
		Logger.getGlobal().log(Level.INFO, "get: Hashing fuer Schluessel "+key+" - Position: "+pos);
		while ((ht[pos]!=null)&&(i<n))
		{
			if (ht[pos].key.compareTo(key)==0)
				return ht[pos];
			i++;
			pos=(hc+i)%n;
			Logger.getGlobal().log(Level.INFO,
			    "get: Suche geht weiter fuer Schluessel "+key+" - neue Position: "+pos);
		}
		return null;
	}
}
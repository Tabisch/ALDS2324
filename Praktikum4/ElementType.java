class ElementType<KeyType>
{
	KeyType key;
	
	// Code aus der Vorlesung ergaenzt um Konstruktor
	
	ElementType ( KeyType key )
	{
		this.key=key;
	}
	
	// Code aus der Vorlesung ergaenzt um toString-Methode
	
	public String toString ()
	{
		return key.toString();
	}
}
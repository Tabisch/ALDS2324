public class Auftrag implements Comparable<Auftrag>{
    
    int Auftragsnr;

    public Auftrag(int Auftragsnr) {
        this.Auftragsnr = Auftragsnr;
    }

    public int getAuftragsnr() {
        return this.Auftragsnr;
    }

	@Override
	public int compareTo(Auftrag o) {
		if(this.Auftragsnr == o.getAuftragsnr())
		{
            return 0;
		}

        if(this.Auftragsnr < o.getAuftragsnr())
		{
            return -1;
		}
        else {
            return 1;
        }
	}

    @Override
    public String toString()
    {
        return String.valueOf(this.Auftragsnr);
    }
}

abstract class Publication
{
	private String title;
	private String publisher;
	private int numOfPages;
	private double price;
	
	Publication()
	{}
	
	Publication (String title, String publisher, int numOfPages, double price)
	{
		setTitle(title);

		setPublisher(publisher);
		setNumOfPages(numOfPages);
		setPrice(price);	
	}
	
	//Accessor Methods
	public String getTitle()
	{
		return title;
	}
	public String getPublisher()
	{
		return publisher;
	}	
	public int getNumOfPages()
	{
		return numOfPages;
	}	
	public double getPrice()
	{
		return price;
	}
	
	
	//Mutator methods
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	public void setNumOfPages(int numOfPages)
	{
		this.numOfPages = numOfPages;
	}	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String toString()
	{
		String str1 = String.format("The publication \"%s\" published by %s has %d pages and costs $%4.2f\n", title, publisher, numOfPages, price);
		return str1;
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Publication))
		{
			return false;
		}
		else 
		{
			Publication pub = (Publication)obj;
			if(pub.title.equals(title) && pub.publisher.equals(publisher) && pub.numOfPages == numOfPages)
				return true;
			else
				return false;
		}
	}				
}
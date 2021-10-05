class Book extends Publication implements CalculateSales
{
	private String author;
	
	Book()
	{ }
	
	Book (String title, String author, String publisher, int numOfPages, double price)
	{
		super(title, publisher, numOfPages, price);
		setAuthor(author);
		
	}
	//Accessor Method
	public String getAuthor()
	{
		return author;
	}
	//Mutator
	public void setAuthor (String author)
	{
		this.author = author;
	}
	
	public double salesValue(int copies)
	{
		return copies* getPrice() *.8;
	}
	
	public String toString()
	{
		String str1 = String.format("The book \"%s\" written by %s and published by %s ", getTitle(), author, getPublisher());
		str1 = str1 + String.format("has %d pages and costs $%4.2f\n", getNumOfPages(), getPrice());
		return str1;
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Book))
		{
			return false;
		}
		else 
		{
			Book bk1 = (Book)obj;
			if(super.equals(bk1) && bk1.author.equals(author))
				return true;
			else
				return false;
		}
	}
}
class ChildBook extends Book implements CalculateSales
{
	private int minAge, maxAge;
	
	ChildBook()
	{}
	
	ChildBook (String title, String author, String publisher, int numOfPages, double price, int minAge, int maxAge)
	{
		super(title, author, publisher, numOfPages, price);
		setMinAge(minAge);
		setMaxAge(maxAge);
	}
	//Accessor Method
	public int getMinAge()
	{
		return minAge;
	}
	public int getMaxAge()
	{
		return maxAge;
	}
	//Mutator
	public void setMinAge (int minAge)
	{
		this.minAge = minAge;
	}
	public void setMaxAge (int maxAge)
	{
		this.maxAge = maxAge;
	}
	public double salesValue(int copies)
	{
		return copies*getPrice()*.65;
	}
	public String toString()
	{
		String str1 = String.format("The children's book \"%s\" written by %s and published by %s ", getTitle(), getAuthor(), getPublisher());
		str1 = str1 + String.format("has %d pages and costs $%4.2f\n", getNumOfPages(), getPrice());
		str1 = str1 + String.format("The minimum age for the book is %d\nThe maximum age for the book is %d\n", minAge, maxAge);
		return str1;
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ChildBook))
		{
			return false;
		}
		else 
		{
			ChildBook cbk1 = (ChildBook)obj;
			if(super.equals(cbk1) && cbk1.minAge == minAge && cbk1.maxAge == maxAge)
				return true;
			else
				return false;
		}
	}
}
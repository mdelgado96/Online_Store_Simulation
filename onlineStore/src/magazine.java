class Magazine extends Publication implements CalculateSales
{
	public static enum pubUnitType {monthly, weekly, biweekly, other};
	private pubUnitType pubUnit;
	
	Magazine()
	{}
	
	Magazine (String title, String publisher, int numOfPages, double price, String pubUnitString)
	{
		super(title, publisher, numOfPages, price);
		setPubUnit(pubUnitString);
		
	}
	//Accessor Method
	public pubUnitType getPubUnit()
	{
		return pubUnit;
	}
	//Mutator Method
	public void setPubUnit(String pubUnitString)
	{
		pubUnit = pubUnitType.valueOf(pubUnitString);
	}
	
	
	public double salesValue(int copies)
	{
		return copies*getPrice()*.6;
	}
	
	public String toString()
	{
		String str1 = String.format("The magazine \"%s\" published by %s on a %s basis ", getTitle(), getPublisher(), pubUnit.toString());
		str1 = str1 + String.format("has %d pages and costs $%4.2f\n", getNumOfPages(), getPrice());
		return str1;
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Magazine))
		{
			return false;
		}
		else 
		{
			Magazine mag = (Magazine)obj;
			if(super.equals(mag) && mag.pubUnit.equals(pubUnit))
				return true;
			else
				return false;
		}
	}
}
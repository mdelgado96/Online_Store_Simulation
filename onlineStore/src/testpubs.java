import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
public class TestPubs
{
	public static Scanner keyboard = new Scanner(System.in);
	public static Scanner createScanner(String pubType) throws IOException
	{
		System.out.printf("Please enter the name of the %s input file: \n", pubType);
		String inputFileName = keyboard.next();
		File inputFile = new File(inputFileName);
		if(!inputFile.exists())
		{
			System.out.printf("Input File %s was not found.\n", inputFileName);
			System.exit(0);
		}
		return new Scanner(inputFile);
	}
	
	public static void main(String [] args) throws IOException
	{
		// create three Scanners
		Scanner magsReader = createScanner("Magazine");
		Scanner booksReader = createScanner("Books");
		Scanner childBooksReader = createScanner("Childrens Book");
		ArrayList<Publication> pubsAL = new ArrayList<Publication>();
		
		//Create The Output File
		System.out.printf("Please enter the name of the output file: \n");
		String outputFileName = keyboard.next();
		File outputFile = new File(outputFileName);
		PrintWriter outputWriter = new PrintWriter(outputFile);
		
		//Temporary variables used to create objects
		String title, publisher, pubFrequency, author;
		int numberPages, minAge, maxAge;
		double price;
		Magazine magTemp;
		Book bookTemp;
		ChildBook cbookTemp;
		
		//Read in data on Magazine, create object, and add to ArrayList
		
		while (magsReader.hasNext())
		{
			title = magsReader.nextLine();
			publisher = magsReader.nextLine();
			numberPages = magsReader.nextInt();
			price = magsReader.nextDouble();
			pubFrequency = magsReader.next();
			magsReader.nextLine();
			magTemp = new Magazine(title, publisher, numberPages, price, pubFrequency);
			pubsAL.add(magTemp);			
		}
		while (booksReader.hasNext())
		{
			title = booksReader.nextLine();
			author = booksReader.nextLine();
			publisher = booksReader.nextLine();
			numberPages = booksReader.nextInt();
			price = booksReader.nextDouble();
			booksReader.nextLine();
			bookTemp = new Book(title, author, publisher, numberPages, price);
			pubsAL.add(bookTemp);			
		}
		while (childBooksReader.hasNext())
		{
			title = childBooksReader.nextLine();
			author = childBooksReader.nextLine();
			publisher = childBooksReader.nextLine();
			numberPages = childBooksReader.nextInt();
			price = childBooksReader.nextDouble();
			minAge = childBooksReader.nextInt();
			maxAge = childBooksReader.nextInt();
			childBooksReader.nextLine();			
			pubsAL.add(new ChildBook(title, author, publisher, numberPages, price, minAge, maxAge));			
		}
		//Generate The Output
		double magazineSales = 0.0, bookSales = 0.0, childBookSales = 0.0;
		
		int copies = 0;
		for(Publication pubTemp: pubsAL)
		{
			System.out.printf("How many copies of %s have been sold?: \n", pubTemp.getTitle());
			copies = keyboard.nextInt();
			if(pubTemp instanceof Magazine)
			{
				magTemp = (Magazine)pubTemp;
				outputWriter.printf("The magazine %s has total sales of $%4.2f\n", magTemp.getTitle(), magTemp.salesValue(copies));
				outputWriter.printf("%s\n", magTemp.toString());
				magazineSales = magazineSales + magTemp.salesValue(copies);
			}
			if((pubTemp instanceof Book)  && !(pubTemp instanceof ChildBook))
			{
				bookTemp = (Book)pubTemp;
				outputWriter.printf("The book %s has total sales of $%4.2f\n", bookTemp.getTitle(), bookTemp.salesValue(copies));
				outputWriter.printf("%s\n", bookTemp.toString());
				bookSales = bookSales + bookTemp.salesValue(copies);
			}
			if(pubTemp instanceof ChildBook)
			{
				cbookTemp = (ChildBook)pubTemp;
				outputWriter.printf("The child book %s has total sales of $%4.2f\n", cbookTemp.getTitle(), cbookTemp.salesValue(copies));
				outputWriter.printf("%s\n", cbookTemp.toString());
				childBookSales = childBookSales + cbookTemp.salesValue(copies);
			}
		}
		outputWriter.printf("The total sales for magazines is %4.2f\n", magazineSales);
		outputWriter.printf("The total sales for books is %4.2f\n", bookSales);
		outputWriter.printf("The total sales for childrens books is %4.2f\n", childBookSales);
		outputWriter.printf("The total for all sales is %4.2f\n", magazineSales + bookSales + childBookSales);
		
		//Testing the equals function
		ChildBook childBookTester = new ChildBook();
		cbookTemp = (ChildBook)(pubsAL.get(pubsAL.size()-1));
		System.out.printf("It is %b that \n%s equals \n%s\n", childBookTester.equals(cbookTemp), childBookTester.toString(), cbookTemp.toString());
		
		childBookTester.setTitle(cbookTemp.getTitle());
		childBookTester.setAuthor(cbookTemp.getAuthor());		
		childBookTester.setPublisher(cbookTemp.getPublisher());
		childBookTester.setNumOfPages(cbookTemp.getNumOfPages());
		childBookTester.setMinAge(cbookTemp.getMinAge());
		childBookTester.setMaxAge(cbookTemp.getMaxAge());
		
		System.out.printf("It is %b that \n%s equals \n%s\n", childBookTester.equals(cbookTemp), childBookTester.toString(), cbookTemp.toString());
		
		magsReader.close();
		booksReader.close();
		childBooksReader.close();
		outputWriter.close();
	}
}
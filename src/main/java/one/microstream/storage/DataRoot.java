package one.microstream.storage;

import java.util.ArrayList;
import java.util.List;

import one.microstream.domain.Book;


public class DataRoot
{
	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks()
	{
		return books;
	}
	
	public void setBooks(List<Book> books)
	{
		this.books = books;
	}
	
}

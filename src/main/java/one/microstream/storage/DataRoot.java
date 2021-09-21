package one.microstream.storage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import one.microstream.domain.Book;


public class DataRoot
{
	private CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<Book>();
	
	public List<Book> getBooks()
	{
		return books;
	}
}

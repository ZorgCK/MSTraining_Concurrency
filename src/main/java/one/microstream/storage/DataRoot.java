package one.microstream.storage;

import java.util.ArrayList;
import java.util.List;

import one.microstream.domain.Author;
import one.microstream.domain.Book;


public class DataRoot
{
	private final List<Book>	books	= new ArrayList<Book>();
	private final List<Author>	authors	= new ArrayList<Author>();
	
	public List<Book> getBooks()
	{
		return books;
	}
	
	public List<Author> getAuthors()
	{
		return authors;
	}
	
}

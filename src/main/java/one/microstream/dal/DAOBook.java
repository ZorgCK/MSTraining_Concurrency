package one.microstream.dal;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import one.microstream.domain.Book;
import one.microstream.storage.DB;

@Singleton
public class DAOBook
{
	@Inject DB db;
	
	public void addBooks(List<Book> books)
	{
		db.getRoot().getBooks().addAll(books);
		db.store(db.getRoot().getBooks());
	}
	
	public void clearBooks()
	{
		db.getRoot().getBooks().clear();
		db.store(db.getRoot().getBooks());
	}
	
	public List<Book> books()
	{
		return db.getRoot().getBooks();
	}	
	
	public void deleteRandomBook()
	{
		db.getRoot().getBooks().remove(100);
	}
}


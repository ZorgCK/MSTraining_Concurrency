package one.microstream.dao;

import java.util.List;

import org.eclipse.serializer.concurrency.LockScope;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import one.microstream.domain.Book;
import one.microstream.storage.DB;


@Singleton
public class DAOBook extends LockScope
{
	@Inject DB db;
	
	public void addBooks(List<Book> books)
	{
		this.write(() ->
		{
			db.getRoot().getBooks().addAll(books);
			db.store(db.getRoot().getBooks());
		});
	}
	
	public void clearBooks()
	{
		this.write(() ->
		{
			db.getRoot().getBooks().clear();
			db.store(db.getRoot().getBooks());
		});
	}
	
	public void iterate()
	{
		while(true)
		{
			this.read(() ->
			{
				db.getRoot().getBooks().forEach(book -> System.out.println(book.getName()));
			});
		}
	}
	
	public List<Book> books()
	{
		return this.read(() -> db.getRoot().getBooks());
	}
	
	public void deleteRandomBook()
	{
		this.write(() ->
		{
			db.getRoot().getBooks().remove(100);
		});
	}
	
}

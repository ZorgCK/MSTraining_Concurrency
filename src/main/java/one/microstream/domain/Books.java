package one.microstream.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.microstream.concurrent.ReadWriteLocked;
import one.microstream.persistence.types.Persister;
import one.microstream.storage.DB;


public class Books extends ReadWriteLocked
{
	private final Map<String, Book>			isbn13ToBook	= new HashMap<>();
	private final Map<Author, List<Book>>	authorToBooks	= new HashMap<>();
	
	public void add(final Book book)
	{
		this.add(book, DB.storageManager);
	}
	
	public void addAll(final Collection<? extends Book> books)
	{
		this.addAll(books, DB.storageManager);
	}
	
	private void add(
		final Book book,
		final Persister persister)
	{
		this.write(() ->
		{
			this.addToCollections(book);
			this.storeCollections(persister);
		});
	}
	
	private void addAll(
		final Collection<? extends Book> books,
		final Persister persister)
	{
		this.write(() ->
		{
			books.forEach(this::addToCollections);
			this.storeCollections(persister);
		});
	}
	
	private void addToCollections(final Book book)
	{
		this.isbn13ToBook.put(book.getIsbn(), book);
		this.addToAuthorToBooks(book.getAuthor(), book);
	}
	
	private void storeCollections(final Persister persister)
	{
		persister.storeAll(
			this.isbn13ToBook,
			this.authorToBooks);
	}
	
	private void addToAuthorToBooks(
		final Author key,
		final Book book)
	{
		this.authorToBooks.computeIfAbsent(
			(one.microstream.domain.Author)key,
			k -> new ArrayList<>(1024)).add(book);
	}
	
	public List<Book> all()
	{
		return this.read(() -> this.isbn13ToBook.values().stream().collect(toList()));
	}
	
	public void deleteAll()
	{
		this.write(() ->
		{
			authorToBooks.clear();
			isbn13ToBook.clear();
			this.storeCollections(DB.storageManager);
		});
	}
	
	public void removeRandom()
	{
		this.write(() ->
		{
			String first = isbn13ToBook.keySet().stream().findFirst().get();
			Book book = isbn13ToBook.get(first);
			
			isbn13ToBook.remove(first);
			authorToBooks.get(book.getAuthor()).remove(book);
			
			this.storeCollections(DB.storageManager);
		});
	}
}

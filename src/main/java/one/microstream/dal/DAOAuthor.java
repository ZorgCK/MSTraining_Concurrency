package one.microstream.dal;

import java.util.function.Consumer;

import one.microstream.domain.Author;
import one.microstream.storage.DB;


public class DAOAuthor
{
	private static synchronized void update(Author author, Consumer<Author> writer)
	{
		writer.accept(author);
		
		DB.storageManager.store(author);
	}
	
	
	/**
	 * Example call
	 */
	private static void callUpdate(Author author)
	{
		update(author, a -> {
			author.setAlias("");
			author.setEmail("");
			author.setLastname("");
		});
	}
}


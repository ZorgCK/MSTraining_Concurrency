package one.microstream.dal;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Predicate;

import one.microstream.domain.Book;
import one.microstream.persistence.types.Storer;
import one.microstream.storage.DB;

public class DAOBook
{
	private static synchronized void batchUpdate(Predicate<Book> filter, Consumer<Book> writer)
	{
		Storer storer = DB.storageManager.createEagerStorer();
		
		DB.root.getBooks().stream().filter(filter).forEach(b ->
		{
			writer.accept(b);
			storer.store(b);
		});
		
		storer.commit();
	}
	
	/**
	 * Example call
	 */
	private static void callbatchUpdate()
	{
		Predicate<Book> pred = p -> p.getName().startsWith("A");
		
		batchUpdate(pred, a -> {
			a.setPrice(a.getPrice().multiply(new BigDecimal(0.9)));
		});
	}
}

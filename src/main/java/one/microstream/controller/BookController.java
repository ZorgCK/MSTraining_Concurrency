package one.microstream.controller;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import one.microstream.domain.Book;
import one.microstream.storage.DB;
import one.microstream.utils.MockupUtils;


@Controller("/books")
public class BookController
{
	@Get("/create")
	public HttpResponse<?> createBooks()
	{
		List<Book> allCreatedBooks = MockupUtils.loadMockupData();
		
		DB.root.getBooks().addAll(allCreatedBooks);
		DB.storageManager.store(DB.root.getBooks());
		
		return HttpResponse.ok("Books successfully created!");
	}
	
	@Get("/iterate")
	public void runThrough()
	{
		while(true)
		{
			DB.root.getBooks().all().forEach(book -> System.out.println(book.getName()));
		}
	}
	
	@Get("/deletebook")
	public HttpResponse<?> deleterandom()
	{
		DB.root.getBooks().removeRandom();
		
		return HttpResponse.ok("Book deleted!");
	}
	
	@Get
	public List<Book> getBook()
	{
		return DB.root.getBooks().all();
	}
	
	@Get("/clear")
	public HttpResponse<?> clearBooks()
	{
		DB.root.getBooks().deleteAll();
		
		return HttpResponse.ok("Books successfully cleared!");
	}
}

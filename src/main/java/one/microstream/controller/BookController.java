package one.microstream.controller;

import java.util.Arrays;
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
			DB.root.getBooks().forEach(book -> System.out.println(book.getName()));
		}
	}
	
	@Get("/createexception")
	public void createException()
	{
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
		
		for(Integer integer : integers)
		{
			integers.remove(1);
		}
	}
	
	@Get("/deletebook")
	public HttpResponse<?> deleterandom()
	{
		DB.root.getBooks().remove(100);
		
		return HttpResponse.ok("Book deleted!");
	}
	
	@Get
	public List<Book> getBook()
	{
		return DB.root.getBooks();
	}
	
	@Get("/clear")
	public HttpResponse<?> clearBooks()
	{
		DB.root.getBooks().clear();
		DB.storageManager.store(DB.root.getBooks());
		
		return HttpResponse.ok("Books successfully cleared!");
	}
}

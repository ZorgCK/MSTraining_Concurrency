package one.microstream.controller;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import one.microstream.dao.DAOBook;
import one.microstream.domain.Book;
import one.microstream.utils.MockupUtils;


@Controller("/books")
public class BookController
{
	@Inject private DAOBook dao;
	
	@Get("/create")
	public HttpResponse<?> createBooks()
	{
		List<Book> createdBooks = MockupUtils.loadMockupData();
		
		dao.addBooks(createdBooks);
		
		return HttpResponse.ok("Books successfully created!");
	}
	
	@Get("/iterate")
	public void runThrough()
	{
		dao.iterate();
	}
	
	@Get("/deletebook")
	public HttpResponse<?> deleterandom()
	{
		dao.deleteRandomBook();
		
		return HttpResponse.ok("Book deleted!");
	}
	
	@Get
	public List<Book> getBook()
	{
		return dao.books();
	}
	
	@Get("/clear")
	public HttpResponse<?> clearBooks()
	{
		dao.clearBooks();
		
		return HttpResponse.ok("Books successfully cleared!");
	}
}

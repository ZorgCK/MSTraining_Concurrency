package one.microstream.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Book
{
	private final String	isbn;
	private final String	name;
	private final LocalDate	release;
	private BigDecimal		price;
	private final Author	author;
	
	public Book(String isbn, String name, LocalDate release, BigDecimal price, Author author)
	{
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
		this.release = release;
		this.author = author;
	}
	
	public String getIsbn()
	{
		return isbn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public Author getAuthor()
	{
		return author;
	}
	
	public LocalDate getRelease()
	{
		return release;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
}

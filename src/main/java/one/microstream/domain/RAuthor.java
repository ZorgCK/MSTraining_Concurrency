package one.microstream.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record RAuthor(String id, String	firstname, String lastname, String email, String gender, String alias)
{
	
}

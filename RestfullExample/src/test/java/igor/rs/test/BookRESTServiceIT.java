package igor.rs.test;

import static org.junit.Assert.*;
import igor.entity.Book;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status.Family;
import javax.xml.bind.JAXBException;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

public class BookRESTServiceIT {
	private static URI uri = UriBuilder.fromUri("http://localhost/restfull/rs/book").port(8080).build();
	private static Client client = ClientBuilder.newClient();
	
	@Test
	public void shouldNotCreateANullBook() throws JAXBException {
		// Post a null book
		Entity<Book> entity = Entity.entity(null, MediaType.APPLICATION_XML);
		Response response = client.target(uri).request().post(entity);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		//assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo());
	}
	
	@Test
	public void shouldNotFoundTheBookID() throws JAXBException{
		// Gets a book with an unknown ID
		Response response = client.target(uri).path("unknownID").request().get();
		assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
	}
	
	@Test
	public void shouldCreateAndDeleteABook() throws JAXBException{
		Book book = new Book("H2G2", 12.5F, "Science book", "1-84023-742-2", 354, false);
		// POST a book
		Response response = client.target(uri).request().post(Entity.entity(book, MediaType.APPLICATION_XML));
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		URI bookURI = response.getLocation();
		
		// With the location, Gets the Book
		response = client.target(bookURI).request().get();
		book = response.readEntity(Book.class);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("H2G2", book.getTitle());
		
		// Delete a book
		response = client.target(uri).path(book.getId()).request().delete();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
		
		// Gets the book and checks it has been deleted
		response = client.target(bookURI).request().get();
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
		
		
	}
}

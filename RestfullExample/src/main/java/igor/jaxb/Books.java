package igor.jaxb;

import igor.entity.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(igor.entity.Book.class)
public class Books extends ArrayList<Book> {
	public Books(){
		super();
	}
	
	public Books(Collection<? extends Book> c){
		super(c);
	}
	
	@XmlElement(name = "book")
	public List<Book> getBooks(){
		return this;
	}
	
	public void setBooks(List<Book> books){
		this.addAll(books);
	}
}

package igor.jersey;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

@ApplicationPath("rs")
public class RESTApplicationConfig extends Application{
	private final Set<Class<?>> classes;
	
	public RESTApplicationConfig(){
		HashSet<Class<?>> c = new HashSet<>();
		c.add(igor.service.BookRestService.class);
		c.add(MOXyJsonProvider.class);
		classes = Collections.unmodifiableSet(c);
	}
	
	@Override
	public Set<Class<?>> getClasses(){
		return classes;
	}
}

package service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class APIApplication extends Application{
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(ImpastoService.class);
		set.add(UtenteService.class);
		set.add(IngredienteService.class);
		return set;
	}
}

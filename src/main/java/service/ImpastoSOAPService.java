package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import model.Impasto;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ImpastoSOAPService {
	
	@WebMethod
	public List<Impasto> getImpasti();
	
	@WebMethod
	public Impasto getImpastoByID(long id);
}

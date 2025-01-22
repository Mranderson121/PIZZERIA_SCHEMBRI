package implementation;

import java.util.List;

import javax.jws.WebService;

import dao.DAO;
import model.Impasto;
import service.ImpastoSOAPService;

@WebService(endpointInterface = "service.ImpastoSOAPService")
public class ImpastoSOAPServiceImpl implements ImpastoSOAPService{

	@Override
	public List<Impasto> getImpasti(){
		return DAO.getImpasti();
	}
	
	@Override
	public Impasto getImpastoByID(long id) {
		return DAO.getImpastoById(id);
	}
	
	
}

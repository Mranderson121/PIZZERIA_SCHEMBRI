package implementation;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import dao.DAO;
import model.Impasto;
import service.ImpastoSOAPService;

@Stateless
@WebService(endpointInterface = "service.ImpastoSOAPService")
public class ImpastoSOAPServiceImpl implements ImpastoSOAPService {
	@EJB
	private DAO dao;

	@Override
	public List<Impasto> getImpasti() {
		return dao.getImpasti();
	}

	@Override
	public Impasto getImpastoByID(long id) {
		return dao.getImpastoById(id);
	}

}

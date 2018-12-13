package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Marca;

public class MarcaRepository extends Repository<Marca> {

	public MarcaRepository(EntityManager entityManager) {
		super(entityManager);
	}

	public List<Marca> getMarcas(String marca) {

		Query query = getEntityManager().createQuery("SELECT c FROM Marca c WHERE lower(c.marca) like lower(:marca) ");
		query.setParameter("marca", "%" + marca + "%");

		List<Marca> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Marca>();

		return lista;
	}
	
	public List<Marca> getListMarcas(){
		Query query = getEntityManager().createQuery("SELECT m FROM Marca m");
		
		List<Marca> lista = query.getResultList();
		
		if(lista == null) {
			lista = new ArrayList<Marca>();
		}
		return lista;
	}

}

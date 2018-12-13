package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Modelo;

public class ModeloRepository extends Repository<Modelo> {

	public ModeloRepository(EntityManager entityManager) {
		super(entityManager);
	}

	public List<Modelo> getModelos(String Modelo) {

		Query query = getEntityManager()
				.createQuery("SELECT c FROM Modelo c WHERE c.Modelo like lower(:Modelo) ");
		query.setParameter("Modelo", "%" + Modelo + "%");

		List<Modelo> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Modelo>();

		return lista;
	}

}

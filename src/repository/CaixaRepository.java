package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Servico;

public class CaixaRepository extends Repository<Servico> {

	public CaixaRepository(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	public List<Servico> getServico(String placa) {

		Query query = getEntityManager()
				.createQuery("SELECT c FROM Servico c WHERE lower(c.placa) like lower(:placa) ");
		query.setParameter("placa", "%" + placa + "%");

		List<Servico> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Servico>();
		return lista;
	}
}

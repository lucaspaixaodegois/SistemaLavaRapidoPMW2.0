package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cliente;
import model.Marca;
import model.Modelo;
import model.Veiculo;

public class VeiculoRepository extends Repository<Veiculo> {

	public VeiculoRepository(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	public List<Veiculo> getVeiculo(String placa) {

		Query query = getEntityManager()
				.createQuery("SELECT c FROM Veiculo c WHERE lower(c.placa) like lower(:placa) ");
		query.setParameter("placa", "%" + placa + "%");

		List<Veiculo> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Veiculo>();

		return lista;
	}

	public List<Modelo> getModeloVeiculo(Marca marca) {

		Query query = getEntityManager().createQuery("SELECT m FROM Modelo m WHERE m.marca=:marca");
		query.setParameter("marca", marca);

		List<Modelo> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Modelo>();

		return lista;
	}
	
	public List<Veiculo> getPlacas(Cliente cliente) {
		
		Query query = getEntityManager()
				.createQuery("SELECT v FROM Veiculo v WHERE v.cliente=:cliente");
		query.setParameter("cliente", cliente);

		List<Veiculo> lista = query.getResultList();

		if (lista == null)
			lista = new ArrayList<Veiculo>();
		return lista;
	}
}

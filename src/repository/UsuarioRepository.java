package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	public UsuarioRepository(EntityManager entityManager) {
		super(entityManager);

	}
	
	public List<Usuario> getLogin(String cpf, String senha) {
		Query query = getEntityManager()
				.createQuery("SELECT c FROM Usuario c WHERE c.cpf=:cpf AND c.senha=:senha");
		query.setParameter("cpf", cpf);
		query.setParameter("senha", senha);

		List<Usuario> lista = query.getResultList();
		if (lista == null) {
			lista = new ArrayList<Usuario>();
		}
		return lista;
	}
}
package controller;

import factory.JPAFactory;
import model.DefaultEntity;
import model.Tamanho;
import model.Usuario;
import repository.Repository;

public class Controller<T extends DefaultEntity<? super T>> {
	
	private static Usuario usuario = null;

	public T save(T entity) {
		Repository<T> repository = new Repository<T>(JPAFactory.getEntityManager());

		// iniciando a transacao
		repository.getEntityManager().getTransaction().begin();
		repository.save(entity);
		repository.getEntityManager().getTransaction().commit();
		repository.getEntityManager().close();
		return entity;

	}

	public void remove(T entity) {
		Repository<T> repository = new Repository<T>(JPAFactory.getEntityManager());
		repository.getEntityManager().getTransaction().begin();
		repository.remove(entity);
		repository.getEntityManager().getTransaction().commit();
		repository.getEntityManager().close();
	}

	protected void updateItem(Tamanho item, boolean empty) {
		// TODO Auto-generated method stub

	}
	
	public static Usuario getUsuarioLogado() {
		return usuario;
	}

	public static void setUsuarioLogado(Usuario usuario) {
		Controller.usuario = usuario;
	}
}

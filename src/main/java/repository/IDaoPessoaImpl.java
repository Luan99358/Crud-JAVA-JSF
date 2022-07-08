package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import JpaUtil.JpaUtil;
import entidades.Pessoa;

public class IDaoPessoaImpl<E>  implements IDaoPessoa {

	@Override
	public Pessoa consultaUsuario(String login, String password) {
		
		Pessoa pessoa = null;
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		pessoa = (Pessoa) entityManager.createQuery("SELECT p FROM Pessoa p WHERE p.login ='"+login+"' AND "
				+ "p.password = '"+password+"'").getSingleResult();
		
		entityTransaction.commit();
		entityManager.close();
		
		return pessoa;
	}
	




	@Override
	public void deleteIdHeritage(Class entidade, Long id) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.createQuery("DELETE FROM "+ entidade.getName() +     
				" where usuario.id=" +id ).executeUpdate();
	    /*  |entidade.getClass() ---> tras o nome da clase do objeto no BD|  
	     *  |entityManager.createQuery cria a query de deleção em hql|         */
		entityTransaction.commit();
		entityManager.close();
		
	}
	


}

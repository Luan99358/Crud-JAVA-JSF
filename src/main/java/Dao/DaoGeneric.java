package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import JpaUtil.JpaUtil;
import entidades.Endereco;

public class DaoGeneric <E> {
	/*salva*/
	public void salvar(E entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}
	
	/*salva atraves do merge q retorna o objeto salvo no BD*/
	public E salvarMerge(E entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		E eRetorno = entityManager.merge(entidade);
		entityTransaction.commit();
		entityManager.close();
		return eRetorno;
		
	}
	
	public void delete(E entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.remove(entidade);
		entityTransaction.commit();
		entityManager.close();
		
		
	}
	
	public void deleteId(E entidade) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Object id = JpaUtil.getPrimaryKey(entidade);
		entityManager.createQuery("DELETE FROM "+   entidade.getClass().getCanonicalName() +     
				" where id=" +id ).executeUpdate();
	    /*  |entidade.getClass() ---> tras o nome da clase do objeto no BD|  
	     *  |entityManager.createQuery cria a query de deleção em hql|         */
		entityTransaction.commit();
		entityManager.close();
				
	}
	
	
	

   
	public List<E> getListEntity(Class<E> entidade){
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> list = entityManager.createQuery(" FROM "+ entidade.getName()).getResultList();
	    /*  |entidade.getClass() ---> tras o nome da clase do objeto no BD|  
	     *  |entityManager.createQuery cria a query de deleção em hql essa e a de consulta|         */
		entityTransaction.commit();
		entityManager.close();
		
		return list;			
		
	}

}

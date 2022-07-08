package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import JpaUtil.JpaUtil;
import entidades.Lancamentos;
import entidades.Pessoa;

public class IDaoLancamentosImpl implements IDaoLancamentos {

	@Override
	public List<Lancamentos> consultaLancamentos(Pessoa user) {
       
		List<Lancamentos> list = null;
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		list =  entityManager.createQuery("FROM Lancamentos WHERE usuario.id = "+user.getId()).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return list;
		
	}

	

}

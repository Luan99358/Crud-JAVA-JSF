package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import JpaUtil.JpaUtil;
import entidades.Endereco;
import entidades.Pessoa;

public class IDaoEnderecoImpl implements IDaoEndereco{

	@Override
	public List<Endereco> consultadaoEnderecos(Pessoa user) {
		 
		List<Endereco> list = null;
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		list =  entityManager.createQuery("FROM Endereco WHERE usuario.id = "+user.getId()).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return list;
	}



}

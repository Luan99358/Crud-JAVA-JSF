package JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static EntityManagerFactory factory;
	
	static {
		/*So pode ser executado esse factory uma vez na execução do app*/
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("JSF-01");
		}

	}
	
	/*acessando e retornando a classe de persistencia*/
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	/*retorna o id do objeto*/
	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}

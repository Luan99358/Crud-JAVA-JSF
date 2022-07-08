package JSF_01.JSF_01;

import javax.persistence.Persistence;

public class TesteJPA {
	
 
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("JSF-01");/*testando a pensitencia criando tabelas no BD e atualizando*/
	}

}

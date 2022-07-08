package repository;

import entidades.Pessoa;

public interface IDaoPessoa <E>{

	Pessoa consultaUsuario(String login,String password);
	public void deleteIdHeritage(Class<E> entidade,Long id);
	
}

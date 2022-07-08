package repository;

import java.util.List;

import entidades.Endereco;
import entidades.Pessoa;

public interface IDaoEndereco {
	List<Endereco> consultadaoEnderecos(Pessoa user);


}

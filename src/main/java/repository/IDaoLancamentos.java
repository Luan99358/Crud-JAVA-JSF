package repository;

import java.util.List;

import entidades.Lancamentos;
import entidades.Pessoa;

public interface IDaoLancamentos {
	List<Lancamentos> consultaLancamentos(Pessoa user);

}

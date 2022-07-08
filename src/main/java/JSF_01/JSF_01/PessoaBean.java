package JSF_01.JSF_01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.xml.sax.InputSource;

import Dao.DaoGeneric;
import entidades.Endereco;
import entidades.Lancamentos;
import entidades.Pessoa;
import repository.IDaoEndereco;
import repository.IDaoEnderecoImpl;
import repository.IDaoPessoa;
import repository.IDaoPessoaImpl;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	
	private  Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();
	
	@ManagedProperty(value = "#{enderecoBean}")
	private EnderecoBean enderecoBean;
	


	




	public String salvar() {
		/*daoGeneric.salvarMerge(pessoa); salvando sem o merge
		pessoa = new Pessoa();*/
		pessoa = daoGeneric.salvarMerge(pessoa);
		loadPessoas();
		Msg("Cadastrado  com sucesso !");
		return "";
	}

	

	public String deleteID() {
		iDaoPessoa.deleteIdHeritage(Endereco.class, pessoa.getId());
		iDaoPessoa.deleteIdHeritage(Lancamentos.class, pessoa.getId());
		pessoa = new Pessoa();
		loadPessoas();
		Msg("Deletado com sucesso !");
		return "";
	}
	
	
	
	private void Msg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
	}
	
	
     @PostConstruct /*com essa anotação esse metodo sempre sera carregado ao abrir a tela*/
	public void loadPessoas() {	
		pessoas = daoGeneric.getListEntity(Pessoa.class);
		//enderecos =  enderecoBean.loadEnderecos(adminAcess());
		
	}
	
	
 	
 	public List<Pessoa> getPessoas() {
 		return pessoas;
 	}



 	public void setPessoas(List<Pessoa> pessoas) {
 		this.pessoas = pessoas;
 	}

	public String novo() {	
		pessoa = new Pessoa();
		return "";
	}
	
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		enderecoBean.setPessoa(pessoa);
        enderecoBean.loadEnderecos();

	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
    
	public List<Pessoa> getListPessoas() {
		return pessoas;
	}

	public void setDaoGeneric(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
    

	public EnderecoBean getEnderecoBean() {
		return enderecoBean;
	}


	public void setEnderecoBean(EnderecoBean enderecoBean) {
		this.enderecoBean = enderecoBean;
	}
	

	
	public Pessoa getUserSession() {
		
		FacesContext context =FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
				
		return pessoaUser;
	
    }
	
	
	public void setUserSession(Pessoa user) {
		
		FacesContext context =FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().put("usuarioLogado", user);

	
    }
	
	public String removeUserSession() {
		
		FacesContext context =FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");

	    return "index.jsf";
    }
	
	
	
	public String Logar() {
		
		Pessoa pessoaUser = iDaoPessoa.consultaUsuario(pessoa.getLogin(), pessoa.getPassword());
		
		if (pessoaUser != null) {
			
			setUserSession(pessoaUser);
			
			return "cad_user.jsf";
		}		
		return "index.jsf";
	}
	
	public boolean adminAcess() {
		
			Pessoa pessoaUser = getUserSession();	
			
			if(pessoaUser.getNivel().equalsIgnoreCase("Administrador")) {
		
				return true;
			}
			
			return false;
		
	}

	public boolean employeeAcess() {
		
		Pessoa pessoaUser = getUserSession();	
		
		if(pessoaUser.getNivel().equalsIgnoreCase("Administrador") ||
				pessoaUser.getNivel().equalsIgnoreCase("Funcionário")) {
	
			return true;
		}
		
		return false;
	
    }

	
	



	
}

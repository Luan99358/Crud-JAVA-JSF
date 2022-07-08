package JSF_01.JSF_01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.google.gson.Gson;

import Dao.DaoGeneric;
import entidades.Endereco;
import entidades.Lancamentos;
import entidades.Pessoa;
import repository.IDaoEndereco;
import repository.IDaoEnderecoImpl;

@ViewScoped
@ManagedBean(name = "enderecoBean")
public class EnderecoBean {
    private Endereco  endereco = new Endereco();
    private Pessoa pessoa = new Pessoa();
    private DaoGeneric<Endereco> daoGeneric = new DaoGeneric<Endereco>();
    private List<Endereco> enderecos = new ArrayList<Endereco>();
	private IDaoEndereco daoEnderecos = new IDaoEnderecoImpl();
	
	
	
	public String salvarEndereco() {
		
		if(pessoa.getId() != null) {
		   endereco.setUsuario(pessoa);
		   endereco = daoGeneric.salvarMerge(endereco);	
		}
		loadEnderecos();
		return "";
		
	}
	
	

	public String deleteID() {
		daoGeneric.deleteId(endereco);
		endereco = new Endereco();
		loadEnderecos();
		return "";
	}
	

	
	
	
  /*@PostConstruct /*com essa anotação esse metodo sempre sera carregado ao abrir a tela*/
	public void loadEnderecos() {	
		
		enderecos = daoEnderecos.consultadaoEnderecos(pessoa);    	
	     	 
	}
	
	
	/*todo req ajax deve ter AjaxBehaviorEvent event para funcionar*/
    public void pesquisaCep(AjaxBehaviorEvent event) {
    
    	try {
    		URL url = new URL("https://viacep.com.br/ws/"+endereco.getCep()+"/json/");
    		URLConnection urlConnection = url.openConnection();
    		InputStream is = urlConnection.getInputStream();
    		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
    		
    		String cep ="";
    		StringBuilder jsonCep =new StringBuilder(); 
    		while ((cep = br.readLine()) != null) {
    			jsonCep.append(cep);
    		}
    		 
    		Endereco gson =  new Gson().fromJson(jsonCep.toString(), Endereco.class);
    	  
    	    endereco.setUf(gson.getUf());
    	    endereco.setLocalidade(gson.getLocalidade());
    	    endereco.setBairro(gson.getBairro());
    	    endereco.setLogradouro(gson.getLogradouro());


                    
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	
 
    } 
    
    public String novo() {	
		endereco = new Endereco();
		return "";
	}
    
    
    
    public IDaoEndereco getDaoEnderecos() {
		return daoEnderecos;
	}



	public void setDaoEnderecos(IDaoEndereco daoEnderecos) {
		this.daoEnderecos = daoEnderecos;
	}




	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
		
	}
	public Pessoa getPessoa() {
		return pessoa;
	}



	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public DaoGeneric<Endereco> getDaoGeneric() {
		return daoGeneric;
	}
	public void setDaoGeneric(DaoGeneric<Endereco> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
    

   
    

	
	
	

}

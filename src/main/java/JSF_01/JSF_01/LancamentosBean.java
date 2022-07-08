package JSF_01.JSF_01;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Dao.DaoGeneric;
import entidades.Lancamentos;
import entidades.Pessoa;
import repository.IDaoLancamentos;
import repository.IDaoLancamentosImpl;


@ViewScoped
@ManagedBean(name="lancamentosBean")
public class LancamentosBean {
    private Lancamentos  lacamentos = new Lancamentos();
    private DaoGeneric<Lancamentos> daoGeneric = new DaoGeneric<Lancamentos>();
    private List<Lancamentos> list = new ArrayList<Lancamentos>();
	private PessoaBean pessoaBean = new PessoaBean();
    private IDaoLancamentos daoLancamentos = new IDaoLancamentosImpl();
 	
	
	public String salvar() {
		
		lacamentos.setUsuario(pessoaBean.getUserSession());
		lacamentos = daoGeneric.salvarMerge(lacamentos);
		loadLancamenstos();
		return "";
	}
	
	

	public PessoaBean getPessoaBean() {
		return pessoaBean;
	}



	public void setPessoaBean(PessoaBean pessoaBean) {
		this.pessoaBean = pessoaBean;
	}



	public String deleteID() {
		daoGeneric.deleteId(lacamentos);; 
		lacamentos = new Lancamentos();
		loadLancamenstos();
		return "";
	}
	
	
     @PostConstruct /*com essa anotação esse metodo sempre sera carregado ao abrir a tela*/
	public void loadLancamenstos() {	
   
    	if(pessoaBean.adminAcess()) {
    		list = daoGeneric.getListEntity(Lancamentos.class);
    	}else { 
    	    list = daoLancamentos.consultaLancamentos(pessoaBean.getUserSession());
    	}
	}
	

     
     
	

	public String novo() {	
		lacamentos = new Lancamentos();
		return "";
	}
	
    
    
    
    public Lancamentos getLacamentos() {
		return lacamentos;
	}
	public void setLacamentos(Lancamentos lacamentos) {
		this.lacamentos = lacamentos;
	}
	public DaoGeneric<Lancamentos> getDaoGeneric() {
		return daoGeneric;
	}
	public void setDaoGeneric(DaoGeneric<Lancamentos> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	public List<Lancamentos> getList() {
		return list;
	}
	public void setList(List<Lancamentos> list) {
		this.list = list;
	}
    
    
   
    
    
    
}

package entidades;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Pessoa implements Serializable{
      
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String sobrenome;
	
	private Integer idade;
	
	@Temporal(TemporalType.DATE)
	private Date dataNasc = new Date();
	
	private String sexo;
	
	private String[] frameworks;

    private Boolean concordo;
    
    private String login;
    
    private String password;
    
    private String nivel;
    
    @Column(columnDefinition = "text")/*salva a base 64 em texto*/
    private String fotoIconBase64;
    
    private String extensao;/*jpg ou png e etc */
    
    @Lob   /* grava o arquivo no banco de dados*/
    @Basic(fetch = FetchType.LAZY)/*so carrrega quando pedido */
    private byte[] fotoIconBOriginal;
    




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String[] getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(String[] frameworks) {
		this.frameworks = frameworks;
	}
	
	public Boolean getConcordo() {
		return concordo;
	}

	public void setConcordo(Boolean concordo) {
		this.concordo = concordo;
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	

	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}
	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBOriginal() {
		return fotoIconBOriginal;
	}

	public void setFotoIconBOriginal(byte[] fotoIconBOriginal) {
		this.fotoIconBOriginal = fotoIconBOriginal;
	}


}

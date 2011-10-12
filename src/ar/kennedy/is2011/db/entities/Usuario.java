package ar.kennedy.is2011.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -2548441551324306490L;
	
	@Id
	@Column(name = "NOMBRE_USR")
	private String nombreUsr;

	@Column(name = "CLAVE")
	private String clave;
	
	@Column(name = "MAIL")
	private String mail;
	
	@Column(name = "MAIL_SECUNDARIO")
	private String mailSecundario;
	
	@Column(name = "ID_PREGUNTA_SECRETA")
	private String idPreguntaSecreta;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDO")
	private String apellido;
	
	@Column(name = "FECHA_NACIMIENTO")
	private String fechaNacimiento;
	
	@Column(name = "SEXO")
	private String sexo;
	
	@Column(name = "PAIS")
	private String pais;
	
	@Column(name = "ID_PROVINCIA")
	private String idProvicia;
	
	@Column(name = "RESPUESTA_PREGUNTA")
	private String respuestaPregunta;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String nomUsr,String clave,String mail,String pSecreta,String respuesta){
	   	this();
		
		this.nombreUsr = nomUsr;
	   	this.clave = clave;
	   	this.mail = mail;
	   	this.idPreguntaSecreta = pSecreta;
	   	this.respuestaPregunta = respuesta;
	}
	
	public String getIdPreguntaSecreta() {
		return idPreguntaSecreta;
	}

	public void setIdPreguntaSecreta(String idPreguntaSecreta) {
		this.idPreguntaSecreta = idPreguntaSecreta;
	}

	public String getRespuestaPregunta() {
		return respuestaPregunta;
	}

	public void setRespuestaPregunta(String respuestaPregunta) {
		this.respuestaPregunta = respuestaPregunta;
	}

	public String getNombreUsr() {
		return nombreUsr;
	}
	public void setNombreUsr(String nombreUsuario) {
		this.nombreUsr = nombreUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getMailSecundario() {
		return mailSecundario;
	}

	public void setMailSecundario(String mailSecundario) {
		this.mailSecundario = mailSecundario;
	}
	
	public String getIdProvicia() {
		return idProvicia;
	}

	public void setIdProvicia(String idProvicia) {
		this.idProvicia = idProvicia;
	}
	
}
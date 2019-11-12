package controle;

import java.util.ArrayList;
import java.util.List;

//import javax.faces.context.FacesContext;
//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import dao.ViagemDAO;
import model.Viagem;

@ManagedBean(name = "ViagemBean")
@RequestScoped 
public class ViagemBean {
	
	private Viagem viagem;
	private ViagemDAO viagemDAO;
	
	private List<Viagem> listaViagens = new ArrayList<Viagem>();
	
	public ViagemBean() {
		this.viagemDAO = new ViagemDAO();
		//this.listaViagens= new ArrayList<Viagem>();
		this.listaViagens= this.viagemDAO.listar();
		
		this.viagem = new Viagem(0, null, 0, null, null);
		/*this.viagem.setCodigo(0);
		this.viagem.setFotos(null);
		this.viagem.setLugar(null);
		this.viagem.setNome(null);
		this.viagem.setPreco(0);*/
		
	}
	
	public void cadastrar() {
		try {
			this.viagemDAO.inserir(viagem);	
			System.out.println("------ Salvou -----");
			//this.listaViagens = this.viagemDAO.listar();
			
			/*FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"", "Salvo com Sucesso!!!"));*/	
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

	
	public List<Viagem> getListaViagens() {
		return listaViagens;
	}

	public void setListaViagens(List<Viagem> listaViagens) {
		this.listaViagens = listaViagens;
	}

	public void Listar() {
		this.listaViagens = this.viagemDAO.listar();
	}
	

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public ViagemDAO getViagemDAO() {
		return viagemDAO;
	}

	public void setViagemDAO(ViagemDAO viagemDAO) {
		this.viagemDAO = viagemDAO;
	}	
}

package test;

import dao.ViagemDAO;
import model.Viagem;

public class InserirTest {
	public static void main(String[] args) {
		ViagemDAO viagemDao = new ViagemDAO();
		Viagem viagem = new Viagem(114,"passeio",200,"japaratinga",null);
		
		viagemDao.inserir(viagem);
		System.out.println("INSERIDO COM SUCESSO");
	}
}

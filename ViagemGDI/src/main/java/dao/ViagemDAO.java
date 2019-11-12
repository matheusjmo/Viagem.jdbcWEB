package dao;

import model.Viagem;
//import util.Conexao;

import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
	
	public static final String CREATE_TABLE = "CREATE TABLE VIAGEM"
			+"("
			+"CODIGO INT NOT NULL,"
			+"NOME VARCHAR(20) NOT NULL,"
			+"PRECO FLOAT NOT NULL,"
			+"LUGAR VARCHAR(20) NOT NULL,"
			+"FOTOS MEDIUMBLOB,"
			+" PRIMARY KEY (CODIGO)"
			+")";
	
	public static Connection conexaoBD() {
		try {
			String sqlDriver = "oracle.jdbc.driver.OracleDriver";
			/*String url = "jdbc:oracle:thin:@oracle12c.cin.ufpe.br:1521:instance01";
			String user = "g192if685cc_eq08";
			String password = "tkwnxnjj";*/
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "12345";
			
			Class.forName(sqlDriver);
			System.out.println("conectando....");
			Connection conn = DriverManager.getConnection(url, user, password);
			
			//Statement stmt = conn.createStatement();
			//stmt.execute(CREATE_TABLE);
			System.out.println("conectado");
			//stmt.executeQuery("SELECT * FROM VIAGEM");
			return conn;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
  /*public static List<Viagem> listar() {
        try {
        	
        	Connection conn = conexaoBD();
        	List<Viagem> viagens = new ArrayList<Viagem>();        	
            String sql = "SELECT * FROM VIAGEM";

            PreparedStatement stmt = conn.prepareCall(sql);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Viagem viagem = new Viagem(0, sql, 0, sql, null);
                
                viagem.setCodigo(res.getInt("CODIGO"));
                viagem.setNome(res.getString("NOME"));
                viagem.setPreco(res.getInt("PRECO"));
                viagem.setLugar(res.getString("LUGAR"));
                viagem.setFotos(res.getBlob("FOTOS"));
                //System.out.println(viagem.toString());
                viagens.add(viagem);
                
                return viagens;
            }
            stmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
	
	public List<Viagem> listar() {
		List<Viagem> viagens = new ArrayList<>();
		try {
			Connection conn = conexaoBD();
			Statement stmt = conn.createStatement();
			String sql = "SELECT V.CODIGO, V.NOME, V.PRECO, V.LUGAR, V.FOTOS FROM VIAGEM V";
			//PreparedStatement stmt = conn.prepareCall(sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				//Viagem viagem = new Viagem(0, sql, 0, sql, null);
				
				int codigo = rs.getInt("CODIGO");
				String nome = rs.getString("NOME");
				int preco = rs.getInt("PRECO");
				String lugar = rs.getString("LUGAR");
				Blob fotos = rs.getBlob("FOTOS");
				//Clob fotostxt = rs.getClob("FOTOSTXT");

				Viagem viagem = new Viagem(codigo, nome, preco, lugar, fotos);
				//System.out.println("test");
				System.out.println(nome);
				
				viagens.add(viagem);
			}
			//stmt.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return viagens;
	}
	
	
	public void inserir(Viagem viagem) {
		System.out.println("try");
		try {
			//System.out.println("try2");
			Connection conn = conexaoBD();
			//System.out.println("try3");
			String sql= "INSERT INTO VIAGEM(codigo,nome,lugar,preco, fotos) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement stm = conn.prepareStatement(sql);
		    
			
			//System.out.println("codigo");
			stm.setInt(1, viagem.getCodigo());
			//System.out.println("nome");
			stm.setString(2, viagem.getNome());
			//System.out.println("lugar");
			stm.setString(3,viagem.getLugar());
			//System.out.println("preco");
			stm.setFloat(4,viagem.getPreco());
			//System.out.println("fotos");
			stm.setBlob(5,viagem.getFotos());
			
			stm.execute(); // 
			stm.close();
			//conn.commit();
		} catch (Exception e) {
			System.out.println(e);
			e.getMessage();
		}
	}
}

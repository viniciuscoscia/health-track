package br.com.healthtrack.usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.healthtrack.utils.ConexaoDB;
import br.com.healthtrack.utils.CriptografiaUtils;

public class UsuarioDAO {
	private static final String SELECTALL 	= "SELECT ds_email, isMale, nm_usuario, nm_sobrenome_usuario, dt_nascimento, tx_senha, vl_altura "
			+ "FROM T_USUARIO";

	private static final String SELECT 	= "SELECT ds_email, isMale, nm_usuario, nm_sobrenome_usuario, dt_nascimento, tx_senha, vl_altura "
			+ "FROM T_USUARIO "
			+ "WHERE ds_email = ?";

	private static final String UPDATE 	= "UPDATE T_USUARIO " 
			+ "SET ds_email = ?, isMale = ?, nm_usuario = ?, nm_sobrenome_usuario = ?, dt_nascimento = ?, vl_altura = ? " 
			+ "WHERE ds_email = ?";

	private static final String DELETE 	= "DELETE FROM T_USUARIO "
			+ "WHERE ds_email = ?";

	private static final String INSERT 	= "INSERT INTO T_USUARIO(ds_email, isMale, nm_usuario, nm_sobrenome_usuario, dt_nascimento, tx_senha, vl_altura) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATESENHA = "UPDATE T_USUARIO "
			+ "SET TX_SENHA = ? "
			+ "WHERE ds_email = ?";
	
	private static final String VALIDARUSUARIO = "SELECT ds_email, tx_senha "
			+ "FROM T_USUARIO "
			+ "WHERE ds_email = ? AND tx_senha = ?";

	public static ArrayList<Usuario> select() throws SQLException{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String ds_email;
		boolean isMale;
		String nm_usuario;
		String nm_sobrenome_usuario;
		LocalDate dt_nascimento;
		float vl_altura;

		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECTALL);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				ds_email = rst.getString("DS_EMAIL");
				isMale = Boolean.parseBoolean(rst.getString("isMale"));
				nm_usuario = rst.getString("nm_usuario");
				nm_sobrenome_usuario = rst.getString("nm_sobrenome_usuario");
				dt_nascimento = rst.getDate("dt_nascimento").toLocalDate();
				vl_altura = rst.getFloat("vl_altura");
				usuarios.add(new Usuario(ds_email, nm_usuario, nm_sobrenome_usuario, dt_nascimento, "null", vl_altura, isMale));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar insert");
			usuarios = null;
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}

		if(usuarios == null) {
			System.out.println("Sem dados para retorno");
			return null;
		}

		return usuarios;
	}

	public static Usuario select(String email) {
		Usuario usuario = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;

		String ds_email;
		boolean isMale;
		String nm_usuario;
		String nm_sobrenome_usuario;
		LocalDate dt_nascimento;
		String tx_senha;
		float vl_altura;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, email);
			rst = pstmt.executeQuery();
			rst.next();
			ds_email = rst.getString("ds_email");
			isMale = Boolean.parseBoolean(rst.getString("isMale"));
			nm_usuario = rst.getString("nm_usuario");
			nm_sobrenome_usuario = rst.getString("nm_sobrenome_usuario");
			dt_nascimento = rst.getDate("dt_nascimento").toLocalDate();
			vl_altura = rst.getFloat("vl_altura");
			tx_senha = rst.getString("tx_senha");
			usuario = new Usuario(ds_email, nm_usuario, nm_sobrenome_usuario, dt_nascimento, tx_senha, vl_altura, isMale);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}

		if(usuario == null) {
			System.out.println("Sem dados para retorno");
			return null;
		}

		return usuario;
	}

	public static void update(String ds_email, String novoEmail, boolean isMale, String nm_usuario, String nm_sobrenome_usuario, LocalDate dt_nascimento, float vl_altura) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(UPDATE);
			pstmt.setString(1, novoEmail);
			pstmt.setString(2, Boolean.toString(isMale));
			pstmt.setString(3, nm_usuario);
			pstmt.setString(4, nm_sobrenome_usuario);
			pstmt.setDate(5, Date.valueOf(dt_nascimento));
			pstmt.setFloat(6, vl_altura);
			pstmt.setString(7, ds_email);
			System.out.println(pstmt.toString());
			pstmt.executeUpdate();
			System.out.println("Update realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Update");
		}
	}
	
	public static void alterarSenha(String ds_email, String senhaAtual, String novaSenha) {
		Usuario user = select(ds_email);
		
		if(!user.getSenha().equals(CriptografiaUtils.criptografar(senhaAtual))) {
			System.out.println("Erro ao realizar troca de senhas: Senhas diferentes");
			return;
		}
		
		PreparedStatement pstmt = null;
		try {
			novaSenha = CriptografiaUtils.criptografar(novaSenha);
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(UPDATESENHA);
			pstmt.setString(1, novaSenha);
			pstmt.setString(2, ds_email);
			pstmt.executeQuery();
			System.out.println("Troca de senha feita com sucesso");
		} catch (Exception e) {
			System.err.println("Erro ao realizar troca de senha");
			e.printStackTrace();
		}
	}
	
	public static void delete(String usuario) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(DELETE);
			pstmt.setString(1, usuario);
			pstmt.executeUpdate();
			System.out.println("Delete realizado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao realizar delete!!");
			e.printStackTrace();
		}

	}
	
	public static void insert(String ds_email, boolean isMale, String nm_usuario, String nm_sobrenome_usuario, LocalDate dt_nascimento, String tx_senha, float vl_altura)
	throws SQLException{
		tx_senha = CriptografiaUtils.criptografar(tx_senha);
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(INSERT);
			pstmt.setString(1, ds_email);
			pstmt.setString(2, Boolean.toString(isMale));
			pstmt.setString(3, nm_usuario);
			pstmt.setString(4, nm_sobrenome_usuario);
			pstmt.setDate(5, Date.valueOf(dt_nascimento));
			pstmt.setString(6, tx_senha);
			pstmt.setFloat(7, vl_altura);
			pstmt.executeUpdate();
			System.out.println("Insert realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean validarUsuario(String usuario, String senha) {
		senha = CriptografiaUtils.criptografar(senha);
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(VALIDARUSUARIO);
			pstmt.setString(1, usuario);
			pstmt.setString(2, senha);
			rst = pstmt.executeQuery();
			
			if(rst.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Erro ao validar usuário e senha");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		return false;
	}
}

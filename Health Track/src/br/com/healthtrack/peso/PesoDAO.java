package br.com.healthtrack.peso;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.healthtrack.utils.ConexaoDB;

public class PesoDAO {
	private static final String SELECT 		= "SELECT cd_peso, ds_email, vl_peso, dt_peso "  
											+ "FROM t_peso "
											+ "Where ds_email = ? "
											+ "ORDER BY DT_PESO DESC, cd_peso desc";
	
	private static final String SELECTBYID	= "SELECT cd_peso, ds_email, vl_peso, dt_peso "  
			+ "FROM t_peso "
			+ "Where cd_peso = ? "
			+ "ORDER BY DT_PESO DESC, cd_peso desc";
	
	private static final String INSERT 		= "INSERT INTO T_PESO(cd_peso, ds_email, vl_peso, dt_peso) "
											+ "VALUES(SQ_PESO.nextval, ?, ?, ?)";
	
	private static final String UPDATE 		= "UPDATE T_PESO "  
											+ "SET dt_peso = ?, vl_peso = ? " 
											+ "Where cd_peso = ?";

	private static final String DELETE 		= "DELETE FROM t_peso "
											+ "WHERE cd_peso = ? AND ds_email = ?";
	
	public static ArrayList<Peso> select(String usuario) {
		ArrayList<Peso> listaPeso = new ArrayList<Peso>();
		short cd_peso;
		String ds_email;
		float vl_peso;
		LocalDate dt_peso;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, usuario);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				cd_peso = rst.getShort("cd_peso");
				ds_email = rst.getString("ds_email");
				vl_peso = rst.getFloat("vl_peso");
				dt_peso = rst.getDate("dt_peso").toLocalDate();
				listaPeso.add(new Peso(cd_peso, ds_email, vl_peso, dt_peso));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		if(listaPeso.size() <= 0) {
			System.out.println("Não há dados para retornar");
			return null;
		}
		return listaPeso;
	}
	
	public static Peso selectLast(String usuario) {
		Peso peso = null;
		short cd_peso;
		String ds_email;
		float vl_peso;
		LocalDate dt_peso;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, usuario);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_peso = rst.getShort("cd_peso");
				ds_email = rst.getString("ds_email");
				vl_peso = rst.getFloat("vl_peso");
				dt_peso = rst.getDate("dt_peso").toLocalDate();
				peso = new Peso(cd_peso, ds_email, vl_peso, dt_peso);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		return peso;
	}
	
	
	public static Peso selectById(short cd_peso) {
		Peso peso = null;
		String ds_email;
		float vl_peso;
		LocalDate dt_peso;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECTBYID);
			pstmt.setShort(1, cd_peso);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_peso = rst.getShort("cd_peso");
				ds_email = rst.getString("ds_email");
				vl_peso = rst.getFloat("vl_peso");
				dt_peso = rst.getDate("dt_peso").toLocalDate();
				peso = new Peso(cd_peso, ds_email, vl_peso, dt_peso);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		return peso;
	}
	
	public static void insert(String ds_email, float vl_peso, LocalDate dt_peso) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(INSERT);
			pstmt.setString(1, ds_email);
			pstmt.setFloat(2, vl_peso);
			pstmt.setDate(3, Date.valueOf(dt_peso));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar insert");
		}
		
	}
	
	public static void update(LocalDate dt_peso, float vl_peso, short cd_peso) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(UPDATE);
			pstmt.setDate(1, Date.valueOf(dt_peso));
			pstmt.setFloat(2, vl_peso);
			pstmt.setShort(3, cd_peso);
			pstmt.executeUpdate();
			System.out.println("Update realizado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar update!");
		}
		
	}
	
	public static void delete(short cd_medicao, String user) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(DELETE);
			pstmt.setShort(1, cd_medicao);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			System.out.println("Delete executado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar delete!");
		} 
	}

}

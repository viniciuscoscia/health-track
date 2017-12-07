package br.com.healthtrack.pressaoArterial;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.healthtrack.utils.ConexaoDB;

public class PressaoArterialDAO {
	
	private static final String select 	= "SELECT cd_medicao, ds_email, vl_sistolica, vl_diastolica, dt_medicao " 
										+ "FROM t_pressao_arterial "
										+ "WHERE ds_email = ? "
										+ "ORDER BY DT_MEDICAO DESC";
	
	private static final String SELECTBYID 	= "SELECT cd_medicao, ds_email, vl_sistolica, vl_diastolica, dt_medicao " 
			+ "FROM t_pressao_arterial "
			+ "WHERE cd_medicao = ? "
			+ "ORDER BY DT_MEDICAO DESC";
	
	private static final String insert = "INSERT INTO T_PRESSAO_ARTERIAL (cd_medicao, ds_email, vl_sistolica, vl_diastolica, dt_medicao) "
								+ "VALUES (SQ_PRESSAO_ARTERIAL.NEXTVAL, ?, ?, ?, ?)";
	
	private static final String update = "UPDATE T_PRESSAO_ARTERIAL "
								+ "SET vl_sistolica = ?, vl_diastolica = ?, dt_medicao = ? "
								+ "WHERE cd_medicao = ?";
	
	private static final String delete = "DELETE FROM T_PRESSAO_ARTERIAL "
								+ "WHERE cd_medicao = ? and ds_email = ?";
	
	public static PressaoArterial selectById(short cd_medicao) {
		String ds_email;
		short vl_sistolica;
		short vl_diastolica;
		LocalDate dt_medicao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		PressaoArterial pressao = new PressaoArterial();
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECTBYID);
			pstmt.setShort(1, cd_medicao);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_medicao = rst.getShort("cd_medicao");
				ds_email = rst.getString("ds_email");
				vl_sistolica = (short)rst.getShort("vl_sistolica");
				vl_diastolica = (short)rst.getShort("vl_diastolica");
				dt_medicao = rst.getDate("dt_medicao").toLocalDate();
				pressao = new PressaoArterial(ds_email, cd_medicao, vl_sistolica, vl_diastolica, dt_medicao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select!");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		return pressao;
	}

	public static void insert(String ds_email, short vl_sistolica, short vl_diastolica, LocalDate dt_medicao) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(insert);
			
			pstmt.setString(1, ds_email);
			pstmt.setShort(2, vl_sistolica);
			pstmt.setShort(3, vl_diastolica);
			pstmt.setDate(4, Date.valueOf(dt_medicao));

			pstmt.executeUpdate();
			System.out.println("Insert realizado com sucesso!");
		} catch (SQLException e) { 
			e.printStackTrace();
			System.err.println("Erro ao realizar inserção");
		}
	}

	
	public static ArrayList<PressaoArterial> select(String user) {
		short cd_medicao;
		String ds_email;
		short vl_sistolica;
		short vl_diastolica;
		LocalDate dt_medicao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		ArrayList<PressaoArterial> listaPressao = new ArrayList<PressaoArterial>();
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(select);
			pstmt.setString(1, user);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				cd_medicao = rst.getShort("cd_medicao");
				ds_email = rst.getString("ds_email");
				vl_sistolica = (short)rst.getShort("vl_sistolica");
				vl_diastolica = (short)rst.getShort("vl_diastolica");
				dt_medicao = rst.getDate("dt_medicao").toLocalDate();
				listaPressao.add(new PressaoArterial(ds_email, cd_medicao, vl_sistolica, vl_diastolica, dt_medicao));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select!");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		return listaPressao.size() > 0 ? listaPressao : null;
	}
	
	public static PressaoArterial selectLast(String user) {
		short cd_medicao;
		String ds_email;
		short vl_sistolica;
		short vl_diastolica;
		LocalDate dt_medicao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		PressaoArterial pressao = new PressaoArterial();
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(select);
			pstmt.setString(1, user);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_medicao = rst.getShort("cd_medicao");
				ds_email = rst.getString("ds_email");
				vl_sistolica = (short)rst.getShort("vl_sistolica");
				vl_diastolica = (short)rst.getShort("vl_diastolica");
				dt_medicao = rst.getDate("dt_medicao").toLocalDate();
				pressao = new PressaoArterial(ds_email, cd_medicao, vl_sistolica, vl_diastolica, dt_medicao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar Select!");
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		
		return pressao;
		
	}

	public static void update(short cd_pressao, short vl_sistolica, short vl_diastolica, LocalDate dt_medicao) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(update);
			pstmt.setShort(1, vl_sistolica);
			pstmt.setShort(2, vl_diastolica);
			pstmt.setDate(3, Date.valueOf(dt_medicao));
			pstmt.setShort(4, cd_pressao);
			pstmt.executeUpdate();
			System.out.println("Update realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar update");
		}
	}

	public static void delete(short cd_medicao, String user) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(delete);
			pstmt.setShort(1, cd_medicao);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			System.out.println("Delete realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar delete");
		}
	}
		
}

/*private final String selectAll 	= "SELECT cd_medicao, ds_email, vl_sistolica, vl_diastolica, dt_medicao " 
+ "FROM t_pressao_arterial";*/

/*public ArrayList<PressaoArterial> select() {
		short cd_medicao;
		String ds_email;
		short vl_sistolica;
		short vl_diastolica;
		LocalDate dt_medicao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		ArrayList<PressaoArterial> listaPressao = new ArrayList<PressaoArterial>();
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(selectAll);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				cd_medicao = rst.getShort("cd_medicao");
				ds_email = rst.getString("ds_email");
				vl_sistolica = (short)rst.getShort("vl_sistolica");
				vl_diastolica = (short)rst.getShort("vl_diastolica");
				dt_medicao = rst.getDate("dt_medicao").toLocalDate();
				listaPressao.add(new PressaoArterial(ds_email, cd_medicao, vl_sistolica, vl_diastolica, dt_medicao));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoDB.closeConnection(pstmt, rst);
		}
		
		return listaPressao.size() > 0 ? listaPressao : null;
		
	}*/
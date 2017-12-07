package br.com.healthtrack.alimentacao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.healthtrack.utils.ConexaoDB;

public class AlimentacaoDAO {
	private static final String SELECT = "SELECT * FROM T_ALIMENTACAO "
			+ "WHERE ds_email = ? "
			+ "Order by DT_ALIMENTACAO DESC, VL_HORARIO DESC";
	
	private static final String SELECTBYID = "SELECT * FROM T_ALIMENTACAO "
			+ " WHERE cd_alimentacao = ?";

	private static final String INSERT 	= "INSERT INTO t_alimentacao (cd_alimentacao, cd_tipo_alimentacao, ds_email, vl_calorias, dt_alimentacao, vl_horario, ds_alimentacao) "
			+ "VALUES (SQ_ALIMENTACAO.NEXTVAL, ?, ?, ?, ?, ?, ?) ";

	private static final String UPDATE = "UPDATE t_alimentacao "
			+ "SET cd_tipo_alimentacao = ?, vl_calorias = ?, dt_alimentacao = ?, vl_horario = ?, ds_alimentacao = ? "
			+ "WHERE cd_alimentacao = ?";

	private static final String DELETE = "DELETE FROM T_ALIMENTACAO WHERE cd_alimentacao = ? and ds_email = ?";

	public static void insert(byte cd_tipo_alimentacao, String ds_email, LocalDate dt_alimentacao, LocalDateTime vl_horario, short vl_calorias, String ds_alimentacao) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(INSERT);
			pstmt.setByte(1, cd_tipo_alimentacao);
			pstmt.setString(2, ds_email);
			pstmt.setShort(3, vl_calorias);
			pstmt.setDate(4, Date.valueOf(dt_alimentacao));
			pstmt.setTimestamp(5, Timestamp.valueOf(vl_horario));
			pstmt.setString(6, ds_alimentacao);
			pstmt.executeUpdate();
			System.out.println("Executado insert com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao realizar insert!");
			e.printStackTrace();
		}

	}

	public static ArrayList<Alimentacao> select(String user) {
		ArrayList<Alimentacao> alimentList = new ArrayList<Alimentacao>();
		short cd_alimentacao;
		byte cd_tipo_alimentacao;
		String ds_email;
		short vl_calorias;
		LocalDate dt_alimentacao;
		LocalDateTime vl_horario;
		String ds_alimentacao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, user);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				cd_alimentacao = rst.getShort("cd_alimentacao");
				cd_tipo_alimentacao = rst.getByte("cd_tipo_alimentacao");
				ds_email = rst.getString("ds_email");
				vl_calorias = rst.getShort("vl_calorias");
				dt_alimentacao = rst.getDate("dt_alimentacao").toLocalDate();
				vl_horario = rst.getTimestamp("vl_horario").toLocalDateTime();
				ds_alimentacao = rst.getString("ds_alimentacao");
				alimentList.add(new Alimentacao(ds_email, dt_alimentacao, vl_horario, vl_calorias, cd_tipo_alimentacao, ds_alimentacao, cd_alimentacao));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar select");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		if(alimentList.size() <= 0) {
			return null;
		}

		return alimentList;
	}
	
	public static Alimentacao selectLast(String user) {
		Alimentacao alimentacao = new Alimentacao();
		short cd_alimentacao;
		byte cd_tipo_alimentacao;
		String ds_email;
		short vl_calorias;
		LocalDate dt_alimentacao;
		LocalDateTime vl_horario;
		String ds_alimentacao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, user);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_alimentacao = rst.getShort("cd_alimentacao");
				cd_tipo_alimentacao = rst.getByte("cd_tipo_alimentacao");
				ds_email = rst.getString("ds_email");
				vl_calorias = rst.getShort("vl_calorias");
				dt_alimentacao = rst.getDate("dt_alimentacao").toLocalDate();
				vl_horario = rst.getTimestamp("vl_horario").toLocalDateTime();
				ds_alimentacao = rst.getString("ds_alimentacao");
				alimentacao = new Alimentacao(ds_email, dt_alimentacao, vl_horario, vl_calorias, cd_tipo_alimentacao, ds_alimentacao, cd_alimentacao);
				return alimentacao;
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar select");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}

		return alimentacao;
	}
	
	public static Alimentacao selectById(int codigo) {
		Alimentacao alimentacao = new Alimentacao();
		short cd_alimentacao;
		byte cd_tipo_alimentacao;
		String ds_email;
		short vl_calorias;
		LocalDate dt_alimentacao;
		LocalDateTime vl_horario;
		String ds_alimentacao;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECTBYID);
			pstmt.setInt(1, codigo);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_alimentacao = rst.getShort("cd_alimentacao");
				cd_tipo_alimentacao = rst.getByte("cd_tipo_alimentacao");
				ds_email = rst.getString("ds_email");
				vl_calorias = rst.getShort("vl_calorias");
				dt_alimentacao = rst.getDate("dt_alimentacao").toLocalDate();
				vl_horario = rst.getTimestamp("vl_horario").toLocalDateTime();
				ds_alimentacao = rst.getString("ds_alimentacao");
				alimentacao = new Alimentacao(ds_email, dt_alimentacao, vl_horario, vl_calorias, cd_tipo_alimentacao, ds_alimentacao, cd_alimentacao);
				return alimentacao;
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar select");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}

		return alimentacao;
	}

	public static void update(byte cd_tipo_alimentacao, LocalDate dt_alimentacao, LocalDateTime vl_horario, short vl_calorias, String ds_alimentacao, short cd_alimentacao) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(UPDATE);
			pstmt.setByte(1, cd_tipo_alimentacao);
			pstmt.setShort(2, vl_calorias);
			pstmt.setDate(3, Date.valueOf(dt_alimentacao));
			pstmt.setTimestamp(4, Timestamp.valueOf(vl_horario));
			pstmt.setString(5, ds_alimentacao);
			pstmt.setShort(6, cd_alimentacao);
			pstmt.executeUpdate();
			System.out.println("Update executado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao realizar update");
			e.printStackTrace();
		} 

	}

	public static void delete(short cd_alimentacao, String user) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(DELETE);
			pstmt.setShort(1, cd_alimentacao);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			System.out.println("Executado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao realizar delete");
			e.printStackTrace();
		}
	}

}

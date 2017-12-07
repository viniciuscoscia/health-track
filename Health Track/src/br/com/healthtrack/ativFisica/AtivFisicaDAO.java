package br.com.healthtrack.ativFisica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.healthtrack.utils.ConexaoDB;

public class AtivFisicaDAO {
	private static final String SELECT 	= "SELECT cd_atividadefisica, cd_tipo_atividade, ds_email, dt_ativi_fisica, vl_horario, ds_atividade, vl_calorias "
			+ "FROM t_ativ_fisica "
			+ "WHERE ds_email = ? "
			+ "ORDER BY DT_ATIVI_FISICA DESC, VL_HORARIO DESC";

	private static final String INSERT 	= "INSERT INTO t_ativ_fisica (cd_atividadefisica, cd_tipo_atividade, ds_email, dt_ativi_fisica, vl_horario, ds_atividade, vl_calorias) "
			+ "VALUES (SQ_ATIV_FISICA.NEXTVAL, ?, ?, ?, ?, ?, ?) ";

	private static final String UPDATE	= "UPDATE t_ativ_fisica "
			+ "SET cd_tipo_atividade = ?, dt_ativi_fisica = ?, vl_horario = ?, ds_atividade = ?, vl_calorias = ?"
			+ "WHERE cd_atividadefisica = ?";

	private static final String DELETE 	= "DELETE FROM t_ativ_fisica "
			+ "WHERE cd_atividadefisica = ? and ds_email = ?";
	
	private static final String SELECTBYID 	= "SELECT cd_atividadefisica, cd_tipo_atividade, ds_email, dt_ativi_fisica, vl_horario, ds_atividade, vl_calorias "
			+ "FROM t_ativ_fisica "
			+ "WHERE cd_atividadefisica = ? "
			+ "ORDER BY DT_ATIVI_FISICA DESC, VL_HORARIO DESC";

	public static ArrayList<AtivFisica> select(String usuario) {
		short cd_atividadefisica;
		byte cd_tipo_atividade;
		short vl_calorias;
		String ds_email;
		LocalDate dt_ativi_fisica;
		LocalDateTime vl_horario;
		String ds_atividade;
		ArrayList<AtivFisica> atividades = new ArrayList<AtivFisica>();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, usuario);
			rst = pstmt.executeQuery();
			while(rst.next()) {
				cd_atividadefisica = rst.getShort("cd_atividadefisica");
				cd_tipo_atividade = rst.getByte("cd_tipo_atividade");
				vl_calorias = rst.getShort("vl_calorias");
				ds_email = rst.getString("ds_email");
				dt_ativi_fisica = rst.getDate("dt_ativi_fisica").toLocalDate();
				vl_horario = rst.getTimestamp("vl_horario").toLocalDateTime();
				ds_atividade = rst.getString("ds_atividade");
				atividades.add(new AtivFisica(ds_email, cd_atividadefisica, vl_calorias, dt_ativi_fisica, vl_horario, ds_atividade, cd_tipo_atividade));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar select");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}

		return atividades.size() > 0 ? atividades :  null;
	}
	
	public static AtivFisica selectLast(String usuario) {
		AtivFisica atividade = new AtivFisica();
		short cd_atividadefisica;
		byte cd_tipo_atividade;
		short vl_calorias;
		String ds_email;
		LocalDate dt_ativi_fisica;
		LocalDateTime vl_horario;
		String ds_atividade;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECT);
			pstmt.setString(1, usuario);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_atividadefisica = rst.getShort("cd_atividadefisica");
				cd_tipo_atividade = rst.getByte("cd_tipo_atividade");
				vl_calorias = rst.getShort("vl_calorias");
				ds_email = rst.getString("ds_email");
				dt_ativi_fisica = rst.getDate("dt_ativi_fisica").toLocalDate();
				vl_horario = rst.getTimestamp("vl_horario").toLocalDateTime();
				ds_atividade = rst.getString("ds_atividade");
				atividade = new AtivFisica(ds_email, cd_atividadefisica, vl_calorias, dt_ativi_fisica, vl_horario, ds_atividade, cd_tipo_atividade);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar select");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		return atividade;
	}
	
	public static AtivFisica selectById(short cd_atividadefisica) {
		AtivFisica atividade = new AtivFisica();
		byte cd_tipo_atividade;
		short vl_calorias;
		String ds_email;
		LocalDate dt_ativi_fisica;
		LocalDateTime vl_horario;
		String ds_atividade;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(SELECTBYID);
			pstmt.setShort(1, cd_atividadefisica);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				cd_atividadefisica = rst.getShort("cd_atividadefisica");
				cd_tipo_atividade = rst.getByte("cd_tipo_atividade");
				vl_calorias = rst.getShort("vl_calorias");
				ds_email = rst.getString("ds_email");
				dt_ativi_fisica = rst.getDate("dt_ativi_fisica").toLocalDate();
				vl_horario = rst.getTimestamp("vl_horario").toLocalDateTime();
				ds_atividade = rst.getString("ds_atividade");
				atividade = new AtivFisica(ds_email, cd_atividadefisica, vl_calorias, dt_ativi_fisica, vl_horario, ds_atividade, cd_tipo_atividade);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao realizar select");
			e.printStackTrace();
		} finally {
			if (rst != null) {
				ConexaoDB.closeConnection(rst);
			}
		}
		return atividade;
	}

	public static void insert(byte cd_tipo_atividade, String ds_email, LocalDate dt_ativi_fisica, LocalDateTime vl_horario, String ds_atividade, short vl_calorias) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(INSERT);
			pstmt.setByte(1, cd_tipo_atividade);
			pstmt.setString(2, ds_email);
			pstmt.setDate(3, Date.valueOf(dt_ativi_fisica));
			pstmt.setTimestamp(4, Timestamp.valueOf(vl_horario));
			pstmt.setString(5, ds_atividade);
			pstmt.setShort(6, vl_calorias);
			pstmt.executeUpdate();
			System.out.println("Insert realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar insert");
		}
	}

	public static void update (byte cd_tipo_atividade, LocalDate dt_ativi_fisica, LocalDateTime vl_horario, String ds_atividade, short vl_calorias, short cd_atividadefisica) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(UPDATE);
			pstmt.setByte(1, cd_tipo_atividade);
			pstmt.setDate(2, Date.valueOf(dt_ativi_fisica));
			pstmt.setTimestamp(3, Timestamp.valueOf(vl_horario));
			pstmt.setString(4, ds_atividade);
			pstmt.setShort(5, vl_calorias);
			pstmt.setShort(6, cd_atividadefisica);
			pstmt.executeUpdate();
			System.out.println("Update realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar update");
		} 
	}

	public static void delete(short cd_ativdadefisica, String user) {
		PreparedStatement pstmt = null;
		try {
			pstmt = ConexaoDB.getInstance().getConnection().prepareStatement(DELETE);
			pstmt.setShort(1, cd_ativdadefisica);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			System.out.println("Delete realizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao realizar delete");
		} 
	}

}

//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//LocalDateTime dataHora = LocalDateTime.parse("22/04/1994 11:55", dtf);
//
//System.out.println(dataHora.toString());
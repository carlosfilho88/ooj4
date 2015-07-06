package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DriverConnection;

public class Disciplina {
	
	private Integer codigo;
	private String descricao;
	
	public Disciplina() {};
	public Disciplina(Integer codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return getCodigo() + " " + getDescricao();
	}

	public static List<Disciplina> getDisciplinas() throws SQLException {
		
		DriverConnection driver = new DriverConnection();
		PreparedStatement st = null;
		Connection conn = null;
		try {
			driver.register();
			conn = driver.getConnection();
			st = (PreparedStatement) conn.prepareStatement("SELECT * from disciplina");
			ResultSet rs = st.executeQuery();
			ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
			
			while (!rs.wasNull() && rs.next()) {
				disciplinas.add(new Disciplina(rs.getInt("codigo"), rs.getString("descricao")));
			}
			rs.close();
			rs = null;
			return disciplinas;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			st.close();
			conn.close();
		}
		return null;
	}
}

package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DriverConnection;

public class DisciplinaCurso {
	
	private Integer codigo;
	private String descricao;
	
	public DisciplinaCurso() {};
	public DisciplinaCurso(Integer codigo, String descricao) {
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

	public static List<DisciplinaCurso> getDisciplinas() throws SQLException {
		
		DriverConnection driver = new DriverConnection();
		PreparedStatement st = null;
		Connection conn = null;
		try {
			driver.register();
			conn = driver.getConnection();
			st = (PreparedStatement) conn.prepareStatement("SELECT * from disciplina");
			ResultSet rs = st.executeQuery();
			ArrayList<DisciplinaCurso> disciplinas = new ArrayList<DisciplinaCurso>();
			
			while (!rs.wasNull() && rs.next()) {
				disciplinas.add(new DisciplinaCurso(rs.getInt("codigo"), rs.getString("descricao")));
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

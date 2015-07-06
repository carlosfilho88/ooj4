package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DriverConnection;

public class Curso {
	
	private Integer id;
	private String nome;
	
	public Curso() {};
	public Curso(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return getId() + " " + getNome();
	}

	public static List<Curso> getCursos() throws SQLException {
		
		DriverConnection driver = new DriverConnection();
		PreparedStatement st = null;
		Connection conn = null;
		try {
			driver.register();
			conn = driver.getConnection();
			st = (PreparedStatement) conn.prepareStatement("SELECT * from curso");
			ResultSet rs = st.executeQuery();
			ArrayList<Curso> cursos = new ArrayList<Curso>();
			
			while (!rs.wasNull() && rs.next()) {
				cursos.add(new Curso(rs.getInt("id"), rs.getString("nome")));
			}
			rs.close();
			rs = null;
			return cursos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			st.close();
			conn.close();
		}
		return null;
	}
}

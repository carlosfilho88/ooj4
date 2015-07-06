package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DriverConnection;

public class Aluno {
	
	private String RA;
	private String nome;
	
	public Aluno() {};
	public Aluno(String rA, String nome) {
		super();
		RA = rA;
		this.nome = nome;
	}
	public String getRA() {
		return RA;
	}
	public void setRA(String rA) {
		RA = rA;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return getRA() + " " + getNome();
	}

	public static List<Aluno> getAlunos() throws SQLException {
		
		DriverConnection driver = new DriverConnection();
		PreparedStatement st = null;
		Connection conn = null;
		try {
			driver.register();
			conn = driver.getConnection();
			st = (PreparedStatement) conn.prepareStatement("SELECT * from aluno");
			ResultSet rs = st.executeQuery();
			ArrayList<Aluno> alunos = new ArrayList<Aluno>();
			
			while (!rs.wasNull() && rs.next()) {
				alunos.add(new Aluno(rs.getString("RA"), rs.getString("nome")));
			}
			rs.close();
			rs = null;
			return alunos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			st.close();
			conn.close();
		}
		return null;
	}
}

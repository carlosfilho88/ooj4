package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DriverConnection;

public class ResumoNotas {
	
	private Float maior;
	private Float menor;
	private Float media;
	private Integer disciplina_id;
	private Integer curso_id;
	
	public ResumoNotas() { 
	}
	public ResumoNotas(Float maior, Float menor, Float media, Integer disciplina_id, Integer curso_id) {
		super();
		this.maior = maior;
		this.menor = menor;
		this.media = media;
		this.disciplina_id = disciplina_id;
		this.curso_id = curso_id;
	}
	public Float getMaior() {
		return maior;
	}
	public void setMaior(Float maior) {
		this.maior = maior;
	}
	public Float getMenor() {
		return menor;
	}
	public void setMenor(Float menor) {
		this.menor = menor;
	}
	public Float getMedia() {
		return media;
	}
	public void setMedia(Float media) {
		this.media = media;
	}
	public Integer getDisciplina_id() {
		return disciplina_id;
	}
	public void setDisciplina_id(Integer disciplina_id) {
		this.disciplina_id = disciplina_id;
	}
	public Integer getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(Integer curso_id) {
		this.curso_id = curso_id;
	}
	
	public String toString() {
		return "disciplina_id: " + getDisciplina_id() + ", curso_id: " + getCurso_id() + ", maior: " + getMaior() + ", menor: " + getMenor() + ", media: " + getMedia();
	}
	
	public static void calculaNotas() throws SQLException {
		DriverConnection driver = new DriverConnection();
		PreparedStatement stQuery = null;
		PreparedStatement stInsert = null;
		PreparedStatement stTruncate = null;
		Connection conn = null;
		ResultSet rs = null;
		int affected_rows = 0;
		String query = "SELECT c.id AS curso_id, d.codigo AS disciplina_id, AVG(ad.nota) AS media, MAX(ad.nota) AS maior, MIN(ad.nota) AS menor FROM disciplina d, aluno a, curso c, disciplina_curso dc, aluno_disciplina ad WHERE c.id = dc.id_curso AND d.codigo = dc.codigo_disciplina AND a.RA = ad.RA AND ad.codigo_disciplina = d.codigo GROUP BY c.id, d.codigo";
		String insert = "INSERT INTO resumo_notas (maior, menor, media, disciplina_id, curso_id) VALUES (?,?,?,?,?)";
		String truncate = "TRUNCATE TABLE resumo_notas";
		
		try {
			driver.register();
			conn = driver.getConnection();
			stTruncate = (PreparedStatement) conn.prepareStatement(truncate);
			stTruncate.executeUpdate();
			stQuery = (PreparedStatement) conn.prepareStatement(query);
			rs = stQuery.executeQuery();
			
			while (!rs.wasNull() && rs.next()) {
				stInsert = (PreparedStatement) conn.prepareStatement(insert);
				stInsert.setFloat(1, rs.getFloat("maior"));
				stInsert.setFloat(2, rs.getFloat("menor"));
				stInsert.setFloat(3, rs.getFloat("media"));
				stInsert.setFloat(4, rs.getFloat("disciplina_id"));
				stInsert.setFloat(5, rs.getFloat("curso_id"));
				affected_rows += stInsert.executeUpdate();
			}
			System.out.println("Registros atualizados: " + affected_rows);
			stQuery.close();
			stInsert.close();
			stTruncate.close();
			rs.close();
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stQuery.close();
			conn.close();
		}
	}
	
	public static <T> List<ResumoNotas> sortByFieldName(String fieldName) throws SQLException {
		return null;
	
	}
	
}


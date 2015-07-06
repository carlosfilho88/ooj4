package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
				stInsert.setFloat(4, rs.getInt("disciplina_id"));
				stInsert.setFloat(5, rs.getInt("curso_id"));
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
	
	public static void sortByFieldName(Comparator<ResumoNotas> comparator) throws SQLException {
		DriverConnection driver = new DriverConnection();
		PreparedStatement stQuery = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			driver.register();
			conn = driver.getConnection();
			String query = "SELECT * from resumo_notas";
			stQuery = (PreparedStatement) conn.prepareStatement(query);
			rs = stQuery.executeQuery();
			
			ArrayList<ResumoNotas> notas = new ArrayList<ResumoNotas>();
			
			while (!rs.wasNull() && rs.next()) {
				notas.add(new ResumoNotas(rs.getFloat("maior"), rs.getFloat("menor"), rs.getFloat("media"), rs.getInt("disciplina_id"), rs.getInt("curso_id")));
			}
			stQuery.close();
			rs.close();
			rs = null;
			System.out.println("-------- Ordenação por " + comparator.toString() + " ASC ---------");
			notas.sort(comparator);
			for (ResumoNotas n : notas) {
				System.out.println(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stQuery.close();
			conn.close();
		}
	}
	
	public static void getCursosDisciplinasByNota(Float media) throws SQLException {
		DriverConnection driver = new DriverConnection();
		PreparedStatement stQuery = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			driver.register();
			conn = driver.getConnection();
			String query = "SELECT * from resumo_notas where media = ?";
			stQuery = (PreparedStatement) conn.prepareStatement(query);
			stQuery.setFloat(1, media);
			rs = stQuery.executeQuery();
			
			List<ResumoNotas> notas = new ArrayList<ResumoNotas>();
			
			while (!rs.wasNull() && rs.next()) {
				notas.add(new ResumoNotas(rs.getFloat("maior"), rs.getFloat("menor"), rs.getFloat("media"), rs.getInt("disciplina_id"), rs.getInt("curso_id")));
			}
			stQuery.close();
			rs.close();
			rs = null;
			System.out.println("-------- Disciplinas/curso com medias = " + media);
			for (ResumoNotas n : notas) {
				System.out.println(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stQuery.close();
			conn.close();
		}
	}
	
	public static void getNotasDistinct() throws SQLException {
		DriverConnection driver = new DriverConnection();
		PreparedStatement stQuery = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			driver.register();
			conn = driver.getConnection();
			String query = "SELECT * from resumo_notas";
			stQuery = (PreparedStatement) conn.prepareStatement(query);
			rs = stQuery.executeQuery();
			List<ResumoNotas> notas = new ArrayList<ResumoNotas>();
			Map<Float,ResumoNotas> medias = new Hashtable<Float,ResumoNotas>();
			Map<Float,ResumoNotas> maiores = new Hashtable<Float,ResumoNotas>();
			Map<Float,ResumoNotas> menores = new Hashtable<Float,ResumoNotas>();
			
			while (!rs.wasNull() && rs.next()) {
				notas.add(new ResumoNotas(rs.getFloat("maior"), rs.getFloat("menor"), rs.getFloat("media"), rs.getInt("disciplina_id"), rs.getInt("curso_id")));
			}
			stQuery.close();
			rs.close();
			rs = null;
			
			for (ResumoNotas n : notas) {
				medias.put(n.getMaior(), n);
				menores.put(n.getMenor(), n);
				maiores.put(n.getMaior(), n);
			}
			
			System.out.println("-------- Notas médias diferentes --------");
			for (Map.Entry<Float, ResumoNotas> entry : medias.entrySet()) {
			    System.out.println(entry.getValue());
			}
			System.out.println("-------- Notas maiores diferentes --------");
			for (Map.Entry<Float, ResumoNotas> entry : maiores.entrySet()) {
			    System.out.println(entry.getValue());
			}
			System.out.println("-------- Notas menores diferentes --------");
			for (Map.Entry<Float, ResumoNotas> entry : menores.entrySet()) {
			    System.out.println(entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stQuery.close();
			conn.close();
		}
	}
	
	public Float compareTo(ResumoNotas nota) {
		Float n1 = ((ResumoNotas) nota).getMedia(); 
		return this.getMedia() - n1;
 
	}
 
	public static Comparator<ResumoNotas> ResumoNotasMediaComparator = new Comparator<ResumoNotas>() {
 
	    public int compare(ResumoNotas nota1, ResumoNotas nota2) {
	      Float n1 = nota1.getMedia();
	      Float n2 = nota2.getMedia();
	      if(n1 < n2)
	    	  return -1;
	      else if(n1 > n2)
	    	  return 1;
	      return 0;
	    }
	    
	    public String toString() {
	    	return "media";
	    }
 
	};
	
	public static Comparator<ResumoNotas> ResumoNotasMaiorComparator = new Comparator<ResumoNotas>() {
		 
	    public int compare(ResumoNotas nota1, ResumoNotas nota2) {
	      Float n1 = nota1.getMaior();
	      Float n2 = nota2.getMaior();
	      if(n1 < n2)
	    	  return -1;
	      else if(n1 > n2)
	    	  return 1;
	      return 0;
	    }
	    
	    public String toString() {
	    	return "maior";
	    }
 
	};
	
	public static Comparator<ResumoNotas> ResumoNotasMenorComparator = new Comparator<ResumoNotas>() {
		 
	    public int compare(ResumoNotas nota1, ResumoNotas nota2) {
	      Float n1 = nota1.getMenor();
	      Float n2 = nota2.getMenor();
	      if(n1 < n2)
	    	  return -1;
	      else if(n1 > n2)
	    	  return 1;
	      return 0;
	    }
	    
	    public String toString() {
	    	return "menor";
	    }
 
	};
	
	public static Comparator<ResumoNotas> ResumoNotasDisciplinaComparator = new Comparator<ResumoNotas>() {
		 
	    public int compare(ResumoNotas nota1, ResumoNotas nota2) {
	      Integer n1 = nota1.getDisciplina_id();
	      Integer n2 = nota2.getDisciplina_id();
	      if(n1 < n2)
	    	  return -1;
	      else if(n1 > n2)
	    	  return 1;
	      return 0;
	    }
	    public String toString() {
	    	return "disciplina";
	    }
 
	};
	
	public static Comparator<ResumoNotas> ResumoNotasCursoComparator = new Comparator<ResumoNotas>() {
		 
	    public int compare(ResumoNotas nota1, ResumoNotas nota2) {
	    	Integer n1 = nota1.getCurso_id();
	    	Integer n2 = nota2.getCurso_id();
	    	if(n1 < n2)
		    	  return -1;
		      else if( 1 > n2)
		    	  return 1;
		      return 0;
	    }
	    
	    public String toString() {
	    	return "curso";
	    }
 
	};
	
}


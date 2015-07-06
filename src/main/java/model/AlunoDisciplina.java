package model;

public class AlunoDisciplina {
	
	private Float nota;
	private String RA;
	private Integer codigo_disciplina;
	
	public AlunoDisciplina() {};
	public AlunoDisciplina(Float nota, String rA, Integer codigo_disciplina) {
		super();
		this.nota = nota;
		RA = rA;
		this.codigo_disciplina = codigo_disciplina;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
	public String getRA() {
		return RA;
	}
	public void setRA(String rA) {
		RA = rA;
	}
	public Integer getCodigo_disciplina() {
		return codigo_disciplina;
	}
	public void setCodigo_disciplina(Integer codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}
	public String toString() {
		return getNota() + " " + getRA() + " " + getCodigo_disciplina();
	}

}

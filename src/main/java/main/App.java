package main;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.ResumoNotas;

public class App 
{
    public static void main( String[] args )
    {
    	try {
			ArrayList<Aluno> alunos = (ArrayList<Aluno>) Aluno.getAlunos();
			ArrayList<Curso> cursos = (ArrayList<Curso>) Curso.getCursos();
			ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) Disciplina.getDisciplinas();
			
			System.out.println("****************** Lista de Alunos ******************");
			for (Aluno aluno : alunos) {
				System.out.println(aluno);
			}
			System.out.println();
			System.out.println("****************** Lista de Cursos ******************");
			for (Curso curso : cursos) {
				System.out.println(curso);
			}
			System.out.println();
			System.out.println("****************** Lista de Disciplinas ******************");
			for (Disciplina disciplina : disciplinas) {
				System.out.println(disciplina);
			}
			System.out.println();
			
			System.out.println("****************** Letra A ******************");
			ResumoNotas.calculaNotas();
			System.out.println();
			
			System.out.println("****************** Letra B ******************");
			ResumoNotas.sortByFieldName(ResumoNotas.ResumoNotasMediaComparator);
			System.out.println();
			ResumoNotas.sortByFieldName(ResumoNotas.ResumoNotasMaiorComparator);
			System.out.println();
			ResumoNotas.sortByFieldName(ResumoNotas.ResumoNotasMenorComparator);
			System.out.println();
			ResumoNotas.sortByFieldName(ResumoNotas.ResumoNotasDisciplinaComparator);
			System.out.println();
			ResumoNotas.sortByFieldName(ResumoNotas.ResumoNotasCursoComparator);
			System.out.println();
			
			System.out.println("****************** Letra C ******************");
    		ResumoNotas.getCursosDisciplinasByNota(new Float("8.57"));
    		System.out.println();
    		
    		System.out.println("****************** Letra D ******************");
    		ResumoNotas.getNotasDistinct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

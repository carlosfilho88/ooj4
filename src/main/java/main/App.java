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
//			ArrayList<Aluno> alunos = (ArrayList<Aluno>) Aluno.getAlunos();
//			ArrayList<Curso> cursos = (ArrayList<Curso>) Curso.getCursos();
//			ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) Disciplina.getDisciplinas();
//			
//			for (Aluno aluno : alunos) {
//				System.out.println(aluno);
//			}
//			for (Curso curso : cursos) {
//				System.out.println(curso);
//			}
//			for (Disciplina disciplina : disciplinas) {
//				System.out.println(disciplina);
//			}
			
			ResumoNotas.calculaNotas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

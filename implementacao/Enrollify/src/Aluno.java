import java.util.List;


public class Aluno extends Usuario {

    private String matricula;
    private Curso curso;
    
    private List<Matricula> turmasMatriculadas;

    public void matricularEmDisciplina(Turma turma, TipoMatricula tipo) {
        // Lógica
        System.out.println("Método matricularEmDisciplina() chamado.");
    }

    public void cancelarMatricula(Matricula matricula) {
        // Lógica
        System.out.println("Método cancelarMatricula() chamado.");
    }

    public List<Disciplina> consultarDisciplinas() {
        // Lógica
        System.out.println("Método consultarDisciplinas() chamado.");
        return null;
    }
}
import java.util.List;

public class Turma {
    
    public static final int MAX_ALUNOS = 60;
    public static final int MIN_ALUNOS = 3;

    private Long idTurma;
    private Disciplina disciplina;
    private List<Matricula> matriculas; 
    private Professor professor;
    private Semestre semestre;
    private EstadoTurma status;

    public void method() {
        
        System.out.println("Método 'method()' da Turma foi chamado.");
    }
}
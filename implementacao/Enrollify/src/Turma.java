import java.util.ArrayList;
import java.util.List;

public class Turma implements java.io.Serializable {
    private static long contadorId = 0;

    public static final int MAX_ALUNOS = 60;
    public static final int MIN_ALUNOS = 3;

    private Long idTurma;
    private Disciplina disciplina;
    private Professor professor;
    private List<Aluno> alunosMatriculados;
    private EstadoDisciplina status;

    public Turma(Disciplina disciplina) {
        this.idTurma = ++contadorId;
        this.disciplina = disciplina;
        this.alunosMatriculados = new ArrayList<>();
        this.status = EstadoDisciplina.PLANEJADA;
    }

    public void adicionarAluno(Aluno aluno) {
        if (alunosMatriculados.size() < MAX_ALUNOS) {
            alunosMatriculados.add(aluno);
        } else {
            throw new RuntimeException("Turma lotada! Máximo de " + MAX_ALUNOS + " alunos.");
        }
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public boolean podeSerOferecida() {
        return alunosMatriculados.size() >= MIN_ALUNOS;
    }

    public boolean estaLotada() {
        return alunosMatriculados.size() >= MAX_ALUNOS;
    }

    public Long getIdTurma() { return idTurma; }
    public Disciplina getDisciplina() { return disciplina; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) {
        this.professor = professor;
        if (professor != null) {
            professor.adicionarTurma(this);
        }
    }
    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }
    public EstadoDisciplina getStatus() { return status; }
    public void setStatus(EstadoDisciplina status) { this.status = status; }

    @Override
    public String toString() {
        return "Turma " + idTurma + " - " + disciplina.getNome() +
               " (" + alunosMatriculados.size() + "/" + MAX_ALUNOS + " alunos)";
    }
}

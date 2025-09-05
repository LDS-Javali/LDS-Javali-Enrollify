import java.util.ArrayList;
import java.util.List;

public class Disciplina implements java.io.Serializable {
    public static final int MAX_ALUNOS = 60;
    public static final int MIN_ALUNOS = 3;
    private static long contadorId = 0;

    private Long idDisciplina;
    private String nome;
    private int creditos;
    private EstadoDisciplina status;
    private Professor professor;
    private List<Matricula> matriculas;
    private List<Disciplina> preRequisitos;
    private List<Disciplina> coRequisitos;
    private List<Turma> turmas;

    public Disciplina(String nome, int creditos) {
        this.idDisciplina = ++contadorId;
        this.nome = nome;
        this.creditos = creditos;
        this.status = EstadoDisciplina.PLANEJADA;
        this.matriculas = new ArrayList<>();
        this.preRequisitos = new ArrayList<>();
        this.coRequisitos = new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public void adicionarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    public void adicionarPreRequisito(Disciplina disciplina) {
        this.preRequisitos.add(disciplina);
    }

    public void adicionarCoRequisito(Disciplina disciplina) {
        this.coRequisitos.add(disciplina);
    }

    public void removerPreRequisito(Disciplina disciplina) {
        this.preRequisitos.remove(disciplina);
    }

    public void removerCoRequisito(Disciplina disciplina) {
        this.coRequisitos.remove(disciplina);
    }

    public int getNumeroMatriculados() {
        return this.matriculas.size();
    }

    public String getNome() { return nome; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) {
        this.professor = professor;
        if (professor != null) {
            professor.adicionarTurma(new Turma(this));
        }
    }
    public EstadoDisciplina getStatus() { return status; }
    public void setStatus(EstadoDisciplina status) { this.status = status; }
    public List<Disciplina> getPreRequisitos() { return preRequisitos; }
    public List<Disciplina> getCoRequisitos() { return coRequisitos; }
    public List<Matricula> getMatriculas() { return matriculas; }
    public List<Turma> getTurmas() { return turmas; }
    public int getCreditos() { return creditos; }
    public Long getIdDisciplina() { return idDisciplina; }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " (ID: " + idDisciplina + ")";
    }
}

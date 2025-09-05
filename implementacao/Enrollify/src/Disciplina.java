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

    public Disciplina(String nome, int creditos) {
        this.idDisciplina = ++contadorId;
        this.nome = nome;
        this.creditos = creditos;
        this.status = EstadoDisciplina.PLANEJADA;
        this.matriculas = new ArrayList<>();
        this.preRequisitos = new ArrayList<>();
        this.coRequisitos = new ArrayList<>();
    }
    
    public void adicionarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    public void adicionarPreRequisito(Disciplina disciplina) {
        this.preRequisitos.add(disciplina);
    }
    
    public int getNumeroMatriculados() {
        return this.matriculas.size();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) {
        this.professor = professor;
        professor.atribuirDisciplina(this);
    }
    public EstadoDisciplina getStatus() { return status; }
    public void setStatus(EstadoDisciplina status) { this.status = status; }
    public List<Disciplina> getPreRequisitos() { return preRequisitos; }
    public List<Matricula> getMatriculas() { return matriculas; }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " (ID: " + idDisciplina + ")";
    }
}
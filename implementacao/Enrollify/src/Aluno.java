import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements java.io.Serializable {
    private String matricula;
    private Curso curso;
    private List<Matricula> historicoDeMatriculas;

    public Aluno(String nome, String login, String senha, String email, String matricula, Curso curso) {
        super(nome, login, senha, email);
        this.matricula = matricula;
        this.curso = curso;
        this.historicoDeMatriculas = new ArrayList<>();
    }

    public void matricularEmDisciplina(Disciplina disciplina, TipoMatricula tipo) {
        for (Matricula m : historicoDeMatriculas) {
            if (m.getDisciplina().equals(disciplina) &&
                (m.getStatus().equals("CURSANDO") || m.getStatus().equals("MATRICULADO"))) {
                throw new RuntimeException("Já matriculado nesta disciplina!");
            }
        }

        Matricula novaMatricula = new Matricula(this, disciplina, tipo);
        historicoDeMatriculas.add(novaMatricula);
        disciplina.adicionarMatricula(novaMatricula);

        System.out.println("Matrícula realizada em " + disciplina.getNome());
    }

    public void cancelarMatricula(Matricula matricula) {
        if (historicoDeMatriculas.contains(matricula)) {
            matricula.setStatus("CANCELADA");
            System.out.println("Matrícula cancelada!");
        } else {
            throw new RuntimeException("Matrícula não encontrada!");
        }
    }

    public List<Disciplina> consultarDisciplinas() {
        List<Disciplina> disciplinasAtivas = new ArrayList<>();
        for (Matricula m : historicoDeMatriculas) {
            if (m.getStatus().equals("CURSANDO") || m.getStatus().equals("MATRICULADO")) {
                disciplinasAtivas.add(m.getDisciplina());
            }
        }
        return disciplinasAtivas;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public List<Matricula> getHistoricoDeMatriculas() { return historicoDeMatriculas; }

    @Override
    public String toString() {
        return "Aluno: " + nome + " (Matrícula: " + matricula + ")";
    }
}

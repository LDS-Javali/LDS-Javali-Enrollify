import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Aluno extends Usuario {
    private String matricula;
    private Curso curso;
    private List<Matricula> historicoDeMatriculas;

    public Aluno(String nome, String login, String senha, String email, String matricula, Curso curso) {
        super(nome, login, senha, email);
        this.matricula = matricula;
        this.curso = curso;
        this.historicoDeMatriculas = new ArrayList<>();
    }

    public void adicionarMatricula(Matricula matricula) {
        this.historicoDeMatriculas.add(matricula);
    }
    
    public List<Disciplina> getDisciplinasAprovadas() {
        return historicoDeMatriculas.stream()
                .filter(m -> "APROVADO".equals(m.getStatus()))
                .map(Matricula::getDisciplina)
                .collect(Collectors.toList());
    }
    
    public List<Matricula> getHistoricoDeMatriculas() {
        return historicoDeMatriculas;
    }

    public Curso getCurso() {
        return curso;
    }
    
    @Override
    public String toString() {
        return "Aluno: " + getNome() + " (Matrícula: " + matricula + ")";
    }
}
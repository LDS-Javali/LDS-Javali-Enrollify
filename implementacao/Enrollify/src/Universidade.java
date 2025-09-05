import java.util.ArrayList;
import java.util.List;

public class Universidade  implements java.io.Serializable {
    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Secretaria> secretarias = new ArrayList<>();

    // Getters para as listas (simulando acesso ao "banco")
    public List<Aluno> getAlunos() { return alunos; }
    public List<Professor> getProfessores() { return professores; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public List<Curso> getCursos() { return cursos; }
    public List<Secretaria> getSecretarias() { return secretarias; }
}
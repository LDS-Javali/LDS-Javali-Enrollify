import java.util.ArrayList;
import java.util.List;

/**
 * Representa a grade curricular de um Curso, contendo o conjunto de
 * disciplinas que o compõem.
 */
public class Curriculo implements java.io.Serializable{
    private static long contadorId = 0;
    private Long idCurriculo;
    private Curso curso;
    private List<Disciplina> disciplinas;

    public Curriculo(Curso curso) {
        this.idCurriculo = ++contadorId;
        this.curso = curso;
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!this.disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Curso getCurso() {
        return curso;
    }
}
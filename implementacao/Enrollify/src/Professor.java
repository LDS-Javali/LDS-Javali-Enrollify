import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario implements java.io.Serializable {
    private List<Turma> turmasAtribuidas;

    public Professor(String nome, String login, String senha, String email) {
        super(nome, login, senha, email);
        this.turmasAtribuidas = new ArrayList<>();
    }

    public void adicionarTurma(Turma turma) {
        if (!turmasAtribuidas.contains(turma)) {
            turmasAtribuidas.add(turma);
        }
    }

    public void removerTurma(Turma turma) {
        turmasAtribuidas.remove(turma);
    }

    public List<Turma> consultarTurmasAtribuidas() {
        return new ArrayList<>(turmasAtribuidas);
    }

    @Override
    public String toString() {
        return "Professor: " + nome + " (" + login + ")";
    }
}

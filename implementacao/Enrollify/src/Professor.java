import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasAtribuidas;

    public Professor(String nome, String login, String senha, String email) {
        super(nome, login, senha, email);
        this.disciplinasAtribuidas = new ArrayList<>();
    }

    public void atribuirDisciplina(Disciplina disciplina) {
        this.disciplinasAtribuidas.add(disciplina);
    }

    public List<Disciplina> consultarDisciplinasAtribuidas() {
        return this.disciplinasAtribuidas;
    }
}
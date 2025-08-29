import java.util.List;

public class Professor extends Usuario {

    private List<Turma> turmasAtribuidas;

    public List<Turma> consultarTurmasAtribuidas() {
        // Lógica
        return this.turmasAtribuidas;
    }
}
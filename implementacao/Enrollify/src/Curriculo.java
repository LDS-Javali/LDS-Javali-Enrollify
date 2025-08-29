import java.util.List;

public class Curriculo {
    
    private Long idCurriculo;
    private List<Disciplina> disciplinas;
    private Curso curso;
    private Semestre semestre;

    public void adicionarDisciplina(Disciplina disciplina) {
        System.out.println("Método adicionarDisciplina() ao currículo chamado.");
    }

    public void removerDisciplina(Disciplina disciplina) {
        System.out.println("Método removerDisciplina() do currículo chamado.");
    }
}
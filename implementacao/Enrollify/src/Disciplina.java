import java.util.List;

public class Disciplina {
    
    private Long idDisciplina;
    private String nome;
    private int creditos;
    private List<Disciplina> preRequisitos;
    private List<Disciplina> coRequisitos;
    private List<Turma> turmas; 

    public void adicionarPreRequisito(Disciplina disciplina) {
        System.out.println("Método adicionarPreRequisito() chamado.");
    }

    public void removerPreRequisito(Disciplina disciplina) {
        System.out.println("Método removerPreRequisito() chamado.");
    }
    
    public void adicionarCoRequisito(Disciplina disciplina) {
        System.out.println("Método adicionarCoRequisito() chamado.");
    }
    
    public void removerCoRequisito(Disciplina disciplina) {
        System.out.println("Método removerCoRequisito() chamado.");
    }
}
import java.util.Date;

/**
 * Classe de associação que representa a matrícula de um Aluno em uma Turma.
 */
public class Matricula {
    
    private Long idMatricula;
    private Date data;
    private String status; // Poderia ser uma Enum StatusMatricula {CONFIRMADA, PENDENTE, CANCELADA}
    private TipoMatricula tipo;

    // Atributos de associação necessários para implementar a ligação
    private Aluno aluno;
    private Turma turma;
}
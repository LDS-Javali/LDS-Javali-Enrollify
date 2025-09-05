import java.util.Date;

public class Matricula  implements java.io.Serializable{
    private static long contadorId = 0;
    private Long idMatricula;
    private Date dataInicio;
    private Date dataFinal;
    private String status;
    private TipoMatricula tipo;
    private Aluno aluno;
    private Disciplina disciplina;

    public Matricula(Aluno aluno, Disciplina disciplina, TipoMatricula tipo) {
        this.idMatricula = ++contadorId;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.tipo = tipo;
        this.dataInicio = new Date(); // Data atual
        this.status = "CURSANDO";
    }

    // Getters
    public Aluno getAluno() { return aluno; }
    public Disciplina getDisciplina() { return disciplina; }
    public String getStatus() { return status; }
    
    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }
}
public class Curso implements java.io.Serializable {
    private static long contadorId = 0;
    private Long idCurso;
    private String nome;
    private Curriculo curriculo;

    public Curso(String nome) {
        this.idCurso = ++contadorId;
        this.nome = nome;
        this.curriculo = new Curriculo(this);
    }

    public String getNome() {
        return nome;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    @Override
    public String toString() {
        return "Curso: " + nome;
    }
}

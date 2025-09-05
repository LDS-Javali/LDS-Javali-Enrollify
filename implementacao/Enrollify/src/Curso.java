/**
 * Representa um curso oferecido pela universidade.
 * Agora possui um Curriculo que define sua grade de disciplinas.
 */
public class Curso  implements java.io.Serializable{
    private static long contadorId = 0;
    private Long idCurso;
    private String nome;
    private Curriculo curriculo; // Substitui a lista de disciplinas

    public Curso(String nome) {
        this.idCurso = ++contadorId;
        this.nome = nome;
        // Um curso é criado juntamente com seu currículo.
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
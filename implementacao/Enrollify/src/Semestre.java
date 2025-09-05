public class Semestre implements java.io.Serializable {
    private static long contadorId = 0;

    private Long idSemestre;
    private int ano;
    private int periodo;

    public Semestre(int ano, int periodo) {
        this.idSemestre = ++contadorId;
        this.ano = ano;
        this.periodo = periodo;
    }

    public Long getIdSemestre() { return idSemestre; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }

    @Override
    public String toString() {
        return ano + "." + periodo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Semestre semestre = (Semestre) obj;
        return ano == semestre.ano && periodo == semestre.periodo;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(ano, periodo);
    }
}

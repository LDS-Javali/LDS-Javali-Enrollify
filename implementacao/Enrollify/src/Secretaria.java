public class Secretaria extends Usuario {

    public Usuario cadastrarUsuario(Usuario usuario) {
        System.out.println("Método cadastrarUsuario() chamado.");
        return null;
    }

    public void removerUsuario(Usuario usuario) {
        System.out.println("Método removerUsuario() chamado.");
    }

    public Usuario consultarUsuario(long idUsuario) {
        System.out.println("Método consultarUsuario() chamado.");
        return null;
    }

    public void definirCurriculo(Curriculo curriculo) {
        System.out.println("Método definirCurriculo() chamado.");
    }

    public void encerrarPeriodoMatricula(Semestre semestre) {
        System.out.println("Método encerrarPeriodoMatricula() chamado.");
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        System.out.println("Método adicionarDisciplina() chamado.");
    }

    public void removerDisciplina(Disciplina disciplina) {
        System.out.println("Método removerDisciplina() chamado.");
    }

    public void cancelarTurma(Turma turma) {
        System.out.println("Método cancelarTurma() chamado.");
    }

    public void definirPreRequisito(Disciplina disciplina, Disciplina preRequisito) {
        System.out.println("Método definirPreRequisito() chamado.");
    }

    public void definirCoRequisito(Disciplina disciplina, Disciplina coRequisito) {
        System.out.println("Método definirCoRequisito() chamado.");
    }
}
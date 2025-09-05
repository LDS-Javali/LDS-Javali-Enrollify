// Conteúdo anterior da classe...
// Adicionamos um método mais específico para a gestão acadêmica.

public class Secretaria extends Usuario {

    private Universidade universidade;

    // Construtor e outros métodos existentes...
    public Secretaria(String nome, String login, String senha, String email, Universidade universidade) {
        super(nome, login, senha, email);
        this.universidade = universidade;
    }

    /**
     * Adiciona uma disciplina tanto ao catálogo geral da universidade quanto
     * ao currículo de um curso específico.
     * @param disciplina A disciplina a ser adicionada.
     * @param curso O curso ao qual a disciplina pertencerá.
     */
    public void adicionarDisciplinaAoCurso(Disciplina disciplina, Curso curso) {
        // Adiciona à lista mestre da universidade
        if (!this.universidade.getDisciplinas().contains(disciplina)) {
            this.universidade.getDisciplinas().add(disciplina);
        }
        // Adiciona ao currículo do curso específico
        curso.getCurriculo().adicionarDisciplina(disciplina);
        System.out.println("INFO: Disciplina '" + disciplina.getNome() + "' adicionada ao curso de " + curso.getNome());
    }
    
    // O método antigo de adicionar disciplina pode ser mantido para adicionar
    // disciplinas ao sistema sem imediatamente associá-las a um curso.
    public void adicionarDisciplina(Disciplina disciplina) {
        this.universidade.getDisciplinas().add(disciplina);
    }
    
    // Restante dos métodos...
    public void matricularAlunoEmDisciplina(Aluno aluno, Disciplina disciplina, TipoMatricula tipo) throws MatriculaException {
        // ...lógica inalterada
    }

    public void encerrarPeriodoMatricula() {
        // ...lógica inalterada
    }

    public void adicionarAluno(Aluno aluno) {
        this.universidade.getAlunos().add(aluno);
    }
}
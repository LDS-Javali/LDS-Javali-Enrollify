// Conteúdo anterior da classe...
// Adicionamos um método mais específico para a gestão acadêmica.

import java.util.List;

public class Secretaria extends Usuario {

    private Universidade universidade;

    // Construtor e outros métodos existentes...
    public Secretaria(String nome, String login, String senha, String email, Universidade universidade) {
        super(nome, login, senha, email);
        this.universidade = universidade;
    }

    /**
     * Cria um novo curso e o adiciona à universidade.
     * @param nome Nome do novo curso.
     * @return O objeto Curso criado.
     */
    public Curso criarCurso(String nome) {
        Curso novoCurso = new Curso(nome);
        this.universidade.getCursos().add(novoCurso);
        return novoCurso;
    }

    /**
     * Cria uma nova disciplina e a adiciona ao catálogo geral da universidade.
     * @param nome Nome da nova disciplina.
     * @param creditos Número de créditos da disciplina.
     * @return O objeto Disciplina criado.
     */
    public Disciplina criarDisciplina(String nome, int creditos) {
        Disciplina novaDisciplina = new Disciplina(nome, creditos);
        this.universidade.getDisciplinas().add(novaDisciplina);
        return novaDisciplina;
    }

    /**
     * Adiciona uma disciplina tanto ao catálogo geral da universidade quanto
     * ao currículo de um curso específico.
     * @param disciplina A disciplina a ser adicionada.
     * @param curso O curso ao qual a disciplina pertencerá.
     */
    public void adicionarDisciplinaAoCurso(Disciplina disciplina, Curso curso) {
        curso.getCurriculo().adicionarDisciplina(disciplina);
        System.out.println(">>> Disciplina '" + disciplina.getNome() + "' adicionada com sucesso ao curso de " + curso.getNome());
    }
    

    public void matricularAlunoEmDisciplina(Aluno aluno, Disciplina disciplina, TipoMatricula tipo) throws MatriculaException {
        // Regra 1: Verificar se a disciplina está lotada
        if (disciplina.getNumeroMatriculados() >= Disciplina.MAX_ALUNOS) {
            throw new MatriculaException("Não foi possível matricular: Disciplina '" + disciplina.getNome() + "' está lotada.");
        }

        // Regra 2: Verificar se o aluno cumpre os pré-requisitos
        List<Disciplina> aprovadas = aluno.getDisciplinasAprovadas();
        for (Disciplina preRequisito : disciplina.getPreRequisitos()) {
            if (!aprovadas.contains(preRequisito)) {
                throw new MatriculaException("Não foi possível matricular: Aluno não cumpriu o pré-requisito '" + preRequisito.getNome() + "'.");
            }
        }

        // Se todas as regras passaram, efetiva a matrícula
        Matricula novaMatricula = new Matricula(aluno, disciplina, tipo);
        aluno.adicionarMatricula(novaMatricula);
        disciplina.adicionarMatricula(novaMatricula);

        System.out.println("SUCESSO: Aluno " + aluno.getNome() + " matriculado em " + disciplina.getNome() + ".");
    }

    public void encerrarPeriodoMatricula() {
        System.out.println("\n--- ENCERRANDO PERÍODO DE MATRÍCULA ---");
        for (Disciplina disciplina : universidade.getDisciplinas()) {
            if (disciplina.getStatus() == EstadoDisciplina.PLANEJADA) {
                if (disciplina.getNumeroMatriculados() < Disciplina.MIN_ALUNOS) {
                    disciplina.setStatus(EstadoDisciplina.CANCELADA);
                    System.out.println("Disciplina '" + disciplina.getNome() + "' CANCELADA por falta de alunos (" + disciplina.getNumeroMatriculados() + ").");
                } else {
                    disciplina.setStatus(EstadoDisciplina.ATIVA);
                    System.out.println("Disciplina '" + disciplina.getNome() + "' ATIVADA com " + disciplina.getNumeroMatriculados() + " alunos.");
                }
            }
        }
        System.out.println("--- PERÍODO ENCERRADO ---\n");
    }

    public void adicionarAluno(Aluno aluno) {
        this.universidade.getAlunos().add(aluno);
    }
    
}
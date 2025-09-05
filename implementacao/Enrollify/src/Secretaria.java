import java.util.ArrayList;
import java.util.List;

public class Secretaria extends Usuario implements java.io.Serializable {
    private Universidade universidade;

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

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario instanceof Aluno) {
            universidade.getAlunos().add((Aluno) usuario);
        } else if (usuario instanceof Professor) {
            universidade.getProfessores().add((Professor) usuario);
        } else if (usuario instanceof Secretaria) {
            universidade.getSecretarios().add((Secretaria) usuario);
        }
        System.out.println("Usuário cadastrado!");
    }

    public void removerUsuario(Usuario usuario) {
        if (usuario instanceof Aluno) {
            universidade.getAlunos().remove(usuario);
        } else if (usuario instanceof Professor) {
            universidade.getProfessores().remove(usuario);
        } else if (usuario instanceof Secretaria) {
            universidade.getSecretarios().remove(usuario);
        }
        System.out.println("Usuário removido!");
    }

    public void atualizarUsuario(Usuario usuario) {
        System.out.println("Usuário atualizado!");
    }

    public Usuario consultarUsuario(String login) {
        for (Aluno aluno : universidade.getAlunos()) {
            if (aluno.getLogin().equals(login)) return aluno;
        }
        for (Professor professor : universidade.getProfessores()) {
            if (professor.getLogin().equals(login)) return professor;
        }
        for (Secretaria secretaria : universidade.getSecretarios()) {
            if (secretaria.getLogin().equals(login)) return secretaria;
        }
        return null;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        universidade.getDisciplinas().add(disciplina);
        System.out.println("Disciplina adicionada!");
    }

    public void removerDisciplina(Disciplina disciplina) {
        universidade.getDisciplinas().remove(disciplina);
        System.out.println("Disciplina removida!");
    }

    public void definirPreRequisito(Disciplina disciplina, Disciplina preRequisito) {
        disciplina.adicionarPreRequisito(preRequisito);
        System.out.println("Pré-requisito definido!");
    }

    public void definirCoRequisito(Disciplina disciplina, Disciplina coRequisito) {
        disciplina.adicionarCoRequisito(coRequisito);
        System.out.println("Co-requisito definido!");
    }

    public void definirCurriculo(Curso curso, List<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas) {
            curso.getCurriculo().adicionarDisciplina(disciplina);
        }
        System.out.println("Currículo definido!");
    }

    public void abrirPeriodoMatricula() {
        System.out.println("Período de matrícula aberto!");
    }

    public void encerrarPeriodoMatricula() {
        for (Disciplina disciplina : universidade.getDisciplinas()) {
            if (disciplina.getNumeroMatriculados() < Disciplina.MIN_ALUNOS) {
                disciplina.setStatus(EstadoDisciplina.CANCELADA);
                System.out.println("Disciplina " + disciplina.getNome() + " cancelada por falta de alunos.");
            }
        }
        System.out.println("Período de matrícula encerrado!");
    }

    public void matricularAlunoEmDisciplina(Aluno aluno, Disciplina disciplina, TipoMatricula tipo) throws MatriculaException {
        if (!universidade.getDisciplinas().contains(disciplina)) {
            throw new MatriculaException("Disciplina não encontrada!");
        }

        for (Disciplina preReq : disciplina.getPreRequisitos()) {
            boolean temPreRequisito = false;
            for (Matricula matricula : aluno.getHistoricoDeMatriculas()) {
                if (matricula.getDisciplina().equals(preReq) &&
                    (matricula.getStatus().equals("APROVADO") || matricula.getStatus().equals("CURSANDO"))) {
                    temPreRequisito = true;
                    break;
                }
            }
            if (!temPreRequisito) {
                throw new MatriculaException("Pré-requisito não atendido: " + preReq.getNome());
            }
        }

        aluno.matricularEmDisciplina(disciplina, tipo);
        notificarSistemaCobrancas(aluno, disciplina);
    }

    private void notificarSistemaCobrancas(Aluno aluno, Disciplina disciplina) {
        System.out.println(">>> NOTIFICAÇÃO SISTEMA DE COBRANÇAS <<<");
        System.out.println("Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
        System.out.println("Disciplina: " + disciplina.getNome());
        System.out.println("Valor: R$ " + (disciplina.getCreditos() * 100));
        System.out.println("=========================================");
    }

    @Override
    public String toString() {
        return "Secretaria: " + nome + " (" + login + ")";
    }
}

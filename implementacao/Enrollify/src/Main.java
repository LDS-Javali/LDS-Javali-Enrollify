import java.util.List;
import java.util.Scanner;

/**
 * Classe principal que executa a Interface de Linha de Comando (CLI) para o
 * Sistema de Matrículas. Gerencia os menus, a interação com o usuário e o
 * ciclo de vida da aplicação (carregar, executar, salvar).
 */
public class Main {

    private static Universidade universidade;
    private static Secretaria secretaria;
    private static final PersistenciaService persistenciaService = new PersistenciaService();
    private static final Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        carregarSistema();
        loopPrincipal();
    }

    /**
     * Carrega os dados da universidade do arquivo de persistência.
     * Se não houver arquivo, cria uma nova instância e um usuário de secretaria padrão.
     */
    private static void carregarSistema() {
        universidade = persistenciaService.carregar();
        if (universidade.getSecretarios().isEmpty()) {
            System.out.println(">>> Primeiro uso: criando usuário da secretaria padrão (login: admin, senha: admin).");
            secretaria = new Secretaria("Admin", "admin", "admin", "admin@uni.com", universidade);
            universidade.getSecretarios().add(secretaria);
        } else {
            secretaria = universidade.getSecretarios().get(0);
        }
    }

    /**
     * Loop principal que controla qual menu exibir com base no usuário logado.
     */
    private static void loopPrincipal() {
        while (true) {
            if (usuarioLogado == null) {
                exibirMenuLogin();
            } else if (usuarioLogado instanceof Secretaria) {
                exibirMenuSecretaria();
            } else if (usuarioLogado instanceof Aluno) {
                exibirMenuAluno();
            }
        }
    }

    /**
     * Exibe o menu inicial para login ou para sair do sistema.
     */
    private static void exibirMenuLogin() {
        System.out.println("\n===== SISTEMA DE MATRÍCULA =====");
        System.out.println("1. Login");
        System.out.println("2. Salvar e Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch (opcao) {
            case 1:
                fazerLogin();
                break;
            case 2:
                persistenciaService.salvar(universidade);
                System.out.println("Sistema finalizado.");
                System.exit(0);
                break;
            default:
                System.out.println("### Opção inválida!");
        }
    }

    /**
     * Solicita credenciais e tenta autenticar um usuário.
     */
    private static void fazerLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Tenta logar como secretaria
        for (Secretaria s : universidade.getSecretarios()) {
            if (s.fazerLogin(login, senha)) {
                usuarioLogado = s;
                System.out.println(">>> Login como Secretaria bem-sucedido! Bem-vindo(a) " + s.getNome() + ".");
                return;
            }
        }

        // Tenta logar como aluno
        for (Aluno a : universidade.getAlunos()) {
            if (a.fazerLogin(login, senha)) {
                usuarioLogado = a;
                System.out.println(">>> Login como Aluno bem-sucedido! Bem-vindo(a) " + a.getNome() + ".");
                return;
            }
        }

        System.err.println("### Login ou senha inválidos.");
    }

    /**
     * Exibe o menu principal de funcionalidades da Secretaria.
     */
    private static void exibirMenuSecretaria() {
        System.out.println("\n--- PAINEL DA SECRETARIA ---");
        System.out.println("1. Gerenciar Cursos e Currículos");
        System.out.println("2. Gerenciar Disciplinas");
        System.out.println("3. Gerenciar Alunos");
        System.out.println("4. Matricular Aluno em Disciplina");
        System.out.println("5. Encerrar Período de Matrícula");
        System.out.println("6. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch (opcao) {
            case 1:
                menuGerenciarCursos();
                break;
            case 2:
                menuGerenciarDisciplinas();
                break;
            case 3:
                menuGerenciarAlunos();
                break;
            case 4:
                matricularAluno();
                break;
            case 5:
                secretaria.encerrarPeriodoMatricula();
                break;
            case 6:
                usuarioLogado = null;
                break;
            default:
                System.out.println("### Opção inválida!");
        }
    }

    private static void menuGerenciarCursos() {
        System.out.println("\n-- Gerenciar Cursos e Currículos --");
        System.out.println("1. Cadastrar Novo Curso");
        System.out.println("2. Listar Cursos");
        System.out.println("3. Adicionar Disciplina a um Currículo");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch(opcao) {
            case 1:
                cadastrarNovoCurso();
                break;
            case 2:
                listarCursos();
                break;
            case 3:
                adicionarDisciplinaAoCurriculo();
                break;
            case 4:
                return;
            default:
                System.err.println("### Opção inválida.");
        }
    }

    private static void menuGerenciarDisciplinas() {
        System.out.println("\n-- Gerenciar Disciplinas --");
        System.out.println("1. Cadastrar Nova Disciplina");
        System.out.println("2. Listar Todas as Disciplinas");
        System.out.println("3. Definir Pré-requisito para Disciplina");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch(opcao) {
            case 1:
                cadastrarNovaDisciplina();
                break;
            case 2:
                listarDisciplinas();
                break;
            case 3:
                definirPreRequisito();
                break;
            case 4:
                return;
            default:
                System.err.println("### Opção inválida.");
        }
    }
    
    private static void menuGerenciarAlunos() {
        System.out.println("\n-- Gerenciar Alunos --");
        System.out.println("1. Cadastrar Novo Aluno");
        System.out.println("2. Listar Alunos Cadastrados");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        
        switch(opcao) {
            case 1:
                cadastrarNovoAluno();
                break;
            case 2:
                listarAlunos();
                break;
            case 3:
                return;
            default:
                System.err.println("### Opção inválida.");
        }
    }
    
    private static void cadastrarNovoCurso() {
        System.out.print("Nome do novo curso: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.err.println("### Nome do curso não pode ser vazio.");
            return;
        }
        secretaria.criarCurso(nome);
        System.out.println(">>> Curso '" + nome + "' cadastrado com sucesso!");
    }

    private static void listarCursos() {
        System.out.println("\n-- Cursos Cadastrados --");
        List<Curso> cursos = universidade.getCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);
            System.out.println((i + 1) + ". " + c.getNome() + " (" + c.getCurriculo().getDisciplinas().size() + " disciplinas no currículo)");
        }
    }

    private static void cadastrarNovaDisciplina() {
        System.out.print("Nome da nova disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Créditos: ");
        int creditos = lerOpcao();
        if (nome.trim().isEmpty() || creditos <= 0) {
            System.err.println("### Dados da disciplina inválidos.");
            return;
        }
        secretaria.criarDisciplina(nome, creditos);
        System.out.println(">>> Disciplina '" + nome + "' cadastrada com sucesso!");
    }

    private static void adicionarDisciplinaAoCurriculo() {
        System.out.println("\n-- Adicionar Disciplina ao Currículo --");
        Curso curso = selecionarCurso();
        if (curso == null) return;

        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        secretaria.adicionarDisciplina(disciplina);
    }
    
    private static void definirPreRequisito() {
        System.out.println("\n-- Definir Pré-requisito --");
        System.out.println("Primeiro, selecione a disciplina que TERÁ o pré-requisito:");
        Disciplina disciplinaPrincipal = selecionarDisciplina();
        if (disciplinaPrincipal == null) return;
        
        System.out.println("\nAgora, selecione a disciplina que SERÁ o pré-requisito:");
        Disciplina disciplinaPreRequisito = selecionarDisciplina();
        if (disciplinaPreRequisito == null) return;
        
        if (disciplinaPrincipal.equals(disciplinaPreRequisito)) {
            System.err.println("### Erro: Uma disciplina não pode ser pré-requisito de si mesma.");
            return;
        }
        
        disciplinaPrincipal.adicionarPreRequisito(disciplinaPreRequisito);
        System.out.println(">>> Sucesso! '" + disciplinaPreRequisito.getNome() + "' agora é pré-requisito para '" + disciplinaPrincipal.getNome() + "'.");
    }

    private static void cadastrarNovoAluno() {
        System.out.println("\n-- Cadastro de Novo Aluno --");
        Curso curso = selecionarCurso();
        if (curso == null) {
            System.err.println("### É necessário ter um curso cadastrado para adicionar um aluno.");
            return;
        }
        
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Aluno novoAluno = new Aluno(nome, login, senha, nome.toLowerCase().replace(" ", ".") + "@uni.com", matricula, curso);
        universidade.getAlunos().add(novoAluno);
        System.out.println(">>> Aluno " + nome + " cadastrado com sucesso!");
    }
    
    private static void matricularAluno() {
        System.out.println("\n-- Matricular Aluno em Disciplina --");
        Aluno aluno = selecionarAluno();
        if (aluno == null) return;
        
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;
        
        try {
            // Simplificação: todas as matrículas são obrigatórias por padrão na interface
            secretaria.matricularAlunoEmDisciplina(aluno, disciplina, TipoMatricula.OBRIGATORIA);
        } catch (MatriculaException e) {
            System.err.println("### ERRO DE MATRÍCULA: " + e.getMessage());
        }
    }
    
    private static void listarAlunos() {
        System.out.println("\n-- Alunos Cadastrados --");
        List<Aluno> alunos = universidade.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println((i + 1) + ". " + alunos.get(i));
        }
    }

    private static void listarDisciplinas() {
        System.out.println("\n-- Disciplinas Disponíveis --");
        List<Disciplina> disciplinas = universidade.getDisciplinas();
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.println((i + 1) + ". " + d.getNome() + " (" + d.getNumeroMatriculados() + " matriculados) - Status: " + d.getStatus());
        }
    }
    
    private static Aluno selecionarAluno() {
        listarAlunos();
        List<Aluno> alunos = universidade.getAlunos();
        if (alunos.isEmpty()) return null;
        System.out.print("Selecione o número do aluno: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < alunos.size()) {
            return alunos.get(idx);
        }
        System.err.println("### Seleção inválida.");
        return null;
    }
    
    private static Disciplina selecionarDisciplina() {
        listarDisciplinas();
        List<Disciplina> disciplinas = universidade.getDisciplinas();
        if (disciplinas.isEmpty()) return null;
        System.out.print("Selecione o número da disciplina: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < disciplinas.size()) {
            return disciplinas.get(idx);
        }
        System.err.println("### Seleção inválida.");
        return null;
    }

    private static Curso selecionarCurso() {
        listarCursos();
        List<Curso> cursos = universidade.getCursos();
        if (cursos.isEmpty()) return null;
        System.out.print("Selecione o número do curso: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < cursos.size()) {
            return cursos.get(idx);
        }
        System.err.println("### Seleção inválida.");
        return null;
    }
    
    private static void exibirMenuAluno() {
        Aluno aluno = (Aluno) usuarioLogado;
        System.out.println("\n--- PAINEL DO ALUNO ("+aluno.getNome()+") ---");
        System.out.println("1. Ver Minhas Matrículas");
        System.out.println("2. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        
        switch(opcao) {
            case 1:
                System.out.println("\n-- Minhas Matrículas --");
                if (aluno.getHistoricoDeMatriculas().isEmpty()) {
                    System.out.println("Você não possui matrículas.");
                    return;
                }
                for (Matricula m : aluno.getHistoricoDeMatriculas()) {
                    System.out.println("- Disciplina: " + m.getDisciplina().getNome() + " | Status: " + m.getStatus());
                }
                break;
            case 2:
                usuarioLogado = null;
                break;
            default:
                System.err.println("### Opção inválida!");
        }
    }
    
    /**
     * Função utilitária para ler a entrada do usuário de forma segura,
     * tratando exceções de formato numérico.
     * @return O número inteiro lido ou -1 em caso de erro.
     */
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
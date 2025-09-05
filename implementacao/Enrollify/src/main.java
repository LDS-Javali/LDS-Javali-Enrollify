import java.util.Scanner;
import java.util.List;

public class Main {

    private static Universidade universidade;
    private static Secretaria secretaria;
    private static PersistenciaService persistenciaService = new PersistenciaService();
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        // Carrega os dados do arquivo ou inicia um novo sistema
        universidade = persistenciaService.carregar();
        
        // Garante que sempre haja uma secretaria (para o primeiro uso)
        if (universidade.getSecretarios().isEmpty()) {
            System.out.println(">>> Primeiro uso: criando usuário da secretaria padrão.");
            secretaria = new Secretaria("Admin", "admin", "admin", "admin@uni.com", universidade);
            universidade.getSecretarios().add(secretaria);
        } else {
            secretaria = universidade.getSecretarios().get(0);
        }

        // Loop principal do programa
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
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void fazerLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Tenta logar como secretaria
        for (Secretaria s : universidade.getSecretarios()) {
            if (s.fazerLogin(login, senha)) {
                usuarioLogado = s;
                System.out.println("Login como Secretaria bem-sucedido! Bem-vindo(a) " + s.getNome() + ".");
                return;
            }
        }
        
        // Tenta logar como aluno
        for (Aluno a : universidade.getAlunos()) {
            if (a.fazerLogin(login, senha)) {
                usuarioLogado = a;
                System.out.println("Login como Aluno bem-sucedido! Bem-vindo(a) " + a.getNome() + ".");
                return;
            }
        }
        
        System.out.println("### Login ou senha inválidos.");
    }

    private static void exibirMenuSecretaria() {
        System.out.println("\n--- PAINEL DA SECRETARIA ---");
        System.out.println("1. Adicionar Disciplina a um Curso");
        System.out.println("2. Cadastrar Novo Aluno");
        System.out.println("3. Matricular Aluno em Disciplina");
        System.out.println("4. Encerrar Período de Matrícula");
        System.out.println("5. Ver Alunos Cadastrados");
        System.out.println("6. Ver Disciplinas Disponíveis");
        System.out.println("7. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1: // Implementar
                break;
            case 2:
                cadastrarNovoAluno();
                break;
            case 3:
                matricularAluno();
                break;
            case 4:
                secretaria.encerrarPeriodoMatricula();
                break;
            case 5:
                verAlunos();
                break;
            case 6:
                verDisciplinas();
                break;
            case 7:
                usuarioLogado = null;
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void cadastrarNovoAluno() {
        System.out.println("\n-- Cadastro de Novo Aluno --");
        // Simplificação: vamos criar um curso se não houver nenhum
        if (universidade.getCursos().isEmpty()) {
            universidade.getCursos().add(new Curso("Engenharia de Computação"));
        }
        Curso cursoPadrao = universidade.getCursos().get(0);
        
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Aluno novoAluno = new Aluno(nome, login, senha, nome.toLowerCase() + "@uni.com", matricula, cursoPadrao);
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
    
    private static void verAlunos() {
        System.out.println("\n-- Alunos Cadastrados --");
        if (universidade.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (int i = 0; i < universidade.getAlunos().size(); i++) {
            System.out.println((i + 1) + ". " + universidade.getAlunos().get(i));
        }
    }
    
    private static void verDisciplinas() {
        System.out.println("\n-- Disciplinas Disponíveis --");
        if (universidade.getDisciplinas().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (int i = 0; i < universidade.getDisciplinas().size(); i++) {
            Disciplina d = universidade.getDisciplinas().get(i);
            System.out.println((i + 1) + ". " + d.getNome() + " (" + d.getNumeroMatriculados() + " matriculados)");
        }
    }
    
    private static Aluno selecionarAluno() {
        verAlunos();
        if (universidade.getAlunos().isEmpty()) return null;
        System.out.print("Selecione o número do aluno: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < universidade.getAlunos().size()) {
            return universidade.getAlunos().get(idx);
        }
        System.out.println("Seleção inválida.");
        return null;
    }
    
    private static Disciplina selecionarDisciplina() {
        verDisciplinas();
        if (universidade.getDisciplinas().isEmpty()) return null;
        System.out.print("Selecione o número da disciplina: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < universidade.getDisciplinas().size()) {
            return universidade.getDisciplinas().get(idx);
        }
        System.out.println("Seleção inválida.");
        return null;
    }

    private static void exibirMenuAluno() {
        Aluno aluno = (Aluno) usuarioLogado;
        System.out.println("\n--- PAINEL DO ALUNO ---");
        System.out.println("1. Ver Minhas Matrículas Atuais");
        System.out.println("2. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        
        switch(opcao) {
            case 1:
                System.out.println("\n-- Minhas Matrículas --");
                if (aluno.getHistoricoDeMatriculas().isEmpty()) {
                    System.out.println("Você não está matriculado em nenhuma disciplina.");
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
                System.out.println("Opção inválida!");
        }
    }
    
    // Função utilitária para ler inteiros de forma segura
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
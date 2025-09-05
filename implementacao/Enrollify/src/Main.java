import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static Universidade universidade;
    private static Secretaria secretaria;
    private static PersistenciaService persistenciaService = new PersistenciaService();
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;
    private static boolean periodoMatriculaAberto = false;

    public static void main(String[] args) {
        universidade = persistenciaService.carregar();

        if (universidade.getSecretarios().isEmpty()) {
            System.out.println("Criando usuário da secretaria padrão.");
            secretaria = new Secretaria("Admin", "admin", "admin", "admin@uni.com", universidade);
            universidade.getSecretarios().add(secretaria);
        } else {
            secretaria = universidade.getSecretarios().get(0);
        }

        if (universidade.getCursos().isEmpty()) {
            universidade.getCursos().add(new Curso("Engenharia de Computação"));
        }

        while (true) {
            if (usuarioLogado == null) {
                exibirMenuLogin();
            } else if (usuarioLogado instanceof Secretaria) {
                exibirMenuSecretaria();
            } else if (usuarioLogado instanceof Aluno) {
                exibirMenuAluno();
            } else if (usuarioLogado instanceof Professor) {
                exibirMenuProfessor();
            }
        }
    }

    private static void exibirMenuLogin() {
        System.out.println("\n===== SISTEMA DE MATRÍCULA =====");
        System.out.println("1. Login");
        System.out.println("2. Recuperar Senha");
        System.out.println("3. Salvar e Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch (opcao) {
            case 1:
                fazerLogin();
                break;
            case 2:
                recuperarSenha();
                break;
            case 3:
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

        for (Secretaria s : universidade.getSecretarios()) {
            if (s.fazerLogin(login, senha)) {
                usuarioLogado = s;
                System.out.println("Login como Secretaria bem-sucedido! Bem-vindo(a) " + s.getNome() + ".");
                return;
            }
        }

        for (Aluno a : universidade.getAlunos()) {
            if (a.fazerLogin(login, senha)) {
                usuarioLogado = a;
                System.out.println("Login como Aluno bem-sucedido! Bem-vindo(a) " + a.getNome() + ".");
                return;
            }
        }

        for (Professor p : universidade.getProfessores()) {
            if (p.fazerLogin(login, senha)) {
                usuarioLogado = p;
                System.out.println("Login como Professor bem-sucedido! Bem-vindo(a) " + p.getNome() + ".");
                return;
            }
        }

        System.out.println("Login ou senha inválidos.");
    }

    private static void recuperarSenha() {
        System.out.print("Digite seu login: ");
        String login = scanner.nextLine();

        Usuario usuario = secretaria.consultarUsuario(login);
        if (usuario != null) {
            usuario.recuperarSenha();
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private static void exibirMenuSecretaria() {
        System.out.println("\n--- PAINEL DA SECRETARIA ---");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Remover Usuário");
        System.out.println("3. Atualizar Usuário");
        System.out.println("4. Consultar Usuário");
        System.out.println("5. Adicionar Disciplina");
        System.out.println("6. Remover Disciplina");
        System.out.println("7. Definir Pré-requisito");
        System.out.println("8. Definir Co-requisito");
        System.out.println("9. Definir Currículo");
        System.out.println("10. Abrir Período de Matrícula");
        System.out.println("11. Encerrar Período de Matrícula");
        System.out.println("12. Matricular Aluno em Disciplina");
        System.out.println("13. Ver Relatórios");
        System.out.println("14. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch (opcao) {
            case 1: cadastrarUsuario(); break;
            case 2: removerUsuario(); break;
            case 3: atualizarUsuario(); break;
            case 4: consultarUsuario(); break;
            case 5: adicionarDisciplina(); break;
            case 6: removerDisciplina(); break;
            case 7: definirPreRequisito(); break;
            case 8: definirCoRequisito(); break;
            case 9: definirCurriculo(); break;
            case 10: abrirPeriodoMatricula(); break;
            case 11: encerrarPeriodoMatricula(); break;
            case 12: matricularAluno(); break;
            case 13: verRelatorios(); break;
            case 14: usuarioLogado = null; break;
            default: System.out.println("Opção inválida!");
        }
    }

    private static void exibirMenuAluno() {
        Aluno aluno = (Aluno) usuarioLogado;
        System.out.println("\n--- PAINEL DO ALUNO ---");
        System.out.println("1. Matricular em Disciplina");
        System.out.println("2. Matricular em Disciplina Obrigatória");
        System.out.println("3. Matricular em Disciplina Optativa");
        System.out.println("4. Cancelar Matrícula");
        System.out.println("5. Ver Minhas Matrículas");
        System.out.println("6. Ver Disciplinas Disponíveis");
        System.out.println("7. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch(opcao) {
            case 1: matricularEmDisciplina(); break;
            case 2: matricularEmDisciplinaObrigatoria(); break;
            case 3: matricularEmDisciplinaOptativa(); break;
            case 4: cancelarMatricula(); break;
            case 5: verMinhasMatriculas(); break;
            case 6: verDisciplinasDisponiveis(); break;
            case 7: usuarioLogado = null; break;
            default: System.out.println("Opção inválida!");
        }
    }

    private static void exibirMenuProfessor() {
        Professor professor = (Professor) usuarioLogado;
        System.out.println("\n--- PAINEL DO PROFESSOR ---");
        System.out.println("1. Consultar Turmas Atribuídas");
        System.out.println("2. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();

        switch(opcao) {
            case 1: consultarTurmasAtribuidas(); break;
            case 2: usuarioLogado = null; break;
            default: System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("\n-- Cadastro de Usuário --");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        System.out.println("3. Secretaria");
        System.out.print("Tipo de usuário: ");
        int tipo = lerOpcao();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario novoUsuario = null;

        switch (tipo) {
            case 1:
                System.out.print("Matrícula: ");
                String matricula = scanner.nextLine();
                Curso curso = universidade.getCursos().get(0);
                novoUsuario = new Aluno(nome, login, senha, email, matricula, curso);
                break;
            case 2:
                novoUsuario = new Professor(nome, login, senha, email);
                break;
            case 3:
                novoUsuario = new Secretaria(nome, login, senha, email, universidade);
                break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }

        secretaria.cadastrarUsuario(novoUsuario);
    }

    private static void removerUsuario() {
        System.out.print("Login do usuário a ser removido: ");
        String login = scanner.nextLine();

        Usuario usuario = secretaria.consultarUsuario(login);
        if (usuario != null) {
            secretaria.removerUsuario(usuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private static void atualizarUsuario() {
        System.out.print("Login do usuário a ser atualizado: ");
        String login = scanner.nextLine();

        Usuario usuario = secretaria.consultarUsuario(login);
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
            System.out.print("Novo nome (ou Enter para manter): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) usuario.setNome(novoNome);

            System.out.print("Novo email (ou Enter para manter): ");
            String novoEmail = scanner.nextLine();
            if (!novoEmail.isEmpty()) usuario.setEmail(novoEmail);

            secretaria.atualizarUsuario(usuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private static void consultarUsuario() {
        System.out.print("Login do usuário: ");
        String login = scanner.nextLine();

        Usuario usuario = secretaria.consultarUsuario(login);
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private static void adicionarDisciplina() {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Créditos: ");
        int creditos = lerOpcao();

        Disciplina disciplina = new Disciplina(nome, creditos);
        secretaria.adicionarDisciplina(disciplina);
    }

    private static void removerDisciplina() {
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina != null) {
            secretaria.removerDisciplina(disciplina);
        }
    }

    private static void definirPreRequisito() {
        System.out.println("Selecione a disciplina:");
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        System.out.println("Selecione o pré-requisito:");
        Disciplina preRequisito = selecionarDisciplina();
        if (preRequisito == null) return;

        secretaria.definirPreRequisito(disciplina, preRequisito);
    }

    private static void definirCoRequisito() {
        System.out.println("Selecione a disciplina:");
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        System.out.println("Selecione o co-requisito:");
        Disciplina coRequisito = selecionarDisciplina();
        if (coRequisito == null) return;

        secretaria.definirCoRequisito(disciplina, coRequisito);
    }

    private static void definirCurriculo() {
        Curso curso = universidade.getCursos().get(0);
        List<Disciplina> disciplinas = new ArrayList<>();

        System.out.println("Adicione disciplinas ao currículo (digite 'fim' para terminar):");
        while (true) {
            Disciplina disciplina = selecionarDisciplina();
            if (disciplina == null) break;
            disciplinas.add(disciplina);
            System.out.println("Disciplina adicionada. Adicionar outra? (s/n)");
            if (scanner.nextLine().toLowerCase().charAt(0) != 's') break;
        }

        secretaria.definirCurriculo(curso, disciplinas);
    }

    private static void abrirPeriodoMatricula() {
        secretaria.abrirPeriodoMatricula();
        periodoMatriculaAberto = true;
    }

    private static void encerrarPeriodoMatricula() {
        secretaria.encerrarPeriodoMatricula();
        periodoMatriculaAberto = false;
    }

    private static void matricularAluno() {
        Aluno aluno = selecionarAluno();
        if (aluno == null) return;

        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        System.out.println("Tipo de matrícula:");
        System.out.println("1. Obrigatória");
        System.out.println("2. Optativa");
        int tipo = lerOpcao();

        TipoMatricula tipoMatricula = (tipo == 1) ? TipoMatricula.OBRIGATORIA : TipoMatricula.OPCIONAL;

        try {
            secretaria.matricularAlunoEmDisciplina(aluno, disciplina, tipoMatricula);
        } catch (MatriculaException e) {
            System.err.println("ERRO DE MATRÍCULA: " + e.getMessage());
        }
    }

    private static void verRelatorios() {
        System.out.println("\n-- RELATÓRIOS --");
        System.out.println("Alunos cadastrados: " + universidade.getAlunos().size());
        System.out.println("Professores cadastrados: " + universidade.getProfessores().size());
        System.out.println("Disciplinas cadastradas: " + universidade.getDisciplinas().size());
        System.out.println("Cursos cadastrados: " + universidade.getCursos().size());

        System.out.println("\nDisciplinas com matrículas:");
        for (Disciplina d : universidade.getDisciplinas()) {
            System.out.println("- " + d.getNome() + ": " + d.getNumeroMatriculados() + " alunos");
        }
    }

    private static void matricularEmDisciplina() {
        if (!periodoMatriculaAberto) {
            System.out.println("Período de matrícula fechado!");
            return;
        }

        Aluno aluno = (Aluno) usuarioLogado;
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        System.out.println("Tipo de matrícula:");
        System.out.println("1. Obrigatória");
        System.out.println("2. Optativa");
        int tipo = lerOpcao();

        TipoMatricula tipoMatricula = (tipo == 1) ? TipoMatricula.OBRIGATORIA : TipoMatricula.OPCIONAL;

        try {
            aluno.matricularEmDisciplina(disciplina, tipoMatricula);
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private static void matricularEmDisciplinaObrigatoria() {
        if (!periodoMatriculaAberto) {
            System.out.println("Período de matrícula fechado!");
            return;
        }

        Aluno aluno = (Aluno) usuarioLogado;
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        try {
            aluno.matricularEmDisciplina(disciplina, TipoMatricula.OBRIGATORIA);
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private static void matricularEmDisciplinaOptativa() {
        if (!periodoMatriculaAberto) {
            System.out.println("Período de matrícula fechado!");
            return;
        }

        Aluno aluno = (Aluno) usuarioLogado;
        Disciplina disciplina = selecionarDisciplina();
        if (disciplina == null) return;

        try {
            aluno.matricularEmDisciplina(disciplina, TipoMatricula.OPCIONAL);
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private static void cancelarMatricula() {
        Aluno aluno = (Aluno) usuarioLogado;
        List<Matricula> matriculasAtivas = new ArrayList<>();

        for (Matricula m : aluno.getHistoricoDeMatriculas()) {
            if (m.getStatus().equals("MATRICULADO") || m.getStatus().equals("CURSANDO")) {
                matriculasAtivas.add(m);
            }
        }

        if (matriculasAtivas.isEmpty()) {
            System.out.println("Você não possui matrículas ativas.");
            return;
        }

        System.out.println("Selecione a matrícula a ser cancelada:");
        for (int i = 0; i < matriculasAtivas.size(); i++) {
            Matricula m = matriculasAtivas.get(i);
            System.out.println((i + 1) + ". " + m.getDisciplina().getNome() + " (" + m.getTipo() + ")");
        }

        int opcao = lerOpcao() - 1;
        if (opcao >= 0 && opcao < matriculasAtivas.size()) {
            try {
                aluno.cancelarMatricula(matriculasAtivas.get(opcao));
            } catch (Exception e) {
                System.err.println("ERRO: " + e.getMessage());
            }
        } else {
            System.out.println("Seleção inválida.");
        }
    }

    private static void verMinhasMatriculas() {
        Aluno aluno = (Aluno) usuarioLogado;
        List<Matricula> matriculas = aluno.getHistoricoDeMatriculas();

        if (matriculas.isEmpty()) {
            System.out.println("Você não possui matrículas.");
            return;
        }

        System.out.println("\n-- Suas Matrículas --");
        for (Matricula m : matriculas) {
            System.out.println("- " + m.getDisciplina().getNome() +
                             " | Tipo: " + m.getTipo() +
                             " | Status: " + m.getStatus());
        }
    }

    private static void verDisciplinasDisponiveis() {
        System.out.println("\n-- Disciplinas Disponíveis --");
        if (universidade.getDisciplinas().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (int i = 0; i < universidade.getDisciplinas().size(); i++) {
            Disciplina d = universidade.getDisciplinas().get(i);
            System.out.println((i + 1) + ". " + d.getNome() +
                             " (" + d.getCreditos() + " créditos, " +
                             d.getNumeroMatriculados() + " matriculados)");
        }
    }

    private static void consultarTurmasAtribuidas() {
        Professor professor = (Professor) usuarioLogado;
        List<Turma> turmas = professor.consultarTurmasAtribuidas();

        if (turmas.isEmpty()) {
            System.out.println("Você não possui turmas atribuídas.");
            return;
        }

        System.out.println("\n-- Suas Turmas Atribuídas --");
        for (Turma turma : turmas) {
            System.out.println("- " + turma);
        }
    }

    private static Aluno selecionarAluno() {
        System.out.println("\n-- Alunos Cadastrados --");
        if (universidade.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return null;
        }
        for (int i = 0; i < universidade.getAlunos().size(); i++) {
            System.out.println((i + 1) + ". " + universidade.getAlunos().get(i));
        }
        System.out.print("Selecione o número do aluno: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < universidade.getAlunos().size()) {
            return universidade.getAlunos().get(idx);
        }
        System.out.println("Seleção inválida.");
        return null;
    }

    private static Disciplina selecionarDisciplina() {
        verDisciplinasDisponiveis();
        if (universidade.getDisciplinas().isEmpty()) return null;
        System.out.print("Selecione o número da disciplina: ");
        int idx = lerOpcao() - 1;
        if (idx >= 0 && idx < universidade.getDisciplinas().size()) {
            return universidade.getDisciplinas().get(idx);
        }
        System.out.println("Seleção inválida.");
        return null;
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

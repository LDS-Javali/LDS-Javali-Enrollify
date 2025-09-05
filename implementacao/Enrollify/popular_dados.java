import java.util.ArrayList;
import java.util.List;

public class popular_dados {

    public static void main(String[] args) {
        System.out.println("=== POPULANDO DADOS DE EXEMPLO ===");

        Universidade universidade = new Universidade();

        Secretaria secretaria = new Secretaria("Admin", "admin", "admin", "admin@uni.com", universidade);
        universidade.getSecretarios().add(secretaria);

        Curso cursoEng = new Curso("Engenharia de Computação");
        Curso cursoAdm = new Curso("Administração");
        universidade.getCursos().add(cursoEng);
        universidade.getCursos().add(cursoAdm);
        System.out.println("✓ Cursos criados");

        Disciplina calcI = new Disciplina("Cálculo I", 4);
        Disciplina fisicaI = new Disciplina("Física I", 4);
        Disciplina progI = new Disciplina("Programação I", 4);
        Disciplina algoritmos = new Disciplina("Algoritmos e Estruturas de Dados", 4);
        Disciplina bd = new Disciplina("Banco de Dados", 4);
        Disciplina engSoft = new Disciplina("Engenharia de Software", 4);
        Disciplina so = new Disciplina("Sistemas Operacionais", 4);
        Disciplina redes = new Disciplina("Redes de Computadores", 4);
        Disciplina ia = new Disciplina("Inteligência Artificial", 4);
        Disciplina projetoFinal = new Disciplina("Projeto Final", 6);
        Disciplina matFin = new Disciplina("Matemática Financeira", 4);
        Disciplina contabilidade = new Disciplina("Contabilidade", 4);
        Disciplina marketing = new Disciplina("Marketing", 4);
        Disciplina gestaoPessoas = new Disciplina("Gestão de Pessoas", 4);
        Disciplina economia = new Disciplina("Economia", 4);

        universidade.getDisciplinas().add(calcI);
        universidade.getDisciplinas().add(fisicaI);
        universidade.getDisciplinas().add(progI);
        universidade.getDisciplinas().add(algoritmos);
        universidade.getDisciplinas().add(bd);
        universidade.getDisciplinas().add(engSoft);
        universidade.getDisciplinas().add(so);
        universidade.getDisciplinas().add(redes);
        universidade.getDisciplinas().add(ia);
        universidade.getDisciplinas().add(projetoFinal);
        universidade.getDisciplinas().add(matFin);
        universidade.getDisciplinas().add(contabilidade);
        universidade.getDisciplinas().add(marketing);
        universidade.getDisciplinas().add(gestaoPessoas);
        universidade.getDisciplinas().add(economia);
        System.out.println("✓ Disciplinas criadas");

        algoritmos.adicionarPreRequisito(progI);
        bd.adicionarPreRequisito(algoritmos);
        engSoft.adicionarPreRequisito(bd);
        engSoft.adicionarPreRequisito(algoritmos);
        so.adicionarPreRequisito(algoritmos);
        redes.adicionarPreRequisito(so);
        ia.adicionarPreRequisito(algoritmos);
        ia.adicionarPreRequisito(bd);
        projetoFinal.adicionarPreRequisito(engSoft);
        projetoFinal.adicionarPreRequisito(bd);
        System.out.println("✓ Pré-requisitos definidos");

        cursoEng.getCurriculo().adicionarDisciplina(calcI);
        cursoEng.getCurriculo().adicionarDisciplina(fisicaI);
        cursoEng.getCurriculo().adicionarDisciplina(progI);
        cursoEng.getCurriculo().adicionarDisciplina(algoritmos);
        cursoEng.getCurriculo().adicionarDisciplina(bd);
        cursoEng.getCurriculo().adicionarDisciplina(engSoft);
        cursoEng.getCurriculo().adicionarDisciplina(so);
        cursoEng.getCurriculo().adicionarDisciplina(redes);
        cursoEng.getCurriculo().adicionarDisciplina(ia);
        cursoEng.getCurriculo().adicionarDisciplina(projetoFinal);

        cursoAdm.getCurriculo().adicionarDisciplina(matFin);
        cursoAdm.getCurriculo().adicionarDisciplina(contabilidade);
        cursoAdm.getCurriculo().adicionarDisciplina(marketing);
        cursoAdm.getCurriculo().adicionarDisciplina(gestaoPessoas);
        cursoAdm.getCurriculo().adicionarDisciplina(economia);
        System.out.println("✓ Currículos definidos");

        Professor prof1 = new Professor("Dr. João Silva", "joao.silva", "123456", "joao@uni.com");
        Professor prof2 = new Professor("Dra. Maria Santos", "maria.santos", "123456", "maria@uni.com");
        Professor prof3 = new Professor("Dr. Pedro Costa", "pedro.costa", "123456", "pedro@uni.com");
        Professor prof4 = new Professor("Dra. Ana Oliveira", "ana.oliveira", "123456", "ana@uni.com");

        universidade.getProfessores().add(prof1);
        universidade.getProfessores().add(prof2);
        universidade.getProfessores().add(prof3);
        universidade.getProfessores().add(prof4);
        System.out.println("✓ Professores criados");

        progI.setProfessor(prof1);
        algoritmos.setProfessor(prof1);
        bd.setProfessor(prof2);
        engSoft.setProfessor(prof2);
        so.setProfessor(prof3);
        redes.setProfessor(prof3);
        ia.setProfessor(prof4);
        projetoFinal.setProfessor(prof4);
        System.out.println("✓ Disciplinas atribuídas aos professores");

        Aluno aluno1 = new Aluno("Carlos Eduardo", "carlos.eduardo", "123456", "carlos@uni.com", "2023001", cursoEng);
        Aluno aluno2 = new Aluno("Fernanda Lima", "fernanda.lima", "123456", "fernanda@uni.com", "2023002", cursoEng);
        Aluno aluno3 = new Aluno("Roberto Souza", "roberto.souza", "123456", "roberto@uni.com", "2023003", cursoEng);
        Aluno aluno4 = new Aluno("Juliana Costa", "juliana.costa", "123456", "juliana@uni.com", "2023004", cursoAdm);
        Aluno aluno5 = new Aluno("Lucas Pereira", "lucas.pereira", "123456", "lucas@uni.com", "2023005", cursoAdm);

        universidade.getAlunos().add(aluno1);
        universidade.getAlunos().add(aluno2);
        universidade.getAlunos().add(aluno3);
        universidade.getAlunos().add(aluno4);
        universidade.getAlunos().add(aluno5);
        System.out.println("✓ Alunos criados");

        Matricula mat1 = new Matricula(aluno1, calcI, TipoMatricula.OBRIGATORIA);
        mat1.setStatus("APROVADO");
        aluno1.getHistoricoDeMatriculas().add(mat1);
        calcI.adicionarMatricula(mat1);

        Matricula mat2 = new Matricula(aluno1, fisicaI, TipoMatricula.OBRIGATORIA);
        mat2.setStatus("APROVADO");
        aluno1.getHistoricoDeMatriculas().add(mat2);
        fisicaI.adicionarMatricula(mat2);

        Matricula mat3 = new Matricula(aluno2, progI, TipoMatricula.OBRIGATORIA);
        mat3.setStatus("APROVADO");
        aluno2.getHistoricoDeMatriculas().add(mat3);
        progI.adicionarMatricula(mat3);

        Matricula mat4 = new Matricula(aluno3, calcI, TipoMatricula.OBRIGATORIA);
        mat4.setStatus("APROVADO");
        aluno3.getHistoricoDeMatriculas().add(mat4);
        calcI.adicionarMatricula(mat4);

        System.out.println("✓ Histórico de matrículas criado");

        PersistenciaService persistencia = new PersistenciaService();
        persistencia.salvar(universidade);

        System.out.println("\n=== DADOS POPULADOS COM SUCESSO! ===");
        System.out.println("Cursos: " + universidade.getCursos().size());
        System.out.println("Disciplinas: " + universidade.getDisciplinas().size());
        System.out.println("Professores: " + universidade.getProfessores().size());
        System.out.println("Alunos: " + universidade.getAlunos().size());
        System.out.println("\nCredenciais de teste:");
        System.out.println("Secretaria: admin/admin");
        System.out.println("Alunos: carlos.eduardo/123456, fernanda.lima/123456, roberto.souza/123456");
        System.out.println("Professores: joao.silva/123456, maria.santos/123456, pedro.costa/123456, ana.oliveira/123456");
    }
}

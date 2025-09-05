import java.io.*;

public class PersistenciaService {

    private static final String NOME_ARQUIVO = "universidade.dat";

    /**
     * Salva o estado atual do objeto Universidade em um arquivo binário.
     * @param universidade O objeto contendo todos os dados do sistema.
     */
    public void salvar(Universidade universidade) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(universidade);
            System.out.println(">>> Dados salvos com sucesso em " + NOME_ARQUIVO);
        } catch (IOException e) {
            System.err.println("### Erro ao salvar os dados: " + e.getMessage());
        }
    }

    /**
     * Carrega o estado do objeto Universidade a partir de um arquivo.
     * Se o arquivo não existir, retorna um novo objeto Universidade.
     * @return O objeto Universidade com os dados carregados ou um novo objeto.
     */
    public Universidade carregar() {
        File arquivo = new File(NOME_ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                Universidade universidade = (Universidade) ois.readObject();
                System.out.println(">>> Dados carregados com sucesso de " + NOME_ARQUIVO);
                return universidade;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("### Erro ao carregar os dados, iniciando um novo sistema: " + e.getMessage());
                return new Universidade(); // Retorna um novo objeto em caso de erro
            }
        }
        System.out.println(">>> Nenhum arquivo de dados encontrado. Iniciando um novo sistema.");
        return new Universidade(); // Retorna um novo objeto se o arquivo não existe
    }
}
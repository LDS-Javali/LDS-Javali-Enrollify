import java.io.*;

public class PersistenciaService {
    private static final String ARQUIVO = "build/data/universidade.dat";

    public void salvar(Universidade universidade) {
        try {
            new File(ARQUIVO).getParentFile().mkdirs();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
                oos.writeObject(universidade);
                System.out.println("Dados salvos em " + ARQUIVO);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public Universidade carregar() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                Universidade universidade = (Universidade) ois.readObject();
                System.out.println("Dados carregados de " + ARQUIVO);
                return universidade;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar, iniciando novo sistema: " + e.getMessage());
                return new Universidade();
            }
        }
        System.out.println("Arquivo não encontrado, iniciando novo sistema.");
        return new Universidade();
    }
}

public abstract class Usuario implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    protected static long contadorId = 0;
    protected Long idUsuario;
    protected String nome;
    protected String login;
    protected String senha;
    protected String email;

    public Usuario(String nome, String login, String senha, String email) {
        this.idUsuario = ++contadorId;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public boolean fazerLogin(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public void recuperarSenha() {
        System.out.println("Instruções de recuperação de senha enviadas para " + this.email);
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + idUsuario + ", Nome: " + nome;
    }
}
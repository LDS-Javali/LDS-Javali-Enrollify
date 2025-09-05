public abstract class Usuario implements java.io.Serializable {
    private static long contadorId = 0;

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
        System.out.println("Solicitação de recuperação enviada para: " + email);
    }

    public Long getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return nome + " (" + login + ")";
    }
}

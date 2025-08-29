public abstract class Usuario {

    protected Long idUsuario;
    protected String nome;
    protected String login;
    protected String senha;
    protected String email;

    public boolean fazerLogin() {
        // Lógica
        return false;
    }

    public void recuperarSenha() {
        // Lógica
        System.out.println("Método recuperarSenha() chamado.");
    }
}
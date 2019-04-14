import java.net.Socket;

public class Cliente {
    private String nome;
    private String userName;
    private Socket socket;

    public Cliente (String nome, String userName, Socket socket) {
        this.nome = nome;
        this.userName = userName;
        this.socket = socket;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNome() {
        return nome;
    }

    public String getUserName() {
        return userName;
    }

    public Socket getSocket() {
        return socket;
    }
}
import java.net.Socket;

public class Cliente {
    private String nome;
    private Socket socket;

    public Cliente (String nome, Socket socket) {
        this.nome = nome;
        this.socket = socket;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNome() {
        return nome;
    }

    public Socket getSocket() {
        return socket;
    }
}
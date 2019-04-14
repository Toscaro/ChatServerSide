import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceptorDeSockets implements Runnable {

    /** Socket é a conexao do cliente */
    private Socket socket;
    /** PrintWriter envia informação (String) para o cliente */
    private PrintWriter messageWriter;
    /** BufferedReader recebe a informação (objeto usuario) do cliente */
    private ObjectInputStream objectReader;

    /**
     * @param socket recebe a conexao de um novo cliente.
     * @throws Exception caso nao recebermos conseguirmos criar o output ou input stream.
     */
    ReceptorDeSockets(Socket socket) throws Exception {
        this.socket = socket;
        messageWriter = new PrintWriter(socket.getOutputStream());
        objectReader = new ObjectInputStream(socket.getInputStream());
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Usuario usuario = (Usuario) objectReader.readObject();
            System.out.println(String.format("Nome: %s, socket: %s", usuario.getNome(), usuario.getSocket()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

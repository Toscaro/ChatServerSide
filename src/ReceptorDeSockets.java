import com.example.socketsproject.Usuario;

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
     * @throws Exception caso nao conseguimos criar o output ou input stream.
     */
    ReceptorDeSockets(Socket socket) throws Exception {
        this.socket = socket;
        messageWriter = new PrintWriter(socket.getOutputStream(), true);
        objectReader = new ObjectInputStream(socket.getInputStream());
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            //IMPORTANTE, quando receber um objeto do android e colocar no server OBRIGATORIAMENTE precisam estar com o mesmo PACKAGE NAME e mesmo nome na CLASS.
            Usuario usuario = (Usuario) objectReader.readObject();
            Cliente cliente = new Cliente(usuario.getNome(), usuario.getUserName(), socket);
            System.out.println(String.format("Nome: %s, nickName: %s, socket: %s", cliente.getNome(), cliente.getUserName(), cliente.getSocket()));

            //TODO checar todos usuarios e ver se algum ja possui o mesmo nick name e permitir ou nao acessar o app com esse usuario (isUsernameFree).
            messageWriter.print(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

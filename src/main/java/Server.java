import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int mPortNumber;

    public static void main(String[] args) {

        try {
            new Server(Constantes.SERVER_PORT).init();

        } catch (Exception e) {

            System.out.println("Couldn't open the server... ");
            e.printStackTrace();
        }
    }

    /**
     * Construtor
     *
     * @param portNumber numero da porta para iniciar o servidor
     */
    private Server(int portNumber) {
        this.mPortNumber = portNumber;
    }

    private void init() throws IOException {
        ServerSocket server = new ServerSocket(mPortNumber);
        System.out.println("Servidor ligado na porta: " + server.getLocalPort());

        //noinspection InfiniteLoopStatement
        while (true) {
            Socket socket = server.accept();
            System.out.println("Novo cliente conectado");

            new ReceptorDeSockets(socket);
        }

    }
}

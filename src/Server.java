import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(Constantes.SERVER_PORT);
            System.out.println("Server ligado na porta: " + serverSocket.getLocalPort());

            //noinspection InfiniteLoopStatement
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado");
                new ReceptorDeSockets(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

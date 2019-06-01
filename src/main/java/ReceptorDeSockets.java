import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class ReceptorDeSockets {

    private Socket mSocket;

    /**
     * @param socket recebe a conexao de um novo cliente.
     * @throws IOException caso nao conseguimos criar o output ou input stream baseado na conexao.
     */
    ReceptorDeSockets(Socket socket) throws IOException {
        this.mSocket = socket;
        new Thread(new MessageHandler(mSocket.getInputStream())).start();
    }

    public class MessageHandler implements Runnable {

        private InputStream mInputCliente;

        MessageHandler(InputStream inputStream) {
            this.mInputCliente = inputStream;
        }

        @Override
        public void run() {
            System.out.println("input cliente");
            Scanner scanner = new Scanner(mInputCliente);
            while (scanner.hasNextLine()) {
                new ReceptorDeJson(scanner.nextLine(), mSocket).init();
            }

        }
    }
}

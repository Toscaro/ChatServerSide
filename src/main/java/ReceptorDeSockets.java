import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ReceptorDeSockets {

    private List<PrintStream> mClientes = new ArrayList<PrintStream>();

    /**
     * @param socket recebe a conexao de um novo cliente.
     * @throws IOException caso nao conseguimos criar o output ou input stream baseado na conexao.
     */
    ReceptorDeSockets(Socket socket) throws IOException {
        mClientes.add(new PrintStream(socket.getOutputStream()));
        new Thread(new MessageHandler(socket.getInputStream(), this)).start();
    }

    private void distribuiMensagem(String msg) {

        System.out.println("distribui mensagem");
        for (PrintStream cliente : mClientes) {
            System.out.println("mensagem: " + msg);
            cliente.println(msg);
        }
    }

    public class MessageHandler implements Runnable {

        private InputStream mInputCliente;
        private ReceptorDeSockets mReceptorDeSockets;

        public MessageHandler(InputStream inputStream, ReceptorDeSockets receptorDeSockets) {
            this.mInputCliente = inputStream;
            this.mReceptorDeSockets = receptorDeSockets;
        }

        @Override
        public void run() {
            System.out.println("input cliente");
            Scanner scanner = new Scanner(mInputCliente);
            while (scanner.hasNextLine()) {
                mReceptorDeSockets.distribuiMensagem(scanner.nextLine());
            }

            scanner.close();
        }
    }
}

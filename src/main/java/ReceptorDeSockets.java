import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class ReceptorDeSockets extends Thread {

    private Socket mSocket;
    private BufferedReader mInputCliente;

    /**
     * @param socket recebe a conexao de um novo cliente.
     * @throws IOException caso nao conseguimos criar o output ou input stream baseado na conexao.
     */
    ReceptorDeSockets(Socket socket) throws IOException {
        this.mSocket = socket;
        mInputCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("input cliente");

        System.out.println("While");
        while (true) {
            System.out.println("Try");
            try {
                System.out.println("Read message from client");

                String s = mInputCliente.readLine();

                if (s != null && !s.isEmpty()) {
                    //Print message from client
                    System.out.println("Message from client: " + s);
                    new ReceptorDeJson(s, mSocket).init();
                } else {
                    System.out.println("null or empty");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Read failed: " + e.toString());
            }

        }
    }
}

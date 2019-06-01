import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ReceptorDeJson {

    private String mJson;
    private Socket mLastConnectedSocket;
    private HashMap<String, Socket> mClientes = new HashMap<String, Socket>();


    ReceptorDeJson(String json, Socket socket)  {
        this.mJson = json;
        this.mLastConnectedSocket = socket;
    }

    void init() {
        JSONObject jsonObject = new JSONObject(mJson);

        System.out.println("Novo json recebido: " + jsonObject.toString());
        String type = jsonObject.optString("type");

        if (type.equals("mensagem")) {

            try {
                enviarMensagem(jsonObject.optString("mensagem"));

            } catch (IOException e) {
                System.out.println("Falha ao enviar mensagem");
                e.printStackTrace();
            }

        } else if (type.equals("login")) {
            confirmarLogin(jsonObject.optString("nome"));

        }
    }

    private void confirmarLogin(String nome) {
        boolean deveConectar = false;
        if (!mClientes.containsKey(nome)) {
            mClientes.put(nome, mLastConnectedSocket);
            deveConectar = true;
        }

            try {
                System.out.println("retornar");
                retornarInfoAoCliente(nome, deveConectar);

            } catch (IOException e) {
                System.out.println("Falha ao enviar info ao usuario");
                e.printStackTrace();
            }
    }


    private void enviarMensagem(String msg) throws IOException{
        if (msg != null && !msg.isEmpty()) {
            System.out.println("distribui mensagem: " + msg + "Clientes conectados: " + mClientes.size());

            for (Map.Entry<String, Socket> cliente : mClientes.entrySet()) {
                Socket value = cliente.getValue();
                new PrintStream(value.getOutputStream()).println(criarMensagem(msg, cliente.getKey()));
            }
        }
    }

    private String criarMensagem(String msg, String nomeCliente){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", "mensagem")
        .put("nome_cliente", nomeCliente)
        .put("mensagem", msg);

        return jsonObject.toString();
    }

    private void retornarInfoAoCliente(String nomeCliente, boolean deveConectar) throws IOException {
        new PrintStream(mClientes.get(nomeCliente).getOutputStream())
                .println(
                        new JSONObject()
                                .put("type", "login")
                                .put("login", deveConectar));
    }

}

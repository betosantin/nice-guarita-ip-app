package robertosantin.nice.guarita.ip.nice_guarita_ip_app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkTask extends AsyncTask<NetworkTaskParams, Void, Boolean> {

    private static String enderecoDisp = "192.168.100.68";
    private static int portaDisp = 9000;
    private static String codigoAcesso = "";
    private static int timeout = 4;

    @Override
    protected Boolean doInBackground(NetworkTaskParams... params) {

        if (params.length == 0) {
            return false;
        }

        NetworkTaskParams taskParams = params[0];

        return acionaRele(taskParams.tipoDisp, taskParams.numDisp, taskParams.rele, taskParams.geraEvt);
    }

    private boolean acionaRele(int tipoDisp, int numDisp, int rele, int geraEvt) {
        tipoDisp = Math.min(7, Math.max(1, tipoDisp));
        numDisp = Math.min(7, Math.max(0, numDisp));
        rele = Math.min(4, Math.max(1, rele));
        geraEvt = (geraEvt == 0) ? 0 : 1;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(enderecoDisp, portaDisp), timeout * 1000);

            if (!socket.isConnected()) {
                return false;
            }

            if (!codigoAcesso.isEmpty()) {
                OutputStream os = socket.getOutputStream();
                os.write(codigoAcesso.getBytes());
                os.flush();
                socket.getInputStream().read(new byte[12]);
            }

            String message = "000d" + String.format("%02d", tipoDisp) + String.format("%02d", numDisp)
                    + String.format("%02d", rele) + String.format("%02d", geraEvt);
            String checksum = calculaChecksum(message);

            byte[] messageBytes = hexStringToByteArray(checksum);
            OutputStream os = socket.getOutputStream();
            os.write(messageBytes);
            os.flush();
            socket.getInputStream().read(new byte[2]);

            return true;
        } catch (IOException e) {
            Log.e("NetworkTask", "Erro na operação de rede", e);
        }

        return false;
    }

    // Restante do código Java existente aqui...

    private String calculaChecksum(String input) {
        //String[] parts = input.split("(<=\\G.{2})");

        List<String> parts = new ArrayList<String>();
        for (int i = 0; i < input.length(); i += 2) parts.add(input.substring(i, Math.min(i + 2,input.length())));
        for (int i = 0; i < parts.size(); i++) System.out.println(parts.get(i));

        int cs = 0;
        for (String part : parts) {
            int decimal = Integer.parseInt(part, 16);
            cs += decimal;
        }

        if (cs > 255) {
            cs = cs & 0xFF;
        }

        String csHex = String.format("%02X", cs);
        return input + csHex;
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
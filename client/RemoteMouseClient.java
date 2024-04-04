package com.example.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class RemoteMouseClient {
    private Socket socket;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;

    public void connect(String serverIp, int serverPort) throws IOException {
        socket = new Socket(serverIp, serverPort);
        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);
    }

    // Method to send touch gestures (implement according to your requirement)
    public void sendTouchGestures(int x, int y) throws IOException {
        if (dataOutputStream != null) {
            dataOutputStream.writeInt(x);
            dataOutputStream.writeInt(y);
            dataOutputStream.flush();
        }
    }

    public void disconnect() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (dataOutputStream != null) {
            dataOutputStream.close();
        }
    }
}

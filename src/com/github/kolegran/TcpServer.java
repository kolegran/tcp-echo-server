package com.github.kolegran;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8081);
            Socket client = server.accept();

            System.out.println("Connection accepted");

            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());

            while (!client.isClosed()) {
                System.out.println("Waiting command from client...");

                String command = dis.readUTF();

                System.out.println("Read from client: " + command);

                dos.writeUTF(command);
                dos.flush();
            }
            System.out.println("Client disconnected");

            dis.close();
            dos.close();

            client.close();

            System.out.println("Closing connections and channels");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

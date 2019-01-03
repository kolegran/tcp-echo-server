package com.github.kolegran;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8081);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            System.out.println("Client connected to socket");
            System.out.println("Enter command: ");

            while (!socket.isOutputShutdown()) {
                if (br.ready()) {
                    String command = br.readLine();

                    dos.writeUTF(command);
                    dos.flush();

                    String echoCommand = dis.readUTF();
                    System.out.println("Reply from server: " + echoCommand);
                    System.out.println("Enter command: ");
                }
            }
            System.out.println("Closing connections & channels on clientSide");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

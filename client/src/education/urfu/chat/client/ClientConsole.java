package education.urfu.chat.client;

import education.urfu.network.TCPConnection;
import education.urfu.network.TCPConnectionListener;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientConsole implements TCPConnectionListener {

    private static final String IP_ADDR = "192.168.1.119";
    private static final int PORT = 1103;
    private TCPConnection connection;
    Scanner scanner = new Scanner(System.in);
    String userName = new String();

    public static void main(String[] args) {
        new ClientConsole();
    }

    private ClientConsole() {
        printMsg("**************************************");
        printMsg("       Client is running...");
        printMsg("**************************************");
        System.out.print("Input your UserName:   ");
        userName = scanner.nextLine();
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
            while (true) {
                String msg = userName + ":  " + scanner.nextLine();
                if(msg.equals(""));
                else {
                    connection.sendString(msg);
                }
            }
        } catch (IOException e) {
            printMsg("Connection Exception: " + e);
        }
    }


    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        System.out.println("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMsg(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        System.out.println("Connection close...");
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMsg("Connection Exception: " + e);
    }

    private synchronized void printMsg(String msg) {
        System.out.println(msg);
    }
}



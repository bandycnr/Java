/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socketudp;

/**
 *
 * @author dhealf
 * dari Praktikum Sistem Terdistribusi
 */
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.print("Inputkan message yang akan di submit : ");
            String message = sc.nextLine();
            byte[] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName("127.0.0.1");
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, message.length(),
                    aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[m.length];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Yang diterima server: " + new String(reply.getData()));
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
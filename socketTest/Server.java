import java.io.*;
import java.net.*;

public class Server {
	public static void main(String args[]) throws IOException {
        //find the server ip address
		InetAddress thisIp =InetAddress.getLocalHost();
        System.out.println("Server IP:"+thisIp.getHostAddress());
        final int portNumber = 8123;
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while (true) {
			Socket socket = serverSocket.accept();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("whats ur move? ");

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = br.readLine();

			pw.println("move, " + str);
			pw.close();
			socket.close();

			System.out.println("move:" + str);
		}
	}
}

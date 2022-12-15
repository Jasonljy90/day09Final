package day09;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	private final Integer port;

	public HttpServer(Integer port) {
		this.port = port;
	}

	public void start() throws Exception {

		ServerSocket server = new ServerSocket(port);

		while (true) {
			System.out.println("Here in step 2");
			final Socket socket = server.accept();
			System.out.println("After server accept");
			HttpClientConnection client = new HttpClientConnection(socket);
			client.run();
		} 
	}
}

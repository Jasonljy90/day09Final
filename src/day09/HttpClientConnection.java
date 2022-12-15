package day09;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClientConnection implements Runnable {

	private final Socket socket;
	private String[] docRoot;

	public HttpClientConnection(Socket socket, String[] docRoot) {
		this.socket = socket;
		this.docRoot = docRoot;
	}

	@Override
	public void run() {

		try {
			//System.out.println("Here in step 3");
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();

			String httpHeader = br.readLine();
			System.out.printf(">>> line: %s\n", httpHeader);
			String[] headerParts = httpHeader.split(" ");
			String httpMethod = headerParts[0];
			String httpPath = headerParts[1];
			


		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { socket.close(); } catch (Exception ex) { }
		}
	}
}

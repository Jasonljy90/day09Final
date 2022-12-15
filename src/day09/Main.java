package day09;

public class Main {

	public static void main(String[] args) throws Exception {
		int port = port(args);
		String[] docRoot = docRoot(args);
		HttpServer server = new HttpServer(port, docRoot);
		server.start();
	}

	private static int port(String[] args) throws Exception {
		
		for (int i = 0; i <=args.length; i++) {
			if (args[i].equals("--port")){
				Integer port = Integer.parseInt(args[i+1]);
				return port;
			}
		}
		throw new Exception("port not specified");
	}

	private static String[] docRoot(String[] args) {
		
		for (int i = 0; i <=args.length; i++) {
			if (args[i].equals("--docRoot")){
				return args[i+1].split(":");
			}
		}
		return new String[] {"static"};
		//throw new Exception("docRoot not specified");
	}
}

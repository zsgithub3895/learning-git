package cn.zsgithub.collections.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import sun.print.PrinterJobWrapper;

public class EchoServer {

	public static void main(String[] args){
		try {
		ServerSocket ss = new ServerSocket(8189);
		boolean done = false;
		int i = 1;
		while(true){
			Socket socket = ss.accept();
			System.out.println("Spawning:"+i);
			ThreadHandle th = new ThreadHandle(socket,i);
			Thread t = new Thread(th);
			t.start();
			i++;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}

class ThreadHandle implements Runnable{
	private Socket incoming;
	int count;
	public ThreadHandle(Socket i,int c){
		incoming = i;
		count = c;
	}
	@Override
	public void run() {
		try{
			InputStream  inStream = incoming.getInputStream();
			OutputStream  outStream = incoming.getOutputStream();
			Scanner in  = new Scanner(inStream);
			PrintWriter out = new PrintWriter(outStream,true);
			out.println("Hello,enter quit can exit");
			boolean done = false;
			while(!done && in.hasNextLine()){
				String line = in.nextLine();
				System.out.println("client info"+count+":"+line);
				//out.println("client info:"+line);//写给客户端
				if(line.trim().equals("quit")) done = true;
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				incoming.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

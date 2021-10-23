package com.example.semana_9_app;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import events.OnMessageListener;


public class UDPConection extends Thread{
	
	private DatagramSocket socket;
	private OnMessageListener observer;
	public void setObsever(OnMessageListener observer){
		this.observer = observer;
	}

	@Override
	public void run() {
		try {

			socket = new DatagramSocket(observer.getPort());
			
			while (true) {
				
				byte[] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				Log.e("esperando","...");
				socket.receive(packet); 
				
				String mensaje = new String(packet.getData()).trim();

				Log.e("llego",mensaje);

				if(mensaje.equals("Ready")){
				observer.onOrderReady();}

				if(mensaje.equals("Received")) {
					observer.onOrderReceived();}


				if(mensaje.equals("Denied")){
					observer.onOrderDenied();
				}

			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
		public void sendMsg(String msg,String ipName) {
			
			new Thread(()->{
				
				try {
					InetAddress ip = InetAddress.getByName(ipName);
					DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.getBytes().length,ip, 5000);
					socket.send(packet);
					}catch(UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}).start();
			
			
		
	}
	
}

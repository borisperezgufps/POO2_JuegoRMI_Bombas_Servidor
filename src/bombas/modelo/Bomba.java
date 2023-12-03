package bombas.modelo;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bombas.IBomba;
import bombas.controlador.BombaController;

public class Bomba extends UnicastRemoteObject implements IBomba{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean[][] bombas;
	private BombaController controlador;
	
	public Bomba() throws RemoteException {
		System.out.println("Iniciando");
		bombas = new boolean[6][6];
		inicializarBombas();
		iniciarServidor();
	}
	
	public void setControlador(BombaController con) {
		controlador = con;
	}
	
	@Override
	public void procesarPosicion(String usuario, int x, int y) throws RemoteException {
		
		boolean explota = bombas[x][y];		
		controlador.actualizarPosicion(usuario, x, y, explota);
	}
	
	public void inicializarBombas() {
		int cantidadBombas = 0;
		
		for(int i=0;i<6;i++) {
			for(int u=0;u<6;u++) {
				int random = (int)(Math.random()*10);
				
				if(random>=8) {
					bombas[i][u] = true;
					cantidadBombas++;
				}
			}
		}
		
		for(int i=0;i<6;i++) {
			for(int u=0;u<6;u++) {
				System.out.print("[ "+ bombas[i][u] + "]");
			}
			System.out.println();
		}
		
		System.out.println("Cantidad bombas = "+cantidadBombas);
		
	}
	
	public void iniciarServidor() {
		
		Thread t = new Thread(() -> {
			try {
				String ip = (InetAddress.getLocalHost()).toString();
				System.out.println("Escuchando en... "+ip+":3232");
				Registry reg = LocateRegistry.createRegistry(3232);
				reg.bind("server", (IBomba)this);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		
		t.start();
		
	}
	
}

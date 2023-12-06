package bombas.modelo;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import bombas.IBomba;
import bombas.controlador.BombaController;

public class Bomba extends UnicastRemoteObject implements IBomba{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean[][] bombas;
	private BombaController controlador;
	private HashMap<String, Boolean> posicionesIngresadas;
	
	public Bomba() throws RemoteException {
		System.out.println("Iniciando");
		bombas = new boolean[6][6];
		posicionesIngresadas = new HashMap<String, Boolean>();
		
		iniciarServidor();
	}
	
	public void setControlador(BombaController con) {
		controlador = con;
	}
	
	@Override
	public void procesarPosicion(String usuario, int x, int y) throws RemoteException {
		
		
		if(usuario.length()>0 && !usuario.equalsIgnoreCase("null")) {
			if(x>=0 && x<6 && y>=0 && y<6) {		
				if(posicionesIngresadas.get(x+"-"+y)==null){
					posicionesIngresadas.put(x+"-"+y, true);
				
					boolean explota = bombas[x][y];		
					controlador.actualizarPosicion(usuario, x, y, explota);
				}else {
					String mensaje = "o.O REPETIDO";
					controlador.notificarIrregularidad(usuario, x, y, mensaje);
				}
			}else {
				String mensaje = "o.O FUERA DE LÍMITES";
				controlador.notificarIrregularidad(usuario, x, y, mensaje);
			}
		}else {
			String mensaje = "o.O Y EL USUARIO?";
			controlador.notificarIrregularidad(usuario, x, y, mensaje);
		}
	}
	
	public int inicializarBombas() {
		
		int cantidadBombas = 0;
		
		do {
			cantidadBombas = generarAleatorioTablero();
		}while(cantidadBombas<12);
		
		return cantidadBombas;
	}
	
	private int generarAleatorioTablero() {
		int cantidadBombas = 0;
		
		for(int i=0;i<6;i++) {
			for(int u=0;u<6;u++) {
				int random = (int)(Math.random()*10);
				
				if(random>=7) {
					bombas[i][u] = true;
					cantidadBombas++;
				}
			}
		}
		return cantidadBombas;
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

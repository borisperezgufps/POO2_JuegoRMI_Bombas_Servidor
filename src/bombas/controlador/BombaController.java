package bombas.controlador;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import bombas.modelo.Bomba;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BombaController implements Initializable {

	@FXML
    private Label lbl00;

    @FXML
    private Label lbl01;

    @FXML
    private Label lbl02;

    @FXML
    private Label lbl03;

    @FXML
    private Label lbl04;

    @FXML
    private Label lbl05;

    @FXML
    private Label lbl10;

    @FXML
    private Label lbl11;

    @FXML
    private Label lbl12;

    @FXML
    private Label lbl13;

    @FXML
    private Label lbl14;

    @FXML
    private Label lbl15;

    @FXML
    private Label lbl20;

    @FXML
    private Label lbl21;

    @FXML
    private Label lbl22;

    @FXML
    private Label lbl23;

    @FXML
    private Label lbl24;

    @FXML
    private Label lbl25;

    @FXML
    private Label lbl30;

    @FXML
    private Label lbl31;

    @FXML
    private Label lbl32;

    @FXML
    private Label lbl33;

    @FXML
    private Label lbl34;

    @FXML
    private Label lbl35;

    @FXML
    private Label lbl40;

    @FXML
    private Label lbl41;

    @FXML
    private Label lbl42;

    @FXML
    private Label lbl43;

    @FXML
    private Label lbl44;

    @FXML
    private Label lbl45;

    @FXML
    private Label lbl50;

    @FXML
    private Label lbl51;

    @FXML
    private Label lbl52;

    @FXML
    private Label lbl53;

    @FXML
    private Label lbl54;

    @FXML
    private Label lbl55;

    @FXML
    private TextArea taMovimientos;
    
    private Bomba bomba;
    
    public BombaController() {
		try {
			bomba = new Bomba();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

    @FXML
    void reiniciar(ActionEvent event) {

    }

	public void actualizarPosicion(String usuario, int x, int y, boolean explota) {
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	Label lbl = obtenerLabel(x, y);
        		
        		if(explota) {
        			lbl.setText("X");
        			lbl.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        			taMovimientos.appendText(usuario + "> ("+x+","+y+") = X\n");
        		}else {
        			lbl.setText("O");
        			lbl.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        			taMovimientos.appendText(usuario + "> ("+x+","+y+") = O\n");
        		}
            }
        });
		
		
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bomba.setControlador(this);
	}
	
	private Label obtenerLabel(int x, int y) {
		
		if(x==0 && y==0)
			return lbl00;
		else if(x==0 && y==1)
			return lbl01;
		else if(x==0 && y==2)
			return lbl02;
		else if(x==0 && y==3)
			return lbl03;
		else if(x==0 && y==4)
			return lbl04;
		else if(x==0 && y==5)
			return lbl05;
		else if(x==1 && y==0)
			return lbl10;
		else if(x==1 && y==1)
			return lbl11;
		else if(x==1 && y==2)
			return lbl12;
		else if(x==1 && y==3)
			return lbl13;
		else if(x==1 && y==4)
			return lbl14;
		else if(x==1 && y==5)
			return lbl15;
		else if(x==2 && y==0)
			return lbl20;
		else if(x==2 && y==1)
			return lbl21;
		else if(x==2 && y==2)
			return lbl22;
		else if(x==2 && y==3)
			return lbl23;
		else if(x==2 && y==4)
			return lbl24;
		else if(x==2 && y==5)
			return lbl25;
		else if(x==3 && y==0)
			return lbl30;
		else if(x==3 && y==1)
			return lbl31;
		else if(x==3 && y==2)
			return lbl32;
		else if(x==3 && y==3)
			return lbl33;
		else if(x==3 && y==4)
			return lbl34;
		else if(x==3 && y==5)
			return lbl35;
		else if(x==4 && y==0)
			return lbl40;
		else if(x==4 && y==1)
			return lbl41;
		else if(x==4 && y==2)
			return lbl42;
		else if(x==4 && y==3)
			return lbl43;
		else if(x==4 && y==4)
			return lbl44;
		else if(x==4 && y==5)
			return lbl45;
		else if(x==5 && y==0)
			return lbl50;
		else if(x==5 && y==1)
			return lbl51;
		else if(x==5 && y==2)
			return lbl52;
		else if(x==5 && y==3)
			return lbl53;
		else if(x==5 && y==4)
			return lbl54;
		else if(x==5 && y==5)
			return lbl55;
		
		return null;
	}

}



/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cbxStati"
    private ComboBox<Country> cbxStati; // Value injected by FXMLLoader

    @FXML // fx:id="btnVisita"
    private Button btnVisita; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void doCalcolaConfini(ActionEvent event) {

    	int anno = 0;
    	txtResult.clear();
       	
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    		Graph<Country,DefaultEdge> grafo =  model.creaGrafo(anno);
    		
    		cbxStati.getItems().addAll(grafo.vertexSet());
    		
    		ConnectivityInspector<Country,DefaultEdge> ispezione = new ConnectivityInspector<>(grafo);
    		
    		for(Country c :grafo.vertexSet()) {
        		
    			txtResult.appendText(c.getNome().toUpperCase() + " #Paesi Confinanti: " + c.numConfini() + " \n");
        	}
    		
    		int tot =0;
    		
    		for(Set<Country> c : ispezione.connectedSets()) {
    			
    			tot = tot + c.size();
    			
//    			txtResult.appendText(c.toString() + "\n");
    		}
    		
    		txtResult.appendText("#COMPONENTI CONNESSE: " + tot);
    		
    	}catch(NumberFormatException e) {
    		txtResult.appendText("inserisci valore numerico per l'anno");
    	}
    }

    @FXML
    void doVisita(ActionEvent event) {

    	txtResult.clear();
    
    	Country c = cbxStati.getValue();
    	
    	
//    		Map<Country,Country> result = model.alberoVisita(c);
    		
    		for(Country c1 : model.alberoVisita(c) ) {
    			txtResult.appendText(c1.getNome() + "\n");
    		}
    	
    		txtResult.appendText("\n"+ "VISITA AMPIEZZA:" + "\n");
    		
    		for(Country c1 : model.visitaAmpiezza(c)) {
    			txtResult.appendText(c1.getNome() + "\n");
    		}
    
    		txtResult.appendText("\n"+ "CONNECTIVITY INSPECTOR:" + "\n");
    		
    		ConnectivityInspector<Country,DefaultEdge> ispezione = new ConnectivityInspector<>(model.getGrafo());
    		
    			
    			
    		for(Country c1 : ispezione.connectedSetOf(c)) {
    			
    			txtResult.appendText(c1.getNome() + "\n");
    		}
    		

    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbxStati != null : "fx:id=\"cbxStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVisita != null : "fx:id=\"btnVisita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }


    
    public void setModel(Model model) {
    	this.model = model;
    }
}

package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;


public class Model {

	
	private Map<Integer,Country> idMap;
	private Graph<Country,DefaultEdge> grafo;
	private BordersDAO dao;
	private List<Country> paesiAnno;
	List<Country> visita = new ArrayList<>();
	
	public Model() {
		
		this.idMap = new HashMap<>();
		dao = new BordersDAO();
		dao.loadAllCountries(idMap);
		
	}
	
	public Graph<Country,DefaultEdge> creaGrafo(int anno) {
		
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		this.paesiAnno = new ArrayList<>();
		
		List<Border> confini = dao.creaConfini(idMap,paesiAnno,anno);
		
		for(Country c : paesiAnno) {
			
			grafo.addVertex(c);
			
		}
		
		for(Border b : confini) {

			this.grafo.addEdge(b.getC1(), b.getC2());
//			Graphs.addEdge(this.grafo, b.getC1(), b.getC2());
		}
		
		for(Country c : grafo.vertexSet()) {
			int count =0;
			for(Border b : confini) {
				if(c.equals(b.getC1())) {
					count++;
				}
			}
			
			c.setNumConfini(count);
		}
		
		return grafo;
	}
	
	public Set<Country> vertici(){
		return grafo.vertexSet();
	}
	
	public Set<DefaultEdge> archi() {
		return this.grafo.edgeSet();
	}
	
	public List<Country> alberoVisita(Country source) {

		List<Country> result = new ArrayList<>();
		
		DepthFirstIterator<Country,DefaultEdge> dfi = new DepthFirstIterator<>(grafo,source);
		
		while(dfi.hasNext()) {
			result.add(dfi.next());
		}

		
		return result;
	}
	
	public List<Country> visitaAmpiezza(Country source){
		
		List<Country> result = new ArrayList<>();
		
		BreadthFirstIterator<Country,DefaultEdge> bfi = new BreadthFirstIterator<>(grafo,source);
		
		while(bfi.hasNext()) {
			result.add(bfi.next());
		}
		
	return result;
		
	}

	public Graph<Country,DefaultEdge> getGrafo() {
		
		return grafo;
	}
	
	

}

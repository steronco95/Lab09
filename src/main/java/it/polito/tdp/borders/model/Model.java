package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.metroparis.model.Fermata;

public class Model {

	
	private Map<Integer,Country> idMap;
	private Graph<Country,DefaultEdge> grafo;
	private BordersDAO dao;
	private List<Country> paesiAnno;
//	private Map<Country,Country> visita;
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
	
	
//	public List<Country> trovaPercorso(Country inizio){
//		
////		visita = new HashMap<>();
////		
////		visita.put(inizio, null);
//		
//		
//		visita.add(inizio);
//		
//		BreadthFirstIterator<Country,DefaultEdge> it = new BreadthFirstIterator<>(grafo,inizio);
//		
//		it.addTraversalListener(new TraversalListener<Country,DefaultEdge>(){
//
//			@Override
//			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
//				DefaultEdge g = e.getEdge();
//				Country Partenza = grafo.getEdgeSource(g);
//				Country Arrivo = grafo.getEdgeTarget(g);
//				if(visita.contains(Partenza)) {
//					visita.add(Arrivo);
//				}else {
//					visita.add(Partenza);
//				}
//				
//				
//			}
//
//			@Override
//			public void vertexTraversed(VertexTraversalEvent<Country> e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void vertexFinished(VertexTraversalEvent<Country> e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			
//			
//		});
//		
//		while(it.hasNext()) {
//			it.next();
//		}
//		
//		return visita;
//	}
	public List<Country> alberoVisita(Country source) {

		List<Country> result = new ArrayList<>();
		
		DepthFirstIterator<Country,DefaultEdge> dfi = new DepthFirstIterator<>(grafo,source);
		
		while(dfi.hasNext()) {
			result.add(dfi.next());
		}

		
		return result;
	}
	
	

}

package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	private Graph <String, DefaultEdge> graph;
	private int numLettere;
	private WordDAO worddao;
	
	public Graph<String, DefaultEdge> getGrafo() {
		return graph;
	}
	
	public Model() {
		worddao = new WordDAO();
	}
	
	public List<String> createGraph(int numeroLettere) {
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		this.numLettere = numeroLettere;
		
		List<String> parole = worddao.getAllWordsFixedLength(numeroLettere);
		for(String parola : parole)
			graph.addVertex(parola);
		
		for(String parola : parole) {
			List<String> paroleSimili = Utils.getParoleUguali(parole, parola, numeroLettere);
			for(String s : paroleSimili) {
				graph.addEdge(parola, s);
			}
		}
		
		return parole;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		if(numLettere != parolaInserita.length())
			throw new RuntimeException("La parola inserita ha una lunghezza differente rispetto al numero inserito.");
		
		List <String> parole = new ArrayList<>();
		parole.addAll(Graphs.neighborListOf(graph, parolaInserita));
		
		return parole;
	}

	public String findMaxDegree() {
		int nMax = 0;
		String s = null;
		
		for(String vertice : graph.vertexSet()) {
			if(graph.degreeOf(vertice) > nMax) {
				nMax = graph.degreeOf(vertice);
				s = vertice;
			}
		}
		
		if(nMax != 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Massimo grado: %d al vertice: %s\n", nMax, s));
			
			for(String v : Graphs.neighborListOf(graph, s)) {
				sb.append(v + "\n");
			}
			
			return sb.toString();
		}
		
		return "Non trovato.";
	}
}

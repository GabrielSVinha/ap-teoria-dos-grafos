package main.java.br.ufcg.mbta_graph.flux_builder;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import main.java.br.ufcg.mbta_graph.builder.Builder;


public class FluxBuilder implements Builder{
	
	private static final String path = "data/mbta-flux.csv";

	public Multigraph<String, DefaultWeightedEdge> build() {
		
		return null;
	}

}

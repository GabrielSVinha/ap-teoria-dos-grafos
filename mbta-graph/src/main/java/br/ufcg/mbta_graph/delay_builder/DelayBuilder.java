package br.ufcg.mbta_graph.delay_builder;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import br.ufcg.mbta_graph.builder.Builder;

public class DelayBuilder implements Builder{
	
	private static final String path = "data/mbta-delay.csv";

	public Multigraph<String, DefaultWeightedEdge> build() {
		
		return null;
	}

}

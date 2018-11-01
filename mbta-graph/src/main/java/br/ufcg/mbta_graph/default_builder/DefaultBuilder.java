package br.ufcg.mbta_graph.default_builder;

import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import com.opencsv.CSVReader;

import br.ufcg.mbta_graph.builder.Builder;
import main.java.br.ufcg.mbta_graph.default_builder.Color;

public class DefaultBuilder implements Builder {
	private static final String path = "../data/mbta-delay.csv";

	public Multigraph<String, DefaultWeightedEdge> build() {
		Multigraph<String, DefaultWeightedEdge> graph = new Multigraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		try {
			CSVReader reader = new CSVReader(new FileReader(this.path));
			String[] line;
			while ((line = reader.readNext()) != null) {
				graph.addVertex(line[0]);
				graph.addVertex(line[1]);
				int color = this.getColor(line[2]);
				DefaultWeightedEdge edge = graph.addEdge(line[0], line[1]);
				graph.setEdgeWeight(edge, color);
				edge = graph.addEdge(line[1], line[0]);
				graph.setEdgeWeight(edge, color);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return graph;
	}
	
	private Integer getColor(String line) {
		Integer color;
		switch (Color.valueOf(line)) {
		case red:
			color = 0;
			break;
		case blue:
			color = 1;
			break;
		case orange:
			color = 2;
			break;
		case green:
			color = 3;
			break;
		case silver:
			color = 4;
			break;
		default:
			color = 5;
			break;
		}
		return color;
	}
}

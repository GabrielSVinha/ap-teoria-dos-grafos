package main.java.br.ufcg.mbta_graph.default_builder;

import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import com.opencsv.CSVReader;

import main.java.br.ufcg.mbta_graph.builder.Builder;

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
		if (line == "red") {
			color = 0;
		} else if (line == "blue") {
			color = 1;
		} else if (line == "orange") {
			color = 2;
		} else if (line == "green") {
			color = 3;
		} else if(line == "silver") {
			color = 4;
		} else {
			color = 5;
		}
		return color;
	}
}

package br.ufcg.mbta_graph.builder;

import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import com.opencsv.CSVReader;

import br.ufcg.mbta_graph.default_builder.DefaultBuilder;
import junit.framework.TestCase;

public class DefaultBuilderTest extends TestCase {

	private Builder builder;

	private final String path = "../data/mbta-delay.csv";

	protected void setUp() throws Exception {
		super.setUp();
		this.builder = new DefaultBuilder();
	}

	public void testBuild() throws IOException {
		Multigraph<String, DefaultWeightedEdge> graph = this.builder.build();
		CSVReader reader = new CSVReader(new FileReader(this.path));
		String[] line;
		while ((line = reader.readNext()) != null) {
			assertTrue(graph.containsVertex(line[0]));
			assertTrue(graph.containsVertex(line[1]));
			assertTrue(graph.containsEdge(line[0], line[1]));
			assertTrue(graph.containsEdge(line[1], line[0]));
			DefaultWeightedEdge edge = graph.getEdge(line[1], line[0]);
			assertEquals(graph.getEdgeWeight(edge), (double)this.getColor(line[2]));
			edge = graph.getEdge(line[0], line[1]);
			assertEquals(graph.getEdgeWeight(edge), (double)this.getColor(line[2]));
		}
		reader.close();
	}
	
	private Integer getColor(String line) {
		Integer color;
		switch (line) {
		case "red":
			color = 0;
			break;
		case "blue":
			color = 1;
			break;
		case "orange":
			color = 2;
			break;
		case "green":
			color = 3;
			break;
		case "silver":
			color = 4;
			break;
		default:
			color = 5;
			break;
		}
		return color;
	}
}

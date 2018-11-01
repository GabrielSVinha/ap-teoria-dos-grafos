package test.java.br.ufcg.mbta_graph.builder;

import java.io.FileReader;
import java.io.IOException;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import com.opencsv.CSVReader;

import junit.framework.TestCase;
import main.java.br.ufcg.mbta_graph.builder.Builder;
import main.java.br.ufcg.mbta_graph.default_builder.DefaultBuilder;

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
		}
		return color;
	}
}

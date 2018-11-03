package br.ufcg.mbta_graph.builder;

import com.opencsv.CSVReader;
import junit.framework.TestCase;
import main.java.br.ufcg.mbta_graph.flux_builder.FluxBuilder;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import main.java.br.ufcg.mbta_graph.builder.Builder;

import java.io.FileReader;
import java.io.IOException;

public class FluxBuilderTest extends TestCase {

    private Builder builder;

    private final String path = "../data/mbta-flux.csv";


    protected void setUp() throws Exception {
        super.setUp();
        this.builder = new FluxBuilder();
    }

    public void testBuild() throws IOException {
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = this.builder.build();
        CSVReader reader = new CSVReader(new FileReader(this.path));
        String[] line;
        while ((line = reader.readNext()) != null) {
            if (line[2].equals("Label")) {
                continue;
            }
            assertTrue(graph.containsVertex(line[0]));
            assertTrue(graph.containsVertex(line[1]));
            assertTrue(graph.containsEdge(line[0], line[1]));
            DefaultWeightedEdge edge = graph.getEdge(line[0], line[1]);
            assertEquals(graph.getEdgeWeight(edge), Double.parseDouble(line[2]));
        }
        reader.close();
    }
}

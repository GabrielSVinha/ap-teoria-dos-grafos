package br.ufcg.mbta_graph.flux_builder;

import com.opencsv.CSVReader;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import main.java.br.ufcg.mbta_graph.builder.Builder;

import java.io.FileReader;
import java.io.IOException;


public class FluxBuilder implements Builder {

    private static final String path = "data/mbta-flux.csv";

    public DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> build() {
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(
                DefaultWeightedEdge.class);
        try {
            CSVReader reader = new CSVReader(new FileReader(this.path));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[2].equals("Label")) {
                    continue;
                }
                graph.addVertex(line[0]);
                graph.addVertex(line[1]);
                DefaultWeightedEdge edge = graph.addEdge(line[0], line[1]);
                graph.setEdgeWeight(edge, Double.parseDouble(line[2]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return graph;
    }

}

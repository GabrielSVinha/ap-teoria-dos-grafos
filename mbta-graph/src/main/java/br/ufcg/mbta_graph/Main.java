package br.ufcg.mbta_graph;

import br.ufcg.mbta_graph.delay_builder.DelayBuilder;
import br.ufcg.mbta_graph.flux_builder.FluxBuilder;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.KShortestPaths;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

/**
 * Main
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        DelayBuilder delay = new DelayBuilder();
        FluxBuilder fluxBuilder = new FluxBuilder();

        DefaultDirectedWeightedGraph delayGraph = delay.build();
        DefaultDirectedWeightedGraph fluxGraph = fluxBuilder.build();

        final String START = "Eliot";
        final String END = "Haymarket Station";

        System.out.println("As perguntas respondidas a seguir consideram a estação de partida sendo "+ START +" e a estação destino é " + END);

        System.out.println("1. Qual caminho entre duas estações é o mais rápido?");
        Object fast = findShortestPath(delayGraph, START, END);
        System.out.println(fast);
        System.out.println();

        System.out.println("2. Dada duas estações, uma de origem e uma de destino, qual caminho é o mais confortável (passa por estações com menos pessoas)?");
        Object confortable = findShortestPath(fluxGraph, START, END);
        System.out.println(confortable);
        System.out.println();

        System.out.println("3. Dada duas estações, uma de origem e uma de destino, qual o caminho ideal (mais rápido e com menos pessoas)?");
        Object ideal = findMatchedPath(delayGraph, fluxGraph, START, END);
        //System.out.println(fast);
        System.out.println(ideal);
        System.out.println("4. Dada duas estações, uma de origem e uma de destino, qual o caminho mais utilizado(mais pessoas nos caminhos e estações) ?");
        Object biggest = findBiggestPath(fluxGraph, START, END);
        System.out.println(biggest);
        System.out.println();
    }

    public static Object findShortestPath(Graph graph, String startVertex, String endVertex)
    {
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph);
        return dijkstra.getPath(startVertex, endVertex);
    }

    public static Object findBiggestPath(Graph graph, String startVertex, String endVertex){
        KShortestPaths allDirect = new KShortestPaths(graph, 50);
        //Returns the k shortest simple paths in increasing order of weight.
        List paths = allDirect.getPaths(startVertex, endVertex);
        return paths.get(paths.size()-1);
    }

    public static Object findMatchedPath(Graph from, Graph to, String startVertex, String endVertex){
        KShortestPaths fromDirect = new KShortestPaths(from, 50);
        KShortestPaths toDirect = new KShortestPaths(to, 50);
        List<GraphPath> fromPaths = fromDirect.getPaths(startVertex, endVertex);
        List aux = new ArrayList();
        for (GraphPath p: fromPaths){
            aux.add(p.toString());
        }
        fromPaths = aux;
        List<GraphPath> toPaths = toDirect.getPaths(startVertex, endVertex);
        aux = new ArrayList();
        for (GraphPath p: toPaths){
            aux.add(p.toString());
        }
        toPaths = aux;
        fromPaths.retainAll(toPaths);
        return fromPaths.get(0);
    }

}

package br.ufcg.mbta_graph;

import br.ufcg.mbta_graph.delay_builder.DelayBuilder;
import br.ufcg.mbta_graph.flux_builder.FluxBuilder;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import br.ufcg.mbta_graph.builder.Builder;


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

        Graph delayGraph = delay.build();
        Graph fluxGraph = fluxBuilder.build();

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
        //Object fast = findShortestPath(delayGraph, START, END);
        System.out.println(fast);
        System.out.println();
        System.out.println("4. Dada duas estações, uma de origem e uma de destino, qual o caminho mais utilizado(mais pessoas nos caminhos e estações) ?");
        //Object fast = findShortestPath(delayGraph, START, END);
        System.out.println(fast);
        System.out.println();
    }

    public static Object findShortestPath(Graph graph, String startVertex, String endVertex)
    {
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph);
        return dijkstra.getPath(startVertex, endVertex);
    }

}

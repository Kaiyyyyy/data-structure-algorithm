package com.kaiy.graph.algorithm;

import com.kaiy.graph.struct.Edge;
import com.kaiy.graph.struct.Graph;
import com.kaiy.graph.struct.Vertex;

import java.util.*;

public class SPFA {

    private static final Integer MAX = (Integer.MAX_VALUE >> 1) - 1;

    private final Graph graph;

    private final Vertex source;

    private final Vertex sink;

    public SPFA(Graph graph, Vertex source, Vertex sink) {
        if (graph == null || source == null || sink == null) {
            throw new IllegalArgumentException("args is null");
        }
        if (graph.vertex(source.getId()) == null || graph.vertex(sink.getId()) == null) {
            throw new IllegalArgumentException("vertex not in graph");
        }
        this.graph = graph;
        this.source = source;
        this.sink = sink;
    }

    public void search() {
        Deque<Vertex> queue = new LinkedList<>();
        Map<Vertex, Integer> dist = new HashMap<>();

        graph.vertices().forEach(v -> dist.put(v, MAX));
        Map<Vertex, Vertex> parent = new HashMap<>();
        queue.offer(source);
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            Vertex cur = queue.pop();
            for (Edge edge : graph.outEdges(cur)) {
                Vertex to = graph.vertex(edge.getTo());
                Integer oldToW = dist.get(to);
                int newToW = dist.get(cur) + edge.getW();
                if (newToW < oldToW) {
                    dist.put(to, newToW);
                    parent.put(to, cur);
                }
                if (!queue.contains(to)) {
                    queue.offer(to);
                }
            }
        }

        if (parent.get(sink) == null) {
            System.out.println("not found to sink path");
            return;
        }
        LinkedList<String> path = new LinkedList<>();
        path.addFirst(sink.getId());
        Vertex pre = sink;
        while (true) {
            Vertex vertex = parent.get(pre);
            if (vertex == null) {
                break;
            }
            path.addFirst(vertex.getId());
            if (vertex == source) {
                break;
            }
            pre = vertex;
        }
        System.out.println("short path value = " + dist.get(sink));
        System.out.println("short path = " + path);
    }


    public static void main(String[] args) {
        Vertex s = new Vertex("s");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex t = new Vertex("t");

        List<Vertex> vertices = List.of(s, vertex2, vertex3, vertex4, vertex5, t);

        Edge edge1 = new Edge(s.getId(), vertex2.getId(), 1);
        Edge edge2 = new Edge(s.getId(), vertex3.getId(), 2);
        Edge edge3 = new Edge(vertex2.getId(), vertex4.getId(), 3);
        Edge edge4 = new Edge(vertex2.getId(), vertex5.getId(), 4);
        Edge edge5 = new Edge(vertex3.getId(), vertex4.getId(), 5);
        Edge edge6 = new Edge(vertex3.getId(), vertex5.getId(), 6);
        Edge edge7 = new Edge(vertex4.getId(), t.getId(), 7);
        Edge edge8 = new Edge(vertex5.getId(), t.getId(), 8);

        /**
         *      2     4
         * s              t
         *      3     5
         */

        List<Edge> edges = List.of(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8);
        Graph graph = Graph.build(vertices, edges);
        SPFA spfa = new SPFA(graph, s, t);
        spfa.search();
    }

}

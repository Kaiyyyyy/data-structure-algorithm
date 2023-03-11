package com.kaiy.graph.algorithm;

import com.kaiy.graph.struct.Edge;
import com.kaiy.graph.struct.Graph;
import com.kaiy.graph.struct.Vertex;

import java.util.*;

/**
 * max flow
 */
public class EdmondKarp {

    private static final Integer MAX = (Integer.MAX_VALUE >> 1) - 1;

    private final Graph reidualGraph;

    private final Graph originGraph;

    private final Vertex source;

    private final Vertex sink;

    public EdmondKarp(Graph graph, Vertex source, Vertex sink) {
        if (graph == null || source == null || sink == null) {
            throw new IllegalArgumentException("args is null");
        }
        if (graph.vertex(source.getId()) == null || graph.vertex(sink.getId()) == null) {
            throw new IllegalArgumentException("vertex not in graph");
        }
        this.reidualGraph = graph.clone();
        reidualGraph.edges().forEach(edge -> reidualGraph.addEdge(new Edge(edge.getTo(), edge.getFrom(), 0, true)));
        this.originGraph = graph;
        this.source = reidualGraph.vertex(source.getId());
        this.sink = reidualGraph.vertex(sink.getId());
    }

    public Flow search() {
        Deque<Vertex> queue = new LinkedList<>();
        Map<Vertex, Integer> flow = new HashMap<>();

        reidualGraph.vertices().forEach(v -> flow.put(v, MAX));
        Map<Vertex, Vertex> parent = new HashMap<>();
        queue.offer(source);

        Set<Vertex> already = new HashSet<>();
        already.add(source);
        while (!queue.isEmpty()) {
            Vertex cur = queue.pop();
            if (cur == sink) {
                break;
            }
            for (Edge edge : reidualGraph.outEdges(cur)) {
                Vertex to = reidualGraph.vertex(edge.getTo());
                if (edge.getW() > 0 && !already.contains(to)) {
                    flow.put(to, Math.min(flow.get(cur), edge.getW()));
                    parent.put(to, cur);
                    queue.offer(to);
                    already.add(to);
                }
            }
        }
        Integer maxFlow = flow.get(sink);
        if (maxFlow == null) {
            return new Flow(-1, null);
        }
        if (parent.get(sink) == null) {
            System.out.println("not found to sink path");
            return new Flow(-1, null);
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
        return new Flow(maxFlow, path);
    }

    public void maxFlow() {
        int maxFlow = 0;
        Map<List<String>, Integer> paths = new HashMap<>();
        while (true) {
            Flow search = search();
            Integer flow = search.maxFlow;
            if (flow == -1) {
                break;
            }
            maxFlow += flow;
            List<String> path = search.path;
            paths.put(path, flow);
            for (int i = 0; i < path.size() - 1; i++) {
                String from = path.get(i);
                String to = path.get(i + 1);
                Edge forwardEdge = reidualGraph.edge(Edge.buildEdgeId(from, to));
                Integer forwardW = forwardEdge.getW();
                forwardEdge.setW(forwardW - flow);
                Edge reverseEdge = reidualGraph.edge(Edge.buildEdgeId(to, from));
                Integer reverseW = reverseEdge.getW();
                reverseEdge.setW(reverseW + flow);
            }
        }
        System.out.println("maxFlow = " + maxFlow);
        System.out.println("paths = " + paths);
    }

    public static void main(String[] args) {
        Vertex s = new Vertex("s");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex t = new Vertex("t");

        List<Vertex> vertices = List.of(s, vertex2, vertex3, vertex4, vertex5, t);

        Edge edge1 = new Edge(s.getId(), vertex2.getId(), 6);
        Edge edge2 = new Edge(s.getId(), vertex3.getId(), 4);
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

        EdmondKarp edmondKarp = new EdmondKarp(graph, s, t);
        edmondKarp.maxFlow();
    }

    record Flow(Integer maxFlow, List<String> path) {
    }
}

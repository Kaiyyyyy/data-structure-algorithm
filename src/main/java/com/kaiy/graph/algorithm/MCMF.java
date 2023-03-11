package com.kaiy.graph.algorithm;

import com.kaiy.graph.struct.Edge;
import com.kaiy.graph.struct.Graph;
import com.kaiy.graph.struct.Vertex;

import java.util.*;

/**
 * Minimum Cost Maximum Flow
 */
public class MCMF {

    private static final Integer MAX = (Integer.MAX_VALUE >> 1) - 1;

    private final Graph reidualGraph;

    private final Graph originGraph;

    private final Vertex source;

    private final Vertex sink;

    public MCMF(Graph graph, Vertex source, Vertex sink) {
        if (graph == null || source == null || sink == null) {
            throw new IllegalArgumentException("args is null");
        }
        if (graph.vertex(source.getId()) == null || graph.vertex(sink.getId()) == null) {
            throw new IllegalArgumentException("vertex not in graph");
        }
        this.reidualGraph = graph.clone();
        reidualGraph.edges().forEach(edge -> reidualGraph.addEdge(new Edge(edge.getTo(), edge.getFrom(), 0, edge.getC(), true)));
        this.originGraph = graph;
        this.source = reidualGraph.vertex(source.getId());
        this.sink = reidualGraph.vertex(sink.getId());
    }

    public Flow spfa() {
        Deque<Vertex> queue = new LinkedList<>();
        HashMap<Vertex, Vertex> parent = new HashMap<>();
        Map<Vertex, Integer> flow = new HashMap<>();
        Map<Vertex, Integer> cost = new HashMap<>();
        reidualGraph.vertices().forEach(v -> {
            flow.put(v, MAX);
            cost.put(v, MAX);
        });
        queue.offer(source);
        cost.put(source, 0);
        while (!queue.isEmpty()) {
            Vertex cur = queue.pop();
            for (Edge edge : reidualGraph.outEdges(cur)) {
                Vertex to = reidualGraph.vertex(edge.getTo());
                Integer oldToC = cost.get(to);
                Integer newToC = cost.get(cur) + edge.getC();
                if (edge.getW() > 0 && newToC < oldToC) {
                    flow.put(to, Math.min(flow.get(cur), edge.getW()));
                    parent.put(to, cur);
                    cost.put(to, newToC);
                    if (!queue.contains(to)) {
                        queue.offer(to);
                    }
                }
            }
        }
        if (parent.get(sink) == null) {
            System.out.println("not found to sink path");
            return new Flow(-1, -1, null);
        }
        Integer maxFlow = flow.get(sink);
        if (maxFlow == null) {
            return new Flow(-1, -1, null);
        }
        Integer minCost = cost.get(sink);
        if (minCost == null) {
            return new Flow(-1, -1, null);
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
        return new Flow(maxFlow, minCost, path);
    }

    public void mcmf() {
        int maxFlow = 0;
        int minCost = 0;
        List<R> r = new ArrayList<>();
        while (true) {
            Flow search = spfa();
            Integer flow = search.flow;
            if (flow == -1) {
                break;
            }
            Integer cost = search.cost;
            if (cost == -1) {
                break;
            }
            maxFlow += flow;
            int flowCost = cost * flow;
            minCost += flowCost;
            List<String> path = search.path;
            r.add(new R(path, flow, flowCost));
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
        System.out.println("minCost = " + minCost);
        System.out.println("path = " + r);
    }

    public static void main(String[] args) {
        Vertex s = new Vertex("s");
        Vertex vertex2 = new Vertex("2");
        Vertex vertex3 = new Vertex("3");
        Vertex vertex4 = new Vertex("4");
        Vertex vertex5 = new Vertex("5");
        Vertex t = new Vertex("t");

        List<Vertex> vertices = List.of(s, vertex2, vertex3, vertex4, vertex5, t);

        Edge edge1 = new Edge(s.getId(), vertex2.getId(), 6, 1);
        Edge edge2 = new Edge(s.getId(), vertex3.getId(), 4, 1);
        Edge edge3 = new Edge(vertex2.getId(), vertex4.getId(), 3, 2);
        Edge edge4 = new Edge(vertex2.getId(), vertex5.getId(), 4, 3);
        Edge edge5 = new Edge(vertex3.getId(), vertex4.getId(), 5, 3);
        Edge edge6 = new Edge(vertex3.getId(), vertex5.getId(), 6, 4);
        Edge edge7 = new Edge(vertex4.getId(), t.getId(), 7, 2);
        Edge edge8 = new Edge(vertex5.getId(), t.getId(), 8, 1);

        /**
         *      2     4
         * s              t
         *      3     5
         */

        List<Edge> edges = List.of(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8);
        Graph graph = Graph.build(vertices, edges);
        MCMF mcmf = new MCMF(graph, s, t);
        mcmf.mcmf();
    }

    record Flow(Integer flow, Integer cost, List<String> path) {
    }

    record R(List<String> path, Integer flow, Integer cost) {
    }
}

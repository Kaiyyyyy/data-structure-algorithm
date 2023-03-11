package com.kaiy.graph.struct;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Graph {

    private final Map<String, Vertex> vertexMap;

    private final Map<String, Edge> edgeMap;

    public Graph() {
        vertexMap = new ConcurrentHashMap<>();
        edgeMap = new ConcurrentHashMap<>();
    }

    public void addVertex(Vertex vertex) {
        vertexMap.putIfAbsent(vertex.getId(), vertex);
    }

    public void addEdge(Edge edge) {
        edgeMap.putIfAbsent(edge.getId(), edge);
    }

    public Vertex vertex(String vertexId) {
        return vertexMap.get(vertexId);
    }

    public Collection<Vertex> vertices() {
        return vertexMap.values();
    }

    public Edge edge(String edgeId) {
        return edgeMap.get(edgeId);
    }

    public Collection<Edge> edges() {
        return edgeMap.values();
    }

    public Collection<Edge> outEdges(Vertex vertex) {
        return edges().stream().filter(e -> vertex.getId().equals(e.getFrom())).collect(Collectors.toList());
    }

    public Collection<Edge> inEdges(Vertex vertex) {
        return edges().stream().filter(e -> vertex.getId().equals(e.getTo())).collect(Collectors.toList());
    }

    public static Graph build(Collection<Vertex> vertices, Collection<Edge> edges) {
        Graph graph = new Graph();
        vertices.forEach(graph::addVertex);
        edges.forEach(graph::addEdge);
        return graph;
    }

    public Graph clone() {
        Graph graph = new Graph();
        vertexMap.forEach((k, v) -> graph.addVertex(new Vertex(v.getId())));
        edgeMap.forEach((k, v) -> graph.addEdge(new Edge(v.getFrom(), v.getTo(), v.getW())));
        return graph;
    }
}

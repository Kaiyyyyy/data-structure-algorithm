package com.kaiy.graph.struct;

public class Edge {

    /**
     * edge id
     */
    private String id;

    /**
     * from
     */
    private String from;

    /**
     * to
     */
    private String to;

    /**
     * weight
     */
    private Integer w;

    /**
     * cost
     */
    private Integer c;

    /**
     * is reverse edge
     */
    private boolean reverse = false;

    public Edge(String from, String to, Integer w) {
        this(from, to, w, false);
    }

    public Edge(String from, String to, Integer w, Integer c) {
        this(from, to, w, c, false);
    }

    public Edge(String from, String to, Integer w, Integer c, boolean reverse) {
        this.id = buildEdgeId(from, to);
        this.from = from;
        this.to = to;
        this.w = w;
        this.c = c;
        this.reverse = reverse;
    }

    public Edge(String from, String to, Integer w, boolean reverse) {
        this.id = buildEdgeId(from, to);
        this.from = from;
        this.to = to;
        this.w = w;
        this.reverse = reverse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public static String buildEdgeId(String from, String to) {
        return from + "->" + to;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", w=" + w +
                ", reverse=" + reverse +
                '}';
    }
}

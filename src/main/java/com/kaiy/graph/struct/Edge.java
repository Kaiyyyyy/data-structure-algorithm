package com.kaiy.graph.struct;

public class Edge {

    private String id;

    private String from;

    private String to;

    private Integer w;

    private boolean reverse = false;

    public Edge(String from, String to, Integer w) {
        this.id = from + "->" + to;
        this.from = from;
        this.to = to;
        this.w = w;
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

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
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

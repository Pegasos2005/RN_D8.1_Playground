package software.RN.org.model;

public record Edge(int id1, int id2, long distanceSq) implements Comparable<software.RN.org.model.Edge> {
    @Override
    public int compareTo(software.RN.org.model.Edge o) {
        return Long.compare(this.distanceSq, o.distanceSq);
    }
}

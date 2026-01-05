package software.RN.org.model;

public record Box(int x, int y, int z, int id) {
    public long dist(Box o) {
        long dx = (long)x - o.x;
        long dy = (long)y - o.y;
        long dz = (long)z - o.z;
        return dx*dx + dy*dy + dz*dz;
    }
}
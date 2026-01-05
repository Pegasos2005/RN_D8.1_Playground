package software.RN.org.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CircuitManager {
    private final int[] parent;
    private final int[] size;
    private int numCircuits; // Nuevo contador

    public CircuitManager(int n) {
        parent = new int[n];
        size = new int[n];
        this.numCircuits = n; // ¡ESTO FALTABA! Al inicio hay N circuitos individuales
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Busca el "representante" del circuito con Path Compression
    public int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    // Une dos circuitos si no están ya conectados
    public boolean unite(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            if (size[rootI] < size[rootJ]) {
                parent[rootI] = rootJ;
                size[rootJ] += size[rootI];
            } else {
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            }
            numCircuits--; // Un grupo menos cada vez que unimos
            return true;
        }
        return false;
    }

    public int getNumCircuits() {
        return numCircuits;
    }
}
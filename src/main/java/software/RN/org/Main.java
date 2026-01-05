package software.RN.org;

import software.RN.org.service.CircuitManager;

import java.nio.file.Path;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static final Path INPUT_PATH = Path.of("src", "main", "resources", "input.txt");

    public static void main(String[] args) {
        try {
            // 1. Leer cajas
            List<Box> boxes = ManifoldReader.readBoxes(INPUT_PATH);
            int n = boxes.size();

            // 2. Calcular todas las posibles conexiones (Aristas)
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    Box b1 = boxes.get(i);
                    Box b2 = boxes.get(j);
                    pq.add(new Edge(b1.id(), b2.id(), b1.dist(b2)));
                }
            }

            // 3. Procesar EXACTAMENTE las 1000 conexiones más cortas
            CircuitManager circuitManager = new CircuitManager(n);
            Box lastBox1 = null;
            Box lastBox2 = null;

            // Seguimos conectando hasta que todo sea un solo circuito
            while (!pq.isEmpty() && circuitManager.getNumCircuits() > 1) {
                Edge edge = pq.poll();

                // Si unite devuelve true, es que esta conexión ha unido dos grupos
                if (circuitManager.unite(edge.id1(), edge.id2())) {
                    // Si acabamos de llegar a 1 circuito, esta fue la última conexión necesaria
                    if (circuitManager.getNumCircuits() == 1) {
                        lastBox1 = boxes.get(edge.id1());
                        lastBox2 = boxes.get(edge.id2());
                    }
                }
            }

            if (lastBox1 != null && lastBox2 != null) {
                long result = (long) lastBox1.x() * lastBox2.x();
                System.out.println("Última conexión entre X=" + lastBox1.x() + " y X=" + lastBox2.x());
                System.out.println("Resultado (multiplicación de X): " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import java.util.ArrayList;

public class GrafoDir {
    private int numVertices;
    private ArrayList<ArrayList<Integer>> adjList;

    public GrafoDir(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new ArrayList<ArrayList<Integer>>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            this.adjList.add(new ArrayList<Integer>());
        }
    }

    public void addEdge(int src, int dest) {
        this.adjList.get(src).add(dest);

    }

    // Método adicional para agregar vértices al grafo dirigido
    public void addVertex() {
        this.adjList.add(new ArrayList<Integer>());
        this.numVertices++;
    }

    public int getNumVertices() {
        return numVertices;
    }

    // Método adicional para obtener una lista de vértices
    public ArrayList<Integer> getVertex() {
        ArrayList<Integer> vertex = new ArrayList<>();
        for(int i = 0; i < this.adjList.size(); i++) {
            vertex.add(i);
        }
        return vertex;
    }

    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GrafoDir g = new GrafoDir(7); // Se agregan dos vértices
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        
        // Agregando nuevas aristas de una sola dirección
        g.addEdge(4, 2); // Arista de 4 hacia 2
        g.addEdge(2, 5); // Arista de 2 hacia 5
        g.addEdge(3, 6); // Arista de 3 hacia 6
        g.addEdge(5, 0); // Arista de 5 hacia 0
        g.printGraph();
    }
}
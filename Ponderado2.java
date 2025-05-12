import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import java.util.Scanner;

public class Ponderado2 {
    public static void main(String[] args) {
        
        List<Node> graph = new ArrayList<>();

        addVertex(graph, "A"); // vértice A
        addVertex(graph, "B"); // vértice B
        addVertex(graph, "C"); // vértice C

        // Se añaden 5 vértices más para probar el algoritmo de Prim
        addVertex(graph, "D");
        addVertex(graph, "E");
        addVertex(graph, "F");
        addVertex(graph, "G");
        addVertex(graph, "H");
        
        // addEdge(graph, "A", "B", 4);
        // addEdge(graph, "B", "C", 6);

        // Se añaden 8 aristas con peso para probar el algoritmo de Prim
        addEdge(graph, "A", "B", 4);
        addEdge(graph, "A", "F", 2);
        addEdge(graph, "B", "E", 3);
        addEdge(graph, "B", "D", 3);
        addEdge(graph, "D", "C", 2);
        addEdge(graph, "D", "H", 3);
        addEdge(graph, "C", "F", 3);
        addEdge(graph, "C", "G", 4);
        addEdge(graph, "G", "H", 3);

        System.out.println("Vértices: " + getVertices(graph));
        System.out.println("Aristas con peso: " + getEdges(graph));

        // Probando el algoritmo de Prim
        System.out.println("\nAplicando el algoritmo de Prim para Árbol de Expansión Mínimo");
        printMST(primMST(graph), graph);

    }

    private static void addVertex(List<Node> graph, String id) {
        graph.add(new Node(id));
    }

    private static void addEdge(List<Node> graph, String id1, String id2, int weight) {
        Node node1 = getNodeById(graph, id1);
        Node node2 = getNodeById(graph, id2);
        node1.addEdge(node2, weight);
        node2.addEdge(node1, weight);
    }

    private static List<String> getVertices(List<Node> graph) {
        List<String> vertices = new ArrayList<>();
        for (Node node : graph) {
            vertices.add(node.getId());
        }
        return vertices;
    }

    private static List<String> getEdges(List<Node> graph) {
        List<String> edges = new ArrayList<>();
        for (Node node : graph) {
            for (Edge edge : node.getEdges()) {
                Node neighbor = edge.getNode();
                int weight = edge.getWeight();
                if (!edges.contains(node.getId() + "-" + neighbor.getId() + "(" + weight + ")")
                        && !edges.contains(neighbor.getId() + "-" + node.getId() + "(" + weight + ")")) {
                    edges.add(node.getId() + "-" + neighbor.getId() + "(" + weight + ")");
                }
            }
        }
        return edges;
    }

    private static Node getNodeById(List<Node> graph, String id) {
        for (Node node : graph) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    //  -- Ejercicio 6. Algoritmo de Prim para Árbol de Expansión Mínimo -- //
    private static List<Edge> primMST(List<Node> graph) {
        List<Edge> MST = new ArrayList<>(); // Lista para almacenar el árbol de expansión mínimo
        PriorityQueue<Edge> PQ = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight)); // Cola de prioridad para seleccionar la arista de menor peso
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingresa el valor del vértice inicial: ");
        String startNodeId = entrada.nextLine();

        Node startNode = getNodeById(graph, startNodeId); // Se obtiene el nodo en el grafo por el ID proporcionado por el usuario

        if(startNode == null) { // Verifica si el nodo inicial es válido
            System.out.println("El vértice ingresado no existe en el grafo.");
            entrada.close();
            return MST; // Retorna la lista vacía
        }

        List<Node> visitados = new ArrayList<>(); // Lista para llevar el control de nodos visitados

        visitados.add(startNode); // Marca el nodo inicial como visitado
        PQ.addAll(startNode.getEdges()); // Agrega todas las aristas del nodo inicial a la cola de prioridad

        // Mientras haya aristas en la cola de prioridad
        while(!PQ.isEmpty()) {
            Edge arista = PQ.poll(); // Extrae la arista de menor peso
            Node nodoActual = arista.getNode(); // Obtiene el nodo al que conduce la arista

            // Agrega la arista al árbol de expansión mínimo y marca el nodo actual como visitado si el nodo actual no ha sido visitado
            if(!visitados.contains(nodoActual)) {
                visitados.add(nodoActual);
                MST.add(arista);

                // Agrega las aristas del nodo actual a la cola de prioridad si los nodos a los que apuntan no estan marcados como visitados
                for(Edge a : nodoActual.getEdges()) {
                    if(!visitados.contains(a.getNode())) {
                        PQ.add(a);
                    }
                }
            }
        }
        entrada.close();

        return MST;
    }

    private static void printMST(List<Edge> MST, List<Node> graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // Itera sobre las aristas del árbol de expansión mínima 
        for(int i = 0; i < MST.size(); i++) {
            Edge arista = MST.get(i);
            Node node = arista.getNode(); // Obtiene el nodo al que conduce la arista
            Node neighbor = null; // Inicializa la variable para almacenar el nodo adyacente
            
            for(Node n : graph) {
                for(Edge a : n.getEdges()) {
                    // Compara si la arista actual es la misma que la arista de otro nodo junto al peso
                    if(a.getNode() == node && a.getWeight() == arista.getWeight()) {
                        neighbor = n; // Asigna el nodo adyacente
                        break;
                    }
                }
                if(neighbor != null)
                    break; // Sale del bucle si se encuentra el vecino 
            }
            
            // Formatea la salida del árbol de expansión mínima
            sb.append(neighbor.getId()).append("-").append(node.getId()).append("(").append(arista.getWeight()).append(")");
            
            if(i < MST.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        System.out.println("MST: " + sb.toString()); // Imprime el árbol de expansión mínima
    }

    // Clases Node y Edge
    static class Node {
        private String id;
        private List<Edge> edges;

        public Node(String id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void addEdge(Node node, int weight) {
            edges.add(new Edge(node, weight));
        }
    }

    static class Edge {
        private Node node;
        private int weight;

        public Edge(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public Node getNode() {
            return node;
        }

        public int getWeight() {
            return weight;
        }
    }
}
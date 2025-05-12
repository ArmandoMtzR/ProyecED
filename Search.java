import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;


public class Search {
    // --- Ejercicio 2. Algoritmo BFS ---
    public static List<Integer> searchBFS(List<List<Integer>> graph, int vr) {
        List<Integer> visitados = new ArrayList<>();
        Queue<Integer> cola = new LinkedList<>();

        cola.add(vr);
        visitados.add(vr);

        while (!cola.isEmpty()) {
            // Obtener y remover el nodo actual de la cola
            int nodoActual = cola.poll();
            
            // Explorar los vecinos del nodo 
            for (int vecino : graph.get(nodoActual)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);    // Marcar el vecino como visitado
                    cola.add(vecino);        // Añadir el vecino a la cola
                }
            }
        }
        return visitados;
    }

    // --- Ejercicio 3. Algoritmo DFS --- 
    public static List<Integer> searchDFS(List<List<Integer>> graph, int starVertex) {
        List<Integer> visitados = new ArrayList<>(); // Lista para almacenar los nodos visitados
        Stack<Integer> pila = new Stack<>(); // Pila para aplicarla en la recursividad
        DFS(graph, starVertex, visitados, pila);
        return visitados;
    }

    public static void DFS(List<List<Integer>> graph, int vertex, List<Integer> visitados, Stack<Integer> pila) {
        pila.push(vertex);

        while(!pila.isEmpty()) {
            //Sacar el vértice de la pila
            int verticeActual = pila.pop();

            // Si no está visitado, marcarlo como visitado
            if(!visitados.contains(verticeActual)) {
                visitados.add(verticeActual); // Se agrega a la lista de visitados
                
                for(int w : graph.get(verticeActual)) { // Se revisan los adyacentes del vértice actual
                    // Si no está marcado como visitado, se aplica recursividad
                    if(!visitados.contains(w)) {
                        DFS(graph, w, visitados, pila);
                    }
                }
            }
        }
    }

    // Método adicional para obtener la lista de nodos visitados con sus respectivos valores
    public static List<String> printVisited(List<Integer> visitados, List<String> valoresVerticesNoDirigido) {
        List<String> valoresVerticesVisitados = new ArrayList<>();
        for(int i = 0; i < visitados.size(); i++) {
            valoresVerticesVisitados.add(valoresVerticesNoDirigido.get(visitados.get(i)));
        }
        return valoresVerticesVisitados;
    }
}
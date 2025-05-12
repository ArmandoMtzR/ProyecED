import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op, subOp;

        do {
            System.out.println("--- Menú de usuario ---");
            System.out.println("1.- Grafo no dirigido");
            System.out.println("2.- Grafo dirigido");
            System.out.println("3.- Salir");
            System.out.println("Selecciona una opción:");
            op = scanner.nextInt();

            switch(op) {
// -- GRAFO NO DIRIGIDO -- //
                case 1 -> {
                    List<String> valoresVerticesNoDirigido = new ArrayList<>(); // Para almacenar los valores de los vértices
                    List<List<Integer>> graph = new ArrayList<>();
                    do {
                        System.out.println("\n-- GRAFO NO DIRIGIDO --");
                        System.out.println("1.- Añadir vértices");
                        System.out.println("2.- Añadir aristas");
                        System.out.println("3.- Mostrar el algoritmo BFS");
                        System.out.println("4.- Mostrar el algoritmo DFS");
                        System.out.println("5.- Mostrar vértices y aristas");
                        System.out.println("6.- Salir a menú general");
                        System.out.println("Selecciona la operación que deseas realizar:");
                        subOp = scanner.nextInt();
                        switch(subOp) {
                            case 1 -> {
                                // De esta forma es posible agregar más de un vértice en una sola iteración
                                System.out.println("¿Cuál es la cantidad que vértices que deseas añadir?");
                                int numVertices = scanner.nextInt();

                                for(int i = 0; i < numVertices; i++) {
                                    System.out.print("Ingresa el valor del vértice "+ (i + 1) +" ["+ (graph.size()) +"]: ");
                                    String valor = scanner.next();
                                    Grafo.addVertex(graph); // Se agrega el vértice al grafo
                                    valoresVerticesNoDirigido.add(valor); // Se agrega el valor del grafo a la lista de valores
                                }
                                System.out.println("Vértices añadidos exitosamente."); // Mensaje de éxito

                            }
                            case 2 -> {
                                // De esta forma es posible agregar más de una arista en una sola iteración
                                System.out.println("¿Cuál es la cantidad que aristas que deseas añadir?");
                                int numAristas = scanner.nextInt();

                                for(int i = 0; i < numAristas; i++) {
                                    System.out.print("Ingresa el vértice 1 (índice): ");
                                    int v1 = scanner.nextInt();
                                    System.out.print("Ingresa el vértice 2 (índice): ");
                                    int v2 = scanner.nextInt();

                                    if(v1 < graph.size() && v2 < graph.size()) { // Verifica que ambos vértices estén en el grafo
                                        Grafo.addEdge(graph, v1, v2);
                                        System.out.println("Arista entre "+ valoresVerticesNoDirigido.get(v1) +" ["+ v1 +"] y "
                                        + valoresVerticesNoDirigido.get(v2) +" ["+ v2 +"] añadida."); // Mensaje de éxito
                                    } else {
                                        System.out.println("Error: Uno de los vértices no existe."); // Mensaje de error
                                        i--;
                                    }
                                }
                            }
// -- Algoritmo BFS -- //
                            case 3 -> {
                                System.out.println("Ingresa el vértice donde quieres iniciar (índice): ");
                                int verticeInicial = scanner.nextInt(); // Cambié vr por verticeInicial
                                System.out.println("Nodos visitados usando BFS:");
                                System.out.println(Search.searchBFS(graph, verticeInicial));
                                //System.out.println(Search.printVisited(Search.searchBFS(graph, verticeInicial), valoresVerticesNoDirigido));
                            }
// -- Algoritmo DFS -- //
                            case 4 -> {
                                System.out.println("Ingresa el vértice donde quieres iniciar (índice): ");
                                int verticeInicial = scanner.nextInt();

                                System.out.println(Search.searchDFS(graph, verticeInicial));
                                System.out.println(Search.printVisited(Search.searchDFS(graph, verticeInicial), valoresVerticesNoDirigido));
                            }
                            case 5 -> {
                                System.out.println("Vértices (valores): " + valoresVerticesNoDirigido);
                                System.out.println("Vértices (índices): " + Grafo.getVertices(graph));
                                System.out.println("Aristas: " + Grafo.getEdges(graph));
                            }
                            case 6 -> System.out.println("Saliendo de grafo no dirigido...");
                            default -> System.out.println("Opción inválida.");
                        }
                    } while(subOp != 6);
                }
// -- GRAFO DIRIGIDO -- //
                case 2 -> {
                    List<String> valoresVerticesDirigido = new ArrayList<>(); // Para almacenar los valores de los vértices
                    GrafoDir graph = new GrafoDir(0); // Se inicializa el grafo dirigido con cero vértices
                    
                    do{
                        System.out.println("\n-- GRAFO DIRIGIDO --");
                        System.out.println("1.- Añadir vértices");
                        System.out.println("2.- Añadir aristas");
                        System.out.println("3.- Mostrar lista de adyacencia");
                        System.out.println("4.- Salir a menú general");
                        System.out.println("Selecciona la operación que deseas realizar:");
                        subOp = scanner.nextInt();
                        switch(subOp) {
                            case 1 -> {
                                // De esta forma es posible agregar más de un vértice en una sola iteración
                                System.out.println("¿Cuál es la cantidad que vértices que deseas añadir?");
                                int numVertices = scanner.nextInt();

                                for(int i = 0; i < numVertices; i++) {
                                    System.out.print("Ingresa el valor del vértice "+ (i + 1) +" ["+ (graph.getNumVertices()) +"]: ");
                                    String valor = scanner.next();
                                    graph.addVertex(); // Se agrega el vértice al grafo
                                    valoresVerticesDirigido.add(valor); // Se agrega el valor del vértice a la lista de valores 
                                }
                                System.out.println("Vértices añadidos exitosamente."); // Mensaje de éxito
                            }
                            case 2 -> {
                                // De esta forma es posible agregar más de una arista en una sola iteración
                                System.out.println("¿Cuál es la cantidad que aristas que deseas añadir?");
                                int numAristas = scanner.nextInt();

                                for(int i = 0; i < numAristas; i++) {
                                    System.out.print("Ingresa el vértice de origen (índice):");
                                    int src = scanner.nextInt();
                                    System.out.print("Ingresa el vértice de destino (índice):");
                                    int dest = scanner.nextInt();

                                    if(src < graph.getNumVertices() && dest < graph.getNumVertices()) { // Verifica que ambos vértices estén en el grafo
                                        graph.addEdge(src, dest);
                                        System.out.println("Arista añadida entre " + valoresVerticesDirigido.get(src) + " ["+ src +"] y "
                                        + valoresVerticesDirigido.get(dest) + " ["+ dest +"]."); // Mensaje de éxito
                                    } else {
                                        System.out.println("Error: Uno de los vértices no existe."); // Mensaje de error
                                        i--;
                                    }
                                }
                            }
                            case 3 -> {
                                System.out.println("Vértices (valores): "+ valoresVerticesDirigido);
                                System.out.println("Vértices (índices): "+ graph.getVertex());
                                System.out.println("\nGrafo representado por lista de adyacencia:");
                                graph.printGraph();
                            }
                            case 4 -> System.out.println("Saliendo de grafo dirigido...");
                            default -> System.out.println("Opción inválida.");
                        }
                    } while(subOp != 4);
                }
                case 3 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }

        } while (op != 3);
        scanner.close();
    }
}
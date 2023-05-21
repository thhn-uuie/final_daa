import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class Test {
    public static int[][] random(int V){
        int[][] adjMatrix = new int[V][V];
        Random random = new Random();
        for (int i = 0; i < V; i++){
            for (int j = 0; j < V; j++){
                int weight = random.nextInt(2*V) + 1;
                adjMatrix[i][j] = weight;
                adjMatrix[j][i] = weight;
            }
        }
        return adjMatrix;
    }

    public static void writeFile(int V, String nameFile) throws IOException {
        for (int i = 0; i < 5; i++) {
            int[][] adj = random(V);
            FileWriter writer = new FileWriter(nameFile, true);
            for (int k = 0; k < V; k++) {
                for (int j = 0; j < V; j++) {
                    writer.write(adj[k][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
        }

    }

    public static int[][] readFile(int begin, int end, String nameFile) throws FileNotFoundException {
        if (begin > end) {
            return null;
        }
        int size = end - begin + 1;
        File file = new File(nameFile);
        Scanner scan = new Scanner(file);
        int[][] adj = new int[size][size];
        int lineNumber = 1;
        List<String> lines = new ArrayList<>();
        while (scan.hasNextLine() && lineNumber <=  end){
            String line = scan.nextLine();
            if (lineNumber >= begin){
                lines.add(line);
            }
            lineNumber++;
        }
        for (int i = 0; i < size; i++){
            String[] words = lines.get(i).split(" ");
            for (int j = 0; j < size; j++){
                adj[i][j] = Integer.valueOf(words[j]);
            }
        }
        scan.close();
        return adj;
    }

    public static void main(String[] args) throws IOException {
        int[] sizes = {10, 100, 1000};
        int loop = 5;
        File file = new File("data.txt");
        if (file.exists()){
            file.delete();
        }
        for (int size : sizes){
            writeFile(size, "data.txt");
        }
        ArrayList times = new ArrayList(sizes.length);

        int count = 0;
        for (int size : sizes){
            int sum = 0;
            for (int i = 0; i < loop; i++){
                int[][] matrix = readFile(count + 1, size + count, "data.txt");
                List<List<Node>> adj = Dijkstra.adjacencyList(matrix);
                count += size;
                Dijkstra g = new Dijkstra(size, adj);
                long startTime = System.nanoTime();
                g.dijkstra(0, size);
                long endTime = System.nanoTime();
                sum += (endTime - startTime);
            }
            times.add(sum/loop);
        }
        System.out.println(times.toString());

    }

    public static void printMatrix(int[][] adj){
        // in ma trận ra màn hình
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package Practice;
import java.util.*;
public class Practice1 {
    public static void main(String[] args) {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 4},
                {4, 3},
                {5, 6},
                {5, 7},
                {6, 7},
                {8, 9}
        };
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int visited[] = new int[10];
        Arrays.fill(visited, -1);
        int count = 0;
        for(int i = 1 ; i < 10; i++){
            if(visited[i] == -1){
                BFS(graph, i, visited);
                count++;
            }
        }
        System.out.println(count);
    }
    public static void BFS(List<List<Integer>> graph, int src, int visited[]){
        visited[src] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()){
            int node = q.poll();

            for(int nbr : graph.get(node)){
                if(visited[nbr] == -1){
                    q.add(nbr);
                    visited[nbr] = 1;
                }
            }
        }
    }
}

package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Day3BOJ1238 {
 
    private static final int INF = Integer.MAX_VALUE>>1;
    private static int N, M, X;
    private static List<List<City>> list, returnList;
    private static int[] dist, returnDist;
    private static boolean[] visited, returnVisited;
 
    static class City implements Comparable<City> {
        int num,ddist;
        public City(int num, int ddist) {
            this.num = num;
            this.ddist = ddist;
        }
        public int compareTo(City o) {
            return this.ddist - o.ddist;
        }
    }
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
 
        list = new ArrayList<>(); // 가는용
        returnList = new ArrayList<>(); // 오는용
        
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            returnList.add(new ArrayList<>());
        }
 
        // 가는놈
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        // 오는놈
        returnDist = new int[N + 1];
        Arrays.fill(returnDist, INF);
        // 방문체크
        visited = new boolean[N + 1];
        returnVisited = new boolean[N + 1];
 
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            list.get(from).add(new City(to, time));
            returnList.get(to).add(new City(from, time));
        }
 
        dijkstra(visited,dist,list);
        dijkstra(returnVisited,returnDist,returnList);
       
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist[i]+returnDist[i]);
        }
        System.out.println(ans);
    }
 
    private static void dijkstra(boolean[] visited, int[] distance, List<List<City>> list) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(X, 0));
        
        distance[X] = 0;
 
        while (!pq.isEmpty()) {
            int idx = pq.poll().num;
 
            if (visited[idx]) continue;
            visited[idx] = true;
 
            for (City node : list.get(idx)) {
                if (distance[node.num] > distance[idx] + node.ddist) {
                    distance[node.num] = distance[idx] + node.ddist;
                    pq.add(new City(node.num, distance[node.num]));
                }
            }
        }
    }
 
}

import java.util.*;
class Solution {
    TreeMap<String,Integer> map;
     ArrayList<Character> arr1;
     ArrayList<Character> arr2;
    boolean[] check = new boolean[27];
    ArrayList<Node>[] arr3 = new ArrayList[11];
    ArrayList<String> arr4 = new ArrayList<String>();
    class Node {
        String s;
        int len;
        
        public Node(String s ,int len) {
            this.s = s;
            this.len = len;
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
       
        boolean[] check = new boolean[11];
         ArrayList<String> AA = new ArrayList<String>();
       arr1 = new ArrayList<Character>();
       arr2 = new ArrayList<Character>();
        map = new TreeMap<>();
        int num=0;
        
        for(int i=0; i<11; i++) {
            arr3[i] = new ArrayList<Node>();
        }
        
        

        for(int i=0; i<course.length; i++) {
            num = course[i];
            for(int j=0; j<orders.length;j++) {
                dfs(orders[j],0,0,num);
            }
        }
        int ccnt=0;
        while(!map.isEmpty()) {
            if(map.get(map.firstKey()) ==1) {
                map.remove(map.firstKey());
            }
            else {
               // AA.add(map.firstKey());
               String fk =  map.firstKey();
               int len = map.get(fk);
                
                if(arr3[fk.length()].size() !=0 ) {
                    if(arr3[fk.length()].get(0).len <len) {
                        ccnt = ccnt - arr3[fk.length()].size();
                        arr3[fk.length()].clear();
                        arr3[fk.length()].add(new Node(fk,len));
                        ccnt++;
                    }
                    else if(arr3[fk.length()].get(0).len  == len) {
                        arr3[fk.length()].add(new Node(fk,len));
                        ccnt++;
                    }
                }
                else {
                    arr3[fk.length()].add(new Node(fk,len));
                    ccnt++;
                }                
                map.remove(map.firstKey());
            }
        }
        int cccount=0;
        String[] answer = new String[ccnt];
        for(int i=0; i<11; i++) {
            if(arr3[i].size() ==0) {
                continue;
            }
            for(int j=0; j<arr3[i].size(); j++) {
                answer[cccount++] = arr3[i].get(j).s;
            }
            
        }
        
        Arrays.sort(answer);
        
        
        
        // int size = AA.size();
        // String[] answer = new String[size];
        // int cnt =0;
        
        
        
        
        
        // for(int i=0; i<size; i++) {
        //     answer[cnt++] = AA.get(i);
        // }
        // Arrays.sort(answer);
        
        
        return answer;
    }
    
    
    String deepcopy() {
        int size = arr1.size();
        
        for(int i=0; i<size;i++) {
            arr2.add(arr1.get(i));
        }
        Collections.sort(arr2);
        String result = "";
        for(int i=0; i<size; i++) {
            result += arr2.get(i);
        }
        arr2.clear();
        return result;
    }
    
    
    void dfs(String s,int cnt,int idx,int num) { //조합,선택갯수,시작,선택길이
        if(cnt == num) {
           String result = deepcopy();
            
            if(map.containsKey(result)) {
                map.put(result,map.get(result)+1);
            }
            else {
                map.put(result,1);
            }
            return;
        }
        
        for(int i=idx; i<s.length(); i++) {
            if(check[s.charAt(i)-'A'] == false) {
                arr1.add(s.charAt(i));
                check[s.charAt(i)-'A'] = true;
                dfs(s,cnt+1,i+1,num);
                check[s.charAt(i)-'A'] = false;
                arr1.remove(arr1.size()-1);
            } 
        }
        
        
    }
    
    
    
    
    
}
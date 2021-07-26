import java.util.*;
class Solution {
    TreeMap<String,ArrayList<Integer>> map = new TreeMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        init(info);
        for (int i = 0; i < query.length; i++) {
            String tmp = query[i].replace(" and ","");
			String queries[] = tmp.split(" ");
			if (map.containsKey(queries[0])) {
				ArrayList<Integer> list = map.get(queries[0]);
				answer[i] = binarySearch(list, Integer.parseInt(queries[1]));
			}
			else		
				answer[i] = 0;
		}
        
        return answer;
    }
    
    
    
    void init(String[] info) {
       for(int i=0; i<info.length; i++) {
            String[] tmp = info[i].split(" ");
			String[] keys = { tmp[0] + tmp[1] + tmp[2] + tmp[3], "-" + tmp[1] + tmp[2] + tmp[3],
					tmp[0] + "-" + tmp[2] + tmp[3], tmp[0] + tmp[1] + "-" + tmp[3], tmp[0] + tmp[1] +
                    tmp[2] + "-",
					"--" + tmp[2] + tmp[3], "-" + tmp[1] + "-" + tmp[3], "-" + tmp[1] + tmp[2] + "-",
					tmp[0] + "--" + tmp[3], tmp[0] + "-" + tmp[2] + "-", tmp[0] + tmp[1] + "--", "---"                      + tmp[3],
					"--" + tmp[2] + "-", "-" + tmp[1] + "--", tmp[0] + "---", "----" };
			Integer score = Integer.parseInt(tmp[4]);
            
        for(int j=0; j<keys.length; j++) {
            String key = keys[j];
    
            if(!map.containsKey(key)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(key,list);
            }
            else {
                ArrayList<Integer> list = map.get(key);
                list.add(score);
                map.put(key,list);  /////////
            }
          }
        }
        Set<String> keyset = map.keySet();
		Iterator<String> it = keyset.iterator();
		while (it.hasNext()) {
			Collections.sort(map.get(it.next()));
		}

    }
    
    
    public int binarySearch(ArrayList<Integer> list, int score) {
		int left = 0, right = list.size() - 1;

		if (list.get(0) >= score)
			return list.size();

		while (left <= right) {
			int mid = (left + right) / 2;
			if (list.get(mid) > score)
				right = mid-1;
			else if (list.get(mid) < score)
				left = mid+1;
			else {
				int index = mid;
				for (int i = index; i >= 0; i--) {
					if (list.get(i) >= score)
						index = i;
					else
						break;
				}
				return list.size()-index;
			}
		}

		return list.size()-left;
	}


}
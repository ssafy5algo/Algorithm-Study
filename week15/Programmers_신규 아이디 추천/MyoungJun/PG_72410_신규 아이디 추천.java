import java.util.*;
class Solution {
    public String solution(String new_id) {
        String answer = "";
        String s = new_id;
        Character[] alpa = new Character[26];
        StringBuilder sb = new StringBuilder();

        new_id = new_id.toLowerCase(); // 1단계 : 소문자로 모두 변경

        char[] sentence = new_id.toCharArray(); // 문자형으로 한개씩 넣기

        int size = sentence.length;
        char one;
        for(int i=0; i<size; i++) { // 2단계 : 소문자 / 숫자 / - / _ / .
            one = sentence[i];
            if((one>='a' && one<='z') || (one>='0' && one <='9') ||
                    one =='-' || one =='_' || one =='.') {
                sb.append(one);
            }
        }
        new_id = sb.toString();
        while(true) { // 3단계 : . 2개이상일 시 .로 치환
            if(new_id.contains("..")) {
                new_id = new_id.replace("..",".");
            }
            else {
                break;
            }
        }

        if(new_id.length() >0) { // 4-1 단계 : 처음 . 제거
            if(new_id.charAt(0) =='.') {
                new_id = new_id.substring(1,new_id.length());
            }
        }

        if(new_id.length() >0) { // 4-2 단계 : 마지막 . 제거
            if(new_id.charAt(new_id.length()-1) =='.') {
                new_id = new_id.substring(0,new_id.length()-1);
            }
        }

        if(new_id.length() ==0) { // 5단계 : 빈 문자열이라면 a 대입
            new_id = "a";
        }


        if(new_id.length() >=16) { // 6단계 : 0-15개 빼고 삭제
            new_id = new_id.substring(0,15);
            if(new_id.charAt(14) =='.') {
                new_id = new_id.substring(0,14);
            }
        }

        if(new_id.length() <=2) { // 7단계 : 길이 2이하일 때 마지막 문자 3까지 늘리기
            while(new_id.length() !=3) {
                new_id += new_id.charAt(new_id.length()-1);
            }

        }





        return new_id;
    }
}
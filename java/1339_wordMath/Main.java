package free.wordMath;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N;
    static int totalAlpha = 0;
    static ArrayList<char[]> words= new ArrayList(10);
    static ArrayList<Character> chars = new ArrayList<>(10);
    static boolean[] isValueUsed = new boolean[10];
    static int[] vals = new int[26];
    static int maxVal = 0;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/free/wordMath/in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        boolean[] usingAlpha = new boolean[26];

        for (int i = 0; i < N; i++) {
            char[] currInput = br.readLine().toCharArray();
            words.add(currInput);
            for (int j = 0; j < currInput.length; j++) {
                usingAlpha[cToIn(currInput[j])] = true;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (usingAlpha[i]){
                totalAlpha++;
                chars.add((char)('A' + i));
            }
        }
        dfs(0);
        System.out.println(maxVal);
    }
    static void dfs(int depth){
        if (depth == totalAlpha){
            maxVal = Math.max(maxVal, cal());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (isValueUsed[i]) continue;
            isValueUsed[i] = true;
            vals[cToIn(chars.get(depth))] = i;
            dfs(depth + 1);
            isValueUsed[i] = false;
        }
    }
    static int cToIn(char c){
        return (int)(c - 'A');
    }
    static int cal(){
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int temp = 0;
            char[] currCharArr = words.get(i);
            for (int j = 0; j < currCharArr.length; j++) {
                temp *= 10;
                temp += vals[cToIn(currCharArr[j])];
            }
            sum += temp;
        }
        return sum;
    }
}

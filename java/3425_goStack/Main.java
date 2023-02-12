package algo.goStack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> instructions;
    static List<Integer> nums;
    static LinkedList<Long> stack = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/algo/goStack/in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        boolean isQuit = false;
        while (!isQuit){
            instructions = new ArrayList<>();
            nums = new ArrayList<>();
            boolean isEnd = false;
            while (!isEnd){
                st = new StringTokenizer(br.readLine(), " ");
                String input = st.nextToken();
                if (input.equals("QUIT")){
                    isQuit = true; break;
                } else if (input.equals("END")){
                    isEnd = true;
                } else if (input.equals("NUM")){
                    instructions.add(1);
                    nums.add(Integer.parseInt(st.nextToken()));
                } else if (input.equals("POP")){
                    instructions.add(2);
                } else if (input.equals("INV")){
                    instructions.add(3);
                } else if (input.equals("DUP")){
                    instructions.add(4);
                } else if (input.equals("SWP")){
                    instructions.add(5);
                } else if (input.equals("ADD")){
                    instructions.add(6);
                } else if (input.equals("SUB")){
                    instructions.add(7);
                } else if (input.equals("MUL")){
                    instructions.add(8);
                } else if (input.equals("DIV")){
                    instructions.add(9);
                } else if (input.equals("MOD")){
                    instructions.add(10);
                }
            }
            if (isQuit) continue;
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                boolean isError = false;
                int numsIndex = 0;
                long vi = Integer.parseInt(br.readLine());
                stack.add(vi);
                for (Integer inte : instructions) {
                    if (inte == 1){
                        stack.add((long) nums.get(numsIndex++));
                    } else if (inte == 2){
                        if (stack.isEmpty()){
                            isError = true; break;
                        }
                        stack.removeLast();
                    } else if (inte == 3){
                        if (stack.isEmpty()){
                            isError = true; break;
                        }
                        long temp = stack.removeLast();
                        stack.add(-temp);
                    } else if (inte == 4){
                        if (stack.isEmpty()){
                            isError = true; break;
                        }
                        long temp = stack.getLast();
                        stack.add(temp);
                    } else if (inte == 5){
                        if (stack.size() < 2){
                            isError = true; break;
                        }
                        long temp1 = stack.removeLast();
                        long temp2 = stack.removeLast();
                        stack.add(temp1);
                        stack.add(temp2);
                    } else if (inte == 6){
                        if (stack.size() < 2){
                            isError = true; break;
                        }
                        long temp1 = stack.removeLast();
                        long temp2 = stack.removeLast();
                        long plus = temp1 + temp2;
                        if (plus < -1e9 || 1e9 < plus){
                            isError = true; break;
                        }
                        stack.add(plus);
                    } else if (inte == 7){
                        if (stack.size() < 2){
                            isError = true; break;
                        }
                        long temp1 = stack.removeLast();
                        long temp2 = stack.removeLast();
                        long minus = temp2 - temp1;
                        if (minus < -1e9 || 1e9 < minus){
                            isError = true; break;
                        }
                        stack.add(minus);
                    } else if (inte == 8){
                        if (stack.size() < 2){
                            isError = true; break;
                        }
                        long temp1 = stack.removeLast();
                        long temp2 = stack.removeLast();
                        long mul = temp2 * temp1;
                        if (mul < -1e9 || 1e9 < mul){
                            isError = true; break;
                        }
                        stack.add(mul);
                    } else if (inte == 9){
                        if (stack.size() < 2){
                            isError = true; break;
                        }
                        long temp1 = stack.removeLast();
                        long temp2 = stack.removeLast();
                        if (temp1 == 0){
                            isError = true; break;
                        }
                        long div = Math.abs(temp2) / Math.abs(temp1);
                        stack.add(temp1 * temp2 < 0 ? -div : div);
                    } else if (inte == 10){
                        if (stack.size() < 2){
                            isError = true; break;
                        }
                        long temp1 = stack.removeLast();
                        long temp2 = stack.removeLast();
                        if (temp1 == 0){
                            isError = true; break;
                        }
                        long mod = Math.abs(temp2) % Math.abs(temp1);
                        stack.add(temp2 < 0 ? -mod : mod);
                    }
                }
                if (stack.size() != 1 || isError){
                    sb.append("ERROR").append("\n");
                    stack.clear();
                } else sb.append(stack.removeFirst()).append("\n");
            }
            br.readLine();
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }
}

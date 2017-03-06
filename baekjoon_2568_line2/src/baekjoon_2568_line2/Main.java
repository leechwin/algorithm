package baekjoon_2568_line2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static List<Line> lists = new ArrayList<Line>();
    public static int MAX_LIS;

    // unused
    public static ArrayList<Line> binary_search_list = new ArrayList<Line>();

    static class Line implements Comparable<Line> {
        public int a;
        public int b;
        public int lis;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line line) {
            return this.a > line.a ? 1 : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("input.txt");
        System.setIn(fis);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lists.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lists);
        LIS();
        // solve();
        // FIXME: http://www.jungol.co.kr/theme/jungol/reinfo.php?sid=1793898
    }

    /**
     * O(N^2)
     */
    private static void LIS() {
        for (int i = 0; i < N; i++) {
            Line line = lists.get(i);
            line.lis = 1;
            for (int j = 0; j < i; j++) {
                Line pre = lists.get(j);
                if (line.b > pre.b && line.lis < pre.lis + 1) {
                    line.lis = pre.lis + 1;
                    if (MAX_LIS < line.lis) {
                        MAX_LIS = line.lis;
                    }
                }
            }
        }
        // System.out.println(MAX_LIS);

        ArrayList<Line> temp = new ArrayList<Line>();
        for (int i = N - 1; i >= 0; i--) {
            Line line = lists.get(i);
            if (line.lis == MAX_LIS) {
                for (int j = i - 1; j >= 0; j--) {
                    Line pre = lists.get(j);
                    if (pre.b < line.b && pre.lis == line.lis - 1) {
                        line = pre;
                    } else {
                        temp.add(pre);
                    }
                }
                break;
            } else {
                temp.add(line);
            }
        }

        System.out.println(temp.size());
        for (int i = temp.size() - 1; i >= 0; i--) {
            System.out.println(temp.get(i).a);
        }
    }

    /*
    // O Nlog(N)
    private static void solve() {
        binary_search_list.add(lists.get(0));
        for (int i = 1; i < N; i++) {
            Line lastLine = binary_search_list.get(binary_search_list.size() - 1);
            Line currentLine = lists.get(i);
            if (lastLine.b < currentLine.b) {
                binary_search_list.add(currentLine);
            } else {
                int index = BinarySearch(0, binary_search_list.size() - 1, currentLine.b);
                binary_search_list.set(index, currentLine);
            }
        }
        for (Iterator iterator = binary_search_list.iterator(); iterator.hasNext();) {
            Line line = (Line) iterator.next();
            lists.remove(line);
        }
        System.out.println(lists.size());
        // for (Iterator iterator = lists.iterator(); iterator.hasNext();) {
        // Line line = (Line) iterator.next();
        // System.out.println(line.a);
        // }
    }
    
    private static int BinarySearch(int start, int end, int num) {
        if (start >= end) {
            return start;
        }
    
        int mid = (start + end) / 2;
        if (binary_search_list.get(mid).b == num) {
            return mid;
        } else if (binary_search_list.get(mid).b > num) {
            return BinarySearch(start, mid - 1, num);
        } else {
            return BinarySearch(mid + 1, end, num);
        }
    }
    */

}

/*
12026. Silver 1 - BOJ 거리

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           3063	    1853      1550	         61.802%


    문제
        BOJ 거리는 보도블록 N개가 일렬로 놓여진 형태의 도로이다. 도로의 보도블록은 1번부터 N번까지 번호가 매겨져 있다.
        스타트의 집은 1번에 있고, 링크의 집은 N번에 있다. 스타트는 링크를 만나기 위해서 점프해가려고 한다.
        BOJ거리의 각 보도블록에는 B, O, J 중에 하나가 쓰여 있다. 1번은 반드시 B이다.
        스타트는 점프를 통해서 다른 보도블록으로 이동할 수 있다. 이때, 항상 번호가 증가하는 방향으로 점프를 해야 한다. 만약, 스타트가 현재 있는 곳이 i번이라면, i+1번부터 N번까지로 점프를 할 수 있다. 한 번 k칸 만큼 점프를 하는데 필요한 에너지의 양은 k*k이다.
        스타트는 BOJ를 외치면서 링크를 만나러 가려고 한다. 따라서, 스타트는 B, O, J, B, O, J, B, O, J, ... 순서로 보도블록을 밟으면서 점프를 할 것이다.
        스타트가 링크를 만나는데 필요한 에너지 양의 최솟값을 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 1 ≤ N ≤ 1,000이 주어진다.
        둘째 줄에는 보도블록에 쓰여 있는 글자가 1번부터 순서대로 주어진다.


    출력
        스타트가 링크를 만나는데 필요한 에너지 양의 최솟값을 출력한다. 만약, 스타트가 링크를 만날 수 없는 경우에는 -1을 출력한다.


    예제 입력 1
        9
        BOJBOJBOJ
    예제 출력 1
        8

    예제 입력 2
        9
        BOJBOJBOJ
    예제 출력 2
        8

    예제 입력 3
        8
        BJJOOOBB
    예제 출력 3
        -1

    예제 입력 4
        13
        BJBBJOOOJJJJB
    예제 출력 4
        50

    예제 입력 5
        2
        BO
    예제 출력 5
        1

    예제 입력 6
        15
        BJBOJOJOOJOBOOO
    예제 출력 6
        52


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14440KB
// 시간 : 148ms
// 코드 길이 : 1948B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12026 {
    static int N; // 보도블록의 개수 (1 ≤ N ≤ 1000)
    static String roadStr; // 보도블록에 쓰여 있는 글자
    static int road[]; // 0부터 해당 인덱스의 보도블록까지 가는 데 드는 최소 비용을 저장하는 배열

    public static void find() { // 스타트가 링크를 만나는 데 필요한 에너지 양의 최솟값을 구하고 출력하는 메서드
        road = new int[N];

        Arrays.fill(road, Integer.MAX_VALUE);
        road[0] = 0;

        for (int n = 1; n < N; n++) {
            char nowAlphabet = roadStr.charAt(n);
            for (int i = 0; i < n; i++) {
                char prevAlphabet = roadStr.charAt(i);
                if (nowAlphabet == 'B') {
                    if (prevAlphabet != 'J' || road[i] == Integer.MAX_VALUE) {
                        continue;
                    }
                }
                else if (nowAlphabet == 'O') {
                    if (prevAlphabet != 'B' || road[i] == Integer.MAX_VALUE) {
                        continue;
                    }
                }
                else if (nowAlphabet == 'J') {
                    if (prevAlphabet != 'O' || road[i] == Integer.MAX_VALUE) {
                        continue;
                    }
                }

                road[n] = Math.min(road[n], road[i] + (int) Math.pow((n - i), 2));
            }
        }

        if (road[N - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(road[N - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        roadStr = bf.readLine();

        find();
    }
}

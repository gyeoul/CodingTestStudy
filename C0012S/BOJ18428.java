/*
18428. Gold 5 - 감시 피하기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    256 MB           13498	    6048      4037	         44.231%


    문제
        NxN 크기의 복도가 있다. 복도는 1x1 크기의 칸으로 나누어지며, 특정한 위치에는 선생님, 학생, 혹은 장애물이 위치할 수 있다. 현재 몇 명의 학생들은 수업시간에 몰래 복도로 빠져나왔는데, 복도로 빠져나온 학생들은 선생님의 감시에 들키지 않는 것이 목표이다.
        각 선생님들은 자신의 위치에서 상, 하, 좌, 우 4가지 방향으로 감시를 진행한다. 단, 복도에 장애물이 위치한 경우, 선생님은 장애물 뒤편에 숨어 있는 학생들은 볼 수 없다. 또한 선생님은 상, 하, 좌, 우 4가지 방향에 대하여, 아무리 멀리 있더라도 장애물로 막히기 전까지의 학생들은 모두 볼 수 있다고 가정하자.

        다음과 같이 3x3 크기의 복도의 정보가 주어진 상황을 확인해보자. 본 문제에서 위치 값을 나타낼 때는 (행,열)의 형태로 표현한다. 선생님이 존재하는 칸은 T, 학생이 존재하는 칸은 S, 장애물이 존재하는 칸은 O로 표시하였다. 아래 그림과 같이 (3,1)의 위치에는 선생님이 존재하며 (1,1), (2,1), (3,3)의 위치에는 학생이 존재한다. 그리고 (1,2), (2,2), (3,2)의 위치에는 장애물이 존재한다.
            S   O   []
            S   O   []
            T   O   S

            이 때 (3,3)의 위치에 존재하는 학생은 장애물 뒤편에 숨어 있기 때문에 감시를 피할 수 있다. 하지만 (1,1)과 (2,1)의 위치에 존재하는 학생은 선생님에게 들키게 된다.

        학생들은 복도의 빈 칸 중에서 장애물을 설치할 위치를 골라, 정확히 3개의 장애물을 설치해야 한다. 결과적으로 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지 계산하고자 한다. NxN 크기의 복도에서 학생 및 선생님의 위치 정보가 주어졌을 때, 장애물을 정확히 3개 설치하여 모든 학생들이 선생님들의 감시를 피하도록 할 수 있는지 출력하는 프로그램을 작성하시오.

        예를 들어 N=5일 때, 다음과 같이 선생님 및 학생의 위치 정보가 주어졌다고 가정하자.
            []  S   []  []  T
            T   []  S   []  []
            []  []  []  []  []
            []  T   []  []  []
            []  []  T   []  []

        이 때 다음과 같이 3개의 장애물을 설치하면, 모든 학생들을 선생님의 감시로부터 피하도록 만들 수 있다.
            []  S   []  O   T
            T   O   S   []  []
            []  []  O   []  []
            []  T   []  []  []
            []  []  T   []  []


    입력
        첫째 줄에 자연수 N이 주어진다. (3 ≤ N ≤ 6) 둘째 줄에 N개의 줄에 걸쳐서 복도의 정보가 주어진다. 각 행에서는 N개의 원소가 공백을 기준으로 구분되어 주어진다. 해당 위치에 학생이 있다면 S, 선생님이 있다면 T, 아무것도 존재하지 않는다면 X가 주어진다.
        단, 전체 선생님의 수는 5이하의 자연수, 전체 학생의 수는 30이하의 자연수이며 항상 빈 칸의 개수는 3개 이상으로 주어진다.


    출력
        첫째 줄에 정확히 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지의 여부를 출력한다. 모든 학생들을 감시로부터 피하도록 할 수 있다면 "YES", 그렇지 않다면 "NO"를 출력한다.


    예제 입력 1
        5
        X S X X T
        T X S X X
        X X X X X
        X T X X X
        X X T X X
    예제 출력 1
        YES

    예제 입력 2
        4
        S S S T
        X X X X
        X X X X
        T T T X
    예제 출력 2
        NO


    알고리즘 분류
        브루트포스 알고리즘
        백트래킹
*/


// 메모리 : 17592KB
// 시간 : 160ms
// 코드 길이 : 3883B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18428 {
    static int N; // 복도의 가로와 세로의 길이
    static String corridor[][]; // 복도의 정보를 저장하는 배열
    static ArrayList<int[]> teacherIndex; // 선생님이 위치한 복도의 좌표를 저장하는 리스트
    static boolean isBuilt[][]; // 해당 좌표의 장애물 설치 여부를 저장하는 배열  // true : 장애물 설치 O, false : 장애물 설치 X
    static int dx[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dy[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static boolean flag; // 3 개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지의 여부
    static StringBuilder sb;

    public static boolean checkIndex(int x, int y) { // 해당 좌표가 복도 내의 좌표인지 검사하는 메서드  // 복도 내의 좌표일 경우 true, 복도 내의 좌표가 아닐 경우 false
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }

        return false;
    }

    public static boolean checkStudent() { // 선생님의 감시로부터 학생이 피할 수 있는지를 구하는 메서드  // 학생이 선생님의 감시를 피할 수 있을 경우 true, 학생이 선생님의 감시를 피할 수 없을 경우 false
        for (int nowTeacher[] : teacherIndex) {
            for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우 방향 검사
                int nx = nowTeacher[0] + dx[d];
                int ny = nowTeacher[1] + dy[d];

                while (checkIndex(nx, ny)) { // 한 가지 방향으로 쭉 검사
                    if (corridor[nx][ny].equals("S")) { // 선생님의 감시 구역에 학생이 있을 경우
                        return false;
                    }
                    else if (isBuilt[nx][ny]) { // 선생님의 감시 구역에 장애물이 설치되었을 경우
                        break;
                    }

                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }

        return true;
    }

    public static void buildAndCheckStudent(int selectedNum) { // 복도에 장애물을 3 개 설치하고, 해당 조합이 모든 학생들을 감시로부터 피하도록 할 수 있는지의 여부를 검사하는 메서드
        if (selectedNum >= 3) { // 장애물을 3 개 설치했을 경우
            if (checkStudent()) {
                flag = true;
            }

            return;
        }

        // 설치된 장애물의 좌표 조합 구하기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!isBuilt[r][c]) {
                    isBuilt[r][c] = true;
                    buildAndCheckStudent(selectedNum + 1);
                    isBuilt[r][c] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        corridor = new String[N][N];
        teacherIndex = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                corridor[i][j] = token.nextToken();

                if (corridor[i][j].equals("T")) {
                    teacherIndex.add(new int[] {i, j});
                }
            }
        }

        isBuilt = new boolean[N][N];
        buildAndCheckStudent(0);

        sb = new StringBuilder();
        if (flag) {
            sb.append("YES");
        }
        else {
            sb.append("NO");
        }

        System.out.println(sb);
    }
}

/*
Lv. 3 #42884 - 단속카메라

    문제 설명
        고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.
        고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.


    제한사항
        · 차량의 대수는 1대 이상 10,000대 이하입니다.
        · routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점, routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
        · 차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
        · 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.


    입출력 예
        routes	                                        return
        [[-20,-15], [-14,-5], [-18,-13], [-5,-3]]	    2


    입출력 예 설명
        -5 지점에 카메라를 설치하면 두 번째, 네 번째 차량이 카메라를 만납니다.
        -15 지점에 카메라를 설치하면 첫 번째, 세 번째 차량이 카메라를 만납니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.46ms, 72.9MB)
        테스트 2 〉	통과 (0.78ms, 66.9MB)
        테스트 3 〉	통과 (0.51ms, 67.4MB)
        테스트 4 〉	통과 (0.79ms, 79.3MB)
        테스트 5 〉	통과 (0.79ms, 76.3MB)

    효율성  테스트
        테스트 1 〉	통과 (4.43ms, 53.1MB)
        테스트 2 〉	통과 (3.14ms, 52.8MB)
        테스트 3 〉	통과 (6.79ms, 54MB)
        테스트 4 〉	통과 (0.88ms, 52.1MB)
        테스트 5 〉	통과 (8.65ms, 56.7MB)

    채점 결과
        정확성: 50.0
        효율성: 50.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]); // 차량이 고속도로에 나간 지점을 기준으로 오름차순 정렬

        int end = routes[0][1]; // 카메라를 설치할 어느 차량의 진출 지점
        int answer = 1; // 모든 차량이 한 번은 단속용 카메라를 만나도록 하는 카메라의 최소 개수
        for (int r = 1, len = routes.length; r < len; r++) {
            if (routes[r][0] <= end) { // 해당 차량의 진입 지점이 기존의 설치된 단속용 카메라의 위치 이전에 위치해서, 해당 차량이 기존의 설치된 단속용 카메라와 만날 경우
                continue;
            }

            // 새로운 단속용 카메라 위치 설정 및 개수 추가
            end = routes[r][1];
            answer += 1;
        }

        return answer;
    }
}

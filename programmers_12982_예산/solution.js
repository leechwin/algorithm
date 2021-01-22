/**
 * https://programmers.co.kr/learn/courses/30/lessons/12982
 */
function solution(d, budget) {
    var answer = 0;
    // sort 내의 compareFunction 를 재구현하여 복잡도를 줄인다.
    // 기본 정렬 순서는 문자열의 유니코드 코드 포인트를 따르기때문에
    // 문자열로 변환하고 유니 코드 코드 포인트 순서로 문자열을 비교하여 정렬된다.
    // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/sort
    d.sort(function(a, b) {
        return a - b;
    });

    var sum = 0;
    for (var i = 0; i < d.length; i++) {
        if (sum + d[i] <= budget) {
            sum += d[i];
            answer++;
        } else {
            break;
        }
    }
    return answer;
}

/**
 * https://programmers.co.kr/learn/courses/30/lessons/12939
 */
function solution(s) {
    const numbers = s.split(' ');
    numbers.sort(function (a, b) {
        return a - b;
    });

    var answer = numbers[0] + ' ' + numbers[numbers.length - 1];
    return answer;
}

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 */
function solution(progresses, speeds) {
    var remain = progresses.map((progress, idx) => {
        var todo = 100 - progresses[idx];
        return Math.ceil(todo / speeds[idx]);
    })

    var answer = [];
    let cnt = 1;
    let prev = remain[0];
    for (var i = 1; i < remain.length; i++ ) {
        var cur = remain[i];
        if (prev >= cur) {
            cnt += 1;
        } else {
            answer.push(cnt);
            cnt = 1;
            prev = cur;
        }
    }
    answer.push(cnt);
    return answer;
}

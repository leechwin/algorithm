/**
 * https://programmers.co.kr/learn/courses/30/lessons/17678
 */
function solution(n, t, m, timetable) {
    const bus = [];
    // 버스시간표를 분으로 변환
    for (let i = 0; i < n; i++) {
        bus.push(540 + (i * t));
    }

    // 탑승자들 시간을 분으로 변환 후 정렬
    const people = timetable.map((x) => ((x.split(":")[0] * 60) + (x.split(":")[1] * 1)))
        .sort((a, b) => a - b)
        .filter(p => p <= bus[n - 1]); // 마지막 버스 탑승불가인원 제거
    
    let answer = 0;
    for (let i = 0; i < n; i++) {
       // 탑승가능 인원수 조사
       const available = people.filter(p => p <= bus[i]).length;
       // 마지막 버스인 경우
       if (i == n - 1) {
           if (available >= m) {
               answer = people[m - 1] - 1;
           } else {
               answer = bus[n - 1]
           }
       } else {
           if (available > m) {
               people.splice(0, m);
           } else {
               people.splice(0, available);
           }
       }
    }

    const hour = Math.floor(answer / 60);
    const min = answer % 60;
    return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min)
}

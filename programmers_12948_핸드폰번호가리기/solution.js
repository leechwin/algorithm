/**
 * https://programmers.co.kr/learn/courses/30/lessons/12948
 */
function solution(phone_number) {
    // var result = "*".repeat(phone_number.length - 4) + phone_number.slice(-4);
    var result = phone_number.slice(-4).padStart(phone_number.length, "*");
    return result;
}

// INFO: node.js input example
// https://velog.io/@exploit017/%EB%B0%B1%EC%A4%80Node.js-Node.js-%EC%9E%85%EB%A0%A5-%EB%B0%9B%EA%B8%B0

let fs = require('fs');
let input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

let N = Number(input[0]);
let RGB = [];

for (let i = 1; i < input.length; i++) {
  if (input[i] !== '') {
    RGB.push(input[i].split(' ').map(x => Number(x)));
  }
}

const INIT = 123456789;
let dp = Array(N).fill(INIT).map(x => Array(3).fill(INIT));
let result = INIT;

function Problem() {};
Problem.prototype.main = function() {
    function solve() {
        for (let k = 1; k < N; k++) {
            dp[k][0] = RGB[k][0] + Math.min(dp[k - 1][1], dp[k - 1][2]);
            dp[k][1] = RGB[k][1] + Math.min(dp[k - 1][0], dp[k - 1][2]);
            dp[k][2] = RGB[k][2] + Math.min(dp[k - 1][0], dp[k - 1][1]);
        }
    }

    // R
    dp[0][0] = RGB[0][0];
    dp[0][1] = INIT;
    dp[0][2] = INIT;
    solve();
    for (let i = 0; i < 3; i++) {
        if (i !== 0) {
          result = Math.min(result, dp[N - 1][i]);
        }
    }

    // G
    dp[0][0] = INIT;
    dp[0][1] = RGB[0][1];
    dp[0][2] = INIT;
    solve();
    for (let i = 0; i < 3; i++) {
        if (i !== 1) {
          result = Math.min(result, dp[N - 1][i]);
        }
    }

    // B
    dp[0][0] = INIT;
    dp[0][1] = INIT;
    dp[0][2] = RGB[0][2];
    solve();
    for (let l = 0; l < 3; l++) {
        if (l !== 2) {
            result = Math.min(result, dp[N - 1][l]);   
        }
    }

    console.log(result);
}

const problem = new Problem();
problem.main();

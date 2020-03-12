class Solution {
    public int orangesRotting(int[][] grid) {
        int X = grid.length;
        int Y = grid[0].length;
        int fresh = 0;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) {
            return 0;
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int totalRotten = queue.size();
            // check all rotten
            while (totalRotten > 0) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                // up
                if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                    grid[x][y - 1] = 2;
                    queue.add(new int[] { x, y - 1 });
                    fresh--;
                }
                // down
                if (y + 1 < Y && grid[x][y + 1] == 1) {
                    grid[x][y + 1] = 2;
                    queue.add(new int[] { x, y + 1 });
                    fresh--;
                }
                // left
                if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                    grid[x - 1][y] = 2;
                    queue.add(new int[] { x - 1, y });
                    fresh--;
                }
                // right
                if (x + 1 < X && grid[x + 1][y] == 1) {
                    grid[x + 1][y] = 2;
                    queue.add(new int[] { x + 1, y });
                    fresh--;
                }
                totalRotten--;
            }
            if (!queue.isEmpty()) {
                cnt++;
            }
        }

        if (fresh > 0) {
            return -1;
        }

        return cnt;
    }
}
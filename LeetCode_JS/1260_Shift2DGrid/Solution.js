/**
 * @param {number[][]} grid
 * @param {number} k
 * @return {number[][]}
 */
const shiftGrid = function (grid, k) {
    const m = grid.length, n = grid[0].length;
    const total = m * n;
    k %= total; // k可能超过total，进行一次取余操作

    // 记录grid中最后k个元素
    const lastK = new Array(k).fill(0);
    let ind = k - 1, row = m - 1, col = n - 1;
    while (ind >= 0) {
        lastK[ind] = grid[row][col];
        ind--;
        col--;
        if (col < 0) {
            col = n - 1;
            row--;
        }
    }

    // 进行迁移
    row = m - 1;
    col = n - 1;
    let i = Math.floor((total - 1 - k) / n),
        j = Math.floor((total - 1 - k) % n);
    while (i >= 0) {
        grid[row][col] = grid[i][j];
        col--;
        j--;
        if (col < 0) {
            col = n - 1;
            row--;
        }
        if (j < 0) {
            j = n - 1;
            i--;
        }
    }

    // 将记录下的k个元素放到grid头部
    row = 0;
    col = 0;
    ind = 0;
    while (ind < k) {
        grid[row][col] = lastK[ind];
        ind++;
        col++;
        if (col === n) {
            col = 0;
            row++;
        }
    }
    return grid;
};
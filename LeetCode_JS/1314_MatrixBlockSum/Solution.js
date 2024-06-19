/**
 * @param {number[][]} mat
 * @param {number} k
 * @return {number[][]}
 */
const matrixBlockSum = function (mat, k) {
    const m = mat.length, n = mat[0].length;
    // regionSum[i][j]表示前i行中前j列区域内的和
    const regionSum = new Array(m + 1).fill(0).
        map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            regionSum[i][j] = regionSum[i - 1][j] + regionSum[i][j - 1] -
                regionSum[i - 1][j - 1] + mat[i - 1][j - 1];
        }
    }

    const answer = new Array(m).fill(0).
        map(() => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const startRow = Math.max(0, i - k),
                startCol = Math.max(0, j - k),
                endRow = Math.min(m - 1, i + k),
                endCol = Math.min(n - 1, j + k);
            answer[i][j] = regionSum[endRow + 1][endCol + 1] - regionSum[endRow + 1][startCol] -
                regionSum[startRow][endCol + 1] + regionSum[startRow][startCol];
        }
    }
    return answer;
};
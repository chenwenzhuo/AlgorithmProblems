/**
 * @param {character[][]} board
 * @return {number}
 */
var countBattleships = function (board) {
    const m = board.length, n = board[0].length;
    let cnt = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (board[i][j] !== 'X')
                continue;
            // 根据条件，只需要从当前坐标开始横向、纵向将连续的'X'标记为'.'
            board[i][j] = 'X';
            for (let k = i + 1; k < m && board[k][j] === 'X'; k++)
                board[k][j] = '.';
            for (let k = j + 1; k < n && board[i][k] === 'X'; k++)
                board[i][k] = '.';
            cnt++;
        }
    }
    return cnt;
};// 复杂度O(m × n × max(m,n))

/**进阶：复杂度O(mn)，且不修改board的值
 * 由于战舰只会水平或垂直放置，且任意两战舰不相邻，
 * 可以枚举每个战舰的左上角的'X'，并统计数量
 */
var countBattleships = function (board) {
    const m = board.length, n = board[0].length;
    let cnt = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            // 当一个坐标(i,j)位置上是'X'，且上方和左边不是'X'，则其为一个战舰的左上角
            if (board[i][j] === 'X' &&
                (i - 1 < 0 || board[i - 1][j] !== 'X') &&
                (j - 1 < 0 || board[i][j - 1] !== 'X')
            ) cnt++;
        }
    }
    return cnt;
}
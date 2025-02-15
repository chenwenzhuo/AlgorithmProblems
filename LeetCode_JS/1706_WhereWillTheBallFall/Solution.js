/**
 * @param {number[][]} grid
 * @return {number[]}
 */
var findBall = function (grid) {
  const m = grid.length, n = grid[0].length;
  const answer = [];
  for (let i = 0; i < n; i++) {
    let row = 0, col = i; // 起始坐标
    while (true) {
      // 当前单元格会把球导向右边，且当前格子在最右侧，或者右边的单元格是左边导向，则球会卡住
      // 当前单元格会把球导向左边，且当前格子在最左侧，或者左边的单元格是右边导向，则球会卡住
      if (
        (grid[row][col] === 1 && (col === n - 1 || grid[row][col + 1] === -1)) ||
        (grid[row][col] === -1 && (col === 0 || grid[row][col - 1] === 1))
      ) {
        answer.push(-1);
        break;
      }

      // 不会卡住，则向右下或左下移动
      row++;
      col += grid[row - 1][col];

      if (row === m) { // 到达最底部，则记录当前球的位置
        answer.push(col);
        break;
      }
    }
  }
  return answer;
};

console.log(findBall([[1, 1, 1, -1, -1], [1, 1, 1, -1, -1], [-1, -1, -1, 1, 1], [1, 1, 1, 1, -1], [-1, -1, -1, -1, -1]]));

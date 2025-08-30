/**
 * @param {character[][]} board
 * @return {boolean}
 */
var isValidSudoku = function (board) {
    // 创建三个映射数组，分别用于跟踪每行、每列和每个3x3子方格中的数字出现次数
    const rowMaps = new Array(9).fill(0).map(() => new Map());
    const colMaps = new Array(9).fill(0).map(() => new Map());
    const boxMaps = new Array(9).fill(0).map(() => new Map());
    
    // 遍历整个9x9棋盘
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            const char = board[i][j];
            if (char === '.')
                continue; // 跳过空位
            
            // 更新当前数字在行、列和子方格中的出现次数
            rowMaps[i].set(char, (rowMaps[i].get(char) || 0) + 1);
            colMaps[j].set(char, (colMaps[j].get(char) || 0) + 1);
            
            // 计算当前位置属于哪个3x3子方格
            // 子方格索引计算方式：Math.floor(i / 3) * 3 + Math.floor(j / 3)
            // 例如：(0,0)属于子方格0；(0,3)属于子方格1；(3,0)属于子方格3等
            const boxInd = Math.floor(i / 3) * 3 + Math.floor(j / 3);
            boxMaps[boxInd].set(char, (boxMaps[boxInd].get(char) || 0) + 1);
            
            // 检查当前数字在所在行、列或子方格中是否已经出现过（出现次数大于1）
            // 如果有重复，数独无效，返回false
            if (rowMaps[i].get(char) > 1 || colMaps[j].get(char) > 1 || boxMaps[boxInd].get(char) > 1)
                return false;
        }
    }
    return true; // 遍历完整个棋盘没有发现重复，数独有效，返回true
};

/**
 * @param {number[]} arr
 * @param {number[][]} mat
 * @return {number}
 */
const firstCompleteIndex = function (arr, mat) {
    const m = mat.length,
        n = mat[0].length;
    const val2Cord = new Map(); // 存储mat中每个值及其坐标
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            val2Cord.set(mat[i][j], [i, j]);
        }
    }

    // rowPaintedCnt[i]表示第i行已经涂色的格子数量
    const rowPaintedCnt = new Array(m).fill(0);
    const colPaintedCnt = new Array(n).fill(0);
    // 遍历arr，进行“涂色”
    for (let i = 0; i < arr.length; i++) {
        const val = arr[i];
        const [x, y] = val2Cord.get(val);
        // 涂色后，对每行、每列进行计数
        rowPaintedCnt[x]++;
        colPaintedCnt[y]++;
        if (rowPaintedCnt[x] === n || colPaintedCnt[y] === m) {
            return i;
        }
    }
};

let arr = [1, 3, 4, 2],
    mat = [
        [1, 4],
        [2, 3]
    ];
console.log(firstCompleteIndex(arr, mat));
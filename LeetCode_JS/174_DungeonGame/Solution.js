/**
 * @param {number[][]} dungeon
 * @return {number}
 */
var calculateMinimumHP = function (dungeon) { // 迭代解法
    const m = dungeon.length, n = dungeon[0].length;
    // hp[i][j]表示从此坐标出发走到右下角，所需的最低血量
    // 若落地到坐标(i,j)，需要保证：
    // 1.落地后在坐标(i,j)必须存活；
    // 2.下一步走到坐标(i+1,j)或(i,j+1)时，血量不低于hp[i+1][j]、hp[i][j+1]
    const hp = new Array(m).fill(0).
        map(() => new Array(n).fill(0));

    // 直接落地到右下角，房间的值非负，则只需要1点血量，否则至少剩余1点
    hp[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 1 : 1 - dungeon[m - 1][n - 1];

    // base case：最后一行/列，只有一种走法，直接计算
    for (let i = m - 2; i >= 0; i--) {
        hp[i][n - 1] = dungeon[i][n - 1] >= 0 ? 1 : 1 - dungeon[i][n - 1]; // 先保证落地后存活
        // 在坐标(i,n-1)落地后的血量，不能低于hp[i + 1][n - 1]
        const landingHp = hp[i][n - 1] + dungeon[i][n - 1];
        if (landingHp < hp[i + 1][n - 1])
            hp[i][n - 1] += (hp[i + 1][n - 1] - landingHp);
    }
    for (let j = n - 2; j >= 0; j--) {
        hp[m - 1][j] = dungeon[m - 1][j] >= 0 ? 1 : 1 - dungeon[m - 1][j]; // 先保证落地后存活
        // 在坐标(m-1,j)落地后的血量，不能低于hp[m - 1][j + 1]
        const landingHp = hp[m - 1][j] + dungeon[m - 1][j];
        if (landingHp < hp[m - 1][j + 1])
            hp[m - 1][j] += (hp[m - 1][j + 1] - landingHp);
    }

    for (let i = m - 2; i >= 0; i--) {
        for (let j = n - 2; j >= 0; j--) {
            hp[i][j] = dungeon[i][j] >= 0 ? 1 : 1 - dungeon[i][j]; // 先保证落地后存活
            // 在坐标(i,j)落地后的血量，不能低于hp[i + 1][j]、hp[i][j + 1]
            const landingHp = hp[i][j] + dungeon[i][j];

            // 从当前格子出发，经过下方、右边格子到达右下角所需的血量
            let hpToGoBottom = hp[i][j], hpToGoRight = hp[i][j];
            if (landingHp < hp[i + 1][j])
                hpToGoBottom += (hp[i + 1][j] - landingHp);
            if (landingHp < hp[i][j + 1])
                hpToGoRight += (hp[i][j + 1] - landingHp);
            hp[i][j] = Math.min(hpToGoBottom, hpToGoRight);
        }
    }
    return hp[0][0];
};

var calculateMinimumHP = function (dungeon) { // 递归解法
    const m = dungeon.length, n = dungeon[0].length;
    const hp = new Array(m).fill(0).
        map(() => new Array(n).fill(-1)); // 备忘录，避免重复计算

    // 计算从坐标(i,j)出发，到达右下角所需的最低血量
    const calcHp = (i, j) => {
        if (i === m - 1 && j === n - 1) // 直接落地到右下角
            return dungeon[m - 1][n - 1] >= 0 ? 1 : 1 - dungeon[m - 1][n - 1];
        if (i >= m || j >= n) // 越界，无法到达
            return Infinity;
        if (hp[i][j] !== -1)
            return hp[i][j];

        // 保证落地存活
        const initial = dungeon[i][j] >= 0 ? 1 : 1 - dungeon[i][j];;
        const landingHp = initial + dungeon[i][j]; // 落地后的血量
        // 从下方、右边格子出发，到达右下角所需的血量，landingHp不能低于这两个值
        let hpFromBottom = calcHp(i + 1, j),
            hpFromRight = calcHp(i, j + 1);
        // 从(i,j)经过下方、右边格子到达右下角，所需的血量
        let initialHpCrossBottom = initial, initialHpCrossRight = initial;
        if (landingHp < hpFromBottom)
            initialHpCrossBottom += (hpFromBottom - landingHp);
        if (landingHp < hpFromRight)
            initialHpCrossRight += (hpFromRight - landingHp);
        hp[i][j] = Math.min(initialHpCrossBottom, initialHpCrossRight);
        return hp[i][j];
    }
    return calcHp(0, 0);
}
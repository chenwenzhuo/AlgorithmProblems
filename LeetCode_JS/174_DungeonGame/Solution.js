/**
 * @param {number[][]} dungeon
 * @return {number}
 */
var calculateMinimumHP = function (dungeon) {
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

/**
 * @param {number} neededApples
 * @return {number}
 */
const minimumPerimeter = function (neededApples) {
    // 边长为0的正方形中有0个苹果。边长增加2，区域内的苹果数量才会增加
    // 边长为n的正方形中，苹果数量为n*(n+1)*(n+2)/2
    let sideLen = 0, apples = 0;
    while (apples < neededApples) {
        sideLen += 2;
        apples = sideLen * (sideLen + 1) * (sideLen + 2) / 2;
    }
    return sideLen * 4;
};
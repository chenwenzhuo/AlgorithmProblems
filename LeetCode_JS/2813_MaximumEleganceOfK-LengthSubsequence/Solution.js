/**
 * @param {number[][]} items
 * @param {number} k
 * @return {number}
 */
const findMaximumElegance = function (items, k) {
    items.sort((a, b) => b[0] - a[0]); // 将数组按items[i][0]降序排序
    const n = items.length;
    // 计算前k项（利润最大的k项）的优雅度
    let elegance = 0, totalProfit = 0;
    const map = new Map(); // 记录选中的k项中，各类别及出现次数
    const stack = []; // 记录选中的k项中，类别重复但利润不是最大的项
    for (let i = 0; i < k; i++) {
        totalProfit += items[i][0];
        if (map.has(items[i][1])) {
            map.set(items[i][1], map.get(items[i][1]) + 1);
            stack.push(items[i]);
        } else
            map.set(items[i][1], 1);
    }
    elegance = totalProfit + map.size * map.size;

    // 尝试从选中的k项中（stack中），移除一个利润最小、且类别重复出现的
    // 并在其余项中，选择一个利润最大、且类别未出现过的
    let ind = k; // 遍历余下n-k项的下标
    while (stack.length > 0 && ind < n) {
        // 将stack栈顶的项取消选中
        const top = stack.pop();
        totalProfit -= top[0];
        map.set(top[1], map.get(top[1]) - 1);
        if (map.get(top[1]) === 0)
            map.delete(top[1]);

        // 从下标[ind...n-1]范围内，选择一个类别未出现过的
        while (ind < n) {
            if (map.has(items[ind][1])) {
                ind++;
                continue;
            }
            totalProfit += items[ind][0];
            map.set(items[ind][1], 1);
            ind++;
            break;
        }
        elegance = Math.max(elegance, totalProfit + map.size * map.size);
    }
    return elegance;
};
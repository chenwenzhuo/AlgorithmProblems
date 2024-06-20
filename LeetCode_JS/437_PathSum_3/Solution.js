/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} targetSum
 * @return {number}
 */
const pathSum = function (root, targetSum) {
    // 从根节点到当前节点的路径可看作一个数组，可对其求前缀和
    // 路径和为targetSum即存在前缀和的差为targetSum
    const prefixSum = [0];
    const map = new Map([[0, 1]]); // 存储每个前缀和与其出现次数的映射
    let ans = 0;
    const fn = node => {
        if (!node)
            return;
        const sum = prefixSum[prefixSum.length - 1] + node.val;
        const need = sum - targetSum;
        if (map.has(need))
            ans += map.get(need);

        // 在prefixSum、map中记录当前节点的数据
        prefixSum.push(sum);
        if (map.has(sum))
            map.set(sum, map.get(sum) + 1);
        else map.set(sum, 1);

        fn(node.left);
        fn(node.right);
        // 返回上层递归前，将当前节点的记录移除
        prefixSum.pop();
        if (map.get(sum) === 1)
            map.delete(sum);
        else
            map.set(sum, map.get(sum) - 1);
    }
    fn(root);
    return ans;
};

const root = {
    val: 10,
    left: {
        val: 5,
        left: {
            val: 3,
            left: { val: 3, left: null, right: null },
            right: { val: -2, left: null, right: null },
        },
        right: {
            val: 2,
            left: null,
            right: { val: 1, left: null, right: null },
        },
    },
    right: {
        val: -3,
        left: null,
        right: { val: 11, left: null, right: null }
    },
};

console.log(pathSum(root, 8));

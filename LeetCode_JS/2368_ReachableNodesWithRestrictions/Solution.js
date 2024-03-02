/**
 * @param {number} n
 * @param {number[][]} edges
 * @param {number[]} restricted
 * @return {number}
 */
const reachableNodes = function (n, edges, restricted) {
    restricted = new Set(restricted);
    // 存储从每个节点出发，能直接连接到的节点
    const directReachable = new Map();
    for (let i = 0; i < n; i++)
        directReachable.set(i, new Set());
    edges.forEach(e => {
        directReachable.get(e[0]).add(e[1]);
        directReachable.get(e[1]).add(e[0]);
    });

    // 计算从节点node出发，在受限条件下能到达的节点数
    const visited = new Set();
    const dfs = node => {
        let total = 0;
        visited.add(node);
        const nodeReachable = directReachable.get(node);
        nodeReachable.forEach(nd => {
            if (!restricted.has(nd) && !visited.has(nd)) {
                total++;
                total += dfs(nd);
            }
        });
        return total;
    }
    return dfs(0) + 1; //加上节点0本身
};

let n = 10,
    edges = [[4, 1], [1, 3], [1, 5], [0, 5], [3, 6], [8, 4], [5, 7], [6, 9], [3, 2]],
    restricted = [2, 7];

n = 7;
edges = [[0, 1], [1, 2], [3, 1], [4, 0], [0, 5], [5, 6]];
restricted = [4, 5];

console.log(reachableNodes(n, edges, restricted));
/**
 * @param {number} n
 * @param {number[][]} connections
 * @return {number}
 */
const minReorder = function (n, connections) {
    // 图的邻接表
    const graph = new Array(n).fill(0).map(() => new Array());
    connections.forEach(conn => {
        const [from, to] = conn;
        // 从from到to为正值，表示原本可通行，to到from为负值，表示不可通行
        graph[from].push(to);
        graph[to].push(-from);
    });

    // 计算从节点0到达其他所有节点，需要反转的边数
    let edgeToReverse = 0;
    const dfs = (fromNode, father) => {
        graph[fromNode].forEach((toNode) => {
            if (Math.abs(toNode) === father) return;
            if (toNode < 0) {
                edgeToReverse++;
            }
            dfs(Math.abs(toNode), Math.abs(fromNode));
        });
    };
    dfs(0, -n - 1);
    // 所有边数减去edgeToReverse，得到其他节点到达节点0需要反转的边数
    return n - 1 - edgeToReverse;
};

let n = 6;
let connections = [
    [0, 1],
    [1, 3],
    [2, 3],
    [4, 0],
    [4, 5]
];

console.log(minReorder(n, connections));
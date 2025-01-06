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
 * @param {number} start
 * @return {number}
 */
var amountOfTime = function (root, start) {
  const graph = {}; // 邻接表存储图结构
  const traverse = (node, parent) => {
    if (!node) return;

    const val = node.val;
    if (!graph[val])
      graph[val] = [];
    // 存储父节点和子节点
    if (parent)
      graph[val].push(parent.val);
    if (node.left)
      graph[val].push(node.left.val);
    if (node.right)
      graph[val].push(node.right.val);

    traverse(node.left, node);
    traverse(node.right, node);
  }

  traverse(root, null); // 构建图

  // BFS遍历图
  const visited = new Set();
  const queue = [start];
  let time = 0;
  while (queue.length) {
    const size = queue.length;
    // 遍历队列中所有节点，若cur的邻居中有未访问过的节点，则将flag置为true
    let flag = false;
    for (let i = 0; i < size; i++) {
      const cur = queue.shift();
      const neighbors = graph[cur];
      for (const nb of neighbors) {
        if (!visited.has(nb)) {
          queue.push(nb); // 将未访问过的节点加入队列
          flag = true;
        }
      }
      visited.add(cur); // 标记当前节点为已访问
    }

    if (flag)
      time++; // 若有未访问过的节点，则时间+1
  }
  return time;
};

/**
 * @param {TreeNode} root
 * @param {number} start
 * @return {number}
 */
var amountOfTime = function (root, start) {
  const parentMap = new Map(); // 存储每个节点的父节点
  let startNode; // 起始节点
  const traverse = (node, parent) => {
    if (!node) return;

    if (node.val === start)
      startNode = node;
    parentMap.set(node, parent);
    traverse(node.left, node);
    traverse(node.right, node);
  }
  traverse(root, null);

  // BFS遍历图
  const visited = new Set();
  const queue = [startNode];
  let time = 0;
  while (queue.length) {
    const size = queue.length;
    // 遍历队列中所有节点，若与curNode相邻的节点未访问过，则将flag置为true
    let flag = false;
    for (let i = 0; i < size; i++) {
      const curNode = queue.shift();
      const parent = parentMap.get(curNode);

      [parent, curNode.left, curNode.right].forEach(node => {
        if (!node || visited.has(node))
          return;
        queue.push(node);
        flag = true;
      });
      visited.add(curNode); // 标记当前节点为已访问
    }
    if (flag)
      time++; // 若有未访问过的节点，则时间+1
  }
  return time;
}

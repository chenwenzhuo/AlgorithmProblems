/**
 * Definition for Employee.
 * function Employee(id, importance, subordinates) {
 *     this.id = id;
 *     this.importance = importance;
 *     this.subordinates = subordinates;
 * }
 */

/**
 * @param {Employee[]} employees
 * @param {number} id
 * @return {number}
 */
var GetImportance = function (employees, id) {
  // 存储id与对象之间的映射
  const id2employee = new Map();
  employees.forEach(emp => id2employee.set(emp.id, emp));

  let totalImportance = 0;
  const queue = [id];
  while (queue.length > 0) {
    const curId = queue.shift();
    const curEmp = id2employee.get(curId);
    totalImportance += curEmp.importance;
    // 将下属id加入队列
    curEmp.subordinates.forEach(subId => queue.push(subId));
  }
  return totalImportance;
};
class RandomizedSet {
    constructor() {
        this.list = []; // 存储元素
        this.elem2Index = new Map(); // 存储元素及其下标的映射
        this.size = 0; // 集合中有效值的数量
    }

    /** 
     * @param {number} val
     * @return {boolean}
     */
    insert(val) {
        const { list, elem2Index, size } = this;
        if (elem2Index.has(val))
            return false; // 不能插入一个已存在的值
        if (size === list.length) {
            list.push(val);
            elem2Index.set(val, list.length - 1);
        } else {
            list[size] = val;
            elem2Index.set(val, size);
        }
        this.size++; // 不能直接size++，要this.
        return true;
    };

    /** 
     * @param {number} val
     * @return {boolean}
     */
    remove(val) {
        const { list, elem2Index, size } = this;
        if (!elem2Index.has(val))
            return false; // 不能删除一个不存在的值
        const ind = elem2Index.get(val); // 获取下标后删除
        elem2Index.delete(val);

        if (size > 1 && ind !== size - 1) {
            list[ind] = list[size - 1]; // 使用最后一个有效值覆盖当前下标
            elem2Index.set(list[ind], ind); // 修改这个值的下标
        }
        this.size--; // 不能直接size--，要this.
        return true;
    };

    /**
     * @return {number}
     */
    getRandom() {
        const { list, size } = this;
        const randomInd = Math.floor(Math.random() * size);
        return list[randomInd];
    };
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = new RandomizedSet()
 * var param_1 = obj.insert(val)
 * var param_2 = obj.remove(val)
 * var param_3 = obj.getRandom()
 */

const randSet = new RandomizedSet();
console.log(randSet.remove(0));
console.log(randSet.remove(0));
console.log(randSet.insert(0));
console.log(randSet.getRandom());
console.log(randSet.remove(0));
console.log(randSet.insert(0));
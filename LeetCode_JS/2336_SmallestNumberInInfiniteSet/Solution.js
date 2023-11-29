const {
    PriorityQueue
} = require('@datastructures-js/priority-queue');

class SmallestInfiniteSet {
    constructor() {
        this.popped = new Set(); // 从集合中被移除的数字
        this.added = new Set(); // 重新被加入集合中的数字
        this.sorted = new PriorityQueue({ // 将重新加入的数字升序排序
            compare: (a, b) => a - b
        });
        // 无限集合中，但未包含在added中的最小值，大于等于此值的所有数都在集合中
        this.includedMin = 1;
    }

    /**
     * @return {number}
     */
    popSmallest() {
        const {
            added,
            sorted,
        } = this;
        // 存在二次添加进集合中的值，则将其中最小的一个移除
        let valueToPop;
        if (added.size > 0) {
            valueToPop = sorted.dequeue();
            added.delete(valueToPop);
        } else { // 否则移除includedMin
            valueToPop = this.includedMin;
            this.includedMin++;
        }
        return valueToPop;
    }

    /** 
     * @param {number} num
     * @return {void}
     */
    addBack(num) {
        const {
            added,
            sorted,
        } = this;
        // 若num已经存在于集合中，不进行操作
        if (added.has(num) || this.includedMin <= num) {
            return;
        }
        // num不存在时，进行添加
        if (num + 1 === this.includedMin) {
            // 二者相差1时，仅需改变includedMin，num不用加入集合中，减少排序
            this.includedMin--;
        } else { // 加入集合，进行排序
            added.add(num);
            sorted.enqueue(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = new SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */
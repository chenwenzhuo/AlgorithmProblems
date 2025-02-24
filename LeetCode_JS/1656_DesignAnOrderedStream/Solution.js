/**
 * @param {number} n
 */
var OrderedStream = function (n) {
  this.n = n;
  this.ptr = 1;
  this.values = new Array(n + 1).fill(null);
};

/** 
 * @param {number} idKey 
 * @param {string} value
 * @return {string[]}
 */
OrderedStream.prototype.insert = function (idKey, value) {
  this.values[idKey] = value;

  const res = [];
  while (this.ptr <= this.n && this.values[this.ptr]){
    res.push(this.values[this.ptr]);
    this.ptr++;
  }

  return res;
};

/** 
 * Your OrderedStream object will be instantiated and called as such:
 * var obj = new OrderedStream(n)
 * var param_1 = obj.insert(idKey,value)
 */

const stream = new OrderedStream(5);
console.log(stream.insert(3, "ccccc"));
console.log(stream.insert(1, "aaaaa"));
console.log(stream.insert(2, "bbbbb"));
console.log(stream.insert(5, "eeeee"));
console.log(stream.insert(4, "ddddd"));
/**
 * @param {string} s
 * @return {string}
 */
const makeSmallestPalindrome = function (s) {
    const arr = Array.from(s);
    let left = 0,
        right = s.length - 1;
    while (left < right) {
        if (arr[left] !== arr[right]) {
            const smaller = arr[left].charCodeAt() < arr[right].charCodeAt() ?
                arr[left] : arr[right];
            arr[left] = smaller;
            arr[right] = smaller;
        }
        left++;
        right--;
    }
    return arr.join('');
};
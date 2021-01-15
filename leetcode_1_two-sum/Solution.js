/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    const map = [];

    for (let i = 0; i < nums.length; i++) {
        // key: number, value: index
        map[nums[i]] = i;
    }

    for (let i = 0; i < nums.length; i++) {
        const result = target - nums[i];

        if (result in map) {
            if (map[result] !== i) {
                return [map[result], i];
            }
        }
    }

    return null;
};

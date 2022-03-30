import java.util.*
import kotlin.collections.ArrayDeque

fun main() {

    val root = TreeNode(12)
    root.left = TreeNode(7, left=TreeNode(4))
    root.right = TreeNode(1, left=TreeNode(10), right=TreeNode(5))

    val secondRoot = TreeNode(1)
    secondRoot.left = TreeNode(7, TreeNode(4), TreeNode(5))
    secondRoot.right = TreeNode(9, TreeNode(2), TreeNode(7))
    println("Tree has path: ${hasPath(root, 23)}")
    println("Tree has path: ${hasPath(root, 16)}")

    println("Tree paths with sum expect 2: ${countPaths(root, 23)}")
    println("Tree paths with sum expect 2: ${countPaths(secondRoot, 12)}")

    val thirdRoot = TreeNode(1)
    thirdRoot.left = TreeNode(7)
    thirdRoot.right = TreeNode(9, TreeNode(2), TreeNode(9))
    println("Sum of path numbers expect 408: ${sumPathNumbers(thirdRoot, 0)}")

    println("Find if tree has path 1,9,9 expect true: ${findPath(thirdRoot, LinkedList(listOf(1,9,9)))}")

    println("Count # of Paths which sum to target 12 expect 2: ${findCountOfPathsWhichSumToTarget(root, ArrayDeque(), 11)}")
}

fun findCountOfPathsWhichSumToTarget(root: TreeNode?, arr: ArrayDeque<Int>, target: Int): Int {
    if(root == null) return 0

    arr.add(root.value)

    // find sum of all subpaths, meaning get all adjacent sums inside arr
    var pathSum = 0
    var pathCount = 0
    val pathIterator: ListIterator<Int> = arr.listIterator(arr.size)
    println("going to iterate over $arr")
    while(pathIterator.hasPrevious()) {
        val current = pathIterator.previous()
        println("pathSum: $pathSum and current: $current")
        pathSum += current
        if(pathSum == target) pathCount++
    }
    pathCount += findCountOfPathsWhichSumToTarget(root.left, arr, target)
    pathCount += findCountOfPathsWhichSumToTarget(root.right, arr, target)

    arr.removeLast()

    return pathCount
}
fun findPath(root: TreeNode?, arr: LinkedList<Int>): Boolean {
    if(root == null){
        return arr.isEmpty()
    }
    if(arr.isEmpty()) return true
    return if(arr.first == root.value) {
        // pop value go recursion
        arr.removeFirst()
        findPath(root.left, arr) || findPath(root.right, arr)
    } else false
}
fun sumPathNumbers(root: TreeNode?, sum: Int): Int {
    if(root == null) return 0

    val newSum = sum * 10 + root.value
    if(root.left == null && root.right == null) return newSum

    return sumPathNumbers(root.left, newSum) + sumPathNumbers(root.right, newSum)
}
fun countPaths(root: TreeNode?, target: Int): Int {
    if(root == null) return 0
    var count = 0
    if(root.value == target && (root.left == null || root.right == null)) {
        return 1
    }
    count += countPaths(root.left, target - root.value)
    count += countPaths(root.right, target - root.value)
    return count

}
fun hasPath(root: TreeNode?, target: Int): Boolean {
    if(root == null) return false

    if(root.value == target && (root.left == null || root.right ==null)) {
        return true
    }

    return hasPath(root.left, target - root.value) || hasPath(root.right, target - root.value)
}
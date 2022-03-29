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
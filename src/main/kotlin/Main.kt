fun main() {

    val root = TreeNode(12)
    root.left = TreeNode(7, left=TreeNode(9))
    root.right = TreeNode(1, left=TreeNode(10), right=TreeNode(5))
    println("Tree has path: ${hasPath(root, 23)}")
    println("Tree has path: ${hasPath(root, 16)}")
}

fun hasPath(root: TreeNode?, target: Int): Boolean {
    if(root == null) return false

    if(root.value == target && (root.left == null || root.right ==null)) {
        return true
    }

    return hasPath(root.left, target - root.value) || hasPath(root.right, target - root.value)
}
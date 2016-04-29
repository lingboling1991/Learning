package algorithm.util;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public int maxLeft;// ×ó±ß×î³¤±ßÊý
	public int maxRight;

	public TreeNode(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}

import java.util.List;

import javax.swing.text.DefaultEditorKit.CopyAction;

import java.awt.HeadlessException;
import java.awt.Insets;
import java.util.ArrayList;

/*
 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

示例:

输入: 3
输出:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释:
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateTrees {
	public List<TreeNode> generateTrees(int n) 
	{
        List<Integer> nums = new ArrayList<Integer>();
        for(int i = 1;i <= n; i++)
        	nums.add(i);
        List<TreeNode> all = new ArrayList<TreeNode>();
        
        for(int i = 0; i < nums.size();i++)
		{
			int t = nums.remove(i);
			TreeNode tree_temp = new TreeNode(nums.get(i));
			
			produceTrees(nums, Insert(tree_temp, t), all);
			nums.add(0, t);
		}
        return all;
    }
	
	public void produceTrees(List<Integer> nums, TreeNode tree, List<TreeNode> all)
	{
		if(nums.isEmpty())
		{
			all.add(tree);
		}
		for(int i = 0; i < nums.size();i++)
		{
			int t = nums.remove(i);
			TreeNode tree_temp = new TreeNode(t);
			
			produceTrees(nums, Insert(tree_temp, t), all);
			nums.add(0, t);
		}
		
	}
	
	public TreeNode Insert(TreeNode tree, int x)
	{
		TreeNode head = tree;
		if(tree == null)
			return head;
		
		TreeNode treeLast = tree;
		while(tree != null)
		{
			if(tree.val < x)
			{
				treeLast = tree;
				tree = tree.right;
			}
			else
			{
				treeLast = tree;
				tree = tree.left;
			}
		}
		TreeNode temp = new TreeNode(x);
		if(treeLast.left == tree)
		{
			treeLast.left = temp;
		}
		else 
		{
			treeLast.right = temp;
		}
		return head;
	}
	
	public TreeNode copy(TreeNode tree)
	{
		if(tree == null)
			return null;
		else
		{
			TreeNode temp = new TreeNode(tree.val);
			temp.left = copy(tree.left);
			temp.right = copy(tree.right);
			return temp;
		}
	}
	
	public static void main(String args[])
	{
		GenerateTrees a = new GenerateTrees();
		a.generateTrees(2);
	}
}

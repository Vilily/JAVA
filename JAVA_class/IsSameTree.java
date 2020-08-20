import java.util.ArrayList;
import java.util.List;

public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
        	return true;
        else if(p == null || q == null)
        	return false;
        boolean left = isSameTree(p.left, q.left);
        boolean right = true;
        
        if(left)
        {
        	right = isSameTree(p.right, q.right);
        }
        else 
        {
			return false;
		}
        
        if(right && p.val == q.val)
        {
        	return true;
        }
        else 
        {
        	return false;
		}
        	
        
    }
//    public void MidTraversal(List<Integer> s, TreeNode root)
//    {
//    	if(root == null)
//    		return;
//    	MidTraversal(s, root.left);
//    	s.add(root.val);
//    	MidTraversal(s, root.right);
//    }
//    public void FirstTraversal(List<Integer> s, TreeNode root)
//    {
//    	if(root == null)
//    		return;
//    	s.add(root.val);
//    	FirstTraversal(s, root.left);
//    	FirstTraversal(s, root.right);
//    }
}

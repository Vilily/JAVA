import java.util.ArrayList;
import java.util.List;

public class MidTravelTree {
	    public List<Integer> inorderTraversal(TreeNode root) {
	        List<Integer> s = new ArrayList<Integer>();
	        inorderTraversal(s, root);
	        
	        return s;
	    }
	    public void inorderTraversal(List<Integer> s, TreeNode root)
	    {
	    	if(root == null)
	    		return;
	    	inorderTraversal(s, root.left);
	    	s.add(root.val);
	    	inorderTraversal(s, root.right);
	    }
	    
}

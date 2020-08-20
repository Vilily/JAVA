import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class IsSymmetric {
	
	 public boolean isSymmetric(TreeNode root) {
		 if(root == null)
		 {
			 return true;
		 }
		 else if(isSame(root.left, root.right))
		 {
			 return true;
		 }
		 else 
		 {
			 return false;
		 }
		 
	 }
	 
	 public boolean isSame(TreeNode left, TreeNode right)
	 {
		 if(left == null && right == null)
			 return true;
		 if(left == null || right == null)
			 return false;
		 boolean rootLeft = isSame(left.left, right.right);
		 boolean rootRight = false;
		 if(rootLeft) 
		 {
			 rootRight = isSame(left.right, right.left);
		 }
		 else 
		 {
			 return false;
		 }
		 
		 if(rootRight && left.val == right.val)
		 {
			 return true;
		 }
		 else 
		 {
			 return false;
		 }
	 }
	 
	 public static TreeNode stringToTreeNode(String input) {
	        input = input.trim();
	        input = input.substring(1, input.length() - 1);
	        if (input.length() == 0) {
	            return null;
	        }
	    
	        String[] parts = input.split(",");
	        String item = parts[0];
	        TreeNode root = new TreeNode(Integer.parseInt(item));
	        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
	        nodeQueue.add(root);
	    
	        int index = 1;
	        while(!nodeQueue.isEmpty()) {
	            TreeNode node = nodeQueue.remove();
	    
	            if (index == parts.length) {
	                break;
	            }
	    
	            item = parts[index++];
	            item = item.trim();
	            if (!item.equals("null")) {
	                int leftNumber = Integer.parseInt(item);
	                node.left = new TreeNode(leftNumber);
	                nodeQueue.add(node.left);
	            }
	    
	            if (index == parts.length) {
	                break;
	            }
	    
	            item = parts[index++];
	            item = item.trim();
	            if (!item.equals("null")) {
	                int rightNumber = Integer.parseInt(item);
	                node.right = new TreeNode(rightNumber);
	                nodeQueue.add(node.right);
	            }
	        }
	        return root;
	    }
	    
	    public static String booleanToString(boolean input) {
	        return input ? "True" : "False";
	    }
	    
	    public static void main(String[] args) throws IOException {
	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String line;
	        while ((line = in.readLine()) != null) {
	            TreeNode root = stringToTreeNode(line);
	            
	            IsSymmetric a = new IsSymmetric();
	            boolean ret = a.isSymmetric(root);
	            
	            String out = booleanToString(ret);
	            
	            System.out.print(out);
	        }
	    }
}

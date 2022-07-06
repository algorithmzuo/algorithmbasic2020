package util;

import java.util.List;

public class NTreeNode {
    public int val;
    public List<TreeNode> children;
    public NTreeNode(){};
    public NTreeNode(int _val){
        val = _val;
    }
    public NTreeNode(int _val,List<TreeNode> _children){
        val = _val;
        children = _children;
    }
}

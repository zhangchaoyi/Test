package leetcode.tree;

/**
 *
 给定一个二叉树，我们在树的节点上安装摄像头。

 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

 计算监控树的所有节点所需的最小摄像头数量。

               0
             /
           0     -----》放摄像头
         / \
        0  0
    输入：[0,0,null,0,0]
    输出 ：1
    解释：只需要把摄像头放在 第二位0上 即可

                            0
                          /
                        0  ---------》摄像头
                      /
                    0
                  /
                0  ---------》摄像头
                 \
                  0

    输入：[0,0,null,0,null,0,null,null,0]
    输出 ：2
    解释：只需要把摄像头放在 第二位0上 以及 倒数第二个0 上即可


    思路：从下往上遍历，由当前节点的 左右子节点的状态来判断当前的状态，以及是否需要安装摄像头
          状态 1 ：待覆盖
          状态 2 ： 已被覆盖
          状态 3 ：已放置摄像头
          空节点无需监管，状态 2
          根据贪心算法对于叶子节点不会放置摄像头，一定是状态 1
          非叶子节点则考虑左右子节点两两组合
 */
public class MinCameraCover {

    private int num = 0;

    public static void main(String[] args){
//        TreeNode root = new TreeNode(0);
//        TreeNode rootLeft = new TreeNode(0);
//        TreeNode rootLeftLeft = new TreeNode(0);
//        TreeNode rootLeftRight= new TreeNode(0);
//        root.left = rootLeft;
//        rootLeft.left = rootLeftLeft;
//        rootLeft.right = rootLeftRight;

        TreeNode root = new TreeNode(0);
        TreeNode rootLeft = new TreeNode(0);
        TreeNode rootLeftLeft = new TreeNode(0);
        TreeNode rootLeftLeftLeft= new TreeNode(0);
        TreeNode rootLeftLeftRight= new TreeNode(0);
        root.left = rootLeft;
        rootLeft.left = rootLeftLeft;
        rootLeftLeft.left = rootLeftLeftLeft;
        rootLeftLeft.right = rootLeftLeftRight;

        MinCameraCover mcc = new MinCameraCover();
        System.out.println(mcc.minCameraCover(root));
    }

    public int minCameraCover(TreeNode root) {
        if(travel(root) == 1){//当前节点待覆盖
            return num+1;
        }
        return num;
    }


    public int travel(TreeNode treeNode){

        if (treeNode == null) {//空节点无需监管
            return 2;
        }
        if(treeNode.left == null && treeNode.right == null){//叶子节点是状态 1
            return 1;
        }
        //非叶子节点 根据子节点的状态判断
        int leftStatus = travel(treeNode.left);
        int rightStatus = travel(treeNode.right);

        if(leftStatus == 1 && rightStatus == 1){//左右子节点都是待监管，当前节点需要放置
            num++;
            return 3;
        } else if ((leftStatus == 1 && rightStatus == 2) || (leftStatus == 2 && rightStatus == 1)) {
            num++;
            return 3;
        } else if((leftStatus == 1 && rightStatus == 3) || (leftStatus == 3 && rightStatus == 1)){
            //有一个子节点待监管，待监管的子节点它的子节点一定是状态 2
            //当前节点要放置摄像头
            num++;
            return 3;
        } else if(leftStatus == 2 && rightStatus == 2){//左右子节点都是已覆盖,当前节点待覆盖
            return 1;
        } else if((leftStatus == 2 && rightStatus == 3) || (leftStatus == 3 && rightStatus == 2)){//子节点存在一个状态3，存在一个状态2， 当前节点可以被覆盖
            return 2;
        } else if(leftStatus == 3 && rightStatus == 3){
            return 2;
        }
        System.out.println("should not happend");
        return -1;
    }

}

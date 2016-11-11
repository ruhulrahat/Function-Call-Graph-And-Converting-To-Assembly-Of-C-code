package softwareProjectLab;
 
import javax.swing.JButton;
import javax.swing.JFrame;
 
public class TreePrinter extends JFrame{
     
    int width=1300;
     
    public TreePrinter(Node root, int n) {
        super("Tree test"+Integer.toString(n));
        setLayout(null);
        setNode(root, 0, 0);
    }
     
    public void setNode(Node root,int level,int x){
         
        if(root==null) return;
         
        int y = width/(int)Math.pow(2, level);
        JButton button = new JButton(root.Name);
        button.setBounds(x+1, level*55+5,Math.abs(y-2), 50);
        add(button);
         
        setNode(root.LeftChild, level+1, x);
        setNode(root.RightChild, level+1, x+y/2);
    }
}
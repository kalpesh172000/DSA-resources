import java.util.Random;
import java.util.Scanner;

public class BinarySearchTree
{
    Node root;
    int height;
    int count;
    int minimum;


    static class Node
    {
        int data;
        Node lchild,rchild;
        Node(int data)
        {
            this.data=data;
            lchild=rchild=null;
        }
    }


    public static void insert(BinarySearchTree tree,int data)
    {
        Node temp=new Node(data);
        if (tree.root==null)
        {
            tree.root=temp;
            System.out.print("\ninorder traversal is : ");
            inorder(tree.root);
            return;
        }
        Node current=tree.root;
        while (true)
        {
            if(data<current.data)
            {
                if(current.lchild==null)
                {
                    current.lchild=temp;
                    break;
                }
                else
                    current=current.lchild;
            }
            else if (data> current.data)
            {
                if (current.rchild==null)
                {
                    current.rchild=temp;
                    break;
                }
                else
                    current=current.rchild;
            }
            else
            {
                System.out.print("\nnumber already exits in the tree");
                return;
            }

        }
        System.out.print("\ninorder traversal is : ");
        inorder(tree.root);
    }


    public static void inorder(Node root)
    {
        if(root!=null)
        {
            inorder(root.lchild);
            System.out.print(" "+root.data);
            inorder(root.rchild);
        }
    }


    public static void preorder(Node root)
    {
        if(root!=null)
        {
            System.out.print(" "+root.data);
            preorder(root.lchild);
            preorder(root.rchild);
        }
    }


    public static void postorder(Node root)
    {
        if(root!=null)
        {
            postorder(root.lchild);
            postorder(root.rchild);
            System.out.print(" "+root.data);
        }
    }


    public static void max_len(BinarySearchTree tree , Node root)
    {
        if(tree.height<tree.count)
            tree.height=tree.count;
        if(root!=null)
        {
            tree.count++;
            //System.out.print(root.data + "-" + tree.count + " ");
            max_len(tree, root.lchild);
            max_len(tree, root.rchild);
            tree.count--;
        }
    }


    public static void search(Node root , int data)
    {
        if(root==null)
        {
            System.out.print("\nnumber doesn't exit in the tree");
            return;
        }
        if(data==root.data)
            System.out.print("\nnumber exits in the tree");
        else if(data<root.data)
            search(root.lchild,data);
        else
            search(root.rchild,data);
    }


    void deleteKey(int data)
    {
        root = deleteNode(root, data);
    }


    Node deleteNode(Node root, int data)
    {
        if (root == null)
        {
            System.out.print("\nnumber doesn't exist in the tree");
            return root;
        }

        if (data < root.data)
            root.lchild = deleteNode(root.lchild, data);
        else if (data > root.data)
            root.rchild = deleteNode(root.rchild, data);
        else {
            // node with only one child or no child
            if (root.lchild == null)
                return root.rchild;
            else if (root.rchild == null)
                return root.lchild;

            // node with two children: Get the inorder
            // successor (smallest in the rchild subtree)
            root.data = inorderSuccessor(root.rchild);

            // Delete the inorder successor
            root.rchild = deleteNode(root.rchild, root.data);
        }

        return root;
    }

    int inorderSuccessor(Node root)
    {
        int minValue = root.data;
        while (root.lchild != null) {
            minValue = root.lchild.data;
            root = root.lchild;
        }
        return minValue;
    }


    void reverse(Node root)
    {
        if(root != null)
        {
            Node temp=root.lchild;
            root.lchild=root.rchild;
            root.rchild=temp;
            reverse(root.lchild);
            reverse(root.rchild);
        }
    }


    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        int choice,data;
        BinarySearchTree tree=new BinarySearchTree();

        insert(tree,15);
        insert(tree,10);
        insert(tree,20);
        insert(tree,17);
        insert(tree,11);
        insert(tree,5);
        insert(tree,23);
        insert(tree,30);
        //max_len(tree,tree.root);
        /*
                         15
                       /   \
                     10     20
                   /   \   /   \
                  5    11 17    23
                                  \
                                   30
         */
        while (true)
        {
            System.out.println("\n\n-------------------------------------------------------\nMENU");
            System.out.println("1. insert");
            System.out.println("2. number of nodes in the longest path");
            System.out.println("3. search the tree");
            System.out.println("4. delete");
            System.out.println("5. inorder");
            System.out.println("6. preorder");
            System.out.println("7. postorder");
            System.out.println("8. reverse");
            System.out.println("9. insert random numbers");
            System.out.println("10. exit");
            System.out.print("Enter your choice: ");
            choice= scan.nextInt();
            switch (choice) {
                case 1 ->
                {
                    System.out.print("\nenter the number you want to insert: ");
                    data = scan.nextInt();
                    insert(tree, data);
                }
                case 2 ->
                {
                    tree.count=0;
                    tree.height=0;
                    max_len(tree,tree.root);
                    System.out.print("\nnumber of nodes in the longest path is : " + tree.height);
                }
                case 3 ->
                {
                    System.out.print("\nenter the number you want to search : ");
                    data=scan.nextInt();
                    search(tree.root, data);
                }
                case 4 ->
                {
                    System.out.print("\nenter the number you want to delete : ");
                    data=scan.nextInt();
                    tree.deleteKey(data);
                }
                case 5 ->
                {
                    System.out.print("\ninorder traversal is : ");
                    inorder(tree.root);
                }
                case 6 ->
                {
                    System.out.print("\npreorder traversal is : ");
                    preorder(tree.root);
                }
                case 7 ->
                {
                    System.out.print("\npreorder traversal is : ");
                    postorder(tree.root);
                }
                case 8 -> tree.reverse(tree.root);
                case 9 ->
                {
                    Random rng=new Random();
                    System.out.print("\nenter the total number of random numbers you want to enter: ");
                    data=scan.nextInt();
                    for (int i = 0;i<data;i++)
                    {
                        insert(tree,rng.nextInt(100));
                    }
                }
                case 10 ->
                {
                    return;
                }
                default -> System.out.print("\nenter correct choice !!!");
            }
        }
    }
}

import org.junit.Test;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Flight on 2015/9/10.
 */
public class Main {

    /**
     * 二维数组中的查找
     *
     * @param array
     * @param number
     * @return
     */
    public boolean findNumberInArray(int array[][], int number) {
        int rows = array.length;
        int cloumns = array[0].length;
        boolean found = false;
        if (array != null && rows > 0 && cloumns > 0) {
            int row = 0;
            int cloumn = cloumns - 1;
            while (row < rows && cloumn >= 0) {
                if (array[row][cloumn] == number) {
                    found = true;
                    break;
                } else if (array[row][cloumn] > number) {
                    cloumn--;
                } else {
                    row++;
                }
            }
        }
        return found;
    }

    @Test
    public void testFindNumberInArray() {
        int a[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int number = 1;
        System.out.println(findNumberInArray(a, number));
    }

    /**
     * 替换空格
     *
     * @param str
     * @return
     */
    public String repalceSpace(StringBuffer str) {
        int length = str.length();
        int spaceNum = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                spaceNum += 2;
            }
        }
        int newStringLength = length + spaceNum;
        int behindIndex = newStringLength - 1;
        char[] newStr = new char[newStringLength];
        for (int i = length - 1; i >= 0; --i) {
            if (str.charAt(i) != ' ') {
                newStr[behindIndex] = str.charAt(i);
                behindIndex--;
            } else {
                newStr[behindIndex] = '0';
                newStr[behindIndex - 1] = '2';
                newStr[behindIndex - 2] = '%';
                behindIndex -= 3;
            }
        }


        return new String(newStr);
    }

    @Test
    public void testRepalceSpace() {
        StringBuffer str = new StringBuffer("We Are Happy.");
        String afterReplace = repalceSpace(str);
        System.out.println(afterReplace);
    }

    /**
     * 从尾到头打印链表

     */

    /**
     * 单链表实现，及常用操作
     */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 递归算法
     *
     * @param listNode
     * @param arrayList
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode, ArrayList<Integer> arrayList) {
        if (listNode != null) {
            if (listNode.next != null) {
                printListFromTailToHead(listNode.next, arrayList);
            }
        }
        arrayList.add(listNode.val);
        return arrayList;
    }

    /**
     * 非递归
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.peek());
            stack.pop();
        }
        return list;
    }

    /**
     * 重建二叉树
     */

    // Definition for binary tree
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    /**
     * @param pre 前序排列结果
     * @param in  中序排列结果
     * @return 二叉树
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null)
            return null;
        return ConstructBinaryTree(pre, in);
    }

    public TreeNode ConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null)
            return null;
        int preLength = pre.length;
        int inLength = in.length;
        int postitionOfIn = -1;
        if (preLength == 0)
            return null;
        int rootValue = pre[0];
        TreeNode root = new TreeNode(rootValue);
        //查找当前root节点在中序遍历中的位置
        for (int i = 0; i < preLength; i++) {
            if (rootValue == in[i])
                postitionOfIn = i;
        }
        if (postitionOfIn == -1)
            return null;
        //重建左子树
        int leftChildTreeNumber = postitionOfIn;
        int[] leftPre = new int[leftChildTreeNumber];
        for (int i = 0; i < leftChildTreeNumber; i++) {
            leftPre[i] = pre[i + 1];
        }
        int[] leftIn = new int[leftChildTreeNumber];
        for (int i = 0; i < leftChildTreeNumber; ++i) {
            leftIn[i] = in[i];
        }
        root.left = reConstructBinaryTree(leftPre, leftIn);

        //重建右子树
        int rightChildTreeNumber = inLength - leftChildTreeNumber - 1;
        int[] rightPre = new int[rightChildTreeNumber];
        for (int i = 0; i < rightChildTreeNumber; ++i) {
            rightPre[i] = pre[leftChildTreeNumber + i + 1];
        }
        int[] rightIn = new int[rightChildTreeNumber];
        for (int i = 0; i < rightChildTreeNumber; i++) {
            rightIn[i] = in[leftChildTreeNumber + i + 1];
        }
        root.right = reConstructBinaryTree(rightPre, rightIn);

        return root;
    }

    public LinkedList list = new LinkedList();

    public void traverseBinaryTree(TreeNode node) {
        if (node == null)
            return;
        if (node.left != null)
            traverseBinaryTree(node.left);
        if (node.right != null)
            traverseBinaryTree(node.right);
        list.add(node.val);
    }

    /**
     * 用两个栈实现队列
     */
    public class TwoStackRepresetQueue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack1 == null)
                return 0;
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    int node = stack1.peek();
                    stack2.push(node);
                    stack1.pop();
                }
            }
            int val = stack2.peek();
            stack2.pop();

            return val;
        }
    }

    /**
     * 旋转数组的最小数字
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        int length = array.length;
        if (array == null || length <= 0)
            return 0;
        int indexOfHead = 0;
        int indexOfBehind = length - 1;
        int indexOfMid = indexOfHead;
        while (array[indexOfHead] >= array[indexOfBehind]) {
            if (indexOfBehind - indexOfHead == 1) {
                indexOfMid = indexOfBehind;
                break;
            }

            indexOfMid = (indexOfHead + indexOfBehind) / 2;

            if (array[indexOfHead] == array[indexOfMid] && array[indexOfMid] == array[indexOfBehind]) {
                return particular(array, indexOfHead, indexOfBehind);
            }
            if (array[indexOfMid] >= array[indexOfHead]) {   //注意=边界值，少了=号，用例[2,2,1,2,2,2]输出为2
                indexOfHead = indexOfMid;
            } else {
                indexOfBehind = indexOfMid;
            }
        }
        return array[indexOfMid];

    }

    //特殊情况如{1,0,1,1,1},顺序查找
    int particular(int[] array, int index1, int index2) {
        int result = array[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }

    @Test
    public void testMinNumberInRotateArray() {
        int[] a = {3, 4, 5, 1, 2};
        int[] b = {1, 0, 1, 1, 1};
        int[] c = {2, 2, 1, 2, 2, 2};
        System.out.println(minNumberInRotateArray(a));
        System.out.println(minNumberInRotateArray(b));
        System.out.println(minNumberInRotateArray(c));

    }


    /**
     * 斐波那契数列
     */
    /**
     * 递归
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    /**
     * 非递归
     *
     * @param n
     * @return
     */
    public int FibWithoutRecurse(int n) {
        int result[] = {0, 1};
        if (n < 2) {
            return result[n];
        }
        int fib1 = 1;
        int fib2 = 0;
        int fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fib1 + fib2;
            fib2 = fib1;
            fib1 = fibN;
        }
        return fibN;
    }

    @Test
    public void testFib() {
        System.out.println(Fibonacci(9));
        System.out.println(FibWithoutRecurse(3));
    }

    /**
     * 变态跳台阶 Fib(n) = Fib(n-1)+Fib(n-2)+Fib(n-3)+...+Fib(1) = 2Fib(n-1)
     *
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        if (target == 1)
            return 1;
        else
            return 2 * JumpFloorII(target - 1);
    }

    @Test
    public void testJumpFloorII() {
        System.out.println(JumpFloorII(4));
    }

    public int NumberOf1(int n) {
        int number = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0)
                number++;
            flag = flag << 1;
        }
        return number;
    }

    @Test
    public void testNumberOf1() {
        System.out.println(NumberOf1(10));
    }

    /**
     * 数值的整数次方
     *
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        double result = 0;
        if (equal(0.0, base) && exponent <= 0)          //计算机中表示小数都有误差，包括float，double，判定是否相等需要判断误差值范围
            return 1.0;
        if (exponent < 0) {
            exponent = exponent * (-1);
            result = 1.0 / powerPositive(base, exponent);
        } else {
            result = powerPositive(base, exponent);
        }
        return result;
    }

    public double powerPositive(double base, int exp) {
        double result = 1.0;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    boolean equal(double num1, double num2) {
        if ((num1 - num2 > -0.00000001) && (num1 - num2) < 0.00000001)
            return true;
        else
            return false;
    }

    @Test
    public void testPower() {
        System.out.println(Power(2, -3));
    }

    /**
     * 调整数组顺序使奇数位于偶数前面,(不能保证奇数和奇数之间，偶数和偶数的相对顺序)
     *
     * @param array
     */
    public void reOrderArray(int[] array) {
        int length = array.length;
        int indexBegin = 0;
        int indexBehind = length - 1;
        while (indexBegin < indexBehind) {
            if (array[indexBegin] % 2 == 1) {
                indexBegin++;
                continue;
            }
            if (array[indexBehind] % 2 == 0) {
                indexBehind--;
                continue;
            }
            swap(array, indexBegin, indexBehind);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 调整数组顺序使奇数位于偶数前面,相对位置稳定
     *
     * @param array
     */
    public void reOrderArrayWithStable(int[] array) {
        if (array == null || array.length == 0)
            return;
        for (int m = 0; m < array.length; m++) {
            if (array[m] % 2 == 1) {
                int temp = array[m];
                int j = m - 1;
                while (j >= 0 && array[j] % 2 == 0) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = temp;
            }
        }
    }

    @Test
    public void testReOrderArray() {
        int[] a = {6, 4, 1, 5, 8, 9, 2, 7, 6};
        //reOrderArray(a);
        reOrderArrayWithStable(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }

    /**
     * 链表中倒数第k个结点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k < 0)
            return null;
        ListNode behind = head;
        ListNode ahead = head;

        for (int i = 0; i < k - 1; i++) {
            if (behind.next != null) {
                behind = behind.next;
            } else {
                return null;
            }
        }
        while (behind.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }
        return ahead;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode reverseHead = null;
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            if (next == null)
                reverseHead = node;
            node.next = pre;
            pre = node;
            node = next;
        }
        return reverseHead;
    }

    /**
     * 合并两个排序的链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeSortedLinkedList(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        ListNode merge = null;

        if (list1.val < list2.val) {
            merge = list1;
            merge.next = mergeSortedLinkedList(list1.next, list2);
        } else {
            merge = list2;
            merge.next = mergeSortedLinkedList(list1, list2.next);
        }
        return merge;
    }

    /**
     * 输入两颗二叉树A，B，判断B是不是A的子结构。
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return false;
        if (root2 != null && root1 == null)
            return false;
        boolean flag = false;

        if (root1.val == root2.val) {
            flag = doesHave(root1, root2);
        }
        if (!flag) {
            flag = HasSubtree(root1.left, root2);
            if (!flag)
                flag = HasSubtree(root1.right, root2);
        }
        return flag;
    }

    private boolean doesHave(TreeNode pointerOfA, TreeNode pointerOfB) {
        if (pointerOfB == null)
            return true;
        if (pointerOfB != null && pointerOfA == null)
            return false;
        if (pointerOfA.val == pointerOfB.val)
            return doesHave(pointerOfA.left, pointerOfB.left) && doesHave(pointerOfA.right, pointerOfB.right);
        return false;
    }

    /**
     * 二叉树的镜像
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;

        //交换当前
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        //递归子节点
        if (root.left != null)
            Mirror(root.left);
        if (root.right != null)
            Mirror(root.right);
    }

    /**
     * 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null)
            return null;
        int rows = matrix.length;
        int columns = matrix[0].length;
        ArrayList<Integer> printList = new ArrayList<>();

        int start = 0;
        //打印循环终止条件:cloumns > startX *2 && rows > startY * 2
        while (columns > start * 2 && rows > start * 2) {
            printMatrixInCircle(matrix, columns, rows, start, printList);
            ++start;
        }
        return printList;
    }

    private void printMatrixInCircle(int[][] matrix, int columns, int rows, int start, ArrayList printList) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;

        //第一步，从左到右打印
        for (int i = start; i <= endX; i++) {
            int number = matrix[start][i];
            printList.add(number);
        }

        //第二步，从上到下，条件为:终止行号大于起始行号
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                int number = matrix[i][endX];
                printList.add(number);
            }
        }

        //第三步，从右到左，条件为：至少有两行两列
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; --i) {
                int number = matrix[endY][i];
                printList.add(number);
            }
        }

        //第四步，从下到上，条件为：终止行号比起始行号至少大2，终止列号大于起始列号
        if ((endY - start) > 1 && endX > start) {
            for (int i = endY - 1; i > start; --i) {
                int number = matrix[i][start];
                printList.add(number);
            }
        }

    }

    @Test
    public void testPrintMatrix() {
        int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int matrix2[][] = {{1}};
        int matrix3[][] = {{1, 2}, {3, 4}};
        int matrix4[][] = {{1}, {2}, {3}, {4}, {5}};
        ArrayList<Integer> list = printMatrix(matrix4);
        for (Integer i : list) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 包含min函数的栈
     */
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> stackHelper = new Stack<Integer>();

    public void push(int node) {
        stack.push(node);
        if (stackHelper.size() == 0 || node < stackHelper.peek()) {
            stackHelper.push(node);
        } else {
            stackHelper.push(stackHelper.peek());
        }

    }

    public void pop() {
        assert (stack.size() > 0 && stackHelper.size() > 0);
        stack.pop();
        stackHelper.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        assert (stack.size() > 0 && stackHelper.size() > 0);
        return stackHelper.peek();
    }

    @Test
    public void testMin() {
        push(3);
        System.out.println(min());
        push(4);
        System.out.println(min());
        push(2);
        System.out.println(min());
        push(3);
        System.out.println(min());
        pop();
        System.out.println(min());
        pop();
        System.out.println(min());
        pop();
        System.out.println(min());
        push(0);
        System.out.println(min());

    }

    /**
     * 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (popA.length == 0 && pushA.length == 0)
            return false;
        Stack<Integer> stack = new Stack<>();
        boolean flag = false;
        int pushIndex = 0;
        int popIndex = 0;
        while (popIndex < popA.length) {
            while (stack.empty() || stack.peek() != popA[popIndex]) {
                if (pushIndex == pushA.length) {
                    break;
                }
                stack.push(pushA[pushIndex]);
                pushIndex++;
            }
            if (stack.peek() != popA[popIndex]) {
                System.out.println("peek:" + stack.peek() + " popA index of " + popIndex + " is : " + popA[popIndex]);
                break;
            }
            stack.pop();
            popIndex++;
        }
        if (popIndex == popA.length && stack.isEmpty())
            flag = true;
        return flag;
    }

    @Test
    public void testIsPopOrder() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        System.out.println(IsPopOrder(a, b));
    }

    /**
     * 从上往下打印二叉树
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.peek();
            list.add(node.val);
            queue.remove(node);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        return list;
    }

    @Test
    public void testPrintFromTopToBottom() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.right.left = new TreeNode(5);
        tree.right.right = new TreeNode(6);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> result2 = new ArrayList<>();

        TreeNode treeNode = null;

        result2 = PrintFromTopToBottom(treeNode);
        result = PrintFromTopToBottom(tree);
        System.out.println(result2);
        for (Integer i : result) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 二叉搜索树的后序遍历序列
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 0)
            return false;
        int length = sequence.length;
        int root = sequence[length - 1];
        int leftEnd = 0;
        for (int i = 0; i < length - 1; ++i) {
            if (sequence[i] > root)
                break;
            else
                ++leftEnd;
        }
        for (int k = leftEnd + 1; k < length; ++k) {
            if (sequence[k] < root)
                return false;
        }

        int rightLength = length - 1 - leftEnd;
        int[] left = new int[leftEnd];
        int[] right = new int[rightLength];
        for (int i = 0; i < leftEnd; ++i)
            left[i] = sequence[i];
        for (int j = 0; j < rightLength; j++) {
            right[j] = sequence[leftEnd + j];
        }
        boolean leftFlag = false;
        boolean rightFlag = false;
        if (left.length > 0)
            leftFlag = VerifySquenceOfBST(left);
        else
            leftFlag = true;
        if (right.length > 0)
            rightFlag = VerifySquenceOfBST(right);
        else
            rightFlag = true;

        return leftFlag && rightFlag;
    }

    @Test
    public void testVerifySquenceOfBST() {
        int[] a = {5, 7, 6, 9, 11, 10, 8};
        int[] b = {7, 4, 5, 6};
        System.out.println(VerifySquenceOfBST(a));
        System.out.println(VerifySquenceOfBST(b));
    }

    /**
     * 二叉树中和为某一值的路径
     *
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        if (root == null)
            return pathList;
        int currentSum = 0;

        findPath(root, target, stack, currentSum, pathList);
        return pathList;
    }

    private void findPath
            (TreeNode root, int target, Stack<Integer> stack, int currentSum, ArrayList<ArrayList<Integer>> pathList) {
        currentSum += root.val;
        stack.push(root.val);

        boolean isLeaf = root.left == null && root.right == null;

        if (currentSum == target && isLeaf) {
            Iterator it = stack.iterator();
            ArrayList<Integer> nodeList = new ArrayList<>();
            while (it.hasNext()) {
                nodeList.add((Integer) it.next());
            }
            pathList.add(nodeList);
        }
        if (root.left != null)
            findPath(root.left, target, stack, currentSum, pathList);
        if (root.right != null)
            findPath(root.right, target, stack, currentSum, pathList);

        //返回父节点之前，删除当前节点
        stack.pop();
    }

    @Test
    public void testFindPath() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.right = new TreeNode(3);

        ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();

        pathList = FindPath(tree, 7);
        for (ArrayList<Integer> a : pathList) {
            for (Integer i : a) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }


    /**
     * 复杂链表的复制
     * 开辟hashMap表来记录新旧节点关系
     */
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode copy = new RandomListNode(pHead.label);
        RandomListNode node = copy;
        RandomListNode old = pHead.next;

        Map<RandomListNode, RandomListNode> listMap = new HashMap<>();
        listMap.put(pHead, copy);

        //第一遍解决next
        while (old != null) {
            node.next = new RandomListNode(old.label);
            listMap.put(old, node.next);
            old = old.next;
            node = node.next;
        }

        //第二遍解决random
        old = pHead;
        node = copy;
        while (old != null) {
            node.random = listMap.get(old.random);
            node = node.next;
            old = old.next;
        }
        return copy;
    }


    /**
     * 二叉搜索树转换成一个排序的双向链表
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {

        if (pRootOfTree == null)
            return null;
        if (pRootOfTree.left == null && pRootOfTree.right == null)
            return pRootOfTree;

        //左子树分治
        TreeNode left = Convert(pRootOfTree.left);
        TreeNode copy = left;
        //定位左子树的最后一个节点
        while (copy != null && copy.right != null)
            copy = copy.right;

        if (left != null) {
            copy.right = pRootOfTree;
            pRootOfTree.left = copy;
        }

        //右子树
        TreeNode right = Convert(pRootOfTree.right);
        if (right != null) {
            right.left = pRootOfTree;
            pRootOfTree.right = right;
        }
        return left != null ? left : pRootOfTree;
    }

    @Test
    public void testConvert() {
        TreeNode tree = new TreeNode(10);
        tree.left = new TreeNode(6);
        tree.right = new TreeNode(14);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(8);
        tree.right.left = new TreeNode(12);
        tree.right.right = new TreeNode(16);

        TreeNode treeNode = Convert(tree);
        System.out.println();
    }

    /**
     * 字符串的排列
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str == null || str.length() <= 0)
            return list;

        Permutation(str.toCharArray(), 0, list);
        Collections.sort(list);
        return list;
    }

    private void Permutation(char[] str, int indexStart, ArrayList<String> list) {
        if (indexStart >= str.length)
            list.add(new String(str));
        else {
            for (int i = indexStart; i < str.length; ++i) {
                if (i != indexStart && str[i] == str[indexStart]) continue; //相同不用交换

                char temp = str[i];
                str[i] = str[indexStart];
                str[indexStart] = temp;
                Permutation(str, indexStart + 1, list);

                //回复原来的子串顺序
                temp = str[i];
                str[i] = str[indexStart];
                str[indexStart] = temp;
            }
        }

    }

    @Test
    public void testPermutation() {
        String str = "abc";
        ArrayList<String> list = Permutation(str);
        for (String string : list) {
            System.out.println(string);
        }
    }


    /**
     * 数组中出现次数超过一半的数字
     * 思路：保存当前元素，并把计数置1，如果下一个数字和当前相等，计数+1，否则-1，如计数为0，则用当前元素值作替换记录值
     *
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length <= 0)
            return 0;
        int target = array[0];
        int totolCount = 1;
        for (int i = 1; i < array.length - 1; ++i) {
            if (totolCount == 0) {
                target = array[i];
                totolCount = 1;
            } else if (array[i] == target)
                ++totolCount;
            else
                --totolCount;

        }
        if (!checkMoreThanHalf(array, target))
            target = 0;
        return target;
    }

    private boolean checkMoreThanHalf(int[] array, int target) {
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target)
                times++;
        }
        boolean more = true;
        if (times * 2 <= array.length)
            more = false;
        return more;
    }

    @Test
    public void testMoreThanHalfNum_Solution() {
        int[] arr = {1, 2, 3, 4, 2, 2, 2, 2, 5, 4, 2};
        int[] arr2 = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        int[] arr3 = {1};
        System.out.println(MoreThanHalfNum_Solution(arr));
        System.out.println(MoreThanHalfNum_Solution(arr2));
        System.out.println(MoreThanHalfNum_Solution(arr3));
    }

    /**
     * 最小的K个数
     */
    int partition(int[] input, int low, int high) {
        int key = input[high];
        while (low < high) {
            while (low < high && input[low] <= key) ++low;
            swap(input, low, high);
            while (low < high && input[high] >= key) --high;
            swap(input, low, high);
        }
        return low;
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k <= 0 || k > input.length)
            return list;
        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);

        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(input, start, end);
            } else {
                start = index + 1;
                index = partition(input, start, end);
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    @Test
    public void testGetLeastNumbers_Solution() {
        int[] a = {4, 5, 1, 6, 2, 7, 3, 8};
        ArrayList<Integer> list = GetLeastNumbers_Solution(a, 4);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 连续子数组的最大和
     *
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array != null && array.length <= 0)
            return 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            if (sum < 0) {
                sum = array[i];
            } else
                sum += array[i];
            max = sum > max ? sum : max;
        }
        return max;
    }

    @Test
    public void testFindGreatestSumOfSubArray() {
        int[] a = {6, -3, -2, 7, -15, 1, 2, 2};
        int[] b = {-2, -8, -1, -5, -9};
        System.out.println(FindGreatestSumOfSubArray(a));
        System.out.println(FindGreatestSumOfSubArray(b));
    }

    /**
     * 整数中1出现的次数（从1到n整数中1出现的次数）
     *
     *参考：http://www.cnblogs.com/nailperry/p/4752987.html
     * 1. 如果第i位（自右至左，从1开始标号）上的数字为0，则第i位可能出现1的次数由更高位决定（若没有高位，视高位为0），
     * 等于更高位数字X当前位数的权重10i-1。

     2. 如果第i位上的数字为1，则第i位上可能出现1的次数不仅受更高位影响，还受低位影响（若没有低位，视低位为0），
     等于更高位数字X当前位数的权重10i-1+（低位数字+1）。

     3. 如果第i位上的数字大于1，则第i位上可能出现1的次数仅由更高位决定（若没有高位，视高位为0），
     等于（更高位数字+1）X当前位数的权重10i-1。
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n < 0)
            return 0;
        int high, low, curr, temp, i = 1;
        high = n;
        int total = 0;
        while (high != 0) {
            high = n / (int) Math.pow(10, i);
            temp = n % (int) Math.pow(10, i);
            curr = temp / (int) Math.pow(10, i - 1);
            low = temp % (int) Math.pow(10, i - 1);
            if (curr == 1) {
                total += high * (int) Math.pow(10, i - 1) + low + 1;
            } else if (curr < 1) {
                total += high * (int) Math.pow(10, i - 1);
            } else {
                total += (high+1 ) * (int) Math.pow(10, i - 1);
            }
            i++;
        }
        return total;
    }
    @Test
    public void testNumberOf1Between1AndN_Solution(){
       System.out.println(NumberOf1Between1AndN_Solution(13));
    }

    /**
     * 把数组排成最小的数
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        String s = "";
        if (numbers == null || numbers.length <= 0)
            return s;
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0;i < numbers.length;++i){
            numList.add(numbers[i]);
        }
        Collections.sort(numList,new Comparetor());
        for (Integer i:numList){
            s += i;
        }
        return s;
    }

    static class Comparetor implements Comparator<Integer>{

        @Override
        public int compare(Integer str1, Integer str2) {
            String joinStr1 = str1+""+str2;
            String joinStr2 = str2+""+str1;
            if (Integer.valueOf(joinStr1) > Integer.valueOf(joinStr2)){
                return 1;
            }if (Integer.valueOf(joinStr1) < Integer.valueOf(joinStr2))
                return -1;
            else
                return 0;
        }
    }
    @Test
    public void testPrintMinNumber(){
        int[] number = {3,32,321};
        System.out.println(PrintMinNumber(number));
    }

    /**
     * 丑数
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0)
            return 0;
        ArrayList<Integer> uglyNumbers = new ArrayList<>();
        uglyNumbers.add(1);
        int nextUglyIndex = 1;

        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;

        while (nextUglyIndex < index){
            int min = minNumberOf235(uglyNumbers.get(multiply2)*2,uglyNumbers.get(multiply3)*3,uglyNumbers.get(multiply5)*5);

            uglyNumbers.add(min);
            while (uglyNumbers.get(multiply2)*2 <= uglyNumbers.get(nextUglyIndex))
                ++multiply2;
            while (uglyNumbers.get(multiply3)*3 <= uglyNumbers.get(nextUglyIndex))
                ++multiply3;
            while (uglyNumbers.get(multiply5)*5 <= uglyNumbers.get(nextUglyIndex))
                ++multiply5;

            ++nextUglyIndex;
        }
        int ugly = uglyNumbers.get(nextUglyIndex - 1);;
        return ugly;
    }

    private int minNumberOf235( int multiply2, int multiply3, int multiply5) {
        int min = multiply2<multiply3 ? multiply2:multiply3;
        min = min<multiply5 ? min:multiply5;
        return min;
    }

    @Test
    public void testGetUglyNumber_Solution(){
        System.out.println(GetUglyNumber_Solution(3));
    }


    /**
     * 第一个只出现一次的字符位置
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        Map<Character,Integer> map = new LinkedHashMap<>();
        for (int i = 0;i < str.length();++i){
            for (Character character = 'A';character<='z';++character){
                if (str.charAt(i) == character && map.get(character) != null){
                    map.put(character,map.get(character) + 1);
                }else if (str.charAt(i) == character && map.get(character) == null){
                    map.put(character,1);
                }
            }
        }
        Iterator iterator = map.keySet().iterator();
        Character key = null;
        while (iterator.hasNext()){
            Character c = (Character) iterator.next();
            if (map.get(c) == 1) {
                key = c;
                break;
            }
        }
        if (key == null)
            return -1;
        for (int i = 0;i<str.length();++i){
            if (str.charAt(i) == key)
                return i;
        }
         return -1;
    }

    @Test
    public void testFirstNotRepeatingChar(){
        int c = FirstNotRepeatingChar("ABACCDEFF");
        System.out.println(c);
    }

    /**
     * 数组中的逆序对
     * @param array
     * @return
     */
    public int InversePairs(int [] array) {
        int length = array.length;
        if (array == null || length < 0)
            return 0;

       int[] copy = new int[length];
        for (int i =0;i < length;++i)
            copy[i] = array[i];

        int count = InversePairsCore(array, copy, 0, length - 1);
        return count;
    }

    public int InversePairsCore(int[] data,int[] copy,int start,int end){
        if (start == end){
            copy[start] = data[start];
            return 0;
        }

        int length = (end- start) / 2;
        int left = InversePairsCore(copy,data,start,start+length);
        int right = InversePairsCore(copy,data,start+length+1,end);

        //i为前半段下标，j为后半段下标
        int i = start+length;
        int j = end;
        int indexCopy = end;
        int count = 0;

        while (i >= start && j>= start+length+1){
            if (data[i] > data[j]){
                copy[indexCopy -- ] = data[i--];
                count += j -start-length;
            }else {
                copy[indexCopy--] = data[j--];
            }
        }

        for (;i >= start;--i)
            copy[indexCopy--] = data[i];
        for (;j >= start+length+1;--j)
            copy[indexCopy--] = data[j];

        return left+right+count;
    }

    @Test
    public void testInversePairs(){
        int[] arr = {1,2,3,4,7,6,5};
        System.out.println(InversePairs(arr));
    }

    /**
     * 两个链表的第一个公共结点
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        int length1 = 0;
        int length2 = 0;
        ListNode copy1 = pHead1;
        ListNode copy2 = pHead2;
        while (copy1.next != null) {
            length1++;
            copy1 = copy1.next;
        }
        while (copy2.next != null) {
            length2++;
            copy2 = copy2.next;
        }
        int diff = 0;
        if (length1 > length2){
            diff = length1 - length2;
            while (diff > 0){
                pHead1 = pHead1.next;
                --diff;
            }
        }else{
            diff = length2 - length1;
            while (diff > 0){
                pHead2 = pHead2.next;
                --diff;
            }
        }
        while (pHead1 != null || pHead2 != null){
            if (pHead1.val == pHead2.val){
                return pHead1;
            }else {
                pHead1 = pHead1.next;
                pHead2 = pHead2.next;
            }
        }
        return null;
    }
    @Test
    public void testFindFirstCommonNode(){
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        listNode1.next.next.next.next = new ListNode(7);
        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(7);
        //listNode2.next.next.next = new ListNode(7);

        ListNode result = FindFirstCommonNode(listNode1,listNode2);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    /**
     * 数字在排序数组中出现的次数
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        int number = 0;
        int length = array.length;
        if (array!=null && length > 0) {
            int first = GetNumberOfFirstK(array, k, 0, length - 1);
            int last = GetNumberOfLastK(array, k, 0, length - 1);

            if (first > -1 && last > -1)
                number = last - first + 1;
        }
        return number;
    }

    public int  GetNumberOfFirstK(int[] array,int k,int start,int end){
        if (start > end)
            return -1;
        int midIndex = (start + end)/2;
        int midData = array[midIndex];

        if (midIndex == 0 && midData == k)
            return 0;
        if (midIndex == 0 && midData != k)
            return -1;

        if (midData == k){
            if (midIndex > 0 && array[midIndex-1] != k)
                return midIndex;
            else
                end  = midIndex-1;
        }
        else if (midData > k)
            end = midIndex -1;
        else
            start = midIndex +1;

        return GetNumberOfFirstK(array,k,start,end);
    }
    public int  GetNumberOfLastK(int[] array,int k,int start,int end){
        if (start > end)
            return -1;
        int midIndex = (start + end)/2;
        int midData = array[midIndex];

        if (midData == k){
            if (midIndex < array.length-1 && array[midIndex+1] != k || midIndex == array.length-1)
                return midIndex;
            else
                start  = midIndex+1;
        }
        else if (midData > k)
            end = midIndex -1;
        else
            start = midIndex +1;

        return GetNumberOfLastK(array, k, start, end);
    }

    @Test
    public void testGetNumberOfK(){
        int[] arr = {3,3,3,3,4,5};
        int[] arr2 = {1,2,3,3,3,3};
        final int  key = 3;
        System.out.println(GetNumberOfK(arr, key));
        System.out.println(GetNumberOfK(arr2, key));
    }


    /**
     * 二叉树的深度
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);

        return leftDepth>rightDepth?(leftDepth+1):(rightDepth+1);
    }
    @Test
    public void testTreeDepth(){
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.left.left = new TreeNode(2);
        treeNode1.left.right = new TreeNode(2);
        treeNode1.right = new TreeNode(2);
        treeNode1.right.right = new TreeNode(2);
        treeNode1.right.right.right = new TreeNode(2);
        System.out.println(TreeDepth(treeNode1));
    }

    /**
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null)
            return true;

        int left = BalancedHight(root.left);
        int right = BalancedHight(root.right);

        int diff = right-left;

        if (diff > 1 || diff < -1)
            return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);

    }

    private int BalancedHight(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = BalancedHight(root.left);
        int rightDepth = BalancedHight(root.right);

        return leftDepth>rightDepth?(leftDepth+1):(rightDepth+1);
    }

    @Test
    public void testIsBalanced(){
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.left.left = new TreeNode(2);
        treeNode1.left.right = new TreeNode(2);
       // treeNode1.left.right.right = new TreeNode(2);
        treeNode1.right = new TreeNode(2);
        treeNode1.right.right = new TreeNode(2);
    //    treeNode1.right.right.right = new TreeNode(2);

        System.out.println(IsBalanced_Solution(treeNode1));
    }


    /**
     * 数组中只出现一次的数字
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == num1 || array.length <2)
            return;
        int resultXOR = 0;
        for (int i = 0;i<array.length;++i)
            resultXOR ^= array[i];
        int indexOf1 = FindFirstBitIs1(resultXOR);
        for (int j =0;j<array.length;++j){
            //按第indexOf1位是否为1分两组
            if (isBit1(array[j],indexOf1)){
                //异或的一个性质：任何一个数字异或自己为零，即该数组从头异或到尾得到的是唯一的那个数
                num1[0] ^= array[j];
            }else {
                num2[0] ^=array[j];
            }
        }
    }

    //整数中找到最右边为1的位
    public int FindFirstBitIs1(int num) {
        int indexOfBit = 0;
        while ((num & 1) == 0 &&(indexOfBit <64)){
            num = num>>1;
            indexOfBit++;
        }
        return indexOfBit;
    }
    //判断num二进制右边数起indexBit位是不是1
    public boolean isBit1(int num,int indexOfBit){
        num = num>>indexOfBit;
        return (num&1)==1;
    }

    @Test
    public void testFindNumsAppearOnce(){
        int[] arr = new int[]{2,4,3,6,3,2,5,5};
        int[] num1 = new int[]{0};
        int[] num2 = new int[]{0};
        FindNumsAppearOnce(arr,num1,num2);
        System.out.println(num1[0] + "   " + num2[0]);
    }

    /**
     * 和为S的连续正数序列
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> numberList = new ArrayList<>();
        LinkedList<Integer> singleList = new LinkedList<>();
        if (sum <= 1)
            return numberList;
        int currentSum = 0;
        for (int key = 1; key <= sum/2+1;++key){
            currentSum += key;
            singleList.add(key);

            while (currentSum > sum) {
                currentSum -= singleList.getFirst();
                singleList.removeFirst();
            }
            if (currentSum == sum){
                ArrayList<Integer> list = new ArrayList<>(singleList);
                numberList.add(list);
                currentSum -= singleList.getFirst();
                singleList.removeFirst();
            }

        }
        return numberList;
    }

    @Test
    public void testFindContinuousSequence(){
        ArrayList<ArrayList<Integer>> numberList = FindContinuousSequence(100);
        for (ArrayList<Integer> list : numberList){
            for (Integer i:list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 和为S的两个数字
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if (array == null || sum <= 1){
            return result;
        }
        int indexBefore = 0;
        int indexAfter = array.length-1;
        while (indexBefore < indexAfter){
            int sumOfTwo = array[indexBefore] + array[indexAfter];
            if ( sumOfTwo== sum){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(array[indexBefore]);
                temp.add(array[indexAfter]);
                resultList.add(temp);
                indexAfter --;
                indexBefore ++;
            }else if(sumOfTwo > sum){
                indexAfter --;
            }else {
                indexBefore ++;
            }
        }
        if (resultList.size() != 0)
            result = resultList.get(0);
        return result;
    }

    @Test
    public void testFindNumbersWithSum(){
        int[] arr = {1,2,3,4,5,6,7,8};
        int[] arr2 = {1,2,4,7,11,16};

        ArrayList<Integer> list = FindNumbersWithSum(arr2,10);
        for (Integer i : list){
            System.out.print(i + "  ");
        }
    }

    /**
     * 左旋转字符串
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        LinkedList<Character> characterLinkedList = new LinkedList<>();
        if (str == null || n > str.length())
            return new String();
        if (n == 0)
            return str;
        for (int i = 0;i < str.length();i++){
            characterLinkedList.add(str.charAt(i));
        }
        LinkedList<Character> removed = new LinkedList<>();
        for (int j = 0; j < n;++j){
            removed.add(characterLinkedList.getFirst());
            characterLinkedList.removeFirst();
        }
        for (int k = 0;k < removed.size();k++){
            characterLinkedList.add(removed.get(k));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length();i++)
            sb.append(characterLinkedList.get(i));

        return sb.toString();
    }
    @Test
    public void testLeftRotateString(){
        String str = "abcXYZdef";
        String str2 = "abcdefg";
        String result = LeftRotateString(str2,2);
        System.out.println(result);
    }

    /**
     * 翻转单词顺序列
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str == null || str.length() <=0)
            return new String();
        if (!str.contains(" ")|| str.split(" ").length <= 1)
            return str;

        StringBuffer sb = new StringBuffer();
        for (int i = str.length()-1;i >= 0;--i){
            sb.append(str.charAt(i));
        }
        String[] words = sb.toString().split(" ");

        StringBuffer result = new StringBuffer();
        for (int i = 0;i < words.length-1;++i){
            StringBuffer temp = new StringBuffer(words[i]);
            result.append( temp.reverse());
            result.append(" ");
        }
        StringBuffer temp2 = new StringBuffer(words[words.length-1]);
        result.append( temp2.reverse());
        return result.toString();
    }

    @Test
    public void testReverseSentence(){
        String str = "I am a student.";
        String str2 = "Wonderful.";
        String str3 = " ";
        String str4 = "Hello World! ";
        System.out.println(ReverseSentence(str4));
    }

    /**
     * 扑克牌顺子
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length <= 0)
            return false;
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int i= 0;i< numbers.length;++i)
            numberList.add(numbers[i]);
        Collections.sort(numberList);
        int numberOf0 = 0;
        int numberOfGap = 0;
        for (int i = 0;i< numberList.size();++i){
            if (numberList.get(i) == 0)
                ++numberOf0;
        }

        int small = numberOf0;
        int big = small + 1;
        while (big < numbers.length){
            if (numberList.get(small) == numberList.get(big))
                return false;
            numberOfGap += numberList.get(big) - numberList.get(small) -1;
            small = big;
            ++big;
        }
        return numberOf0 >= numberOfGap? true:false;
    }

    @Test
    public void testIsContinuous(){
        int[] num = {0,1,3,4,5};
        int[] num2 = {0,3,1,6,4};
        System.out.println(isContinuous(num2));
    }

    /**
     * 孩子们的游戏(圆圈中最后剩下的数)
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1)
            return -1;
        int[] circle = new int[n];

        int count = n;
        int i = -1,step = 0;
        while(count > 0){
            i++;
            if (i >= n) i = 0;
            if (circle[i] == -1) continue;
            step ++;
            if (step == m){
                circle[i] = -1;
                step = 0;
                count --;
            }
        }
        return i;
    }


    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int result = n;
        int a =1;
        boolean ans = (n>0)&&((result += Sum_Solution(n-1))>0);
        return result;
    }
    @Test
    public void testSum_Solution(){
        System.out.println(Sum_Solution(4));
    }

    /**
     * 不用加减乘除做加法
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1,int num2) {
        int sum,carry;
        while (num2 != 0){
            sum = num1 ^ num2;  //异或，得到无进位加法结果
            carry = (num1 & num2) << 1; //与运算，左移1位表示得到进位

            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    @Test
    public void testAdd(){
        System.out.println(Add(2,4));
    }

    /**
     * 把字符串转换成整数
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if (str.length()  <= 0)
            return 0;
        boolean flag = true;
        if (str.charAt(0) == '+')
            str = str.substring(1);
        else if (str.charAt(0) == '-'){
            flag = false;
            str = str.substring(1);
        }
        char[] ints = str.toCharArray();
        int number = 0;
        for (int i = 0; i < ints.length;++i){
            if (ints[i]-'0' >= 0 && ints[i]-'0' <=9)
                number = number*10 + (ints[i]-'0');
            else
                return 0;
        }
        if (flag == false)
            number = 0 - number;
        return number;
    }
    @Test
    public void testStrToInt(){
        System.out.println(StrToInt("-123456"));
    }


    /**
     * 数组中重复的数字,hashmap实现
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || length <= 0)
            return false;
        HashMap<Integer,Integer> times = new HashMap<>();
        for(int i = 0 ; i < length;++i){
            if (!times.containsKey(numbers[i]))
                times.put(numbers[i],1);
            else {
                times.put(numbers[i],times.get(numbers[i])+1);
                duplication[0] = numbers[i];
                return true;
            }

        }
        return false;
    }

    @Test
    public void testDuplicate(){
        int[] numbers = new int[]{1,2,3,4,5,2,1};
        int[] duplication = new int[1];
        System.out.println(duplicate(numbers,numbers.length,duplication));
        System.out.println(duplication[0]);
    }
}

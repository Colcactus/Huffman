package demo;

import java.io.*;
import java.util.*;

/**
 * @author luoshilv
 */
final public class HuffmanCode {
    final private static int DICT = 8;

    /**
     * 压缩函数
     *
     * @param src
     * @param dir
     * @throws Exception
     */
    public static void zipFile(String src, String dir) throws Exception {
        // 读取文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        // 创建一个等大的数组
        byte[] b = new byte[bis.available()];
        bis.read(b);
        bis.close();
        // 进行压缩
        byte[] byteZip = huffmanZip(b);
        // 输出文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dir));
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        // 写出压缩后的数据
        oos.writeObject(byteZip);
        // 写出编码表
        oos.writeObject(huffmanCodes);
        oos.close();
        bos.close();
    }

    /**
     * Huffman压缩实现
     *
     * @param bytes
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        // 获取节点
        List nodes = getNodes(bytes);
        // 创建树
        Node tree = createHuffmanTree(nodes);
        // 获取编码表
        Map<Byte, String> huffmanCodes = getCodes(tree);
        // 返回数据和编码表
        System.out.println(zip(bytes,huffmanCodes));
        return zip(bytes, huffmanCodes);
    }

    /**
     * Huffman解码
     *
     * @param huffmanCodes
     * @param bytes
     * @return
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] bytes) {
        // 逆过程
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean flag = (i == bytes.length - 1);
            sb.append(byteToBitStr(!flag, b));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        System.out.println(b);
        return b;
    }

    /**
     * 将字节转换为String类型
     * 强健型设计
     */
    private static String byteToBitStr(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - DICT);
        } else {
            return str;
        }
    }

    /**
     * Huffman压缩
     *
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 将字节转换为可变字符串
        StringBuilder sb = new StringBuilder();
        // 递归传入
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }

        // 按照字典大小将数据分块
        int len;
        if (sb.length() % DICT == 0) {
            len = sb.length() / DICT;
        } else {
            len = sb.length() / DICT + 1;
        }

        // 将每一个块的数据进行压缩
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += DICT) {
            String strByte = null;
            if (i + DICT > sb.length()) {
                sb.substring(i);
            } else {
                sb.substring(i, i + DICT);
            }
            assert strByte != null;
            byte byt = (byte) Integer.parseInt(strByte, 2);
            by[index] = byt;
            index++;
        }
        System.out.println(by);
        return by;
    }

    /**
     * 临时存储
     */
    static StringBuilder sb = new StringBuilder();
    /**
     * 临时映射
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    /**
     * 获取Huffman编码
     *
     * @param tree
     * @return
     */
    private static Map<Byte, String> getCodes(Node tree) {
        if (tree == null) {
            return null;
        }
        getCodes(tree.left, "0");
        getCodes(tree.right, "1");
        System.out.println(huffmanCodes);
        return huffmanCodes;
    }

    /**
     * 获取Huffman编码
     *
     * @param node
     * @param code
     */
    private static void getCodes(Node node, String code) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(code);
        if (node.data == Byte.parseByte(null)) {
            getCodes(node.left, "0");
            getCodes(node.right, "1");
        } else {
            huffmanCodes.put(node.data, sb2.toString());
        }
        System.out.println(sb2.toString());
    }

    /**
     * 创建Huffman树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        // 就是一个计算父节点的函数
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        System.out.println(nodes.get(0));
        return nodes.get(0);
    }

    /**
     * 获取节点
     *
     * @param bytes
     * @return
     */
    private static List getNodes(byte[] bytes) {
        // 数组
        List nodes = new ArrayList<>();
        // 映射
        Map<Byte, Integer> counts = new HashMap<>();
        // 递归获取节点
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);

            for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
                nodes.add(new Node(entry.getKey(), entry.getValue()));
            }
            System.out.println(nodes.toString());
        }
        return nodes;
    }
}
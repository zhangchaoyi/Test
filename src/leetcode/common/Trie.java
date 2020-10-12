package leetcode.common;

/**
 * 208. 实现 Trie (前缀树)
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 *
 *  * Your Trie object will be instantiated and called as such:
 *  * Trie obj = new Trie();
 *  * obj.insert(word);
 *  * boolean param_2 = obj.search(word);
 *  * boolean param_3 = obj.startsWith(prefix);
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/12 15:35
 */
public class Trie {

    private boolean is_string=false;
    private Trie[] next=new Trie[26];

    public Trie(){}

    /**
     * 插入 O(n)
     * @param word
     */
    public void insert(String word){//插入单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)root.next[w[i]-'a']=new Trie();
            root=root.next[w[i]-'a'];
        }
        root.is_string=true;
    }

    /**
     * O(n)
     * @param word
     * @return
     */
    public boolean search(String word){//查找单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)return false;
            root=root.next[w[i]-'a'];
        }
        return root.is_string;
    }

    /**
     * O(n)
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix){//查找前缀
        Trie root=this;
        char p[]=prefix.toCharArray();
        for(int i=0;i<p.length;++i){
            if(root.next[p[i]-'a']==null)return false;
            root=root.next[p[i]-'a'];
        }
        return true;
    }

    public static void main(String[] args){
        Trie obj = new Trie();
        obj.insert("word");
        obj.insert("wa");
        System.out.println(obj.search("word"));
        System.out.println(obj.startsWith("prefix"));
    }
}

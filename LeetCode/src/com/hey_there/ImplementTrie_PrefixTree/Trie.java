package com.hey_there.ImplementTrie_PrefixTree;

import java.util.HashMap;

public class Trie {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] wordChs = word.toCharArray();
        //首先检查树中是否有word的前缀
        int idx = 0;
        TrieNode checkingNode = root;
        while (idx < wordChs.length) {
            //获取正检查的节点的子节点集合
            HashMap<Character, TrieNode> sonsOfCheckingNode = checkingNode.getSons();
            if (sonsOfCheckingNode.containsKey(wordChs[idx])) {
                checkingNode = sonsOfCheckingNode.get(wordChs[idx]);
                idx++;
            } else {
                break;
            }
        }
        //若idx等于word的长度，则前缀树中已有此单词
        if (idx == wordChs.length) {
            //根节点到当前结点的路径能构成单词，设置标志值为true
            checkingNode.setFormWordTag(true);
            return;
        }
        //若idx小于word的长度，则需要插入新节点
        TrieNode prevNode = checkingNode;
        while (idx < wordChs.length) {
            //新建一个节点，存入word的下一个字符值
            TrieNode nextNode = new TrieNode();
            nextNode.setVal(wordChs[idx]);
            nextNode.setFormWordTag(idx == wordChs.length - 1);
            //将新节点加入前一个节点的子节点集合
            prevNode.getSons().put(wordChs[idx], nextNode);
            prevNode = nextNode;
            idx++;
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char[] wordChs = word.toCharArray();
        int idx = 0;
        TrieNode checkingNode = root;
        while (idx < wordChs.length) {
            //获取正检查的节点的子节点集合
            HashMap<Character, TrieNode> sonsOfCheckingNode = checkingNode.getSons();
            //若当前节点没有值为wordChs[idx]的子节点，则前缀树中没有此单词
            if (!sonsOfCheckingNode.containsKey(wordChs[idx])) {
                return false;
            } else {
                //当前节点有值为wordChs[idx]的子节点时，继续向后检查
                checkingNode = sonsOfCheckingNode.get(wordChs[idx]);
                idx++;
            }
        }
        //执行到这里，表示前缀树中有word的所有字符
        return checkingNode.canFormWord();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] prefixChs = prefix.toCharArray();
        int idx = 0;
        TrieNode checkingNode = root;
        while (idx < prefixChs.length) {
            //获取正检查的节点的子节点集合
            HashMap<Character, TrieNode> sonsOfCheckingNode = checkingNode.getSons();
            //若当前节点没有值为prefixChs[idx]的子节点，则前缀树中没有此单词
            if (!sonsOfCheckingNode.containsKey(prefixChs[idx])) {
                return false;
            } else {
                //当前节点有值为prefixChs[idx]的子节点时，继续向后检查
                checkingNode = sonsOfCheckingNode.get(prefixChs[idx]);
                idx++;
            }
        }
        return true;
    }

    private static class TrieNode {
        private char val;//节点字符值
        private boolean formWord;//根节点到此节点的路径值连起来是否是单词
        //子节点值和子节点之间的映射
        private HashMap<Character, TrieNode> sons;

        public TrieNode() {
            this.sons = new HashMap<>();
        }


        public char getVal() {
            return val;
        }

        public boolean canFormWord() {
            return formWord;
        }

        public HashMap<Character, TrieNode> getSons() {
            return sons;
        }

        public void setVal(char val) {
            this.val = val;
        }

        public void setFormWordTag(boolean formWord) {
            this.formWord = formWord;
        }
    }
}

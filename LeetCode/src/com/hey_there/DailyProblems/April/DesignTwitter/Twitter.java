package com.hey_there.DailyProblems.April.DesignTwitter;

import java.util.*;

public class Twitter {
    private static int timestamp = 0;//推文发表的时间戳
    private final Map<Integer, User> userMap;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        //若发推的用户不存在则先创建此用户
        if (!userMap.containsKey(userId)) {
            User u = new User(userId);
            userMap.put(userId, u);
        }
        //取得用户对象并发推
        User u = userMap.get(userId);
        u.post(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList<>();
        //用户不存在则返回一个空集合
        if (!userMap.containsKey(userId)) {
            return ans;
        }
        //获取userId对应的用户所关注的用户
        Set<Integer> followee = userMap.get(userId).following;
        //优先队列用来筛选最新的推文
        PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>();

        //将各个被关注用户的最新一条推文加入优先队列
        for (int f : followee) {
            Tweet newestTweet = userMap.get(f).head;
            //此用户没有推文则不进行添加，避免向priorityQueue加入null
            if (newestTweet != null) {
                priorityQueue.add(newestTweet);
            }
        }
        while (!priorityQueue.isEmpty()) {
            if (ans.size() == 10) {
                break;
            }
            Tweet newestTweet = priorityQueue.poll();
            ans.add(newestTweet.tweetId);
            if (newestTweet.next != null) {
                priorityQueue.add(newestTweet.next);
            }
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        //若followerId对应的用户不存在，则创建此用户
        if (!userMap.containsKey(followerId)) {
            User follower = new User(followerId);
            userMap.put(followerId, follower);
        }
        //若followeeId对应的用户不存在，则创建此用户
        if (!userMap.containsKey(followeeId)) {
            User followee = new User(followeeId);
            userMap.put(followeeId, followee);
        }
        //取得follower对象，将followee加入其关注集合
        User follower = userMap.get(followerId);
        follower.follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        //当followerId对应的用户存在时进行操作，不存在则不进行操作
        if (userMap.containsKey(followerId)) {
            User follower = userMap.get(followerId);
            follower.unfollow(followeeId);
        }
    }

    private static class User {
        private final int userId;
        private final Set<Integer> following;
        private Tweet head;

        public User(int userId) {
            this.userId = userId;
            this.following = new HashSet<>();
            this.head = null;
            //默认关注自己
            follow(this.userId);
        }

        public void follow(int followeeId) {
            following.add(followeeId);
        }

        public void unfollow(int followerId) {
            //无法取关自己
            if (followerId != userId) {
                following.remove(followerId);
            }
        }

        public void post(int tweetId) {
            timestamp++;//更新时间戳
            Tweet t = new Tweet(timestamp, tweetId);
            //将新建的tweet插入到链表头部
            t.next = head;
            head = t;
        }
    }

    private static class Tweet implements Comparable<Tweet> {
        private final int time;
        private final int tweetId;
        private Tweet next;

        public Tweet(int time, int tweetId) {
            this.time = time;
            this.tweetId = tweetId;
            this.next = null;
        }

        @Override
        public int compareTo(Tweet other) {
            /* PriorityQueue使用小顶堆实现，poll时取出堆顶，
             * 所以要使时间戳较小的推文具有更大的权值，实现使时间戳最大的推文排在堆顶*/
            if (time < other.time) {
                return 1;//返回1，使时间戳较小的推文具有更大的权值
            } else if (time > other.time) {
                return -1;//返回-1，使时间戳较大的推文具有更小的权值
            }
            return 0;
        }
    }
}

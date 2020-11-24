package leetcode.array.two_dimension;

import leetcode.DynamicPlanning.LongestCommonSubstring;

import java.util.*;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 思路：1.bfs 超出时间限制
 * 2.取巧 [未想明白]
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/11/10 14:23
 */
public class ReconstructQueue {

    public static void main(String[] args){
        ReconstructQueue rq = new ReconstructQueue();
//        int[][] array = new int[8][5];//{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
//        array[7][0]=1;
//        array[4][4]=1;
//        array[7][1]=1;
//        array[5][0]=1;
//        array[6][1]=1;
//        array[5][2]=1;
        int[][] array = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] result = rq.reconstructQueue(array);

        LongestCommonSubstring.printArray(result);

//
//        List<Pair> curList = new ArrayList<>();
//        curList.add(new Pair(5,0));
//        curList.add(new Pair(7,0));
//        System.out.println(rq.canAdd(curList, new Pair(5,1)));
    }

    public int[][] reconstructQueue(int[][] people) {
        //二维数组多条件排序，按第一列倒序，第二列升序  (第二列升序的原因是防止arrayList插入时下标报错)
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        //排序后的数组，按第二列进行插入排序
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }


//----------------------------------------------------------------------------------------------------------------------------------------------------------
    public int[][] reconstructQueue1(int[][] people) {
        List<Pair> input = new ArrayList<>();
        for(int i=0;i<people.length;i++){
            input.add(new Pair(people[i][0], people[i][1]));
        }

        List<Pair> res = null;
        Queue<Job> jobQueue = new LinkedList<>();
        Job firstJob = new Job(new ArrayList<>(), input);
        jobQueue.add(firstJob);

        while(!jobQueue.isEmpty()){
            Job job = jobQueue.poll();
            //跳出条件得到结果
            if(job.getRemainList().size()==0){
                res = job.getCurList();
                break;
            }
            //取出job中的curList和remainList 进行判断是否可以插入curList
            for(int i=0;i<job.getRemainList().size();i++){
                if(canAdd(job.getCurList(), job.getRemainList().get(i))){
                    List<Pair> newCurList = new ArrayList<>(job.getCurList());
                    newCurList.add(job.getRemainList().get(i));
                    List<Pair> newRemainList = new ArrayList<>(job.getRemainList());
                    newRemainList.remove(i);
                    jobQueue.add(new Job(newCurList, newRemainList));
                }
            }

        }

        int[][] result = new int[people.length][2];
        for(int i=0;i<res.size();i++){
            result[i][0] = res.get(i).getLeft();
            result[i][1] = res.get(i).getRight();
        }
        return result;
    }

    private boolean canAdd(List<Pair> curList, Pair nextPair){
        //判断大于nextPair.left的元素有right个
        int amount = 0;
        for(int i=0;i<curList.size();i++) {
            if(curList.get(i).getLeft() >= nextPair.getLeft()){
                amount++;
            }
        }
        return amount==nextPair.getRight();
    }

    static class Pair{
        private int left;
        private int right;

        Pair(int left, int right){
            this.left = left;
            this.right = right;
        }

        int getLeft(){
            return this.left;
        }

        int getRight(){
            return this.right;
        }
    }

    static class Job{
        private List<Pair> curList;
        private List<Pair> remainList;

        List<Pair> getCurList(){
            return this.curList;
        }

        List<Pair> getRemainList(){
            return this.remainList;
        }

        void setCurList(List<Pair> curList){
            this.curList = curList;
        }

        void setRemainList(List<Pair> remainList){
            this.remainList = remainList;
        }

        Job(List<Pair> curList, List<Pair> remainList){
            this.curList = curList;
            this.remainList = remainList;
        }
    }
}

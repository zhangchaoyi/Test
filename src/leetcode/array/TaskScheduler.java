package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 621. 任务调度器
 *
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 -- 说明相同类型的任务之间隔着n
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 *
 * 示例 ：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 *
 * 思路：1.暴力破解：将task任务进行重新排序，使用map进行分类，类似桶排序思想
 *      根据value倒序排序
 *      每轮取 n+1 个不同的元素，每轮取完后map需要按value排序，直到取完所有的任务
 *      可以通过测试用例，只是超出时间限制
 *
 *      2.因为相同任务中间必须隔着n， 因此先将task进行桶排序，倒序排序，map[0]是最多次数的任务
 *      相当于将map进行填充到 n+1 的数组中,将有两种情况
 *
 *      1.填充时任务种类比较多，中间没有出现 待命 状态，则时间是 task.length;
 *
 *      2.填充时中间有待命的状态，则由图可得，最短时间= (map[0]-1)*(n+1) + cnt
 *
 *      .....n=3.....
 *      A   B   C   D
 *      A   B   C   D
 *      A   B   C   D
 *      A   B   C  待命
 *      A   B   C  待命
 *      A
 *
 *==========================================================================================
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/12 17:33
 */
public class TaskScheduler {

    Map<String, Integer> taskCounter = new LinkedHashMap<>();
    int remainTask;

    public int leastInterval(char[] tasks, int n) {
        //
        //取出时可以取其他种类的任务或者待命进行填充到n，一直到所有任务都完成

        if(tasks==null || tasks.length==0){
            return 0;
        }
        if(n==0){
            return tasks.length;
        }

        remainTask = tasks.length;

        //init map
        for(char c : tasks){
            taskCounter.put(String.valueOf(c), taskCounter.getOrDefault(String.valueOf(c), 0)+1);
        }

        sortByValue();

        List<String> result = new ArrayList<>();
        while(taskCounter.size() > 0 && remainTask > 0){
            List<String> keySetList = getKeySetList();

            //不同类型的key数量是否 > n
            if(keySetList.size() >= n+1){
                List<String> subList = keySetList.subList(0, n+1);
                result.addAll(subList);
                subCountOrRemove(subList);
            } else {
                result.addAll(keySetList);
                subCountOrRemove(keySetList);
                //判断剩余任务和n关系判断是否终止
                if(remainTask > 0){
                    int remainWaiting = n+1 - keySetList.size();
                    for(int i=0;i<remainWaiting;i++){
                        result.add("待命");
                    }
                }
            }
        }

        System.out.println(result);
        return result.size();
    }

    private List<String> getKeySetList(){
        Set<String> keySet = taskCounter.keySet();
        return keySet.stream().collect(Collectors.toList());
    }

    private void subCountOrRemove(List<String> targetKeyList){
        for(String targetKey : targetKeyList){
            Integer times = taskCounter.get(targetKey);
            if(times == 1){
                taskCounter.remove(targetKey);
            } else {
                taskCounter.put(targetKey, times-1);
            }
        }
        remainTask -= targetKeyList.size();
        sortByValue();
    }

    private void sortByValue(){
        //重新根据value排序
        Map<String, Integer> newMap = new LinkedHashMap<>();
        taskCounter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(b->newMap.put(b.getKey(), b.getValue()));
        taskCounter = newMap;
    }

    public static void main(String[] args){
        TaskScheduler taskScheduler = new TaskScheduler();
        //["A","A","A","B","B","B", "C","C","C", "D", "D", "E"]
        //2
        char[] array = new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'F','F', 'G'}; //nums > n+1
        System.out.println(taskScheduler.leastInterval1(array, 6));
    }

    /**
     * 取巧思路：先按map进行分类以及倒序排序， 然后相当于进行填充大小为 n+1 的桶， 桶纵向数量是数量最多的任务个数 map[0]
     * 1.当填充不满时
     * (map[0]-1) * (n+1) + 最后一行的数量（最后一行即与map[0]相等的元素）
     *   ... n+1 ...
     * .  A B C E
     * .  A B C ?
     * .  A ? ? ?
     * .  A # # #
     *
     *  2.如果填充满且元素种类比较多 即tasks的长度length
     * @param tasks
     * @param n
     * @return
     */
    private int leastInterval1(char[] tasks, int n) {
        int len=tasks.length;
        int[] map = new int[26];
        for(char c:tasks) map[c-'A']++;

        Arrays.sort(map);
        map=reverse(map);

        //cnt找出与map[0]数量相等的元素种类数
        int cnt=1;
        while(cnt<map.length&&map[cnt]==map[0]) cnt++;

        return Math.max(len, cnt+(n+1)*(map[0]-1));
    }

    int[] reverse(int[] map){
        int[] newArray = new int[map.length];
        for(int i=0;i<map.length;i++){
            newArray[map.length-1-i]=map[i];
        }
        return newArray;
    }
}

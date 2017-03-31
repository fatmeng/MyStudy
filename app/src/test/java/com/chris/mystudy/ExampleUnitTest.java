package com.chris.mystudy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        Integer a = 34556;
        Integer b = 34556;
        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    @Test
    public void testCollection() {


        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("cn", "中国");
        hashMap.put("us", "米国");
        hashMap.put("en", "英国");
        hashMap.put("", "");
        hashMap.put(null, "jap");

        System.out.println(hashMap);
        System.out.println("cn" + hashMap.get("cn"));
        System.out.println(hashMap.containsKey("cn"));
        System.out.println(hashMap.keySet());
        System.out.println(hashMap.isEmpty());

        Hashtable table = new Hashtable();
        table.put("key1", "value1");//键 和 值,
        table.put("key2", "value2");
        table.put("key3", "value3");//相当于堆栈 后进先出

        Enumeration e = table.keys();//创建枚举
        while (e.hasMoreElements()) {//是否有元素
            String key = (String) e.nextElement();
            System.out.println(key + " : " + table.get(key));
        }


    }

    @Test
    public void testCollection2() {
        List<String> names = new ArrayList<>();
        names.add("tom");
        names.add("mike");
        names.add("alice");
        names.add("mike");
        List<String> tels = new ArrayList<>();
        tels.add("1");
        tels.add("2");
        tels.add("3");
        tels.add("4");
        System.out.print(get(names, tels));

    }

    private Map<String, String> get(List<String> names, List<String> tels) {
        if (names.size() == tels.size()) {
            Map<String, String> result = new HashMap<>(names.size());
            for (int x = 0; x < names.size(); x++) {
                if (result.containsKey(names.get(x))) {
                    result.put(names.get(x), result.get(names.get(x)) + "|" + tels.get(x));
                } else {
                    result.put(names.get(x), tels.get(x));
                }
            }

            return result;
        }
        return null;
    }


    @Test
    public void testHashMap() {
        String src = "lskdjcfoiwensfhaldsfjhasfewqfas";
        final Map<Character, Integer> result = new LinkedHashMap<>();
        char[] midsrc = src.toCharArray();
        for (char c : midsrc) {
            if (result.containsKey(c)) {
                result.put(c, result.get(c) + 1);
            } else {
                result.put(c, 1);
            }
        }
        List<Map.Entry<Character, Integer>> midList = new ArrayList<Map.Entry<Character, Integer>>(result.entrySet());
        Collections.sort(midList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> entry, Map.Entry<Character, Integer> t1) {
//                return t1.getValue().compareTo(entry.getValue());
                return entry.getValue().compareTo(t1.getValue());
            }
        });
        System.out.println(midList);
        Iterator<Map.Entry<Character, Integer>> iterator = midList.iterator();
        result.clear();

        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            result.put(entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }


    @Test
    public void testCollections() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println("2的非值:" + ((Integer.MAX_VALUE + ~2) + 1));
        System.out.println("2与8异或值:" + (2 ^ 8));
    }

    @Test
    public void testSorted() {
        int[] src = new int[]{
                22, 453, 234, 123, 5, 65436, 7328, 8346, 92348, 1
        };
//        int count = src.length;
//        //插入排序
//        for (int x = 1;x<count;x++){ //外循环是分界线
//            int temp = src[x];//被标记的元素
//            int y = x;
//            //循环体内,是将己有序的元素集,挨个元素向右移位
//            while (y > 0 && src[y -1] >= temp){ //内循环的判断条件是,y>0,
//                                                // 且针对y-1位上的元素>=被标记的元素
//                src[y] = src[y-1];
//                y--;
//            }
//            src[y] = temp;  //在跳出循环时, 情况1:y>0不满足,为y=0时,
//                            // 将第一个元素赋值为temp被标记的元素
//                            //情况2:src[y-1]>=src[y]不满足,即为src[y]<src[y-1],
//                            // 则将src[y]的值赋值temp被标记的元素,(因为循环里y--)
//        }
//        Quick quick = new Quick();
//        quick.sort(src,0,src.length-1);

        quickSort(src, 0, src.length - 1);
        for (int x = 0; x < src.length; x++) {
            System.out.println(src[x]);
        }

    }

    public void quickSort(int[] src, int low, int high) {
        if (src == null || src.length <= 1 || low > high) {
            return;
        }
        int l = low;
        int h = high;
        int pivot = src[low];
        while (l < h) {
            while (l < h && src[h] > pivot) {
                h--;
            }
            if (l < h) {
                int temp = src[l];
                src[l] = src[h];
                src[h] = temp;
            }
            while (l < h && src[l] < pivot) {
                l++;
            }
            if (l < h) {
                int temp = src[l];
                src[l] = src[h];
                src[h] = temp;
            }
        }

        quickSort(src, low, h - 1);
        quickSort(src, l + 1, high);
    }


    @Test
    public void testR() {

        if (true == true && false) {
            System.out.println("");
        }
    }

    @Test
    public void testArray() {
        int[][] src = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int left = 0;
        int right = src[0].length-1;
        int low = 0;
        int high = src.length-1;
        while (left <= right || low <= high) {
            for (int i = left; i <= right; i++) {
                System.out.println(src[low][i]);
            }
            low++;
            for (int i = low; i <= high; i++) {
                System.out.println(src[i][right]);
            }
            right--;
            for (int i = right; i >= left; i--) {
                System.out.println(src[high][i]);
            }
            high--;
            for (int i = high; i >= low; i--) {
                System.out.println(src[i][left]);
            }
            left++;
        }


    }

    @Test
    public void testBinaryTree(){
        BinaryNode.createBinaryTree();

        BinaryNode.preOderTraverse(BinaryNode.getFisrt());
        System.out.println();
        BinaryNode.midOdderTraverse(BinaryNode.getFisrt());
        System.out.println();
       BinaryNode.postOderTraverse(BinaryNode.getFisrt());
    }


    @Test
    public void testMid(){
        int a = 50;
        int b = 400;
//        System.out.print("" + ((a+b)>>>1));
        System.out.println("" + ~3);
        System.out.println("" + ~-3);
        System.out.println("" + ~~1);
        System.out.println("" + (1.0f / 0.0f));
    }

    @Test
    public void testArray2(){
        int x = 1;
        int y = x;
        y = 2;
        System.out.println(x);//打印结果:1
        int[] a = new int[] {1,2,3,4};
        int[] b = a;
        b[3] = 5;
        System.out.println(a[3]);//打印结果:5
    }

    static int age = 1;


    @Test
    public void testRam(){
        Runtime.getRuntime().availableProcessors();
    }

}
package com.chris.mystudy;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;
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

}
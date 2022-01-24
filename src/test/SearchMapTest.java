package test;

import com.SearchMap;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchMapTest {

    @org.junit.Test
    @Before
    public void test_parse_file() throws IOException {
        HashMap<String, HashMap<String,Integer>> ideal_list = new HashMap<>();

        HashMap<String, Integer> tool = new HashMap<>();

        tool.put("R", 300);
        tool.put("W", 200);
        ideal_list.put("P", new HashMap<>(tool));
        tool.clear();

        tool.put("X", 375);
        ideal_list.put("Q", new HashMap<>(tool));
        tool.clear();

        tool.put("P", 300);
        tool.put("X", 200);
        tool.put("Y", 600);
        ideal_list.put("R", new HashMap<>(tool));
        tool.clear();

        tool.put("T", 300);
        tool.put("W", 250);
        ideal_list.put("S", new HashMap<>(tool));
        tool.clear();

        tool.put("S", 300);
        tool.put("W", 350);
        ideal_list.put("T", new HashMap<>(tool));
        tool.clear();

        tool.put("P", 200);
        tool.put("S", 250);
        tool.put("T", 350);
        tool.put("Y", 500);
        ideal_list.put("W", new HashMap<>(tool));
        tool.clear();

        tool.put("Q", 375);
        tool.put("R", 200);
        ideal_list.put("X", new HashMap<>(tool));
        tool.clear();

        tool.put("R", 600);
        tool.put("W", 500);
        tool.put("Z", 450);
        ideal_list.put("Y", new HashMap<>(tool));
        tool.clear();

        tool.put("Y", 450);
        ideal_list.put("Z", new HashMap<>(tool));
        tool.clear();

        HashMap<String, HashMap<String, Integer>> actual = SearchMap.parse_file("./SampleInputfile1");

        /*System.out.println(ideal_list);
        System.out.println(actual);*/
        assertEquals(ideal_list, actual);
    }

    @org.junit.Test
    public void main() {
    }
}
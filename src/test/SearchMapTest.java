package test;

import com.SearchMap;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SearchMapTest {

    @Before
    public void setUp() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./SampleInputfile1")));
        bw.write("P\n");
        bw.write("P W 200\n" +
                "P R 300\n" +
                "R X 200\n" +
                "Q X 375\n" +
                "W S 250\n" +
                "S T 300\n" +
                "T W 350\n" +
                "W Y 500\n" +
                "Y Z 450\n" +
                "Y R 600");
        bw.close();
    }

    @org.junit.Test
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

        tool.put("X", 200);
        ideal_list.put("R", new HashMap<>(tool));
        tool.clear();

        tool.put("T", 300);
        ideal_list.put("S", new HashMap<>(tool));
        tool.clear();

        tool.put("W", 350);
        ideal_list.put("T", new HashMap<>(tool));
        tool.clear();

        tool.put("S", 250);
        tool.put("Y", 500);
        ideal_list.put("W", new HashMap<>(tool));
        tool.clear();

        ideal_list.put("X", new HashMap<>(tool));
        tool.clear();

        tool.put("R", 600);
        tool.put("Z", 450);
        ideal_list.put("Y", new HashMap<>(tool));
        tool.clear();

        ideal_list.put("Z", new HashMap<>(tool));
        tool.clear();

        HashMap<String, HashMap<String, Integer>> actual = SearchMap.parse_file("./SampleInputfile1");

        /*System.out.println(ideal_list);
        System.out.println(actual);*/
        assertEquals(ideal_list, actual);

        Exception exception = assertThrows(IOException.class, () -> {
            SearchMap.parse_file("sdasdasd");
        });
    }

    @Test
    public void testDFS() throws IOException {
        HashMap<String, HashMap<String, Integer>> adjacent_list = SearchMap.parse_file("./SampleInputfile1");

        HashMap<String, ArrayList<String>> excepted_routes = new HashMap<>(); // map to store the route
        ArrayList<String> temp = new ArrayList<>();
        temp.add("P");

        ArrayList<String> tool = new ArrayList<>(temp);
        tool.add("R");
        excepted_routes.put("R", new ArrayList<>(tool));

        tool = new ArrayList<>(temp);
        tool.add("W");
        tool.add("S");
        excepted_routes.put("S", new ArrayList<>(tool));

        tool = new ArrayList<>(temp);
        tool.add("W");
        tool.add("S");
        tool.add("T");
        excepted_routes.put("T", new ArrayList<>(tool));

        tool = new ArrayList<>(temp);
        tool.add("W");
        excepted_routes.put("W", new ArrayList<>(tool));

        tool = new ArrayList<>(temp);
        tool.add("R");
        tool.add("X");
        excepted_routes.put("X", new ArrayList<>(tool));

        tool = new ArrayList<>(temp);
        tool.add("W");
        tool.add("Y");
        excepted_routes.put("Y", new ArrayList<>(tool));

        tool = new ArrayList<>(temp);
        tool.add("W");
        tool.add("Y");
        tool.add("Z");
        excepted_routes.put("Z", new ArrayList<>(tool));

        HashMap<String, ArrayList<String>> actual_routes = SearchMap.DFS("P", adjacent_list);

        assertEquals(excepted_routes, actual_routes);
    }

    @Test
    public void test_getCost() throws IOException {
        HashMap<String, HashMap<String, Integer>> adjacent_list = SearchMap.parse_file("./SampleInputfile1");
        HashMap<String, ArrayList<String>> routes = SearchMap.DFS("P", adjacent_list);
        HashMap<String, Integer> expected_cost = new HashMap<>();

        expected_cost.put("P",0);
        expected_cost.put("R",300);
        expected_cost.put("S",450);
        expected_cost.put("T",750);
        expected_cost.put("W",200);
        expected_cost.put("X",500);
        expected_cost.put("Y",700);
        expected_cost.put("Z",1150);

        HashMap<String, Integer> actual_cost = SearchMap.getCost(routes, adjacent_list);

        assertEquals(actual_cost, expected_cost);
    }

    @org.junit.Test
    public void main() {
    }
}
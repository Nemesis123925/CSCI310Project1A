import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SearchMap {

    public static void main(String[] args) throws IOException {

        // first get the input file
        String input_filename = args[0];
        // use bufferedReader to use readline
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input_filename)));

        // create an adjacent_list
        HashMap<String, HashMap<String,Integer>> adjacent_list = new HashMap<>();
        String start_node = br.readLine(); // remember the start_node

        System.out.println(start_node);
        // then deal with the rest, which are the adjacent_lists
        String str = br.readLine();
        // this loop keeps readline entail ends
        while (str != null){
            String[] edge = str.split(" ");
            // System.out.println(str);

            adjacent_list.computeIfAbsent(edge[0], k -> new HashMap<>());
            adjacent_list.computeIfAbsent(edge[1], k -> new HashMap<>());

            adjacent_list.get(edge[0]).put(edge[1], Integer.parseInt(edge[2]));
            adjacent_list.get(edge[1]).put(edge[0], Integer.parseInt(edge[2]));

            // last thing is to read next line
            str = br.readLine();
        }
        // done prasing the whole file
        /*for(Map.Entry entry : adjacent_list.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/
    }
}

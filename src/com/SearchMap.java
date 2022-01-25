package com;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class SearchMap {

    public static HashMap<String, HashMap<String,Integer>> parse_file(String input_filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input_filename)));

        // create an adjacent_list
        HashMap<String, HashMap<String,Integer>> adjacent_list = new HashMap<>();
        String start_node = br.readLine(); // remember the start_node

        //System.out.println(start_node);
        // then deal with the rest, which are the adjacent_lists
        String str = br.readLine();
        // this loop keeps readline entail ends
        while (str != null){
            String[] edge = str.split(" ");
            // System.out.println(str);

            adjacent_list.computeIfAbsent(edge[0], k -> new HashMap<>());
            adjacent_list.computeIfAbsent(edge[1], k -> new HashMap<>());

            adjacent_list.get(edge[0]).put(edge[1], Integer.parseInt(edge[2]));
            //adjacent_list.get(edge[1]).put(edge[0], Integer.parseInt(edge[2]));

            // last thing is to read next line
            str = br.readLine();
        }

        return adjacent_list;
    }

    public static HashMap<String, ArrayList<String>> DFS(String init, HashMap<String, HashMap<String,Integer>> adjacent_list) {
        Stack<String> stack = new Stack<>(); // stack in DFS
        //HashMap<String,Integer> cost = new HashMap<>(); // the result to return
        HashMap<String, ArrayList<String>> routes = new HashMap<>(); // map to store the route
        //cost.put(init,0); // do a favor for the DFS
        stack.push(init); // first push this into stack
        // iterate and push
        while(!stack.empty()){
            String candidate = stack.pop(); // first as the candidate
            HashMap<String, Integer> edges = adjacent_list.get(candidate); // finds its edges
            // use iterator to iterate through the edges
            Iterator<String> it = edges.keySet().iterator();
            while(it.hasNext()){
                String node = it.next(); // get the next neighbor

                if(!routes.containsKey(node)){ // if haven't reached this node
                    stack.push(node);

                    ArrayList<String> tool;
                    // then do with route
                    if(candidate.equals(init)){ // if the last node is init
                        tool = new ArrayList<>();
                        tool.add(init); // then just a simple arraylist of init and node
                    }else{
                        tool = new ArrayList<>(routes.get(candidate)); // else get before and add to it
                    }
                    tool.add(node);
                    routes.put(node, tool);
                }
            }
        }
        System.out.println(routes);

        return routes;
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename)));
    }

    public static HashMap<String,Integer> getCost(HashMap<String, ArrayList<String>> routes, HashMap<String, HashMap<String,Integer>> adjacent_list){
        // create the returned Hashmap and push P into it
        HashMap<String, Integer> cost = new HashMap<>();
        cost.put("P",0);

        // for each dest that's reachable
        for (String dest : routes.keySet()) {
            ArrayList<String> route = routes.get(dest);
            int sum = 0;

            for(int i = 0; i < route.size() - 1; i++){
                // sum +=            from first       to   second
                sum += adjacent_list.get(route.get(i)).get(route.get(i+1));
            }
            cost.put(dest,sum);
        }
        System.out.println(cost);
        return cost;
    }

    public static void main(String[] args) throws IOException {

        // first get the input file
        String input_filename = args[0];
        // first put the input_filename into parse_file to get the adjacent_list
        HashMap<String, HashMap<String,Integer>> adjacent_list = parse_file(input_filename);
        // get the scr
        Iterator<String> it = adjacent_list.keySet().iterator();
        String start_node = it.next();
        // get the routes by using the DFS
        HashMap<String, ArrayList<String>>routes = DFS(start_node, adjacent_list);
        // get the cost by using the getCost function
        HashMap<String, Integer> cost = getCost(routes, adjacent_list);
    }
}

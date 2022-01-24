package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

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
            adjacent_list.get(edge[1]).put(edge[0], Integer.parseInt(edge[2]));

            // last thing is to read next line
            str = br.readLine();
        }

        return adjacent_list;
    }

    /*public static HashMap<String, Integer> DFS(String init, HashMap<String, HashMap<String,Integer>> adjacent_list){

    }*/

    public static void main(String[] args) throws IOException {

        // first get the input file
        String input_filename = args[0];
        HashMap<String, HashMap<String,Integer>> adjacent_list = parse_file(input_filename);

        System.out.println(adjacent_list);
    }
}

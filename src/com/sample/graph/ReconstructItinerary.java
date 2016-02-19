package com.sample.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * TODO: Describe purpose and behavior of Itinerary
 */
public class ReconstructItinerary {

    public static void main(String[] args) {
        String[][] tickets = { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
        //String[][] tickets = { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "ATL" }, { "ATL", "JFK" }, { "ATL", "SFO" } };
        List<String> itinerary = printItinerary(tickets);
        System.out.println(isValidTree(tickets));
        System.out.println(itinerary.toString());
    }

    private static List<String> printItinerary(String[][] tickets) {
        List<String> itinerary = new ArrayList<>();
        Map<String, List<String>> edgeMap = new HashMap<>();
        for (String[] ticket : tickets) {
            if (edgeMap.containsKey(ticket[0])) {
                edgeMap.get(ticket[0]).add(ticket[1]);
            } else {
                List<String> edges = new ArrayList<>();
                edges.add(ticket[1]);
                edgeMap.put(ticket[0], edges);
            }
        }
        System.out.println(edgeMap.toString());
        Queue<String> queue = new LinkedList<>();
        queue.add("JFK");
        while (!queue.isEmpty()) {
            String station = queue.remove();
            itinerary.add(station);

            List<String> destionations = edgeMap.get(station);
            if (destionations == null || destionations.size() == 0)
                continue;
            for (String destination : destionations) {
                queue.add(destination);
            }
            edgeMap.get(station).removeAll(destionations);

        }
        return itinerary;
    }

    private static boolean isValidTree(String[][] tickets) {
        boolean result = true;
        Map<String, List<String>> edgeMap = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>(edgeMap.size());
        for (String[] ticket : tickets) {
            if (edgeMap.containsKey(ticket[0])) {
                edgeMap.get(ticket[0]).add(ticket[1]);
            } else {
                List<String> edges = new ArrayList<>();
                edges.add(ticket[1]);
                edgeMap.put(ticket[0], edges);
                visited.put(ticket[0], false);
            }
        }
        System.out.println(visited.toString());
        Queue<String> queue = new LinkedList<>();
        queue.add("JFK");
        while (!queue.isEmpty()) {
            String start = queue.remove();
            if (visited.get(start) == Boolean.TRUE)
                return false;
            else
                visited.put(start, Boolean.TRUE);
            List<String> destinations = edgeMap.get(start);
            if (destinations == null || destinations.size() == 0)
                continue;
            for (String destination : destinations) {
                queue.add(destination);
            }
            edgeMap.get(start).removeAll(destinations);
        }

        for (Boolean visit : visited.values()) {
            if (!visit)
                return false;
        }

        return result;
    }
}

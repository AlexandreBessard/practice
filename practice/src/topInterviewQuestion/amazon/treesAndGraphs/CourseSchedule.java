package topInterviewQuestion.amazon.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2983/
public class CourseSchedule {

    /*
    prerequisites[i] = [ai, bi]
    indicates that you must take course bi first if you want to take course ai.
     */
    public static void main(String[] args) {
        //int[][] prerequisites = {{1, 0}, {0, 1}};
        int[][] prerequisites1 = {{1, 0}, {2, 0}, {2, 1}, {3, 1}};
        int numCourses = 4;
        var obj = new CourseSchedule();
        System.out.println(obj.canFinish(numCourses, prerequisites1));
        System.out.println(canFinishBFSTopologicalSort(numCourses, prerequisites1));
    }


    //Easy BFS Topological sort
    //Correction: https://leetcode.com/problems/course-schedule/discuss/58516/Easy-BFS-Topological-sort-Java
    /*
    Use Topological sorting for problems where you have some objects that are ordered based on some rules.
    Time: O(V + E)
    Space: O(V + E)
     */
    static boolean canFinishBFSTopologicalSort(int numCourses, int[][] prerequisites) {
        //rows represent prerequisite and col the course after that prerequisite
        //[1, 0]  : first take "0 (course) ----> (in-degree) 1 (course)"
        int[][] vertices = new int[numCourses][numCourses]; //Use to see dependence for each vertice
        int[] indegree = new int[numCourses]; //Use to count indegree for a specific vertice
        for (int i = 0; i < prerequisites.length; i++) {
            int courseAfterRequisite = prerequisites[i][0]; // required pre to be done before the ready
            int prerequisite = prerequisites[i][1]; //Must take this course first
            if (vertices[prerequisite][courseAfterRequisite] == 0) { //For duplicate case: 2  -> 1  ||  2 -> 1
                indegree[courseAfterRequisite]++; //course after requisite has indegree
            }
            vertices[prerequisite][courseAfterRequisite] = 1;
        }
        //BFS:
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) q.add(indegree[i]); //Get elements with 0 in-degree
        }
        while (!q.isEmpty()) {
            int course = q.poll();
            count++;
            for(int i = 0; i < numCourses; i++) {
                if(vertices[course][i] != 0) { //Get neighbors from that course
                    if(--indegree[i] == 0) { //Decrement indegree. If 0 add to the queue for processing
                        q.add(i);
                    }
                }
            }
        }
        return count == numCourses;
    }


    //Approach 2: Topological Sort
    /*
    Time: O(E + V) where V is the number of course and E is the number of dependencies
    Space: O(E + V) with the same denotation as in the above time complexity
     */
    public boolean canFinish(int numsCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true; // No cycle could be formed in empty graph
        }
        // course -> list of next courses
        Map<Integer, GNode> graph = new HashMap<>();
        //Build the graph
        for (int[] relation : prerequisites) {
            // relation[1] -> relation[0]
            GNode prevCourse = this.getCreateGNode(graph, relation[1]);
            GNode nextCourse = this.getCreateGNode(graph, relation[0]);
            prevCourse.outNodes.add(relation[0]);
            nextCourse.inDegrees += 1;
        }
        //we start from courses that have no prerequisites
        int totalDeps = prerequisites.length;
        LinkedList<Integer> nodepCourses = new LinkedList<>(); //could also use a stack
        for (Map.Entry<Integer, GNode> entry : graph.entrySet()) {
            GNode node = entry.getValue();
            //Nodes which have no in degrees (must take this course before doing other course)
            if (node.inDegrees == 0) {
                nodepCourses.add(entry.getKey()); //Add to the stack
            }
        }
        int removedEdges = 0;
        while (nodepCourses.size() > 0) {
            Integer course = nodepCourses.pop(); //First element of the list
            for (Integer nextCourse : graph.get(course).outNodes) {
                GNode childNode = graph.get(nextCourse);
                childNode.inDegrees -= 1;
                removedEdges += 1;
                if (childNode.inDegrees == 0) {
                    nodepCourses.add(nextCourse);
                }
            }
        }
        if (removedEdges != totalDeps) {
            // if there are still some edges left, then there exist some cycles
            // Due to the dead-lock (dependencies), we cannot remove the cyclic edges
            return false;
        }
        return true;
    }

    /**
     * Retrieve the existing <key, value> from graph, otherwise create a new one.
     * and store it into the map with the associated key
     */
    protected GNode getCreateGNode(Map<Integer, GNode> graph, Integer course) {
        GNode node = null;
        if (graph.containsKey(course)) {
            return graph.get(course);
        } else {
            node = new GNode();
            graph.put(course, node);
            return graph.get(course);
        }
    }


    static class GNode {
        public Integer inDegrees = 0;
        public List<Integer> outNodes = new LinkedList<>();
    }

}

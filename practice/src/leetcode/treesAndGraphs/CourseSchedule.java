package leetcode.treesAndGraphs;

import java.util.*;

//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2983/
public class CourseSchedule {
    /*
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.

    Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
     */
    public static void main(String[] args) {
        //int[][] prerequisites = {{1, 0}, {0, 1}};
        //For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
        // 1 ----> 0
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
    Used to find the critical path of the project, sequence milestones and tasks that control the length of the overall project schedule.
    Time: O(V + E)
    Space: O(V + E)
     */
    static boolean canFinishBFSTopologicalSort(int numCourses, int[][] prerequisites) {
        //rows represent prerequisite and col the course after that prerequisite
        //[1, 0]  : first take "0 (course) ----> (in-degree) 1 (course)"
        int[][] vertices = new int[numCourses][numCourses]; //Use to see dependence for each vertice
        int[] indegree = new int[numCourses]; //Use to count indegree for a specific vertice
        /*
        0, 1, 1, 0
        0, 0, 1, 1
        0, 0, 0, 0
        0, 0, 0, 0

        indegree: [0, 1, 2, 1]
         */
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
            if (indegree[i] == 0) {
                q.add(indegree[i]); //Get elements with 0 in-degree
            }
        }
        while (!q.isEmpty()) {
            int course = q.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (vertices[course][i] != 0) { //Get neighbors from that course
                    if (--indegree[i] == 0) { //Decrement indegree. If 0 add to the queue for processing
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
            //For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
            GNode prevCourse = this.getCreateGNode(graph, relation[1]);
            GNode nextCourse = this.getCreateGNode(graph, relation[0]);
            prevCourse.outNodes.add(relation[0]);
            nextCourse.inDegrees += 1;
        }
        //we start from courses that have no prerequisites
        int totalDeps = prerequisites.length;
        //Used to store all nodes which have 0 inDegree (starting point)
        LinkedList<Integer> nodepCourses = new LinkedList<>(); //could also use a stack
        //Add all nodes which have no inDregre (starting point)
        for (Map.Entry<Integer, GNode> entry : graph.entrySet()) {
            GNode node = entry.getValue();
            //Nodes which have no in degrees (must take this course before doing other course)
            if (node.inDegrees == 0) {
                //key is the course itself
                nodepCourses.add(entry.getKey()); //Add to the stack
            }
        }
        int removedEdges = 0; //If no cyclic, we have same edges and nodes (see photo: practice/resources/graph.png)
        while (nodepCourses.size() > 0) {
            //Starting point, this node has 0 inDegree
            Integer course = nodepCourses.pop(); //First element of the list
            List<Integer> outNodes = graph.get(course).outNodes;
            for (Integer nextCourse : outNodes) {
                GNode childNode = graph.get(nextCourse);
                childNode.inDegrees -= 1; //Remove inDegree
                removedEdges += 1;
                if (childNode.inDegrees == 0) { //New starting point
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
        if (graph.containsKey(course)) {
            return graph.get(course);
        } else {
            GNode node = new GNode();
            graph.put(course, node);
            return graph.get(course);
        }
    }


    static class GNode {
        public Integer inDegrees = 0;
        public List<Integer> outNodes = new LinkedList<>();
    }

}

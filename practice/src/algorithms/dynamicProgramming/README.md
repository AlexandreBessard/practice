https://leetcode.com/explore/learn/card/dynamic-programming/

Common DP problems:
ask for the optimum value (maximum or minimum) of something or number of ways there are to do something:
- What is the minimum cost of doing...
- What is the maximum profit from...
- How many ways are there to do...
- What is the longest possible...
- Is it possible to reach a certain point...

Next characteristic to determine whether a problem should be resolved using greedy or DP.
Common DP -> future decisions depend on earlier decisions. (no applicable for greedy)


To solve a DP problem, we need to combine 3 things:
1. A function or data structure that will compute/contain the answer to the problem for every given state.
2. A recurrence relation to transition between states.
3. Base cases, so that our recurrence relation doesn't go on infinitely.

How to find the base case:
When coming up with the base case(s) ask yourself: 
What state(s) can I find the answer to without using dynamic programming?
In this example, we can reason that there is only 1 way to climb to the first stair (1 step once), 
and there are 2 ways to climb to the second stair (1 step twice and 2 steps once). 
Therefore, our base cases are dp(1) = 1 and dp(2) = 2.

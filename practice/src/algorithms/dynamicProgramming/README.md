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
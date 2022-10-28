# MICS Iced Java - Algorithms

## Path Finding

[Pathfinding](https://en.wikipedia.org/wiki/Pathfinding) is determining the shortest path between two points. There are a number of different ways to do this.

Imagine the following scenario. Look familiar?

![graph](/docs/graph.png "graph")
[Citation](https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/)

There are pathfinding algorithms that will allow a computer to determine the shortest path between two waypoints. This structure is often referred to as a `graph` consisting of `vertices` and `edges` between vertices. `edges` often have `weights` associated with them that indicate how "far" it is between vertices. These weights could represent anything, but a traditional example is example this graph is a series of towns with mileage distance between them. The weight of the edge would represent the number of miles between towns.

For example if we wanted to find the shortest path between town `0` and town `4` what would that look like? We can look at the image and make an educated guess such as

- `0`
- `7`
- `6`
- `5`
- `4`

which would have a total traveling distance of 21.

### Algorithm

So how would we do this programmatically? Luckily there are a lot smarter people who have figured this out for us. A straight forward method is using [Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm). While this looks tricky or confusing, the code itself is fairly minimal considering the power of what it can do. Let's break down some of the jargon

- `Vertex` - these are also called nodes or points. They often represent some data point with semantic meaning. Sticking with the town example, each vertex in the graph represents a town.
- `Edge` - these are the pathways between vertices within a graph. As stated above in the town example, the edges could represent roads between towns. An edge can have a weight (or not depending on the problem) that has semantic meaning to differentiate between edges in a graph, i.e. miles between towns
- `Graph` - a set of edge connected vertices. Graphs can be _directed_ or _undirected_. In a directed graph that means the paths are unidirectional and can only be travelled in one direction. In an undirected graph, the paths can be travelled either way.
- `Adjacent` - a vertex can be ajacent to another vertex if there is a path between them. Remember if a graph is directed the edges only go one way so adjacent does not just mean _connected_.
- `Source` - the vertex where we are starting from
- `Destination` - the vertex we want to reach. Often called `target`as well.
- `Queue` - a data structure that can contain elements. Elements that are _pushed_ onto the queue first are the first elements to be _popped_ when removed from the queue. Think any line you have been in like the move theater. First in First Out (FIFO).

This is all well and good, but how do we code it? Here is some pseudocode:

```
 1  function Dijkstra(Graph, source):
 2
 3      for each vertex v in Graph.Vertices:
 4          dist[v] ← INFINITY
 5          prev[v] ← UNDEFINED
 6          add v to Q
 7      dist[source] ← 0
 8
 9      while Q is not empty:
10          u ← vertex in Q with min dist[u]
11          remove u from Q
12
13          for each neighbor v of u still in Q:
14              alt ← dist[u] + Graph.Edges(u, v)
15              if alt < dist[v]:
16                  dist[v] ← alt
17                  prev[v] ← u
18
19      return dist[], prev[]
```

```
1  S ← empty sequence
2  u ← target
3  if prev[u] is defined or u = source:
4      while u is defined:
5          insert u at the beginning of S
6          u ← prev[u]
```

[Citation](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Pseudocode)

See the example code which includes comments for additional implementation details.

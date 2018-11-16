from collections import defaultdict


class Graph:
    def __init__(self, graph):
        self.graph = graph
        self.traversed = {}
        self.stack = []
        for i in graph.keys():
            if i not in self.traversed:
                self.dfs(i)

    def dfs(self, vertex):
        self.traversed[vertex] = True
        for i in self.graph[vertex]:
            if i not in self.traversed.keys():
                self.dfs(i)
        self.stack.append(vertex)


if __name__ == "__main__":
    docs = defaultdict(list)
    for line in open("govern.in"):
        vertexes = line.split()

        if len(vertexes) != 2:
            break

        docs[vertexes[0]].append(vertexes[1])

    result = Graph(docs).stack
    print(result)
    govern_out = open("govern.out", 'w')
    for res in result:
        govern_out.write(res + "\n")
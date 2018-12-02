class Graph:
    def dfs(self, v):
        self.untraversed[v] = False
        for i in self.graph[v]:
            if i in self.untraversed.keys():
                self.dfs(i)
        self.list.append(v)

    def __init__(self, graph):
        self.list = []
        self.graph = set()
        untraversed = set(graph.keys())

        while untraversed:
            for i in graph.keys():
                if i in self.untraversed:
                    self.dfs(i)


def main():
    docs = []
    
    for line in open("govern.in"):
        vertexes = line.split()

        docs[vertexes[0]].append(vertexes[1])

    out = Graph(docs).list

    print(out)

if __name__ == '__main__':
    main()
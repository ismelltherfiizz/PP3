def parse(string):
    rows = string.split(" ")
    vertexes = []
    for i in range(rows.__len__()):
        vertexes.append(list(rows[i]))
    return vertexes


class Graph:


    def __init__(self, size):
        self.vertexes = {}
        for i in range(size):
            self.vertexes[i] = []


    def count_friends(self, head):
        count = -1
        traversed = []

        for neighbor in self.vertexes[head]:
            if neighbor not in traversed:
                count += 1
                traversed.append(neighbor)
            for sub_neighbor in self.vertexes[neighbor]:
                if sub_neighbor not in traversed:
                    count += 1
                    traversed.append(sub_neighbor)

        return count



    def print(self):
        print(self.vertexes)


    def add_vertex(self, v1, v2):
        self.vertexes[v1].append(v2)


def find_max_friends(matrix):
    graph = Graph(matrix.__len__())

    for i in range(matrix.__len__()):
        for j in range(i + 1, matrix.__len__()):
            if matrix[i][j] is 'Y':
                graph.add_vertex(i, j)
                graph.add_vertex(j, i)

    count_max_friends = 0
    for i in range(matrix.__len__()):
        friends = graph.count_friends(i)
        if count_max_friends < friends:
            count_max_friends = friends

    return count_max_friends


example1 = "NNN NNN NNN"
example2 = "NYY YNY YYN"
example3 = "NYNNN YNYNN NYNYN NNYNY NNNYN"

students = parse(example2)
print(find_max_friends(students))
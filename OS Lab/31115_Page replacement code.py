import random


class PageReplacement:

    def __init__(self, size=5):
        self.pages = [7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2]
        self.frame_size = size
        self.loaded_pages = [-1 for _ in range(self.frame_size)]

    def generate_pages(self, size):
        self.pages.clear()
        self.loaded_pages = [-1 for _ in range(self.frame_size)]
        for i in range(size):
            self.pages.append(random.randint(1, 10))

    def fifo(self):
        print(self.pages)
        c = 0
        already_there = set()
        hits, miss = 0, 0
        for j in range(len(self.pages)):
            curr_page = self.pages[j]
            if not (curr_page in already_there):
                miss += 1
                if self.loaded_pages[c] != -1:
                    already_there.remove(self.loaded_pages[c])
                self.loaded_pages[c] = self.pages[j]
                c = (c + 1) % self.frame_size
                already_there.add(curr_page)

            else:
                hits += 1
            print("current page -->", curr_page, self.loaded_pages)
        print("Hits--> ", hits, " miss--> ", miss)
        self.loaded_pages = [-1 for _ in range(self.frame_size)]

    def lru(self):
        print(self.pages)
        curr_time = 1
        already_there = set()
        time = {-1: 0}
        hits, miss = 0, 0
        for i in range(len(self.pages)):
            curr_page = self.pages[i]
            if curr_page in already_there:
                hits += 1
            else:
                miss += 1
                least_recently_used = float("inf")
                pos = -1
                for j in range(self.frame_size):
                    if time[self.loaded_pages[j]] < least_recently_used:
                        least_recently_used = time[self.loaded_pages[j]]
                        pos = j
                if self.loaded_pages[pos] != -1:
                    already_there.remove(self.loaded_pages[pos])
                self.loaded_pages[pos] = curr_page
                already_there.add(curr_page)
            time[curr_page] = curr_time
            curr_time += 1
            print("current page -->", curr_page, self.loaded_pages)
        print("Hits--> ", hits, " miss--> ", miss)
        self.loaded_pages = [-1 for _ in range(self.frame_size)]

    def next_occurrence(self, page, pos):
        for i in range(pos + 1, len(self.pages)):
            if self.pages[i] == page:
                return i - pos
        return float("inf")

    def opr(self):
        print(self.pages)
        already_there = set()
        hits, miss = 0, 0
        for i in range(len(self.pages)):
            curr_page = self.pages[i]
            if curr_page in already_there:
                hits += 1
            else:
                miss += 1
                used_in_future = 0
                who = -1
                for j in range(self.frame_size):
                    dist = self.next_occurrence(self.loaded_pages[j], j)
                    if dist > used_in_future:
                        used_in_future = dist
                        who = j
                if self.loaded_pages[who] != -1:
                    already_there.remove(self.loaded_pages[who])
                already_there.add(curr_page)
                self.loaded_pages[who] = curr_page
            print("current page -->", curr_page, self.loaded_pages)
        print("Hits--> ", hits, " miss--> ", miss)
        self.loaded_pages = [-1 for _ in range(self.frame_size)]


def menu():
    obj1 = PageReplacement(4)

    while True:
        print("""Enter 
       1 to generate new sample page set.
       2 to use FIFO algorithm
       3 to use LRU algorithm
       4 to use OPR algorithm
       5 to exit""")
        option = int(input())
        if option == 1:
            obj1.generate_pages(15)
        elif option == 2:
            obj1.fifo()
        elif option == 3:
            obj1.lru()
        elif option == 4:
            obj1.opr()
        elif option == 5:
            break
        else:
            print("Invalid option")


if __name__ == "__main__":
    menu()

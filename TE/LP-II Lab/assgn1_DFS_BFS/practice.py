class Node:
    def __init__(self,data):
        self.left = None
        self.right = None
        self.data = data

def inorder(root):
    if root:
        inorder(root.left)
        print(root.data, end = " ")
        inorder(root.right)

def preorder(root):
    if root:
        print(root.data, end= " ")
        preorder(root.left)
        preorder(root.right)

def postorder(root):
    if root:
        postorder(root.left)
        postorder(root.right)
        print(root.data, end= " ")

def bfs(root):

    if root is None:
        return
    
    queue = []
    queue.append(root)

    while(len(queue) > 0):
        print(queue[0].data, end = ' ')
        item = queue.pop(0)

        if item.left is not None:
            queue.append(item.left)
        if item.right is not None:
            queue.append(item.right)


root = Node(1)
root.left = Node(2)
root.right = Node(3)
root.left.left = Node(4)
root.right.right = Node(5)

print('inorder', inorder(root))
print('preoder', preorder(root))
print('postorder', postorder(root))
print('bfs', bfs(root))

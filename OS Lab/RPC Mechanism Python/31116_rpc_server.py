from xmlrpc.server import SimpleXMLRPCServer

class Operations:
    def add(self,x,y):
        return x + y
    def multiply(self,x,y):
        return x * y
    def subtract(self,x,y):
        return x - y
    def divide(self,x,y):
        return (x // y,x%y)

with SimpleXMLRPCServer(('localhost',6789)) as server:
    server.register_instance(Operations())
    print('Serving XML-RPC on localhost port 6789')
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\nEXITING...")
        SystemExit()
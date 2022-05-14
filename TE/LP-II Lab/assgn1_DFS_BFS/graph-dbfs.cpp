/*
Implement depth first search algorithm and Breadth First Search algorithm, 
Use an undirected graph and develop a recursive algorithm for searching 
all the vertices of a graph or tree data structure
*/

#include <bits/stdc++.h>
using namespace std;

class Graph
{
private: 
    int v; //no of vertices
    int** p; //matrix representation
public:
    Graph()
    {
        v=0;
        p=NULL;
    }

    Graph(int n)
    {
        v=n; 
        p = new int* [v];
        for (int i=0; i<v; i++) //initialising the matrix
        {
            p[i]=new int[v];
        }
        for(int i=0;i<v;i++)
        {
            for(int j=0;j<v;j++)
            {
                p[i][j]=0;
                p[j][i]=0;
            }    
        }
    }

    void setGraph() //setting the graph
    {
        char x;
        for(int i=0;i<v;i++)
        {
            for(int j=i;j<v;j++)
            {
                cout<<"Are vertices "<<i<<" "<<j<<" connected(y/n)?";
                cin>>x;
                if(x=='Y' || x=='y')
                {
                    p[i][j]=1;
                    p[j][i]=1;   
                }
            }
        }
    }

    void printGraph() //printing the graph
    {
        for(int i=0;i<v;i++)
        {
            for(int j=0;j<v;j++)
            {
                cout<<p[i][j]<<" ";
            }
            cout<<endl;
        }
    }

    void BFS() //Breadth First Search
    {
        bool vis[v];
        for(int i=0;i<v;i++) vis[i]=false;
        int vertex;
        cout<<"Enter the start vertex: ";
        cin>>vertex;
        cout<<"\n";
        queue<int> q;

        q.push(vertex);
        while(!q.empty())
        {
            vertex=q.front();
            cout<<vertex<<" ";
            vis[vertex]=true;
            q.pop();
            for(int i=0;i<v;i++)
            {
                if(p[vertex][i]==1 && !vis[i])
                {
                    q.push(i);
                    vis[i]=true;
                }
            }
        }
    }

    void DFS() //Depth First Search
    {
        int vertex;
        cout<<"Enter the vertex to start DFS: ";
        cin>>vertex;
        bool vis[v];
        for(int i=0;i<v;i++) vis[i]=false;
        stack<int> st;
        st.push(vertex);
        while(!st.empty())
        {
            vertex=st.top();
            cout<<vertex<<" ";
            st.pop();
            vis[vertex]=true;
            for(int i=0;i<v;i++)
            {
                if(p[vertex][i]==1 && !vis[i])
                {
                    st.push(i);
                    vis[i]=true;
                }
            }
        }
    }

    void searchVertex()
    {
        int vertex=0,ver;
        cout<<"Enter the vertex to be searched: ";
        cin>>ver;
        bool vis[v];
        for(int i=0;i<v;i++) vis[i]=false;

        for(int i=0;i<v;i++)
        {
            if(!vis[i])
            {
                vertex=i;
                stack<int> st;
                st.push(vertex);
                while(!st.empty())
                {
                    vertex=st.top();
                    st.pop();
                    if(ver==vertex)
                    {
                        cout<<"\nVertex "<<ver<<" is present in the graph\n";
                        return;
                    }
                    vis[vertex]=true;
                    for(int i=0;i<v;i++)
                    {
                        if(p[vertex][i]==1 && !vis[i])
                        {
                            st.push(i);
                            vis[i]=true;
                        }
                    }
                }
            }
        }
        cout<<"\nVertex "<<ver<<" is not present in the graph\n";
    }

};

int main()
{
    bool cont=true;
    bool isEmpty=true;
    Graph g=Graph(1);
    int choice;
    while(cont)
    {
        cout<<"\nEnter your choice: ";
        cout<<"\n1. Set new graph";
        cout<<"\n2. Print the graph";
        cout<<"\n3. Print BFS from a vertex";
        cout<<"\n4. Print DFS from a vertex";
        cout<<"\n5. Search for a vertex";
        cout<<"\n6. Exit\n";
        cin>>choice;
        switch(choice)
        {
            case 1:
                int v;
                cout<<"\nEnter the number of vertices: ";
                cin>>v;
                g=Graph(v);
                g.setGraph();
                isEmpty=false;
                break;
            case 2:
                if(!isEmpty)
                {
                    cout<<"\n\n";
                    g.printGraph();
                    cout<<"\n\n";
                }
                else cout<<"\nPlease Set the graph first\n";
                break;
            case 3:
                if(!isEmpty)
                {
                    cout<<"BFS is ";
                    g.BFS();
                    cout<<"\n\n";                }
                else cout<<"\nPlease Set the graph first\n";
                break;
            case 4:
                if(!isEmpty)
                {
                    cout<<"DFS is ";
                    g.DFS();
                    cout<<"\n\n";
                }
                else cout<<"\nPlease Set the graph first\n";
                break; 
            case 5:
                if(!isEmpty)
                {
                    g.searchVertex();
                }
                else cout<<"\nPlease Set the graph first\n";
                break;       
            case 6:
                cont=false;
                break;
        }
    }
    return 0;
}
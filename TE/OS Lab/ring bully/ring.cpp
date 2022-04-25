#include <string.h>
#include <iostream>
#include <stdlib.h>
using namespace std;
struct rr
{
    int index;
    int id;
    int f;
    char state[10];
} proc[10];
int i, j, k, m, n;
int main()
{
    int temp;
    char str[10];
    cout << "\n enter the number of process\t";
    cin >> n;
    for (i = 0; i < n; i++)
    {
        proc[i].index;
        cout << "\n enter id of process\t";
        cin >> proc[i].id;
        strcpy(proc[i].state, "active");
        proc[i].f = 0;
    }
    // sorting
    for (i = 0; i < n - 1; i++)
    {
        for (j = 0; j < n - 1; j++)
        {
            if (proc[j].id > proc[j + 1].id)
            {
                temp = proc[j].id;
                proc[j].id = proc[j + 1].id;
                proc[j + 1].id = temp;
            }
        }
    }
    for (i = 0; i < n; i++)
        printf("[%d] %d\t", i, proc[i].id);
    int init;
    int ch;
    int temp1;
    int temp2;
    int arr[10];
    strcpy(proc[n - 1].state, "inactive");  //the highest one becomes inactive
    cout << "\nprocess " << proc[n - 1].id << " selected as the coordinator";
    while (1)
    {
        cout << "\n1)election 2)quit\n";
        cin>>ch;
        if(ch==2){
            break;
        }
        for (i = 0; i < n; i++)
        {
            proc[i].f = 0;
        }
        switch (ch)
        {
        case 1:
            cout << "\nenter the process Number who intialised election : ";
            cin>>init;
            temp2 = init;  // temp2 =2

            temp1 = init + 1; // temp 1 = 3
            i = 0;
            while (temp2 != temp1)
            {
                if (strcmp(proc[temp1].state, "active") == 0 && proc[temp1].f == 0)
                {
                    cout << "process " << proc[init].id << " sent message to " << proc[temp1].id << "\n";
                    proc[temp1].f = 1;
                    init = temp1;
                    arr[i] = proc[temp1].id;
                    i++;
                }
                if (temp1 == n)  //n=6
                    temp1 = 0;
                else
                    temp1++;
            }

            cout << "process " << proc[init].id << " sent message to " << proc[temp1].id << "\n";
            arr[i] = proc[temp1].id;
            i++;
            int max = -1;
            for (j = 0; j < i; j++)
            {
                if (max < arr[j])
                    max = arr[j];    // to find highest number in the array
            }
            cout << "\nprocess " << max << " selected as coordinator";
            for (i = 0; i < n; i++)
            {
                if (proc[i].id == max)
                {
                    strcpy(proc[i].state, "inactive"); // made it inactive
                    
                }
            }
            break;
            break;
        }
    }
    return 0;
}
#include<string.h>
#include<iostream>
#include<stdlib.h>
using namespace std;
struct rr
{
char name[10];
int prior;
char state[10];
}proc[10];
int i,j,k,l,m,n;
int main()
{
    cout<<"\n enter the number of processes \t";
    cin>>n;
    for(i=0;i<n;i++)
    {
        cout<<"\nenter the name of process\t";
        cin>>proc[i].name;
        cout<<"\nenter the priority of process\t";
        cin>>proc[i].prior;
        strcpy(proc[i].state,"active");
    }


    for(i=0;i<n-1;i++)
    {
        for(j=0;j<n-1;j++)
        {
            if(proc[j].prior<proc[j+1].prior)
            {
                char ch[10];
                int t=proc[j].prior;
                proc[j].prior= proc[j+1].prior;
                proc[j+1].prior=t;
                strcpy(ch,proc[j].name);
                strcpy(proc[j].name,proc[j+1].name);
                strcpy(proc[j+1].name,ch);
            }
        }
    }
    int maxm=0;

    for(i=0;i<n;i++)
    cout<<"\n"<<proc[i].name<<"\t"<<proc[i].prior;


    for(i=0;i<n;i++)
    {
        if(maxm<proc[i].prior)
        maxm=proc[i].prior;
    }

    for(i=0;i<n;i++)
    {
        if(proc[i].prior==maxm)
        {
            cout<<"\nprocess "<<proc[i].name<<" selected as coordinator";
            strcpy(proc[i].state,"iactive");  // made that process inactive
            break;
        }
    }
    int pr;
    while(1)
    {
        int ch;
        cout<<"\n1)election\n";
        cout<<"\n 2) exit  \n";
        cin>>ch;
        int max=0;
        int ar[20];
        k=0;
        int fl=0;
        switch(ch)
        {
            case 1: 
                char str[10];
                cout<<"\n 1)intialise election\t";
                cin>>str;          // the process wo notices that the coordinator is no longer responding
                fl=0;
                l1: for(i=0;i<n;i++)           // l1 is a goto statement
                {
                    if(strcmp(str,proc[i].name)==0)
                {
                    pr=proc[i].prior;  // pr will be equal to 5
                }
                    }
                //cout<<"\n"<<pr;
                for(i=0;i<n;i++)
                {
                        if(pr<proc[i].prior) //that process sends election msg to all the processes with higher nos
                        {
                            cout<<"\nprocess "<<str<<" sends message to "<<proc[i].name;
                        }
                }

                for(i=0;i<n;i++)
                {
                        if(pr<proc[i].prior && strcmp(proc[i].state,"active")==0 ) //if the higher process is active
                    {
                        if(fl==0)
                        {
                            ar[k]= proc[i].prior;
                            k++;
                        }
                            cout<<"\nprocess "<<proc[i].name<<" sends OK message to "<<str;
                            if(proc[i].prior>max)
                            max=proc[i].prior;


                    }
                }
                fl=1;



                if(k!=0)
                {
                    k=k-1;
                    for(i=0;i<n;i++)
                    {
                        if(ar[k]==proc[i].prior)
                        strcpy(str,proc[i].name); //the process which is right next to the prev process now sends the election msgs
                    }


                    goto l1;
                }
                m=0;
                    for(j=0;j<n;j++)
                {
                    if(proc[j].prior>m && strcmp(proc[j].state,"active")==0 )
                    {
                        cout<<"\n\nprocess "<<proc[j].name <<" is selected as new coordinator\n";
                        strcpy(proc[j].state,"inactive");  // made this inactive
                        break;
                    }
                }


                    for(i=0;i<n;i++)
                {
                    if(strcmp(proc[i].state,"active")==0 && proc[j].prior>proc[i].prior)
                    {
                        cout<<"\nprocess "<<proc[j].name<<" sends alert message to "<<proc[i].name;
                    }
                }

                break;

            case 2:exit(1);

        }
    }


    return 0;
}
pt=[3,5,2,5,5]
at=[0,1,3,9,12]
n=len(pt)
processn=[1,2,3,4,5]

def fcfs():
    burstTime=[3,5,2,5,5]
    arrivalTime=[0,1,3,9,12]
    gantt=[]
    time=at[0]
    gantt.extend([0]*at[0])
    for i in range(len(at)):
        gantt.extend([processn[i]]*pt[i])
        time+=pt[i]
        if(i<n-1 and at[i+1]>time):
            gantt.extend([0]*at[i+1]-time)
            time=at[i+1]
    
    starttime=[]
    finishtime=[]
    for i in range(n):
        starttime.append(-1)
        finishtime.append(-1)
    for i in range(len(gantt)):
        if(gantt[i]>0 and starttime[gantt[i]-1]==-1):
            starttime[gantt[i]-1]=i 
    for i in range(len(gantt)-1,-1,-1):
        if(gantt[i]>0 and finishtime[gantt[i]-1]==-1):
            finishtime[gantt[i]-1]=i+1
    #print(gantt)
    #print(starttime)
    #print(finishtime)
    tat=[]
    totaltat=0
    for i in range(n):
        tat.append(finishtime[i]-arrivalTime[i])
        totaltat+=(finishtime[i]-arrivalTime[i])
    wt=[]
    totwt=0
    for i in range(n):
        wt.append(tat[i]-burstTime[i])
        totwt+=(tat[i]-burstTime[i])
    avgtat=totaltat/n 
    avgwt=totwt/n
    
    #print(tat)
    #print(wt)
    print('process no  ','arrival time  ','burst time  ','start time    ','finish time ',' turnaround time  ','waiting time  ')
    for i in range(n):
        print(i+1,end="\t\t")
        print(arrivalTime[i],end="\t\t ")
        print(burstTime[i],end=" \t\t")
        print(starttime[i],end="\t\t ")
        print(finishtime[i],end="\t\t ")
        print(tat[i],end="\t\t ")
        print(wt[i])
    print("\navg wt = ",avgwt,"\navg tat = ",avgtat)


def sjfnp():
    pt=[3,5,2,5,5]
    burstTime=[]
    m=len(pt)
    at=[0,1,3,9,12]
    processn=[1,2,3,4,5]
    arrivalTime=[]
    prid=[]
    for i in range(m):
        burstTime.append(pt[i])
        arrivalTime.append(at[i])
        prid.append(processn[i])
    gantt=[]
    
    time=at[0]
    gantt.extend([0]*at[0])
    while(len(pt)>0):
        flag=9999
        ind=-1
        
        for i in range(len(at)):
            if(at[i]<=time):
                if(pt[i]<flag):
                    flag=pt[i]
                    ind=i 
        
        if(ind==-1):
            jj=min(at)
            gantt.extend([0]*(jj-time))
            time=jj 
            continue
        gantt.extend([processn[ind]]*pt[ind])
        time+=pt[ind]
        pt.pop(ind)
        at.pop(ind)
        processn.pop(ind)
    starttime=[]
    finishtime=[]
    for i in range(n):
        starttime.append(-1)
        finishtime.append(-1)
    for i in range(len(gantt)):
        if(gantt[i]>0 and starttime[gantt[i]-1]==-1):
            starttime[gantt[i]-1]=i 
    for i in range(len(gantt)-1,-1,-1):
        if(gantt[i]>0 and finishtime[gantt[i]-1]==-1):
            finishtime[gantt[i]-1]=i+1
    #print(gantt)
    #print(starttime)
    #print(finishtime)
    tat=[]
    totaltat=0
    for i in range(n):
        tat.append(finishtime[i]-arrivalTime[i])
        totaltat+=(finishtime[i]-arrivalTime[i])
    wt=[]
    totwt=0
    for i in range(n):
        wt.append(tat[i]-burstTime[i])
        totwt+=(tat[i]-burstTime[i])
    avgtat=totaltat/n 
    avgwt=totwt/n
    
    #print(tat)
    #print(wt)
    print('process no  ','arrival time  ','burst time  ','start time    ','finish time ',' turnaround time  ','waiting time  ')
    for i in range(n):
        print(i+1,end="\t\t")
        print(arrivalTime[i],end="\t\t ")
        print(burstTime[i],end=" \t\t")
        print(starttime[i],end="\t\t ")
        print(finishtime[i],end="\t\t ")
        print(tat[i],end="\t\t ")
        print(wt[i])
    print("\navg wt = ",avgwt,"\navg tat = ",avgtat)
        
def priority():
    prio=[3,1,3,4,2]
    at=[0,0,0,0,0]
    bt=[10,1,2,1,5]
    processn=[1,2,3,4,5]
    processes=[]
    for i in range(len(bt)):
        pd=[]
        pd.append(prio[i])
        pd.append(processn[i])
        pd.append(bt[i])
        
        processes.append(pd)
    processes.sort()
    
    gantt=[]
    for i in range(len(processes)):
        gantt.extend([processes[i][1]]*processes[i][2])
    starttime=[]
    finishtime=[]
    for i in range(n):
        starttime.append(-1)
        finishtime.append(-1)
    for i in range(len(gantt)):
        if(gantt[i]>0 and starttime[gantt[i]-1]==-1):
            starttime[gantt[i]-1]=i 
    for i in range(len(gantt)-1,-1,-1):
        if(gantt[i]>0 and finishtime[gantt[i]-1]==-1):
            finishtime[gantt[i]-1]=i+1
    #print(gantt)
    #print(starttime)
    #print(finishtime)
    tat=[]
    totaltat=0
    for i in range(n):
        tat.append(finishtime[i]-at[i])
        totaltat+=(finishtime[i]-at[i])
    wt=[]
    totwt=0
    for i in range(n):
        wt.append(tat[i]-bt[i])
        totwt+=(tat[i]-bt[i])
    avgtat=totaltat/n 
    avgwt=totwt/n
    
    #print(tat)
    #print(wt)
    print('process no  ','arrival time  ','burst time  ','start time    ','finish time ',' turnaround time  ','waiting time  ')
    for i in range(n):
        print(i+1,end="\t\t")
        print(at[i],end="\t\t ")
        print(bt[i],end=" \t\t")
        print(starttime[i],end="\t\t ")
        print(finishtime[i],end="\t\t ")
        print(tat[i],end="\t\t ")
        print(wt[i])
    print("\navg wt = ",avgwt,"\navg tat = ",avgtat)

def roundrobin():
    pt=[4,2,2,2]
    at=[20,20,22,24]
    n=len(pt)
    processn=[1,2,3,4]
    slab=1
    gantt=[]
    gantt.extend([0]*at[0])
    inq=[]
    for i in range(n):
        inq.append(0)
    time=at[0]

    queue=[]
    indices=[]
    allc=0
    for i in range(len(at)):
        if(at[i]<=time):
            queue.append(pt[i])
            indices.append(processn[i])
            inq[i]=1
    while(len(queue)>0):
        af=queue[0]
        ind=indices[0]
        
        queue.pop(0)
        indices.pop(0)
        mint=min(af,slab)
        time+=mint 
        gantt.extend([ind]*mint)
        if(allc==0):
            for i in range(len(at)):
                if(at[i]<=time and inq[i]==0):
                    queue.append(pt[i])
                    indices.append(processn[i])
                    inq[i]=1
                if(min(inq)==1):
                    allc=1
    
        af-=slab
        if(af>0):
            queue.append(af)
            indices.append(ind)
    starttime=[]
    finishtime=[]
    for i in range(n):
        starttime.append(-1)
        finishtime.append(-1)
    for i in range(len(gantt)):
        if(gantt[i]>0 and starttime[gantt[i]-1]==-1):
            starttime[gantt[i]-1]=i 
    for i in range(len(gantt)-1,-1,-1):
        if(gantt[i]>0 and finishtime[gantt[i]-1]==-1):
            finishtime[gantt[i]-1]=i+1 
    #print(gantt)
    #print(starttime)
    #print(finishtime)
    tat=[]
    totaltat=0
    for i in range(n):
        tat.append(finishtime[i]-at[i])
        totaltat+=(finishtime[i]-at[i])
    wt=[]
    totwt=0
    for i in range(n):
        wt.append(tat[i]-pt[i])
        totwt+=(tat[i]-pt[i])
    avgtat=totaltat/n 
    avgwt=totwt/n
    
    #print(tat)
    #print(wt)
    print('process no  ','arrival time  ','burst time  ','start time    ','finish time ',' turnaround time  ','waiting time  ')
    for i in range(n):
        print(i+1,end="\t\t")
        print(at[i],end="\t\t ")
        print(pt[i],end=" \t\t")
        print(starttime[i],end="\t\t ")
        print(finishtime[i],end="\t\t ")
        print(tat[i],end="\t\t ")
        print(wt[i])
    print("\navg wt = ",avgwt,"\navg tat = ",avgtat)


def sjfpre():
    pt=[3,5,2,5,5]
    at=[0,1,3,9,12]
    m=len(pt)
    processn=[1,2,3,4,5]
    time=0
    slab=2
    arrivalTime=[]
    prid=[]
    burstTime=[]
    for i in range(m):
        burstTime.append(pt[i])
        arrivalTime.append(at[i])
        prid.append(processn[i])
    gantt=[]
    processid=-1
    while(len(pt)>0):
        flag=9999
        for i in range(len(at)):
            if(time>=at[i]):
                if(pt[i]<flag):
                    flag=pt[i]
                    processid=i 
        if(processid!=-1):
            mintime=min(flag,slab)
            gantt.extend([processn[processid]]*mintime)
            pt[processid]-=mintime
            time+=mintime
            if(pt[processid]<=0):
                pt.pop(processid)
                at.pop(processid)
                processn.pop(processid)
            
    
    starttime=[]
    finishtime=[]
    for i in range(n):
        starttime.append(-1)
        finishtime.append(-1)
    for i in range(len(gantt)):
        if(gantt[i]>0 and starttime[gantt[i]-1]==-1):
            starttime[gantt[i]-1]=i 
    for i in range(len(gantt)-1,-1,-1):
        if(gantt[i]>0 and finishtime[gantt[i]-1]==-1):
            finishtime[gantt[i]-1]=i+1
    #print(gantt)
    #print(starttime)
    #print(finishtime)
    tat=[]
    totaltat=0
    for i in range(n):
        tat.append(finishtime[i]-arrivalTime[i])
        totaltat+=(finishtime[i]-arrivalTime[i])
    wt=[]
    totwt=0
    for i in range(n):
        wt.append(tat[i]-burstTime[i])
        totwt+=(tat[i]-burstTime[i])
    avgtat=totaltat/n 
    avgwt=totwt/n
    #print(tat)
    #print(wt)
    print('process no  ','arrival time  ','burst time  ','start time    ','finish time ',' turnaround time  ','waiting time  ')
    for i in range(n):
        print(i+1,end="\t\t")
        print(arrivalTime[i],end="\t\t ")
        print(burstTime[i],end=" \t\t")
        print(starttime[i],end="\t\t ")
        print(finishtime[i],end="\t\t ")
        print(tat[i],end="\t\t ")
        print(wt[i])
    print("\navg wt = ",avgwt,"\navg tat = ",avgtat)

ch=1
print('\n\tMENU\t')

while(ch!=0):
    print('\n1.FCFS\n2.SJF non preemptive \n3.Priority\n4.Round Robin\n5.SJF Preemptive\n0.Exit')
    print("enter your choice = ")
    ch=int(input())
    if(ch==1):
        fcfs()
    if(ch==2):
        sjfnp()
    if(ch==3):
        priority()
    if(ch==4):
        roundrobin()
    if(ch==5):
        sjfpre()
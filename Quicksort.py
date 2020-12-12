num={'00': 16, '31': 2, '32': 2, '33': 2, '34': 2, '35': 2, '36': 2, '37': 2, '38': 2, '11': 16}
keys=list(num)
values=[]
for i in keys:
    values.append(num[i])

def quickshot(left,right,keys,values):
    if(left>right):
        return
    temp=values[left]
    i=left
    j=right
    while(i!=j):
        while(values[j]>=temp and j>i):
            j=j-1
        while(values[i]<=temp and i<j):
            i=i+1
        if(i<j):
            a=values[j]
            values[j]=values[i]
            values[i]=a

            a=keys[j]
            keys[j]=keys[i]
            keys[i]=a

            del a
    values[left]=values[i]
    values[i]=temp

    #keys[left]=keys[i]
    #keys[i]=keys[left]

    quickshot(left,i-1,keys,values)
    quickshot(i+1,right,keys,values)
    return

quickshot(0,len(values)-1,keys,values)
print(keys)
print(values)
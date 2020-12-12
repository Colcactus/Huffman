def quickshot(left,right,num):
    if(left>right):
        return
    temp=num[left]
    i=left
    j=right
    while(i!=j):
        while(num[j]>=temp and j>i):
            j=j-1
        while(num[i]<=temp and i<j):
            i=i+1
        if(i<j):
            a=num[j]
            num[j]=num[i]
            num[i]=a
            del a
    num[left]=num[i]
    num[i]=temp
    quickshot(left,i-1,num)
    quickshot(i+1,right,num)
    return

def main():
    value={}
    file=open("text.txt","rb")
    data_origin=str(file.read().hex("-")).split("-")
    for i in data_origin:
        if(not(str(i) in value)):
            value[str(i)]=0
        value[str(i)]+=1
    print(value)
    file.close()

main()
def quicksort(left,right,huffman_array):
    if(left>right):
        return
    i=left
    j=right
    temp=huffman_array[left][1]
    while(i!=j):
        while(i<j and temp<=huffman_array[j][1]):
            j-=1
        while(i<j and temp>=huffman_array[i][1]):
            i+=1
        if(i<j):
            a=huffman_array[i]
            huffman_array[i]=huffman_array[j]
            huffman_array[j]=a
            del a
    a=huffman_array[left]
    huffman_array[left]=huffman_array[i]
    huffman_array[i]=a
    del a
    quicksort(left,i-1,huffman_array)
    quicksort(i+1,right,huffman_array)

def main():
    #读取十六进制
    file=open("Test.txt","rb")
    original_hex_data=str(file.read().hex("-")).split("-")
    file.close()
    
    #统计出现次数
    frequency={}
    for count in original_hex_data:
        if(not(count in frequency)):
            frequency[count]=0
        frequency[count]+=1
    
    #创建二维数组，并排序好数组，只留下huffman有序序列
    keys=[]
    value=[]
    lenth=0
    keys=list(frequency)
    for i in keys:
        value.append(frequency[i])
    huffman_array=[]
    for i in range(len(keys)):
        huffman_array.append([])
        huffman_array[i].append(keys[i])
        huffman_array[i].append(value[i])
        huffman_array[i].append("Value")
    quicksort(0,len(keys)-1,huffman_array)
    lenth=len(keys)-1
    del keys,value

    #生成huffman树
    huffman_tree=[]
    index=3
    for i in range(3):
        huffman_tree.append(0)
    if(huffman_array[0][2]=="Value" and huffman_array[1][2]=="Value"):
        for i in range(6):
            huffman_tree.append(0)
        huffman_tree[index]=huffman_array[0][0]
        index+=3
        huffman_tree[index]=huffman_array[1][0]
        index+=3
        huffman_array[0]=[[index-6,index-3],huffman_array[0][1]+huffman_array[1][1],"Tree"]
        del huffman_array[1]
        lenth-=1
        quicksort(0,lenth,huffman_array)
    if(huffman_array[0][2]=="Value" and huffman_array[1][2]=="Tree"):
        pass
    print(huffman_array)
    print(huffman_tree)

main()
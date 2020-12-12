def main():
    value=[]
    key=[]
    count=0
    index={}
    file=open("text.txt","rb")
    data_origin=str(file.read().hex("-")).split("-")
    for hex0 in data_origin:
        if(not(hex0 in value)):
            value.append(hex0)
            key.append(0)
            index[hex0]=count
            count=count+1
        key[index[hex0]]+=1

        if(index[hex0]!=0 and key[index[hex0]]>key[index[hex0]-1]):
            a=key[index[hex0]-1]
            key[index[hex0]-1]=key[index[hex0]]
            key[index[hex0]]=a

            a=value[index[hex0]-1]
            b=value[index[hex0]]
            value[index[hex0]-1]=value[index[hex0]]
            value[index[hex0]]=a

            c=index[a]
            index[a]=index[b]
            index[b]=c
            
    print(index)
    print(value)
    print(key)

main()
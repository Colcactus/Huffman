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
        
    print(index)
    print(value)
    print(key)

main()
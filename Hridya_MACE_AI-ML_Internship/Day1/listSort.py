lst=list(map(int,input("Enter numbers:").split()))
print(lst)
for i in range(len(lst)):
    for j in range(i+1,len(lst)):
        if lst[i]>lst[j]:
            temp=lst[i]
            lst[i]=lst[j]
            lst[j]=temp
print("Sorted list:",lst)
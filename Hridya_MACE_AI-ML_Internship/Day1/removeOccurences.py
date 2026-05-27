lst=[3,2,5,2,2,4,5,3,6,3,7]
print(lst)
lst1=[]
n=int(input("Enter a number:"))
for i in lst:
    if i!=n:
        lst1.append(i)
print("List after removing:",lst1)
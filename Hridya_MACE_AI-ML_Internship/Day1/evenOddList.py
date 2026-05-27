lst=[1,2,3,4,5,6,7,8,9]
print("List:",lst)
even=[]
odd=[]
for i in lst:
    if i%2:
        odd.append(i)
    else:
        even.append(i)
print("Even:",even)
print("Odd:",odd)
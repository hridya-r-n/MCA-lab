#reverse string without buit in functions
s=[1,2,3,4,5,6]
r=[]
for i in range(len(s)-1,-1,-1):
    r.append(s[i])
print("Reverse:",r)

# merge 2 list without +
l1=[1,2,3,4,5]
print("List 1:",l1)
l2=[5,6,7,8,9,8]
print("List 2:",l2)
l1.extend(l2)
print("Merged List:",l1)

d={}
for i in range(len(s)):
    d[s[i]]=l1[i]
print("Dictionary from list:",d)

# find common element from 2 lists
c=[]
for i in l2:
    for j in s:
        if i==j:
            c.append(i)
            
print("Common elements:",c)

# flatten nested list

l1=[[1,2,3],[5,6,7],[10,11]]
fl=[i for j in l1 for i in j]
print(fl)

# rotate list by k positions
print(l2)
k=int(input("Enter k:"))
for i in range(k+1):
    l2.append(l2.pop(0))
print(l2)

# find pairs whose sum =a target
print(l2)
t=int(input("Enter target sum:"))
for i in range(len(l2)):
    for j in range(len(l2)):
        if l2[i]+l2[j]==t:
            print(l2[i],l2[j])

# 
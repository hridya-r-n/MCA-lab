l1=[2,4,8,7,9]
print(l1)
print("Sum:",sum(l1))

print("Largest:",max(l1))
print("Smallest:",min(l1))

l2=[1,2,2,4,5,7,3,4,1]
print(l2)
print("Removed duplicates:",list(set((l2))))

t1=(1,2,3)
print(t1)
print("t1[2]:",t1[2])

print("List to tuple:",tuple(l1))

d1={'Gouri':80,'Ashi':84,'Alee':70}
for k,v in d1.items():
    print(k,":",v)

d1.update({'Anagha':90})
print(d1)

name=input("Enter a name:")
if name in d1.keys():
    print(name,"exists")
else:
    print(name,"does not exists")

s1={1,2,3,5,6}
s2={4,5,7,8,9}
print(s1)
print(s2)
print("Union:",s1.union(s2))
print("Intersection:",s1.intersection(s2))

n=2
print(n,"appears",l2.count(n),"times")
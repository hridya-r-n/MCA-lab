s1={1,2,3,4,5}
print("Set:",s1)

s1.add(6)
print(s1)

s=s1.remove(3)
print("3 removed:",s1)

s1.discard(5)
print("5 discarded:",s1)

s2={5,6,7,8,9}
print("s1 union s2:",s1.union(s2))

print("s1 intersection s2:",s1.intersection(s2))

print("s1-s2:",s1.difference(s2))

print("Length of s1:",len(s1))

lst=[7,8,9,10]
print("List:",lst)
print("Set:",set(lst))

s1.clear()
print("Cleared:",s1)
s1='hello python programming'
print(s1)
print("List:",s1.split())
print("Number of words:",len(s1.split()))

lst=['hello','python','programming']
s2=" ".join(lst)
print(s2)

c=0
for i in " ".join(lst):
    if i.lower() in 'aeiou':
        c+=1
print("Number of vowels:",c)

l1=[2,34,5,4,6,8]
l2=[5,3,6,8,9,8,4]

print("Common elements:",set(l1).intersection(l2))

lst=["a","","b",""]
for i in lst:
    if i=="":
        lst.remove(i)
print(lst)
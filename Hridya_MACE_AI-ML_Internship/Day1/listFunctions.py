l1=['apple','banana','pear']
l1.append('orange')
print(l1)

l1.insert(1,'grapes')
print(l1)

l1.remove('banana')
print(l1)

l=l1.pop()
print(l," removed")
print(l1)

print("Length of the list:",len(l1))

l2=[1,2,2,3]
print("Number of 2:",l2.count(2))

l3=[5,2,9,1]
l3.sort()
print('Sorted list:',l3)

l3.reverse()
print('Reversed list:',l3)
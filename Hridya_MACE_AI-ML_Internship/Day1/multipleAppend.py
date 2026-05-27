lst=['apple','orange']
print(lst)
s=input("Enter element to append:")
while s:
    lst.append(s)
    s=input("Enter element to append:")
print(lst)
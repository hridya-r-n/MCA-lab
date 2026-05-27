d1=dict({1:"A",2:"B",3:"C"})
print(d1)

print(d1[2])


d1.update({4:"D"})
print(d1)
d1[5]="E"
print(d1)

d1.pop(3)
print(d1)

print(d1.keys())
print(d1.values())

print("4:",d1.get(4))

d2=d1.copy()
print("Copy:",d2)

d1.clear()
print("Cleared:",d1)
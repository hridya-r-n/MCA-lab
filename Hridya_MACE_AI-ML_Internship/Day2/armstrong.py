n=input("Enter a number:")
x=len(n)
c=int(n)
s=0
for i in n:
    s+=int(i)**x
if c==s:
    print("Armstrong number")
else:
    print("Not armstrong number")

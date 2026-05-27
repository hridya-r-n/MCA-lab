n1=int(input("Enter first number:"))
n2=int(input("Enter second number:"))
print("MENU")
print("1.Add 2.Subtract 3.Multiply 4.Divide")
c=int(input("Enter your choice:"))
if c==1:
    print(n1,"+",n2,"=",(n1+n2))
elif c==2:
    print(n1,"-",n2,"=",(n1-n2))
elif c==3:
    print(n1,"x",n2,"=",(n1*n2))
elif c==4:
    print(n1,"/",n2,"=",(n1/n2))
else:
    print("Invalid Choice")
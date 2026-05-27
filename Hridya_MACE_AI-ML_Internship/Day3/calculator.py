def addN(a,b):
    return a+b
def sub(a,b):
    return a-b
def mul(a,b):
    return a*b
def div(a,b):
    return a/b
c=0
a=int(input("Enter a number:"))
b=int(input("Enter a number:"))
while c!=5:
    print("1.Add 2.Subtract 3.Multiply 4.Divide 5.Exit")
    c=int(input("Enter your choice:"))
    if c==1:
        print(a,"+",b,"=",addN(a,b))
    elif c==2:
        print(a,"-",b,"=",sub(a,b))
    elif c==3:
        print(a,"x",b,"=",mul(a,b))
    elif c==4:
        print(a,"/",b,"=",div(a,b))
    elif c==5:
        break
    else:
        print("Invalid")
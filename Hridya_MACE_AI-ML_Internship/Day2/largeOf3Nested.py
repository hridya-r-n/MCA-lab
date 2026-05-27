a=int(input("Enter number 1:"))
b=int(input("Enter number 2:"))
c=int(input("Enter number 3:"))
if a>b:
    if a>c:
        print(a,"is greater")
    else:
        print(c,"is greater")
else:
    if b>c:
        print(b,"is greater")

    else:
        print(c,"is greater")
c=1
a=int(input("Enter a number:"))
b=int(input("Enter a number:"))
while c!=5:
    print("1.Add 2.Subtract 3.Multiply 4.Diivide 5.Exit")
    c=int(input("Enter your choice:"))
    if c==1:
        print("Sum=",(a+b))
    elif c==2:
        print("Difference=",(a-b))
    elif c==3:
        print("Product=",(a*b))
    elif c==4:
        print("Quotient=",(a/b))
    elif c==5:
        break
    else:
        print("Invalid choice")

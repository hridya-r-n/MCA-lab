ch=0
while ch!=5:
    try:
        a=int(input('Enter first number:'))
        b=int(input('Enter second number:'))
        print('1.Add 2.Subtract 3.Multiply 4.Divide')
        ch=int(input('Enter your choice:'))
        if ch==1:
            print(a,'+',b,'=',(a+b))
        elif ch==2:
            print(a,'-',b,'=',(a-b))
        elif ch==3:
            print(a,'*',b,'=',(a*b))
        elif ch==4:
            print(a,'/',b,'=',(a/b))
        elif ch==5:
            break
        else:
            print('Invalid choice')
    except ValueError:
        print('Invalid input')
    except ZeroDivisionError:
        print('Cannot divide by zero')
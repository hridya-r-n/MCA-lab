n1=int(input("Enter number 1:"))
n2=int(input("Enter number 2:"))
n3=int(input("Enter number 3:"))

large=n1
if n2>=large:
    large=n2
if n3>=large:
    large=n3
print("Largest:",large)
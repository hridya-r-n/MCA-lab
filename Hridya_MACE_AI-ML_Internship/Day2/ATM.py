balance=1000
amt=int(input("Enter amount to withdraw:"))
if amt>0:
    if amt>balance:
        print("Insufficient balance:")
    else:
        balance-=amt
        print("Amount withdrawed successfully.Current balance:",balance)
else:
    print("Amount must be greater than 0")
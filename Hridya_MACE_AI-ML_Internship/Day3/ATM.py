balance=1000
c=0
while c!=4:
    print("1.Withdraw 2.Deposit 3.Check Balance")
    c=int(input("Enter your choice:"))
    if c==1:
        amt=int(input("Enter amount to withdraw"))
        if amt<0:
            print("Enter valid amount")
        elif amt>balance:
            print("Insufficient balance")
        else:
            balance-=amt
            print("Withdrawed",amt,".Current Balance:",balance)
    elif c==2:
        amt=int(input("Enter amount to deposit:"))
        if amt<0:
            print("Enter valid amount")
        else:
            balance+=amt
            print("Deposited",amt,".Current Balance:",balance)
    elif c==3:
        print("Balance:",balance)
    elif c==4:
        break
    else:
        print("Invalid Choice!")
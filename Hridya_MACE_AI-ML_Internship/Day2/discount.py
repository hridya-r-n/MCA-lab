amt=int(input("Enter amount:"))
if amt>0:
    if amt>1500:
        print("Eligible for discount")
    else:
        print("Not eligible for discount")
else:
    print("Invalid amount")
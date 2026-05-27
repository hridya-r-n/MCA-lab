def billing(**details):
    print("Bill")
    for k,v in details.items():
        print(k,":",v)
    print("Total:",sum(details.values()))
billing(Pen=4,Pencil=5,Book=30)
print()
billing(Book=40,Pen=3,Pencil=5,Scale=10)
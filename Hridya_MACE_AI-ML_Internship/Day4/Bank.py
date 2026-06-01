class BankAccount:
    def __init__(self,acno,name,balance):
        self.__acno=acno
        self.name=name
        self.__balance=balance
    def getAcno(self):
        return self.__acno
    def showBalance(self):
        print('Balance:',self.__balance)
    def deposit(self,amt):
        self.__balance+=amt
        print(amt,'deposited.Current balance:',self.__balance)
    def withdraw(self,amt):
        self.__balance-=amt
        print(amt,'withdrawed.Current balance:',self.__balance)
bc=BankAccount(1,'Hridya',1000)
print('Acno:',bc.getAcno(),' Name:',bc.name)
bc.showBalance()
amt=int(input('Enter amount to deposit:'))
bc.deposit(amt)
amt=int(input('Enter amount to withdraw:'))
bc.withdraw(amt)
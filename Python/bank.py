# Create a Bank account with members account number, name, type of account and balance. Write constructor and methods to deposit at the bank and withdraw an amount from the bank.
class Bank:
    def __init__(self):
        self.acno=int(input('Enter account number:'))
        self.name=input('Enter name:')
        self.type=input('Enter account type:')
        self.balance=float(input('Enter account balance:'))
    def withdraw(self,amt):
        if amt<0:print('Amount must be greater than 0!')
        elif amt>self.balance:print('Insufficient Balance!')
        else:
            self.balance-=amt
            print(f'Successful Withdrawal.{amt} withdrawed\nBalance:{self.balance}')
    def deposit(self,amt):
        if amt<0:print('Amount must be greater than 0!')
        else:
            self.balance+=amt
            print(f'Deposited Successfully.{amt} deposited\nBalance:{self.balance}')
    def display(self):
        print('-----Account Details-----')
        print(f'Account number:{self.acno}\nName:{self.name}\nAccount Type:{self.type}\nCurrent balance:{self.balance}')
ac=Bank()
ac.display()
amt=float(input('Enter amiunt to be deposited:'))
ac.deposit(amt)
amt=float(input('Enter amout to be withdrawed:'))
ac.withdraw(amt)
ac.display()
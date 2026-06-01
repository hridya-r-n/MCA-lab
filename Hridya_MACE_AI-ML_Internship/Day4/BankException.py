class InsufficientBalance(Exception):
    pass
bal=1000
try:
    amt=int(input('Enter amount to deposit:'))
    if amt<0:
        raise ValueError('Amount must be greater than 0')
    bal+=amt
    print(amt,'deposited.Current Balance:',bal)
    amt=int(input('Enter amount to withdraw:'))
    if amt<0:
        raise ValueError('Amount must be greater than 0')
    if amt>bal:
        raise InsufficientBalance('Insufficient Balance')
    bal-=amt
    print(amt,'withdrawed.Current Balance:',bal)
except ValueError as e:
    print(e)
except InsufficientBalance as e:
    print(e)
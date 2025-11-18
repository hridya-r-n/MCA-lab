class Currency:
    def __init__(self):
        self.amt=float(input('Enter amount:'))
        self.unit=input('Enter unit of currency:')
    def display(self):
        print(f'{self.unit}.{self.amt}'if self.unit.isalpha() else f'{self.unit}{self.amt}')
    def __eq__(self,other):
        return self.unit==other.unit and self.amt==other.amt
c1=Currency()
c2=Currency()
c1.display()
c2.display()
if c1==c2:print('Currencies are equal')
else:print('Currencies are different')
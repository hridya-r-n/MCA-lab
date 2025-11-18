# Create a class Complex with private attributes real and imaginary parts. Overload '>= operator to find the greatest number.
import math as m
class Complex:
    def __init__(self,real,im):
        self.__real=real
        self.__im=im
        self.mod=m.sqrt(pow(self.__real,2)+pow(self.__im,2))
    def __str__(self):
        return f'{self.__real}+i{self.__im}'
    def __ge__(self,other):
        return self.mod>=other.mod
print('Enter first complex number:')
r=int(input('Enter real part:'))
i=int(input('Enter imaginary part:'))
c1=Complex(r,i)
print(f'z1={c1}')
print('Enter second complex number:')
r=int(input('Enter real part:'))
i=int(input('Enter imaginary part:'))
c2=Complex(r,i)
print(f'z1={c2}')
if c1>=c2:print(f'{c1} is greater or equal to {c2}')
else:print(f'{c1} is lesser than {c2}')
import re
try:
    num=input('Enter a number:')
    num=float(num)
    print('Float(num):',num)
except ValueError as e:
    print(e)
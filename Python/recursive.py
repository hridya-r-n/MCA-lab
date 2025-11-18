# Write recursive functions to 
# (a) find the factorial of a number 
# (b) find the nth Fibonacci number 
# (c) find the sum of an integer list 
# (d) find the sum of first N whole numbers 
# (e) reverse a string.

def factorial(n):
    fact=n
    if n==1:
        return 1
    fact=fact*factorial(n-1)
    return fact
n=int(input('Enter number:'))
if(n==0):print("Factorial:1")
elif(n<0):print('Factorial can\'t be found for negative nitegers')
else:print('Factorial:',factorial(n))

def fibonacci(n):
    if n==0:return 0
    if n==1:return 1
    return fibonacci(n-1)+fibonacci(n-2)
n=int(input('Enter n:'))
print(f'{n}th fibonacci number:{fibonacci(n)}')

def sumlist(list):
    if not list:return 0
    return list[0]+sumlist(list[1:])
list=list(map(int,input('Enter a comma seperated list of integers:').split(',')))
print('Sum=',sumlist(list))

def sumwhole(n):
    if not n:return 0
    return n+sumwhole(n-1)
n=int(input('Enter n:'))
print(f'Sum of first {n} whole numbers:{sumwhole(n)}')

def rev(s):
    if not s:return
    return s[-1]+rev(s[-2::-1])
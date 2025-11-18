# Write lambda functions:
# (a) To find largest of 2 numbers
# (b)  To check the input number is divisible by 5
# (c) To remove all strings with length < 5 from a list of strings
# (d)  To increment a list of integers by 10% if the number is greater than 1000 else by 5%.

a=int(input('Enter first number:'))
b=int(input('Enter second number:'))
largest=lambda a,b:a if a>b else b
print('Largest :',largest(a,b))

num=int(input('Enter a number:'))
divisible=lambda num:f'{num} is not divisiblle by 5' if num%5 else f'{num} is divisible by 5'
print(divisible(num))

list=input('Enter a list of strings:').split(',')
rem=lambda list:[s for s in list if len(s)>5]
print('Strings without length less than 5',rem(list))

num=map(int,input('Enter a list of integers:').split(','))
increment=lambda x:x*1.10 if x>1000 else x*1.05
print('Icremented numbers:',list(map(increment,num)))
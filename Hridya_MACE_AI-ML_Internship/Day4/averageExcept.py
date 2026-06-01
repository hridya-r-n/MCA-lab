try:
    a=int(input('Enter number 1:'))
    b=int(input('Enter number 2:'))
    c=int(input('Enter number 3:'))
    print('Average of',a,',',b,',',c,'is',((a+b+c)/3))
except ValueError:
    print('Enter valid input')
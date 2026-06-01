try:
    num=int(input('Enter a number:'))
    if num%2:
        print(num,'is odd')
    else:
        print(num,'id even')
except ValueError:
    print('Enter numeric values only')
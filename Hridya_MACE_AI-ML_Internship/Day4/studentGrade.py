try:
    mark=int(input('Enter mark:'))
    if mark<0 or mark>100:
        raise ValueError('Marks must be in the range 0-100')
    if mark>=90:
        print('Grade:A')
    elif mark>=80:
        print('Grade:B')
    elif mark>=70:
        print('Grade:C')
    elif mark>=60:
        print('Grade:D')
    elif mark>=50:
        print('Pass')
    else:
        print('Fail')
except ValueError as e:
    print(e)
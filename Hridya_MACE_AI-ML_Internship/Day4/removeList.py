lst=[1,2,3,4,5]
print(lst)
try:
    n=int(input('Enter a number to remove'))
    if n not in lst:
        raise ValueError('Item not in list')
    lst.remove(n)
    print(lst)
except ValueError as e:
    print(e)
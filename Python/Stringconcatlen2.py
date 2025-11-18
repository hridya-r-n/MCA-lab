s=input('Enter a string:')
if len(s)<2:
    print('Result string: ')
elif len(s)==2:
    print('Result String:',s)
else:
    print('Result String:',s[:2]+s[-2]+s[-1])
    
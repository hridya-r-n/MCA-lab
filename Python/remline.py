f=False
try:
    f=open('text1.txt','r+')
    print(f.read())
    f.seek(0)
    n=int(input('Enter line no. to be removed:'))
    lines=f.readlines()
    lines.pop(n-1)
    f.truncate(0)
    f.seek(0,0)
    f.writelines(lines)
    f.seek(0)
    print(f.read())
except IOError as io:
    print(io)
finally:
    if f:f.close()
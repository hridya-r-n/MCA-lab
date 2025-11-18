f=False
result=[]
try:
    f=open('sumfile.txt','w+')
    n=input('Enter text:')
    while(n):
        f.write(n+'\n')
        n=input('Enter text:')
    f.seek(0)
    l=f.readlines()
    for i in l:
        if not i.strip().startswith('#'):
            result.append(i)
    f.truncate(0)
    f.seek(0)
    f.writelines(result)
    f.seek(0)
    print(f.read())
except IOError as io:
    print(io)
finally:
    if f:f.close()
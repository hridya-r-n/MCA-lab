inf=False
try:
    inf=open('source.txt','r')
    print('Number of lines:',len(inf.readlines()))
except IOError as io:
    print(io)
finally:
    if inf:inf.close()
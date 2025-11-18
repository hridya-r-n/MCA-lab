f=False
try:
    f=open('text1.txt')
    print(f.read())
    f.seek(0)
    lines=f.readlines()
    lines.sort(key=len,reverse=True)
    print('Longest line:',lines[0])
except IOError as io:
    print(io)
finally:
    if f:f.close()
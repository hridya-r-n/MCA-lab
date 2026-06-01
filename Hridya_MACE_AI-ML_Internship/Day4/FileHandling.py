with open('file1.txt','w') as f:
    f.write('This is a file.\nThis is a demo file')

# with open('file1.txt','r') as f:
#     print(f.read())

with open('file1.txt','a') as f:
    f.write('\nthis is the next line')

with open('file1.txt','r') as f:
    data=f.readline()
    while data!='':
        print(data.strip())
        data=f.readline()

with open('file1.txt','r') as f:
    print(f.readlines())

with open('file1.txt','w') as f:
    line=input('Enter line:')
    while line!='':
        line=input('Enter line:')
    f.writelines(line)

with open('file1.txt','r+') as f:
    print(f.tell())
    f.write('Added line')

with open('file1.txt','r+') as f:
    f.seek(0)
    f.write('Second line')

f=open('newfile.txt','x')
f.write('new file')
f.close()
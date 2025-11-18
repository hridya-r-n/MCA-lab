inf=False
outf=False
try:
    inf=open('source.txt')
    outf=open('dest.txt','w')
    outf.write(inf.read())
except IOError as io:
    print(io)
finally:
    inf.close()
    outf.close()
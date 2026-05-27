s='hello world'
s1=''
for j in s.split():
    print(j)
    for i in range(len(j)):
        if i%2:
            s1=s1+j[i].upper()
        else:
            s1=s1+j[i].lower()
    s1=s1+' '
    i-=1


print(s)
print(s1)
lst=['apple',3,5,'orange',4,5,'apple','grape',3,'orange',3,4]
f={}
for i in lst:
    if i in f.keys():
        f[i]+=1
    else:
        f[i]=1
print("Frequencies:",f)
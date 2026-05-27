str1="Python programming"
str1=str1.lower()
c=0
for i in str1:
    if i in 'aeiou':
        c+=1
print("Number of vowels in",str1,":",c)

str2='  hello python programming '
print("String without spaces:",str2.strip())
print("Length without spaces:",len(str2.strip()))

str3='hello python programming'
print('Uppercase with _:',str3.replace(' ','_').upper())

str4='hello python programming'
for i in str4.split():
    print(i)

print("Number of words:",len(str4.split()))

lst=str4.split()
print("Longest word:",max(lst,key=len))

if str4.find('python'):
    print('"python" exist in string')
else:
    print('"python" does not exist in string')
def removeDuplicate(str1):
    str2=''
    
    for i in str1.split().pop():
        print(i)
        if i not in str1[1:]:
            str2=str2+i
    return str2

        

str1='python programming'
str2=removeDuplicate(str1)
print(str2)
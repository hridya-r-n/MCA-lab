# Write a program that count the number of strings where string length is 2 or more and the first and last characters are same.
def count_string(s):
    c=0
    for x in s:
        if len(x)>=2:
            if x[0]==x[-1]:
                c+=1
    return c
s=input('Enter a list of strings:').split(',')
print('Count of strings with same first and last charachter:',count_string(s))
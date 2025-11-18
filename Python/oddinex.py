# Write a program to remove all odd indexed characters from a given string.
def remove_odd(s):
    s1=''
    for i in s[0::2]:
        s1=s1+i
    return s1
s=input('Enter a string:')
print('Resultant string after removing odd indexed characters:',remove_odd(s))
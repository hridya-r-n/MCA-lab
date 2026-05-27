# string is empty
s1=input("Enter a string:")
if not s1:
    print(s1,"is empty")
else:
    print("String is not empty")

# string starts with vowel?
s2=input("Enter a string:")
if s2.lower()[0] in 'aeiou':
    print(s2,"starts with a vowel")
else:
    print(s2,"does not start with a vowel")

# 2 strings re equal?
s1=input("Enter string 1:")
s2=input("Enter string 2:")
if s1==s2:
    print("Strings are equal")
else:
    print("Strings are not equal")

# check if word is uppercase
s1=input("Enter a word:")
if s1.isupper():
    print(s1,"is in uppercase")
else:
    print(s1,"is not uppercase")

# alphabet or not
c=input("Enter a character:")
if c[0].isalpha():
    print(c,"is alphabet")
else:
    print(c,"is not an alphabet")
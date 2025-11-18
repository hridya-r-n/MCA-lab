# Write a function to get a new string from a given string by adding ‘Is’ to the beginning of the input string. If the given string already begins with ‘Is’, return the input string unchanged.
def Is_add(s:str):
    if s.startswith('is'):return s
    else:return 'Is'+s
s=input('Enter a string:')
print('New string:',Is_add(s.lower()))
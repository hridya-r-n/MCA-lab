class PassworException(Exception):
    pass
try:
    pwd=input('Enter your pasword:')
    if len(pwd)<8:
        raise PassworException('Password must contain atleast 8 characters')
    print('Password accepted')
except PassworException as e:
    print(e)

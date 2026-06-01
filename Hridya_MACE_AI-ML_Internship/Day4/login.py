class InvalidCredentials(Exception):
    pass
try:
    user=input('Enter username:')
    pwd=input('Enter password:')
    if user!='user123' or pwd!='1234':
        raise InvalidCredentials('Invalid credentials')
    print('Login Successful')
except InvalidCredentials as e:
    print(e)
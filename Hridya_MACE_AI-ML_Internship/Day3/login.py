def login(name,pwd):
    if name=='user1' and pwd=='user123':
        print("Access granted")
    else:
        print("Access Denied")
name=input("Enter username:")
pwd=input("Enter password:")
login(name,pwd)
# 1-10
for i in range(1,11):
    print(i)

# factorial
# f=1
# n=int(input("Enter a number:"))
# for i in range(1,n+1):
#     f*=i
# print("Factorial of",n,"=",f)

# pyramid
for i in range(5):
    for k in range(5,i,-1):
        print(end=" ")
    for j in range(i):
        print("*",end=" ")
    print()

# prime
n=int(input("Enter a number:"))
for i in range(2,n+1):
    for j in range(2,int(i**(1/2))+1):
        if i%j==0:
            break
    else:
        print(i)
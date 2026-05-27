# sum of any number of values
def sumN(*num):
    print("Numbers:",num)
    print(sum(num))
sumN(2,4,57,9)
sumN(2,3,4,5,6,7)

# print names
def printNames(*name):
    for i in name:
        print("Name:",i)
printNames('Hridya','Ashi','Aleena','Gouri')

# find largest
def largest(*num):
    large=num[0]
    for i in num:
        if i>large:
            large=i
    return large
print("Largest of(3,4,5,2,8):",largest(3,4,5,2,8))

# average of numbers
def average(*n):
    s=0
    for i in n:
        s+=i
    return s/len(n)
print(average(2,3,1,5,6))

# count no. of args passed
def countArgs(*n):
    return len(n)
print("Number of arguments passed:",countArgs(2,3,4,45,5,2))
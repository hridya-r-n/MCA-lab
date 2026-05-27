# multiply
def multiply(a,b):
    return(a*b)
print(multiply(2,4))

# student details
def student(name,mark):
    print("Name:",name)
    print("Mark:",mark)
print("Student Details")
student('Gouri',88)

# area of rectangle
def area(l,b):
    return l*b
print("Area(length=5,breadth=7):",area(5,7))

# even or odd
def evenOdd(n):
    if n%2:
        return "Odd"
    else:
        return "Even"
print("5 is",evenOdd(5))
print("2 is",evenOdd(2))

# greeting
def greet(name):
    print("Welcome",name)
greet('Hridya')
# country
def country(name="India"):
    print("Country:",name)
country()

# power
def power(a,b=2):
    return a**b
print("3^2=",power(3))
print("3^4=",power(3,4))\

# welcome
def welcome(name="Guest"):
    print("Welcome",name)
welcome('Hridya')
welcome()

# salary
def salary(amount=10000):
    print("Salary:",amount)
salary(20000)
salary()

# student
def student(course="MCA"):
    print("Course:",course)
student('BCA')
student()
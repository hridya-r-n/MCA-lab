# Create a class Person with private attributes name and age. Compare 2 Persons by their age.
class Person:
    def __init__(self):
        self.__name=input('Enter Name:')
        self.__age=int(input('Enter age:'))
    def getName(self):
        return f'{self.__name}'
    def display(self) :
        print(f'Name: {self.__name} \nAge: {self.__age}')
    def __eq__(self,other):
        return self.__age==other.__age
    def __lt__(self,other):
        return self.__age<other.__age
print('Enter details of first person')
p1=Person()
print('Enter details of second person')
p2=Person()
p1.display()
p2.display()
if p1==p2:print('Both persons are of same age')
elif p1<p2:print(f'{p1.getName()} is younger')
else:print(f'{p2.getName()} is younger')
class Student:
    def __init__(self,rollno,name):
        self.__rollno=rollno
        self.name=name
    def getRoll(self):
        return self.__rollno
s=Student(2,'Hridya')
print('RollNo:',s.getRoll())
print('Name:',s.name)
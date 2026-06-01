class Student:
    def __init__(self,rollno,name):
        self.rollno=rollno
        self.name=name
    def display(self):
        print(f'Roll No:{self.rollno}\nName:{self.name}')
s1=Student(1,"Hridya")
s1.display()
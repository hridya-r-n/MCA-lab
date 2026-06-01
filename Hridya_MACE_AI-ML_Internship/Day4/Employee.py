class Employee:
    def __init__(self,empid,name):
        self.empid=empid
        self.name=name
    def display(self):
        print(f'Emp ID:{self.empid}  Name:{self.name}')
e1=Employee(101,'Gouri')
e1.display()
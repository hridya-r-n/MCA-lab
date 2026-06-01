from abc import ABC,abstractmethod
class Shape:
    @abstractmethod
    def area(self):
        pass
class Square(Shape):
    def __init__(self,s):
        self.side=s
    def area(self):
        return self.side**2
s=Square(4)
print('Area(4):',s.area())
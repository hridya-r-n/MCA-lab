# Create a class Rectangle with private attributes length and width.
# Overload '«' operator to compare the area of 2 rectangles.

class Rectangle:
    def __init__(self):
        self.__length=float(input('Enter length:'))
        self.__width=float(input('Enter width:'))
    def getlength(self):
        return self.__length
    def getwidth(self):
        return self.__width
    def area(self):
        self.ar=self.__length*self.__width
    def display(self):
        print('Length:',self.__length)
        print('width:',self.__width)
        if hasattr(self,'ar'):print('Area:',self.ar)
    def __lt__(self, other):
        return self.ar<other.ar
print('Rectangle 1:')
r1=Rectangle()
r1.display()
print('Rectangle 2:')
r2=Rectangle()
print("Length:",r2.getlength())
print("Width:",r2.getwidth())
r1.area()
print('Rectangle 1:')
r1.display()
r2.area()
print('Rectangle 2:')
r2.display()
if r1<r2:print('Rectangle 1 has lesser area than Rectangle 2')
else:print('Rectangle 2 has lesser area than Rctangle 1')
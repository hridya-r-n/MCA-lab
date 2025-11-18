# Create Rectangle class with attributes length and breadth and methods to find area and perimeter. Compare two Rectangle objects by their area.
class Rectangle:
    def __init__(self):
        self.length=float(input('Enter length:'))
        self.breadth=float(input('Enter breadth:'))
    def area(self):
        self.ar=self.length*self.breadth
        return self.ar
    def perimeter(self):
        self.per=2*(self.length+self.breadth)
        return self.per
    def display(self):
        print('Length:',self.length)
        print('Breadth:',self.breadth)
        if hasattr(self,'ar'):print('Area:',self.ar)
        if hasattr(self,'per'):print('Perimeter:',self.per)
    def __eq__(self, other):
        return self.ar==other.ar
print('Rectangle 1:')
r1=Rectangle()
r1.display()
print('Rectangle 2:')
r2=Rectangle()
r2.display()
r1.area()
r1.perimeter()
print('Rectangle 1:')
r1.display()
r2.area()
print('Rectangle 2:')
r2.display()
if(r1==r2):print('Rectangles have same area')
else:print('Rectangles have different area')
from graphics import rectangle
length=float(input('Enter length of the rectangle:'))
breadth=float(input('Enter breadth of the rectangle:'))
print('Area:',rectangle.area(length,breadth))
print('Perimeter:',rectangle.perimeter(length,breadth))

from graphics.circle import area as a,perimeter as p
rad=float(input('Enter radius of the circle:0'))
print('Area:',a(rad))
print('Perimeter:',p(rad))

from graphics.graphics3D import*
length=float(input('Enter length of the cuboid:'))
breadth=float(input('Enter breadth of the cuboid:'))
height=float(input('Enter height of the cuboid:'))
print('Area:',cuboid.area(length,breadth,height))
print('Perimeter:',cuboid.perimeter(length,breadth,height))

rad=float(input('Enter radius of the sphere:'))
print('Area:',sphere.area(rad))
print('Perimeter:',sphere.perimeter(rad))

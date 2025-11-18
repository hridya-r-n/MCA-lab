# 	Write lambda functions, each to find area of square, rectangle and triangle.
s=int(input('Enter side of the square:'))
sq=s*s
print('Area:',sq)

l=int(input('Enter legth of the rectangle:'))
b=int(input('Enter breadth of the rectangle:'))
rectArea=l*b
print('Area:',rectArea)

b=int(input('Enter base of the triangle:'))
h=int(input('Enter height of the triangle:'))
triArea=(1/2)*b*h
print('Area:',triArea)
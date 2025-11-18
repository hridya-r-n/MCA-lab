# Create a class Time with private attributes hour, minute and second
# Overload *+' operator to find sum of 2 time
class Time:
    def __init__(self,hr=0,min=0,sec=0):
        self.__hr=hr
        self.__min=min
        self.__sec=sec
    def display(self):
        print(f'{self.__hr}:{self.__min}:{self.__sec}')
    def __add__(self,other):
        h=(self.__hr+other.__hr)%12
        m=(self.__min+other.__min)%60
        s=(self.__sec+other.__sec)%60
        return Time(h,m,s)
t1=Time(12,56,35)
t2=Time(3,34,44)
print('Time 1:')
t1.display()
print('Time 2:')
t2.display()
t3=t1+t2
print("Result:")
t3.display()
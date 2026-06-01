class Dog():
    def displayD(self):
        print('Display Dog')
class Cat(Dog):
    def displayC(self):
        print('Display Cat')
c=Cat()
c.displayD()
c.displayC()
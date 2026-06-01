class Father:
    def displayF(self):
        print('Father')
class Mother:
    def displayM(self):
        print('Mother')
class Child(Father,Mother):
    def display(self):
        print('Child')
c=Child()
c.displayM()
c.displayF()
c.display()
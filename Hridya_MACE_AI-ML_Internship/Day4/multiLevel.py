class A:
    def displayA(self):
        print('display A')
class B(A):
    def displayB(self):
        print('display B')
class C(B):
    def displayC(self):
        print('display C')
c=C()
c.displayA()
c.displayB()
c.displayC()
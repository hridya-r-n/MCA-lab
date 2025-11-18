class FruitBasket:
    def __init__(self,fruit_name,price_per_kg):
        self.fruit_name=fruit_name
        self.price_per_kg=price_per_kg
    def display(self):
        print(f'Fruit:{self.fruit_name}\nPrice(per kg):{self.price_per_kg}')
    def __add__(self,other):
        if self.fruit_name==other.fruit_name:
            newprice=self.price_per_kg if self.price_per_kg<other.price_per_kg else other.price_per_kg
            return FruitBasket(self.fruit_name,newprice)
        else:
            newprice=self.price_per_kg+other.price_per_kg
            return FruitBasket(f'{self.fruit_name} & {other.fruit_name}',newprice)
name1=input('Enter first fruit:')
price1=float(input('Enter price per kg:'))
name2=input('Enter second fruit:')
price2=float(input('Enter price per kg:'))
f1=FruitBasket(name1,price1)
f1.display()
f2=FruitBasket(name2,price2)
f2.display()
f3=f1+f2
f3.display()
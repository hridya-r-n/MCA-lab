num1=int(input("Enter number 1:"))
num2=int(input("Enter number 2:"))
op=input("Enter operator:")
if op=='+':
    print(num1,"+",num2,"=",(num1+num2))
elif op=='-':
    print(num1,"-",num2,"=",(num1-num2))
elif op=='*':
    print(num1,"x",num2,"=",(num1*num2))
elif op=='/':
    print(num1,"/",num2,"=",(num1/num2))
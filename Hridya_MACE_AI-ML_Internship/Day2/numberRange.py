n1=int(input("Enter lower limit:"))
n2=int(input("Enter upper limit:"))
num=int(input("Enter a number:"))
if num in range(n1,n2+1):
    print(num,"is within (",n1,",",n2,")")
else:
    print(num,"is not within (",n1,",",n2,")")
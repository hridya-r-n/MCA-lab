#  Write a program that counts odd and even numbers in a given list.
def odd_even_count(list):
    odd=even=0
    for l in list:
        if l%2:
            odd+=1
        else:even+=1
    return odd,even
list=map(int,input('Enter a list of numbers:').split(','))
count=odd_even_count(list)
print('Count of odd numbers:',count[0])
print('Count of even numbers:',count[1])
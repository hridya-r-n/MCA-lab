# Write a program to print all even numbers from a given list in the given order until you reach number 237 or end of the list. 
def even_list(list):
    for l in list:
        if(l==237):break
        elif not (l%2):
            print(l)
n=input('Enter a collection pf integers:')
n_list=list(map(int,n.split(',')))
even_list(n_list)
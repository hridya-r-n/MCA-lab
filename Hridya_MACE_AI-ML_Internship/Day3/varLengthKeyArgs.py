# display student details
def student(**details):
    for k,v in details.items():
        print(k,":",v)
student(name='Hridya',course='MCA',mark=80)
student(name='Gouri',cource='MCA')

# display employee information
def employee(**details):
    for k,v in details.items():
        print(k,":",v)
employee(name='Hridya',salary=10000,dept='Sales')
employee(name='Gouri',dept='IT')


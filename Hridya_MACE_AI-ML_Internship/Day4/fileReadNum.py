try:
    filename=input('Enter file name:').strip()
    with open(filename,'r') as f:
        print(f.read())
except FileNotFoundError as e:
    print('File does not exist',e)
num=54
guess=int(input("Guess a number:"))
t=0
while t<3:
    if guess==num:
        print("Guess correct!")
        break
    else:
        print("Wrong guess!")
        if t<3:
            guess=int(input("Guess a number:"))
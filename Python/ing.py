s=input("Enetr a string:").lower()
if s.endswith('ing'):
    s=s+'ly'
else:s=s+'ing'
print("Modified string:",s)
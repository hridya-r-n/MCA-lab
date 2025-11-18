import re
phone=open('phoneno.txt','r')
for p in phone.readlines():
    if re.match(r'^6|7|8|9\d{10}',p):print(p)
if(phone):phone.close()
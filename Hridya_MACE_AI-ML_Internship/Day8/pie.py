import matplotlib.pyplot as plt
data=[20,30,40]
labels=['Pyhton','AI','Django']
plt.pie(data,labels=labels,autopct='%1.1f%%')
plt.show()
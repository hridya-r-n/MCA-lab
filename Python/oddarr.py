# Extract all odd numbers from an array
import numpy as np
a=np.array([2,6,25,9,4,43,8,7])
print('Original Array:',a)
odd=a%2!=0
# oddarr=np.array([odd])
print('Odd Number Array',a[odd])
import numpy as np

# a1=np.array([10,20,30,40])
# print(a1)

# # zeros
# a1=np.zeros((3,4))
# print(a1)

# # ones
# a1=np.ones((3,3))
# print(a1)

# # identity
# a1=np.eye(3,4)
# print(a1)

# # arange
# a1=np.arange(0,10)
# print(a1)
# a2=np.arange(0,10,4)
# print(a2)

# # linspace
# a1=np.linspace(0,10,5)
# print(a1)
# a2=np.linspace(0,10,4)
# print(a2)

# # reshape
# a1=np.arange(12)
# print(a1)
# a1=a1.reshape(3,4)
# print(a1)

# # indexing
# print(a1[1,1])
# print(a1[1])
# print(a1[-1])

# # slicing
# print(a1[::2])

# math operations
a1=np.array([1,2,3])
a2=np.array([4,5,6])
print(a1+a2)
print(a1-a2)
print(a1*a2)
print(a2/a1)

# statistical functions
arr=np.array([10,20,30,40,50])
print(arr)
print('Sum=',np.sum(arr))
print('Mean=',np.mean(arr))
print('Min=',np.min(arr))
print('Max=',np.max(arr))
print('Median=',np.median(arr))
print('S.D=',np.std(arr))
print('Variance=',np.var(arr))

# multiplication
a=np.array([[1,2],[3,4]])
b=np.array([[4,5],[6,7]])
print(a+b)
print('Matrix multiplication:',np.dot(a,b))
print(a@b)
# transpose
print('original:',a,np.shape(a))
print('transpose:',a.T,a.T.shape)

# determinant
print('determinant:',np.linalg.det(a))

# inverse
a_inv=np.linalg.inv(a)
print('inverse:',a_inv)

# eigenvalues and eigenvectors
eigenvalues,eigenvectors=np.linalg.eig(a)
print('Enugenvalues:',eigenvalues)
print('Eigenvectors:',eigenvectors)

# flatten
print(a.flatten())

# broadcasting
print(a+4)

# sorting
a=np.array([6,4,7,3,2])
print(np.sort(a))
print(np.argsort(a))

# filtering
print(a[a>3])
print(a[a%2==0])

# random values
np.random.seed(15)
arr=np.random.rand(4)
print(arr)
mat=np.random.rand(3,3)
print(mat)
mat=np.random.randint(1,50,5)
print(mat)
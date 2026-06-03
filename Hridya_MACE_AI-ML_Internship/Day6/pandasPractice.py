import pandas as pd

# series
data=[1,2,3,4]
s=pd.Series(data)
print(s)

# dataframe
data={
    "Name":["John","David","Tom"],
    "Age":[20,19,21]
}
df=pd.DataFrame(data)
print(df)

# csv
df=pd.read_csv('Day6/student.csv')
print(df)
print(df.head(3))
print(df.tail())

print('Info:',df.info())
print('Statistical info:',df.describe())

print('Column names:',df.columns)
print('Single column:')
print(df["Name"])
print(df[["Name","Age"]])
print(df.loc[1])
print(df.iloc[1])

# add and delete column
df["Salary"]=[10000,20000,15000,10000,20000,25000]
print(df)
df.drop("Salary",axis=1,inplace=True)
print(df)

# filtering,sorting
print(df[df["Age"]>20])
df.sort_values('Age',ascending=False,inplace=True)
print(df)

# missing values
df={
    "Name":["John","Tom","David"],
    "Age":[21,None,20]
}
df=pd.DataFrame(df)
print(df)
print(df.isnull())
df.fillna(0,inplace=True)
print(df)

# group,merge
data={
    "Name":["John","Anu","Tom"],
    "Dept":["IT","Sales","Sales"],
    "Salary":[14000,20000,15000]
}
df=pd.DataFrame(data)
print(df)
print(df.groupby("Dept").mean("Salary"))
df1=pd.DataFrame({
    "ID":[1,2,3],
    "Name":["John","Anu","Tom"]
})
df2=pd.DataFrame({
    "ID":[1,2,3],
    "Salary":[12000,14000,15000]
})
df=pd.merge(df1,df2,on="ID")
print(df)

# pivot
df=pd.DataFrame({
    "Dept":["IT","HR","HR","IT"],
    "Month":["Jan","Feb","Jan","Feb"],
    "Salary":[14000,15000,1000,14000]
})
table=pd.pivot_table(df,values="Salary",index="Dept",columns="Month")
print(table)

# date
date={
"Date":['2025-03-12','2025-04-23']
}
date=pd.DataFrame(date)
date["Date"]=pd.to_datetime(date["Date"])
print(date)
date["Year"]=date["Date"].dt.year
date["Month"]=date["Date"].dt.month
date["Day"]=date["Date"].dt.day
print(date)

# export to csv
df.to_csv('output.csv',index=False)
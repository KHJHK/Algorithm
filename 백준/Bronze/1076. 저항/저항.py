color = ['black', 'brown', 'red',
'orange', 'yellow', 'green', 'blue', 'violet', 'grey', 'white']
a = color.index(input())
s = color.index(input())
d = color.index(input())
f = int(str(a) + str(s)) * (10 ** d)
print(f)
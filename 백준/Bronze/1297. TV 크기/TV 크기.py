d, h, v = map(int, input().split())

n = d / ((h ** 2 + v ** 2) ** 0.5)
h = int(h*n)
v = int(v*n)
print(h, v)
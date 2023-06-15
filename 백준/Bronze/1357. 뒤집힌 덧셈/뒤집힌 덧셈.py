a, b = input().split()

a = a[::-1]
b = b[::-1]

c = int(a) + int(b)
print(int(str(c)[::-1]))
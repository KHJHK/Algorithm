import math

x, y = map(int, input().split())
z = int(100 * y / x)
if z >= 99:
    print(-1)
else:
    a = (x * (z + 1) - 100 * y) / (99 - z)
    if math.floor(a) < a:
        a += 1
    print(int(a))
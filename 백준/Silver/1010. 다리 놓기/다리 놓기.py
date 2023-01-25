import math

n = int(input())
for i in range(n):
    a, b = map(int, input().split())
    print(int(math.factorial(b) / (math.factorial(a) * math.factorial(b-a))))
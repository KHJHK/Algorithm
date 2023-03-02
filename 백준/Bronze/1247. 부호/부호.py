import sys

for _ in range(3):
    n = int(sys.stdin.readline())
    num = 0
    for i in range(n):
        num += int(sys.stdin.readline())
    if num > 0:
        print("+")
    elif num < 0:
        print("-")
    else:
        print(0)
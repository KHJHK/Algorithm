import sys
n = int(sys.stdin.readline())
count = 0

while 1:
    if n%5 == 0:
        count += n/5
        break
    count += 1
    n -= 3
    if n < 0:
        count = -1
        break
print(int(count))
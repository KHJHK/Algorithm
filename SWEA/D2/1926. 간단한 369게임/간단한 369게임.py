n = int(input())
list = [i for i in range(n + 1)]

for i in range(1, n + 1):
    clap = 0
    for j in str(list[i]):
        if j == '3' or j == '6' or j == '9':
            clap += 1
    if clap != 0:
        list[i] = '-' * clap
list.pop(0)
print(*list)
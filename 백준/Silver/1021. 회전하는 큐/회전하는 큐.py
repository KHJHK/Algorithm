answer = 0
n, m = map(int, input().split())
num = list(map(int, input().split()))

queue = [i for i in range(1, n+1)]

while len(queue) != n - m:
    if queue[0] == num[0]:
        queue.pop(0)
        num.pop(0)
    else:
        idx = queue.index(num[0])
        if idx <= len(queue) - idx:
            answer += idx
            temp = queue[:idx]
            queue = queue[idx:] + temp
        else:
            answer += len(queue) - idx
            temp = queue[idx:]
            queue = temp + queue[:idx]
print(answer)
def fib(n):
    zero = [1, 0, 1]
    one = [0, 1, 1]
    for i in range(2, n):
        zero.append(zero[i] + zero[i - 1])
        one.append(one[i] + one[i - 1])
    print(f"{zero[n]} {one[n]}")

T = int(input())
for i in range(T):
    n = int(input())
    fib(n)
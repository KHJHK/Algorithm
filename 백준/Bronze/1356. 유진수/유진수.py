n = input()
a = -1
b = 0
for i in range(len(n) - 1):
    a = 1
    b = 1
    for j in range(i + 1):
        a *= int(n[j])
    for k in range(i + 1, len(n)):
        b *= int(n[k])
    if a == b:
        break
if a == b:
    print("YES")
else:
    print("NO")
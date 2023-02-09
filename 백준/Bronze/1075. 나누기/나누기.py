n = input()
f = int(input())
answer = '99'

n = n[0:-2]
n += '00'

for i in range(int(n), int(n)+100):
    if i % f == 0 and int(str(i)[-2:]) < int(answer):
        answer = str(i)[-2:]
        if answer == '00':
            break

print(answer)
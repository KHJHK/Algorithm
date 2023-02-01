n = int(input())
first_name = []
answer = ''
for i in range(n):
    first_name.append(input()[0])
name_set = set(first_name)
for i in sorted(list(name_set)):
    if first_name.count(i) >= 5:
        answer += i
if answer == '':
    print("PREDAJA")
else:
    print(answer)
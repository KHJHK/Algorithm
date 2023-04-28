#4:40

n = int(input())
list = []
len_list = []
for _ in range(n):
    a = input()
    list.append(a)
    len_list.append(len(a))

for idx1 in range(len(list) - 1):
    for idx2 in range(idx1 + 1, len(list)):
        if len_list[idx1] > len_list[idx2]:
            temp = len_list[idx2]
            len_list[idx2] = len_list[idx1]
            len_list[idx1] = temp

            temp = list[idx2]
            list[idx2] = list[idx1]
            list[idx1] = temp

answer = len(list)
for idx1 in range(len(list) - 1):
    for idx2 in range(idx1 + 1, len(list)):
        if list[idx1] == list[idx2][:len(list[idx1])]:
            answer -= 1
            break
print(answer)
n = int(input())
strList = []
temp = []
answer = []

for _ in range(n):
    strList.append(input())
strList = list(set(strList))
strList.sort(key=len)

while strList:
    s = strList.pop(0)
    if temp and len(temp[0]) < len(s):
        temp.sort()
        answer += temp
        temp.clear()
    temp.append(s)
temp.sort()
answer += temp

for a in answer:
    print(a)
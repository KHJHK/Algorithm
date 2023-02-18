aeiou = ['a','e','i','o','u','A','E','I','O','U']
while 1:
    cnt = 0
    s = input()
    if s == "#":
        break
    
    for i in s:
        for j in aeiou:
            if i == j:
                cnt += 1
    print(cnt)
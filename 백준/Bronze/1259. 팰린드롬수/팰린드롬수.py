while 1:
    n = input()
    if n == '0':
        break
    n2 = n[::-1]
    if n == n2:
        print('yes')
    else:
        print('no')
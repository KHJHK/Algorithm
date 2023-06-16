tc = 1
while 1:
    o, w = map(int, input().split())
    if o == 0 and w == 0:
        break
    case = num = -1

    while 1:
        case, num = input().split()
        num = int(num)
        if case == '#' and num == 0:
            if w <= 0:
                print(tc, 'RIP')
            elif o / 2 < w < o * 2:
                print(tc, ':-)')
            else:
                print(tc, ':-(')
            tc += 1
            break

        if w > 0:
            if case == 'E':
                w -= num
            if case == 'F':
                w += num
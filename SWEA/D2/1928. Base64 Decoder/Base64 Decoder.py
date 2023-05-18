T = int(input())
for case in range(1, T + 1):
    words = list(input())
    bit_words = ''
    answer = ''

    for i in range(len(words)):
        a = ord(words[i])
        if 65 <= a <= 90:
            a -= 65
        elif 97 <= a <= 122:
            a -= 71
        elif 48 <= a <= 57:
            a += 4
        elif a == 43:
            a = 62
        elif a == 47:
            a = 63
        b = bin(a)[2:]
        while len(b) < 6:
            b = '0' + b
        bit_words += b

    while bit_words:
        decoding = bit_words[:8]
        bit_words = bit_words[8:]
        answer += chr(int(decoding, 2))
    print(f'#{case} {answer}')
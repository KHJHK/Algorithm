n = int(input())
pattern = []
for i in range(n):
    new_pattern = list(input())
    if not pattern:
        pattern = new_pattern
        continue
    for j in range(len(pattern)):
        if pattern[j] != new_pattern[j]:
            pattern[j] = '?'
print(*pattern, sep='')
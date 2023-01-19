students = [0 for i in range(0, 30)]

for i in range(28):
    num = int(input()) - 1
    students[num] = 1

print(students.index(0)+1)
students[students.index(0)] = 2
print(students.index(0)+1)